package com.brzappi.agendadoestudante;

import java.util.ArrayList;
import java.util.List;

import com.brzappi.agendadoestudante.R;
import com.brzappi.agendadoestudante.dados.TimeClassManager;
import com.brzappi.agendadoestudante.modelo.class_time;
import com.brzappi.agendadoestudante.modelo.h_class;
import com.brzappi.agendadoestudante.modelo.h_location;
import com.brzappi.agendadoestudante.modelo.h_teacher;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class CustomActivity extends Activity  {
	
	public List<h_teacher> teacherList;
	public List<h_class> classList;
	public List<h_location> locationList;
	
	public int WeekDay = 0;
	public String StartTime;
	public String EndTime;
	public int TeacherId =0;
	public int ClassId=0;
	public int LocationId=0;
	public int TimeNumber=0;
	
	
	public TeachersListAdapter adapterTeacherClassLocationListView;
	public TimeClassAdapterList adapterTimeClassListView;

	public ListView timeListView;
	public ListView teacherListView;
	public ListView classListView;
	public ListView locationListView;
	

	public ArrayList<class_time> itens;
	
	
	public TimeClassManager dbConnection;
	
	 public List<String> GetTeacherList()
	   {
		 TimeClassManager banco = new TimeClassManager(getApplicationContext());

		this.teacherList = banco.getAllTeachers(); 
	     
		   List<String> lista = new ArrayList<String>();;
		  
		   lista.add(getResources().getString(R.string.label_spinner_teacher));
		   
		   for(int i = 0; i < teacherList.size(); i++)
		   {
			   
			   lista.add(teacherList.get(i).getName());
			   
		   }
		   
		   return lista;
		   
		   
	   }
	 
	 public List<String> GetClassList()
	   {
		 TimeClassManager banco = new TimeClassManager(getApplicationContext());

		this.classList = banco.getAllClasses();
	     
		   List<String> lista = new ArrayList<String>();;
		  
		   lista.add(getResources().getString(R.string.label_spinner_class));
		   for(int i = 0; i < classList.size(); i++)
		   {
			   lista.add(classList.get(i).getName());
			   
		   }
		   
		   return lista;
		   
		   
	   }
	 
	 public List<String> GetLocationList()
	   {
		 TimeClassManager banco = new TimeClassManager(getApplicationContext());

		 this.locationList = banco.getAllLocations(); 
	     
		   List<String> lista = new ArrayList<String>();;
		  
		   lista.add(getResources().getString(R.string.label_spinner_location));
		   
		   for(int i = 0; i < locationList.size(); i++)
		   {
			   lista.add(locationList.get(i).getName());
			   
		   }
		   
		   return lista;
		   
		   
	   }
	 
	 
	 public void createTimeClassListView(Context contexto, int _WeekDay) {
	     
		 
		 dbConnection = new TimeClassManager(getApplicationContext());
		 
		 try
		 {
		 // Buscar todos os horários salvos
		 itens = (ArrayList<class_time>) dbConnection.getAllTimeClasses();
	        
	        if(itens.size() == 0)
	        {
	        	//Exibe dica de como inserir o primeiro horário
			        class_time itemList = new class_time(0,0,"07:00" ,"08:00",0,WeekDay,1);
			        itemList.setClassName("Você ainda não possui horários cadastrados");
			        itemList.setTeacherName("Clique aqui para lançar um horário");
			        itens.add(itemList);		      
		        		        
	        }
	        else
	        {
	        	//Exibe dica de como inserir o primeiro horário
		        class_time itemList = new class_time(0,0,"00:00" ,"00:00",0,WeekDay,0);
		        itemList.setClassName("Continue cadastrando seus horários de aula");
		        itemList.setTeacherName("Clique aqui para lançar mais um horário");
		        itens.add(itemList);		      
		                	
	        	
	        }
	        
	        //Cria o adapter
	        adapterTimeClassListView = new TimeClassAdapterList(itens, contexto);

	        //Define o Adapter
	        timeListView.setAdapter(adapterTimeClassListView);
	        
	        //Cor quando a lista é selecionada para ralagem.
	        timeListView.setCacheColorHint(Color.TRANSPARENT);
		 }
		 
		 catch(SQLException sqle)
	    	{
	    		Toast.makeText(CustomActivity.this, sqle.getMessage(),
	 				Toast.LENGTH_LONG).show();
	    	}   
	    }
	 
	 public void createTeacherListView(ListView listview) {
		 
		 	dbConnection = new TimeClassManager(getApplicationContext());
	        //Criamos nossa lista que preenchera o ListView
	        teacherList = dbConnection.getAllTeachers(); 
	       
	        //Cria o adapter
	        adapterTeacherClassLocationListView = new TeachersListAdapter(teacherList,null,null,getApplicationContext());

	        //Define o Adapter
	        listview.setAdapter(adapterTeacherClassLocationListView);
	        
	        //Cor quando a lista é selecionada para ralagem.
	        listview.setCacheColorHint(Color.TRANSPARENT);
	    }

	 public void createLocationListView(ListView listview) {
		 
		 	dbConnection = new TimeClassManager(getApplicationContext());
	        //Criamos nossa lista que preenchera o ListView
	        locationList = dbConnection.getAllLocations(); 
	       
	        //Cria o adapter
	        adapterTeacherClassLocationListView = new TeachersListAdapter(null,null,locationList,this);

	        //Define o Adapter
	        listview.setAdapter(adapterTeacherClassLocationListView);
	        
	        //Cor quando a lista é selecionada para ralagem.
	        listview.setCacheColorHint(Color.TRANSPARENT);
	    }
	 
	 public void createClassListView(ListView listview) {
		 
		 	dbConnection = new TimeClassManager(getApplicationContext());
	        //Criamos nossa lista que preenchera o ListView
	        classList = dbConnection.getAllClasses();
	       
	        //Cria o adapter
	        adapterTeacherClassLocationListView = new TeachersListAdapter(null,classList,null,this);

	        //Define o Adapter
	        listview.setAdapter(adapterTeacherClassLocationListView);
	        
	        //Cor quando a lista é selecionada para ralagem.
	        listview.setCacheColorHint(Color.TRANSPARENT);
	    }
	 
	 
	 @Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.activity_principal, menu);
			return true;
		}
	 
	 public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	        
	        case R.id.menuAbout:
	        {
	        	 AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
	 	   		 helpBuilder.setTitle(getResources().getString(R.string.menu_about));
	 	   		 
	 	   		 LayoutInflater inflater = getLayoutInflater();
	 	   		 View detalhe = inflater.inflate(R.layout.layout_about, null);
	 	   		 helpBuilder.setView(detalhe); 	 	   		

		   		  helpBuilder.setNegativeButton(getResources().getString(R.string.button_close), new DialogInterface.OnClickListener() {

		   		  public void onClick(DialogInterface dialog, int which) {
		   		   // Do nothing
		   		  }
		   		 }); 


		   		 AlertDialog helpDialog = helpBuilder.create();
		   		 helpDialog.show();  
	  
	        	return true;}
	        
	        case R.id.menu_help:
	        {
	        	 Intent intent = new Intent(CustomActivity.this, Help.class);
              
                 startActivity(intent);
                 
	        	
	        	return true;}
	        
	        
		    case R.id.menu_newteacher:
		    {
		    
		    	 AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
	 	   		 helpBuilder.setTitle(getResources().getString(R.string.label_newteacher));
	 	   		 
	 	   		 LayoutInflater inflater = getLayoutInflater();
	 	   		 View detalhe = inflater.inflate(R.layout.layout_newteacher, null);
	 	   		 helpBuilder.setView(detalhe); 
	 	   		 
	 	   		 final EditText  editNewTeacherName = (EditText)detalhe.findViewById(R.id.editNewTeacherName);
	 	   		 
		   		  helpBuilder.setPositiveButton(getResources().getString(R.string.button_save),
		   		   new DialogInterface.OnClickListener() {

		   		    public void onClick(DialogInterface dialog, int which) {	   		   
		   		       
		   		    	if(editNewTeacherName != null && editNewTeacherName.getText().length() > 0)
		   		    	{		   		    		
		   		    		try
			   		    	{	
		   		    		   		    	
				   		    	dbConnection = new TimeClassManager(getApplicationContext());
				   		    			   		    	
				   		    	dbConnection.insertTeacher(String.valueOf(editNewTeacherName.getText()));
				   		    	
			   		    	}
			   		    	catch(SQLException sqle)
			   		    	{
			   		    		Toast.makeText(CustomActivity.this, "Não foi possível adicionar esse professor!",
			   		 				Toast.LENGTH_SHORT).show();
			   		    	}
		   		    	}
		   		    	
		   		    	else
		   		    		
		   		    	{
		   		    		Toast.makeText(CustomActivity.this, "Você não informou o nome do professor.",
			   		 				Toast.LENGTH_SHORT).show();
		   		    		
		   		    	}
		   		    }
		   		   });

		   		  helpBuilder.setNegativeButton(getResources().getString(R.string.button_cancel), new DialogInterface.OnClickListener() {

		   		  public void onClick(DialogInterface dialog, int which) {
		   		   // Do nothing
		   		  }
		   		 }); 

		   		 AlertDialog helpDialog = helpBuilder.create();
		   		 helpDialog.show();  
	      	return true;
	 	   		
		    }
		    
		    case R.id.menu_newlocation:
		    {
		    
		    	 AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
	 	   		 helpBuilder.setTitle(getResources().getString(R.string.label_newlocation));
	 	   		 
	 	   		 LayoutInflater inflater = getLayoutInflater();
	 	   		 View detalhe = inflater.inflate(R.layout.layout_newlocation, null);
	 	   		 helpBuilder.setView(detalhe);  		
	 	   		
	 	   		final EditText  editNewLocationName = (EditText)detalhe.findViewById(R.id.editNewLocationName);
	 	   		 
		   		  helpBuilder.setPositiveButton(getResources().getString(R.string.button_save),
		   		   new DialogInterface.OnClickListener() {

		   		    public void onClick(DialogInterface dialog, int which) {
		   		   
		   		    	locationListView = (ListView)findViewById(R.id.listTeacherLocationClass);
		   		    	
		   		    	try
		   		    	{		   		    	
			   		    	dbConnection = new TimeClassManager(getApplicationContext());
			   		    			   		    	
			   		    	dbConnection.insertLocation(String.valueOf(editNewLocationName.getText()));
			   		    	
			   		    	
		   		    	}
		   		    	catch(SQLException sqle)
		   		    	{
		   		    		Toast.makeText(CustomActivity.this, "Não foi possível adicionar essa localização!",
		   		 				Toast.LENGTH_SHORT).show();
		   		    	}
		   		    	
		   		    }
		   		   });

		   		  helpBuilder.setNegativeButton(getResources().getString(R.string.button_cancel), new DialogInterface.OnClickListener() {

		   		  public void onClick(DialogInterface dialog, int which) {
		   		   // Do nothing
		   		  }
		   		 }); 

		   		 
		   		 
		   		 AlertDialog helpDialog = helpBuilder.create();
		   		 helpDialog.show();  
	      	return true;
	 	   		
		    }
		    
		    case R.id.menu_newclassname:
		    {
		    
		    	 AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
	 	   		 helpBuilder.setTitle(getResources().getString(R.string.label_newclassname));
	 	   		 
	 	   		 LayoutInflater inflater = getLayoutInflater();
	 	   		 View detalhe = inflater.inflate(R.layout.layout_newclassname, null);
	 	   		 helpBuilder.setView(detalhe);  		
	 	   		
	 	   		final EditText  editNewClassName = (EditText)detalhe.findViewById(R.id.editNewClassName);
	 	   		
		   		  helpBuilder.setPositiveButton(getResources().getString(R.string.button_save),
		   		   new DialogInterface.OnClickListener() {

		   		    public void onClick(DialogInterface dialog, int which) {
		   		   
		   		    	classListView = (ListView)findViewById(R.id.listTeacherLocationClass);
		   		    	
		   		    	try
		   		    	{		   		    	
			   		    	dbConnection = new TimeClassManager(getApplicationContext());
			   		    			   		    	
			   		    	dbConnection.insertClass(String.valueOf(editNewClassName.getText()));
			   		    	
			   		    	
		   		    	}
		   		    	catch(SQLException sqle)
		   		    	{
		   		    		Toast.makeText(CustomActivity.this, "Oops!Não foi possível adicionar essa disciplina!",
		   		 				Toast.LENGTH_SHORT).show();
		   		    	}
		   		    	
		   		    }
		   		   });

		   		  helpBuilder.setNegativeButton(getResources().getString(R.string.button_cancel), new DialogInterface.OnClickListener() {

		   		  public void onClick(DialogInterface dialog, int which) {
		   		   // Do nothing
		   		  }
		   		 }); 

		   		
		   		 AlertDialog helpDialog = helpBuilder.create();
		   		 helpDialog.show();  
	      	return true;
	 	   		
		    }
	        }
	        
	        return false;
		}

	 
	 
		
	 
}
