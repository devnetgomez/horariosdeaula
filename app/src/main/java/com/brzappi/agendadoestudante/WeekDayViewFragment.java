package com.brzappi.agendadoestudante;

import com.brzappi.agendadoestudante.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WeekDayViewFragment  extends Fragment {

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        ViewGroup rootView = (ViewGroup) inflater.inflate(
	                R.layout.activity_dia_da_semana, container, false);

	        return rootView;
	    }
	}


