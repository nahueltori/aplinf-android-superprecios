package com.aplinf.superprecios.ui;

import java.text.DecimalFormat;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aplinf.superprecios.core.Estrategia;
import com.aplinf.superprecios.core.EstrategiaMinMax;
import com.aplinf.superprecios.core.EstrategiaPromedio;
import com.aplinf.superprecios.core.Precio;
import com.aplinf.superprecios.core.Producto;
import com.aplinf.superprecios.core.SistemaPrecios;
import com.aplinf.ui.superprecios.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends Activity implements OnClickListener {

	private static final int RESULT_SETTINGS = 1;
	
	private Button scanBtn, compararBtn;
	private EditText codigo,precio;
	private TextView resultadoTxt,productoTxt;
	
	private SistemaPrecios sistemaPrecios;
	private Estrategia estrategiaComparacion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		scanBtn = (Button)findViewById(R.id.scan_button);
		scanBtn.setOnClickListener(this);
		compararBtn = (Button)findViewById(R.id.action_button);
		compararBtn.setOnClickListener(this);
		codigo = (EditText)findViewById(R.id.editCodigo);
		precio = (EditText)findViewById(R.id.editPrecio);
		resultadoTxt = (TextView)findViewById(R.id.textResultado);
		productoTxt = (TextView)findViewById(R.id.textProducto);
		
		sistemaPrecios = new SistemaPrecios(this);
		estrategiaComparacion = new EstrategiaPromedio();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
 
        case R.id.menu_settings:
            Intent i = new Intent(this, Settings.class);
            startActivityForResult(i, RESULT_SETTINGS);
            break;
 
        }
 
        return true;
    }
    
    /**
	 * Acceso a la interfaz de escaneo.
	 */
	public void onClick(View v){
		if(v.getId()==R.id.scan_button){
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();
		}
		if(v.getId()==R.id.action_button){
			//Traigo datos de pantalla
			long prodCodigo = Long.valueOf(codigo.getText().toString());
			String precioStr = precio.getText().toString();
			float prodPrecio = Float.valueOf(precioStr.replace(",", "."));
			
			//Proceso informacion
			Producto producto = new Producto(prodCodigo);
			sistemaPrecios.agregarPrecio(new Precio(prodPrecio, producto));
			estrategiaComparacion.setProducto(producto);
			List<Precio> resultado = sistemaPrecios.compararPrecio(estrategiaComparacion);
			
			//Limpio la pantalla
			limpiarPantalla();

			//Muestro en panalla
			mostrarDatos(resultado);
		}
	}
	
	private void limpiarPantalla(){
		codigo.setText("");
		precio.setText("");
		resultadoTxt.setText("");
		productoTxt.setText("");
	}

	private void mostrarDatos(List<Precio> resultado){
		productoTxt.setText(resultado.get(0).getProducto().getDescripcion());
		StringBuilder builder = new StringBuilder();
		for(Precio resul : resultado){
			builder.append("$" + new DecimalFormat("#.##").format(resul.getImporte()) + " - ");
			builder.append(resul.getDescripcion() + "\n");
		}
		resultadoTxt.setText(builder.toString());
	}

	/**
	 * Resultado del escaneo. 
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		
		//Resultado de cambios en las preferencias de usuario
		switch (requestCode) {
        case RESULT_SETTINGS:
            setEstrategiaComparacion();
            break;
        }

		//Resultado del escaneo de código
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (escaneoCorrecto(scanningResult)) {
			String scanContent = scanningResult.getContents();
			codigo.setText(scanContent);
			Producto producto = new Producto(Long.valueOf(scanContent));
			sistemaPrecios.buscarProducto(producto);
			productoTxt.setText(producto.getDescripcion());
		}
	}
	
	private boolean escaneoCorrecto(IntentResult resultadoEscaneo){
		String mensaje;
		if (resultadoEscaneo != null) {
			String scanFormat = resultadoEscaneo.getFormatName();
			if (scanFormat.contentEquals("EAN_13")){
				return true;
			}else{
				mensaje = "Formato de código incorrecto";
			    Toast toast = Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT);
		        toast.show();
			}
		}
		return false;
	}

	private void setEstrategiaComparacion(){
		SharedPreferences sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);
		int estrategiaPreferencia = Integer.valueOf(sharedPrefs.getString("prefComparacion", 
				Integer.toString(EstrategiaPromedio.ESTRAT_PROMEDIO)));
		
		switch(estrategiaPreferencia){
		case EstrategiaPromedio.ESTRAT_PROMEDIO:
			estrategiaComparacion = new EstrategiaPromedio();
			break;
		case EstrategiaMinMax.ESTRAT_MINMAX:
			estrategiaComparacion = new EstrategiaMinMax();
			break;
		}
	}
	
}
