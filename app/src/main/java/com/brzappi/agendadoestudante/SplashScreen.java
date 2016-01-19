package com.brzappi.agendadoestudante;

import com.brzappi.agendadoestudante.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;

public class SplashScreen extends Activity {

	protected boolean ativo = true;
	protected int tempoduracaosplash = 1000;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		Thread splashTread = new Thread()
		{
			@Override
			public void run() 
			{
			try {
					int waited = 0;
					while (ativo && (waited < tempoduracaosplash)) 
					{
						sleep(100);
						if (ativo) 
						{
							waited += 100;
						}
					}
				} catch (InterruptedException e)
				{			 
				} 
				finally 
				{
					startActivity(new Intent(SplashScreen.this, Principal.class));
					finish();
				}
			}
		};
		
		splashTread.start();	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_splash_screen, menu);
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	if (event.getAction() == MotionEvent.ACTION_DOWN) {
	ativo = false;
	}
return true;
}

}
