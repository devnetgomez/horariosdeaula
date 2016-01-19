package com.brzappi.agendadoestudante;

import com.brzappi.agendadoestudante.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DiaDaSemana extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dia_da_semana);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_dia_da_semana, menu);
		return true;
	}

}
