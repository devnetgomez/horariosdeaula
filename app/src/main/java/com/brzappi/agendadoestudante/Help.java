package com.brzappi.agendadoestudante;

import java.util.ArrayList;




import com.brzappi.agendadoestudante.R;
import com.brzappi.agendadoestudante.modelo.help_question;

import android.os.Bundle;
import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.widget.ExpandableListView;

public class Help extends CustomActivity {

		
	  
	    @Override
	    public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_help);

	        final ExpandableListView HelpList;
	        
	        HelpList = (ExpandableListView)findViewById(R.id.expandableList);
	 
	        ArrayList<help_question> QuestionList = new ArrayList<help_question>();
	        ArrayList<String> AnswerList1 = new ArrayList<String>();
	        ArrayList<String> AnswerList2 = new ArrayList<String>();
	 
	            help_question Question1 = new help_question();
	            Question1.setTitle(getResources().getString(R.string.help_question_howtoaddtimeclass));
	            AnswerList1.add(getResources().getString(R.string.help_answer_howtoaddtimeclass));
	            Question1.setAnswerList(AnswerList1);
	            QuestionList.add(Question1);
	           
	            help_question Question2 = new help_question();
	            Question2.setTitle(getResources().getString(R.string.help_question_howtodeleteatimeclass));
	            AnswerList2.add(getResources().getString(R.string.help_answer_howtodeleteatimeclass));
	            Question2.setAnswerList(AnswerList2);
	            QuestionList.add(Question2);
	           
	 
	        //sets the adapter that provides data to the list.
	       HelpList.setAdapter(new HelpAdapter(Help.this,QuestionList)); 
	   
	    }
}