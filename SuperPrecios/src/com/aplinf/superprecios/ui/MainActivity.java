package com.aplinf.superprecios.ui;

import java.text.DecimalFormat;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aplinf.superprecios.core.EstrategiaPromedio;
import com.aplinf.superprecios.core.Precio;
import com.aplinf.superprecios.core.Producto;
import com.aplinf.superprecios.core.SistemaPrecios;
import com.aplinf.ui.superprecios.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends Activity implements OnClickListener {

	private Button scanBtn, compararBtn;
	private EditText codigo,precio;
	private TextView resultadoTxt,productoTxt;
	
	private SistemaPrecios sistemaPrecios;
	
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
			List<Precio> resultado = sistemaPrecios.compararPrecio(new EstrategiaPromedio(producto));
			
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
		Precio promedio = resultado.get(0);
		productoTxt.setText(promedio.getProducto().getDescripcion());
		String promedioStr = new DecimalFormat("#.##").format(promedio.getImporte());
		resultadoTxt.setText("$" + promedioStr + " - ");
		resultadoTxt.append(promedio.getDescripcion());
	}

	/**
	 * Resultado del escaneo. 
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (escaneoCorrecto(scanningResult)) {
			String scanContent = scanningResult.getContents();
			codigo.setText(scanContent);
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
			}
		}else{
		    mensaje = "No se recibió información del escaneo";
		}
	    Toast toast = Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT);
        toast.show();
		return false;
	}

}
