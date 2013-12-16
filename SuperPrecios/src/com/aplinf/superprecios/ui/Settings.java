package com.aplinf.superprecios.ui;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.aplinf.superprecios.core.EstrategiaPromedio;
import com.aplinf.ui.superprecios.R;

public class Settings extends PreferenceActivity {

	@SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        addPreferencesFromResource(R.xml.settings);
 
    }

	protected Object onGetDefaultValue(TypedArray a, int index) {
	    return a.getInteger(index, EstrategiaPromedio.ESTRAT_PROMEDIO);
	}
	
}
