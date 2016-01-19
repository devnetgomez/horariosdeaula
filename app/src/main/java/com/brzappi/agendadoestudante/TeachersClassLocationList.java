package com.brzappi.agendadoestudante;

import com.brzappi.agendadoestudante.R;
import com.brzappi.agendadoestudante.dados.TimeClassManager;
import com.brzappi.agendadoestudante.modelo.h_class;
import com.brzappi.agendadoestudante.modelo.h_location;
import com.brzappi.agendadoestudante.modelo.h_teacher;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TeachersClassLocationList extends CustomActivity {
	
	AdapterContextMenuInfo contextmenuadapter;

	public int SelectedId;
	public String SelectedName;
	
	public int Table; // 1 - Teacher, 2 - Class, 3 - Location

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teachers_location_class_list);
		
		// Pegar os parametros para identificar qual a tabela
		Bundle b = getIntent().getExtras();
		Table = Integer.parseInt(b.getString("table"));

		switch(Table)
		{
			case 1:{
				//Pega a referencia do ListView
		        teacherListView = (ListView) findViewById(R.id.listTeacherLocationClass);
		       
		        registerForContextMenu(teacherListView);          
		        
		        createTeacherListView(teacherListView);
		        break;}
			
			case 2:{//Pega a referencia do ListView
		        locationListView = (ListView) findViewById(R.id.listTeacherLocationClass);
		       
		        registerForContextMenu(locationListView);          
		        
		        createLocationListView( locationListView);
		        break;}
			
			case 3:{//Pega a referencia do ListView
		        classListView = (ListView) findViewById(R.id.listTeacherLocationClass);
		       
		        registerForContextMenu(classListView);          
		        
		        createClassListView(classListView);
		        break;}
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_teachers_list, menu);
		return true;
	}
	
	// COnfigurar opções de menu de contexto(clique prolongado sobre o item da lista)
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	           // TODO Auto-generated method stub
	           super.onCreateContextMenu(menu, v, menuInfo);      
	           
	          
	          contextmenuadapter = (AdapterContextMenuInfo) menuInfo;
	 
	          menu.setHeaderTitle("O que deseja fazer?");      
	               menu.add(Menu.NONE, v.getId(), 0, "Modificar");
	               menu.add(Menu.NONE, v.getId(), 0, "Apagar");}
	        
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	        if (item.getTitle() == "Modificar") {
	        	
	        	 AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
	        	 
	        	 switch(Table)
	     		{
	     			case 1:{
	        	 teacherListView = (ListView) findViewById(R.id.listTeacherLocationClass);
	        	 
	        	 Object itemselecionado = teacherListView.getItemAtPosition(info.position);
	        	 
	        	 h_teacher selectedTeacher = (h_teacher)itemselecionado;
	        	 
	        	 SelectedName = selectedTeacher.getName();
	        	 SelectedId = selectedTeacher.getId();
	        	 break;
	     			}
	     			
	     		case 2:{
	   	        	 locationListView = (ListView) findViewById(R.id.listTeacherLocationClass);
	   	        	 
	   	        	 Object itemselecionado = locationListView.getItemAtPosition(info.position);
	   	        	 
	   	        	 h_location selectedLocation = (h_location)itemselecionado;
	   	        	 
	   	        	 SelectedName = selectedLocation.getName();
	   	        	 SelectedId = selectedLocation.getId();
	   	        	 break;
	   	     			}
	     		case 3:{
	   	        	 classListView = (ListView) findViewById(R.id.listTeacherLocationClass);
	   	        	 
	   	        	 Object itemselecionado = classListView.getItemAtPosition(info.position);
	   	        	 
	   	        	 h_class selectedClass = (h_class)itemselecionado;
	   	        	 
	   	        	 SelectedName = selectedClass.getName();
	   	        	 SelectedId = selectedClass.getId();
	   	        	 break;
	   	     			}
	     		}
	        	
		    	 AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
	 	   		 helpBuilder.setTitle(getResources().getString(R.string.label_updateTeacher));
	 	   		 
	 	   		 LayoutInflater inflater = getLayoutInflater();
	 	   		 View detalhe = inflater.inflate(R.layout.layout_newteacher, null);
	 	   		 helpBuilder.setView(detalhe); 
	 	   		 
	 	   		 final EditText  editNewName = (EditText)detalhe.findViewById(R.id.editNewTeacherName);
	 	   		editNewName.setText(SelectedName);
	 	   		 
		   		  helpBuilder.setPositiveButton(getResources().getString(R.string.button_save),
		   		   new DialogInterface.OnClickListener() {

		   		    public void onClick(DialogInterface dialog, int which) {	   		   
		   		       
		   		    	if(editNewName != null)
		   		    	{		   		    		
		   		    		try
			   		    	{		   		    	
				   		    	dbConnection = new TimeClassManager(getApplicationContext());
				   		    			   		
				   		    	
				   		    	boolean resultUpdate = false;
				   		    	
				   		     switch(Table)
					     		{
					     			case 1:{
					     					resultUpdate = dbConnection.updateTeacher(SelectedId, String.valueOf(editNewName.getText()));
				   		    	
							   		    	if(resultUpdate)
							   		    	{
							   		    		createTeacherListView(teacherListView);
							   		    	}
							   		    	break;
					     			}
					     			
					     			case 2:{
					     					resultUpdate = dbConnection.updateLocation(SelectedId, String.valueOf(editNewName.getText()));
				   		    	
							   		    	if(resultUpdate)
							   		    	{
							   		    		createLocationListView(locationListView);
							   		    	}
							   		 	break;
					     					}
					     			case 3:{
				     					resultUpdate = dbConnection.updateClass(SelectedId, String.valueOf(editNewName.getText()));
			   		    	
						   		    	if(resultUpdate)
						   		    	{
						   		    		createClassListView(classListView);
						   		    	}
						   		 	break;
				     					}
					     		}
			   		    	}
			   		    	catch(SQLException sqle)
			   		    	{
			   		    		Toast.makeText(TeachersClassLocationList.this, "Não foi possível salvar as informações!"+sqle.getMessage(),
			   		 				Toast.LENGTH_SHORT).show();
			   		    	}    
		   		    	}
		   		    }
		   		   });

		   		  helpBuilder.setNegativeButton(getResources().getString(R.string.button_cancel), new DialogInterface.OnClickListener() {

		   		  public void onClick(DialogInterface dialog, int which) {
		   		  
		        	  
		   		    	}
		   		  }
		   		); 		   		
		   		 
		   		 AlertDialog helpDialog = helpBuilder.create();
		   		 helpDialog.show();  
		   		 
	            }
	        
	         else if (item.getTitle() == "Apagar") {
	           
	        	 AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
	        	 
	        	 String UserNotificationMessage = "";
	        	 
	        	 switch(Table)
		     		{
		     			case 1:{
		        	 teacherListView = (ListView) findViewById(R.id.listTeacherLocationClass);
		        	 
		        	 Object itemselecionado = teacherListView.getItemAtPosition(info.position);
		        	 
		        	 h_teacher selectedTeacher = (h_teacher)itemselecionado;
		        	 
		        	 SelectedName = selectedTeacher.getName();
		        	 SelectedId = selectedTeacher.getId();
		        	 break;
		     			}
		     			
		     		case 2:{
		   	        	 locationListView = (ListView) findViewById(R.id.listTeacherLocationClass);
		   	        	 
		   	        	 Object itemselecionado = locationListView.getItemAtPosition(info.position);
		   	        	 
		   	        	 h_location selectedLocation = (h_location)itemselecionado;
		   	        	 
		   	        	 SelectedName = selectedLocation.getName();
		   	        	 SelectedId = selectedLocation.getId();
		   	        	 break;
		   	     			}
		     		case 3:{
		   	        	 classListView = (ListView) findViewById(R.id.listTeacherLocationClass);
		   	        	 
		   	        	 Object itemselecionado = classListView.getItemAtPosition(info.position);
		   	        	 
		   	        	 h_class selectedClass = (h_class)itemselecionado;
		   	        	 
		   	        	 SelectedName = selectedClass.getName();
		   	        	 SelectedId = selectedClass.getId();
		   	        	 break;
		   	     			}
		     		}	        	
	        	 
	        	 try
	   		    	{		
	        		 
		   		    	dbConnection = new TimeClassManager(getApplicationContext());
		   		    			   		    	
		   		    	boolean resultDelete = false;
		   		    	
			   		     switch(Table)
				     		{
				     			case 1:{
				     				
				     					if(dbConnection.getAllTimeClassesFromTeacher(SelectedId).size() >0)
				     					{
				     						UserNotificationMessage ="Existem horários lançados para este professor. Ele não pode ser excluído.";
				     					}
				     					else
				     					{
				     						resultDelete = dbConnection.deleteTeacher(SelectedId);
				     					
							   		    	if(resultDelete)
							   		    	{
							   		    		UserNotificationMessage ="O professor "+SelectedName+" foi excluído.";
							   		    		
							   		    		createTeacherListView(teacherListView);
							   		    	}
				     					}
				     					
				     					Toast.makeText(TeachersClassLocationList.this, UserNotificationMessage,
						   		 				Toast.LENGTH_SHORT).show();
					   		    		
						   		    	break;
				     			}
				     			
				     			case 2:{
						     				if(dbConnection.getAllTimeClassesFromLocation(SelectedId).size() >0)
					     					{
					     						UserNotificationMessage ="Existem horários lançados para esta sala. Ela não pode ser excluída.";
					     					}
					     					else
					     					{
					     						resultDelete = dbConnection.deleteLocation(SelectedId);
					     					
								   		    	if(resultDelete)
								   		    	{
								   		    		UserNotificationMessage ="A sala "+SelectedName+" foi excluída.";
								   		    		
								   		    		createLocationListView(locationListView);
								   		    	}
					     					}
					     					
					     					Toast.makeText(TeachersClassLocationList.this, UserNotificationMessage,
							   		 				Toast.LENGTH_SHORT).show();
						   		    		
							   		    	break;
				     					}
				     			case 3:{
				     				if(dbConnection.getAllTimeClassesFromClass(SelectedId).size() >0)
			     					{
			     						UserNotificationMessage ="Existem horários lançados para esta disciplina. Ela não pode ser excluída.";
			     					}
			     					else
			     					{
			     						resultDelete = dbConnection.deleteClass(SelectedId);
			     					
						   		    	if(resultDelete)
						   		    	{
						   		    		UserNotificationMessage ="A disciplina "+SelectedName+" foi excluída.";
						   		    		
						   		    		createClassListView(classListView);
						   		    	}
			     					}
			     					
			     					Toast.makeText(TeachersClassLocationList.this, UserNotificationMessage,
					   		 				Toast.LENGTH_SHORT).show();
				   		    		
					   		    	break;
		     					}
				     	}
				     			
		   		    	
		   		    	
	   		    	}
	   		    	catch(SQLException sqle)
	   		    	{
	   		    		Toast.makeText(TeachersClassLocationList.this, "Não foi possível salvar essas informações!"+sqle.getMessage(),
	   		 				Toast.LENGTH_SHORT).show();
	   		    	}  
	               }      
	         else     {
	               return false;
	               }
	       return true;
	       }
	

}
