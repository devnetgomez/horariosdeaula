package com.brzappi.agendadoestudante.dados;



import com.brzappi.agendadoestudante.R;

import android.database.Cursor;
import android.view.View;
import android.widget.TextView;

 class StorageTimeClass 
{
	
	 TextView textProfessor = null;
	 TextView textDisciplina = null;
	 TextView textSala = null;
	 TextView textNumHorario = null;
	 TextView textTimStartEnd = null;
	
	 
	 StorageTimeClass(View linha)
	 {
		textProfessor = (TextView) linha.findViewById(R.id.textProfessor);
		textDisciplina = (TextView) linha.findViewById(R.id.textDisciplina);
		textSala = (TextView) linha.findViewById(R.id.textSala);
		textTimStartEnd = (TextView) linha.findViewById(R.id.textTimStartEnd);	
	}

	 
	void FillInterface(Cursor c, TimeClassManager manager)
	{
		textProfessor.setText(c.getString(1));
		textDisciplina.setText(c.getString(2));
		textSala.setText(c.getString(3));
		textTimStartEnd.setText(c.getString(4));	
	}
}