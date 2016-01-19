package com.brzappi.agendadoestudante;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.brzappi.agendadoestudante.R;
import com.brzappi.agendadoestudante.dados.TimeClassManager;

import android.net.NetworkInfo.DetailedState;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.text.format.Time;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class WeekDayActivity extends CustomActivity implements OnItemClickListener {

	private Spinner spnTeacher;
	private Spinner spnClassName;
	private Spinner spnLocation;
	
	public List<String> teacherNames = new ArrayList<String>();
	public  List<String> classNames = new ArrayList<String>();
	public  List<String> locationNames = new ArrayList<String>();	
	
    AdapterView.AdapterContextMenuInfo contextmenuadapter;
    
    
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_dia_da_semana);
	
	 Date[] datas= DaysNumber();
	
	 final Button btnSunday =  (Button)findViewById(R.id.btnShowSunday);
	 final Button btnMonday =  (Button)findViewById(R.id.btnShowMonday);
	 final Button btnTuesday =  (Button)findViewById(R.id.btnShowTuesday);
	 final Button btnWednesdy =  (Button)findViewById(R.id.btnShowWednesday);
	 final Button btnThursday =  (Button)findViewById(R.id.btnShowThursday);
	 final Button btnFriday =  (Button)findViewById(R.id.btnShowFriday);
	 final Button btnSaturday =  (Button)findViewById(R.id.btnShowSaturday);
	 
	 final Button[] ButtonsListWeekDay = new Button[]{ btnSunday, btnMonday, btnTuesday, btnWednesdy, btnThursday, btnFriday, btnSaturday};
	 
	 btnSunday.setText(datas[0].getDate()+" " + btnSunday.getText());
	 btnMonday.setText(datas[1].getDate()+" " + btnMonday.getText());
	 btnTuesday.setText(datas[2].getDate()+" " + btnTuesday.getText());
	 btnWednesdy.setText(datas[3].getDate()+" " + btnWednesdy.getText());
	 btnThursday.setText(datas[4].getDate()+" " + btnThursday.getText());
	 btnFriday.setText(datas[5].getDate()+" " + btnFriday.getText());
	 btnSaturday.setText(datas[6].getDate()+" " + btnSaturday.getText());
	   
	 final TextView textWeekDayName =  (TextView)findViewById(R.id.textWeekDayName); 
	 	 
	 // Identificar que dia é hoje, e deixar como tela inicial
	 
	  	WeekDay = new Date().getDay();		    		   
	    
	    textWeekDayName.setText(GetDayDescription(WeekDay));
	   		    
	    timeListView = (ListView) findViewById(R.id.listView1);
	    
	    timeListView.setOnItemClickListener(WeekDayActivity.this);

	    registerForContextMenu(timeListView);
	    
	    createTimeClassListView(getApplicationContext(),WeekDay);
	 

	    ButtonsListWeekDay[WeekDay].setSelected(true);
		
	    
	    // Configurar botões de escolha do horario
	    btnSunday.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {

		    ButtonsListWeekDay[WeekDay].setSelected(false);
		    btnSunday.setSelected(true);

		    WeekDay = 0;		    		   
		    
		    textWeekDayName.setText(GetDayDescription(WeekDay));
		   		    
		    timeListView = (ListView) findViewById(R.id.listView1);
		    
		    timeListView.setOnItemClickListener(WeekDayActivity.this);

		    registerForContextMenu(timeListView);
		    
		    createTimeClassListView(getApplicationContext(), WeekDay);
		  
	    }
	});
	 
	// Configurar botões de escolha do horario
		 btnMonday.setOnClickListener(new Button.OnClickListener() {
			    public void onClick(View v) {

			    	ButtonsListWeekDay[WeekDay].setSelected(false);
				    btnMonday.setSelected(true);
				    
				    
			    WeekDay = 1;
			    textWeekDayName.setText(GetDayDescription(WeekDay));
			    
			    timeListView = (ListView) findViewById(R.id.listView1);
			    
			    timeListView.setOnItemClickListener(WeekDayActivity.this);

			    registerForContextMenu(timeListView);    
			    			    
			    createTimeClassListView(getApplicationContext(), WeekDay);
		    }
		});
		 
		// Configurar botões de escolha do horario
		 btnTuesday.setOnClickListener(new Button.OnClickListener() {
			    public void onClick(View v) {

			    	ButtonsListWeekDay[WeekDay].setSelected(false);
				    btnTuesday.setSelected(true);
				    
			    WeekDay = 2;
			    
			    textWeekDayName.setText(GetDayDescription(WeekDay));
			    
			    timeListView = (ListView) findViewById(R.id.listView1);
			    
			    timeListView.setOnItemClickListener(WeekDayActivity.this);

			    registerForContextMenu(timeListView);    
			    			    
			    createTimeClassListView(getApplicationContext(), WeekDay);
		    }
		});
		 
		 
		// Configurar botões de escolha do horario
				 btnWednesdy.setOnClickListener(new Button.OnClickListener() {
					    public void onClick(View v) {

					    	ButtonsListWeekDay[WeekDay].setSelected(false);
						    btnWednesdy.setSelected(true);
						    
					    WeekDay = 3;
					    
					    textWeekDayName.setText(GetDayDescription(WeekDay));
					    
					    timeListView = (ListView) findViewById(R.id.listView1);
					    
					    timeListView.setOnItemClickListener(WeekDayActivity.this);

					    registerForContextMenu(timeListView);    
					    			    
					    createTimeClassListView(getApplicationContext(), WeekDay);
				    }
				});
				 
		// Configurar botões de escolha do horario
		btnThursday.setOnClickListener(new Button.OnClickListener() {
					    public void onClick(View v) {
					    	
					    	ButtonsListWeekDay[WeekDay].setSelected(false);
						    btnThursday.setSelected(true);
						    
					    WeekDay = 4;
					    
					    textWeekDayName.setText(GetDayDescription(WeekDay));
					    
					    timeListView = (ListView) findViewById(R.id.listView1);
					    
					    timeListView.setOnItemClickListener(WeekDayActivity.this);

					    registerForContextMenu(timeListView);    
					    			    
					    createTimeClassListView(getApplicationContext(), WeekDay);
				    }
				});
		
		
		// Configurar botões de escolha do horario
		 btnFriday.setOnClickListener(new Button.OnClickListener() {
			    public void onClick(View v) {
			    	
			    	ButtonsListWeekDay[WeekDay].setSelected(false);
				    btnFriday.setSelected(true);
				    
			    WeekDay = 5;
			    
			    textWeekDayName.setText(GetDayDescription(WeekDay));
			    
			    timeListView = (ListView) findViewById(R.id.listView1);
			    
			    timeListView.setOnItemClickListener(WeekDayActivity.this);

			    registerForContextMenu(timeListView);    
			    			    
			    createTimeClassListView(getApplicationContext(), WeekDay);
		    }
		});

			// Configurar botões de escolha do horario
		 btnSaturday.setOnClickListener(new Button.OnClickListener() {
			    public void onClick(View v) {

			    	ButtonsListWeekDay[WeekDay].setSelected(false);
				    btnSaturday.setSelected(true);
				    
			    WeekDay = 6;
			    
			    textWeekDayName.setText(GetDayDescription(WeekDay));
			    
			    timeListView = (ListView) findViewById(R.id.listView1);
			    
			    timeListView.setOnItemClickListener(WeekDayActivity.this);

			    registerForContextMenu(timeListView);    
			    			    
			    createTimeClassListView(getApplicationContext(), WeekDay);
		    }
		});
 



		 

		 
	
}


private String GetDayDescription(int _weekday)
{
	ArrayList<String> weekdayNames = new ArrayList<String>();
	
	Date weekdaynumber =DaysNumber()[_weekday];
	
	weekdayNames.add(getResources().getString(R.string.text_sunday));
	weekdayNames.add(getResources().getString(R.string.text_monday));
	weekdayNames.add(getResources().getString(R.string.text_tuesday));
	weekdayNames.add(getResources().getString(R.string.text_wednesday));
	weekdayNames.add(getResources().getString(R.string.text_thursday));
	weekdayNames.add(getResources().getString(R.string.text_friday));
	weekdayNames.add(getResources().getString(R.string.text_saturday));
			
	return weekdayNames.get(_weekday)+", "+ String.valueOf(weekdaynumber.getDate())+"/"+String.valueOf(weekdaynumber.getMonth()+1);
}

private Date[] DaysNumber()
{
	SimpleDateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	Date[] weekdayNumbers = new Date[7];
	
	Date myDate = new Date();	
	Date now = new Date();	

	//Contagem de dias, começando no sábado
	
	int DaysRemaining = 7 - myDate.getDay();
	int TodayDayWeek = myDate.getDay();
	int DaysGoneBy = 7 - DaysRemaining;
	
	Date current = new Date();
		
			for(int day=TodayDayWeek; day<=6; day++) // Numerar os dias pra frente
			{
				Date newData = new Date(current.getYear(),current.getMonth(), current.getDate());
				
				weekdayNumbers[day] = newData;
				
				current.setDate(current.getDate()+1);	
				
				
			}	
	
	 current = new Date();	
	 
			for(int day1=DaysGoneBy; day1>0; day1--) // Numerar os dias que já passaram
			{
				current.setDate(current.getDate()-1);
				
				Date newData = new Date(current.getYear(),current.getMonth(), current.getDate());
				
				weekdayNumbers[day1-1] = newData;
				
				
			}		
		
		return weekdayNumbers;
		
}

 @Override
public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
           // TODO Auto-generated method stub
           super.onCreateContextMenu(menu, v, menuInfo);      
                    
          contextmenuadapter = (AdapterContextMenuInfo) menuInfo;
 
          menu.setHeaderTitle(itens.get(contextmenuadapter.position).getClassName());      
               menu.add(Menu.NONE, v.getId(), 0, "Alterar");
               menu.add(Menu.NONE, v.getId(), 0, "Apagar");}
        
@Override
public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Alterar") {
           
        	ShowTimeClassWindow(1, item.getOrder()+1);
        	
               }
         else if (item.getTitle() == "Apagar") {
            //Do your working
               }      
         else     {
               return false;
               }
       return true;
       }


private void ShowTimeClassWindow(int Id, int TimeClassNumber)
{
	AlertDialog.Builder helpBuilder = new AlertDialog.Builder(WeekDayActivity.this);
	 helpBuilder.setTitle((Id== 0? "Inserir ":"Alterar") + String.valueOf(TimeClassNumber) +"° Horário");
	 helpBuilder.setIcon(getResources().getDrawable(R.drawable.icon_timeclass));
	 
	LayoutInflater inflater = getLayoutInflater();
	View timeclassdetail = inflater.inflate(R.layout.layout_newtimeclass, null);
	helpBuilder.setView(timeclassdetail);  		

	
	teacherNames = GetTeacherList();
	classNames = GetClassList();
	locationNames = GetLocationList();

	 final Button btnStartTime =  (Button)timeclassdetail.findViewById(R.id.btnStartTime);
	 final Button btnEndTime = (Button)timeclassdetail.findViewById(R.id.btmEndTime);
	
	  // Configurar bot~oes de escolha do horario
	  btnStartTime.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {


		     AlertDialog.Builder TimeChooseBuider = new AlertDialog.Builder(WeekDayActivity.this);
		     TimeChooseBuider.setTitle("Início");
		     TimeChooseBuider.setIcon(getResources().getDrawable(R.drawable.icon_timeclass));
		   	 
		   	LayoutInflater timeChooseinflater = getLayoutInflater();
		   	final View timeclassdetail = timeChooseinflater.inflate(R.layout.layout_timeselection, null);
		   	TimeChooseBuider.setView(timeclassdetail);  
		   		
			
		   	TimeChooseBuider.setPositiveButton(getResources().getString(R.string.button_save),
			   new DialogInterface.OnClickListener() {

		   		  TimePicker tpkTimeSelection = (TimePicker) timeclassdetail.findViewById(R.id.timeSelection);
		   		
			    public void onClick(DialogInterface dialog, int which) {
			   
			    // inserir o horário
			    if(tpkTimeSelection != null)
			    	{
			    	StartTime = tpkTimeSelection.getCurrentHour().toString()+":"+ tpkTimeSelection.getCurrentMinute().toString();
			    	
			    	btnStartTime.setText
			    		(("Começa às "+StartTime));
			    	}
			    }
			   });
		   	
		   	TimeChooseBuider.create().show();
	    }
	});
	  	
	  // Configurar bot~oes de escolha do horario
	  btnEndTime.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {


		     AlertDialog.Builder TimeChooseBuider = new AlertDialog.Builder(WeekDayActivity.this);
		     TimeChooseBuider.setTitle("Término");
		     TimeChooseBuider.setIcon(getResources().getDrawable(R.drawable.icon_timeclass));
		   	 
		   	LayoutInflater timeChooseinflater = getLayoutInflater();
		   	final View timeclassdetail = timeChooseinflater.inflate(R.layout.layout_timeselection, null);
		   	TimeChooseBuider.setView(timeclassdetail);		   		
			
		   	TimeChooseBuider.setPositiveButton(getResources().getString(R.string.button_save),
			   new DialogInterface.OnClickListener() {

		   		  TimePicker tpkTimeSelection = (TimePicker) timeclassdetail.findViewById(R.id.timeSelection);
		   		
			    public void onClick(DialogInterface dialog, int which) {
			   
			    // inserir o horário
			    if(tpkTimeSelection != null)
			    	{
			    		EndTime = tpkTimeSelection.getCurrentHour().toString()+":"+ tpkTimeSelection.getCurrentMinute().toString();
			    	
			    	btnEndTime.setText
			    		(("Termina às "+ EndTime));
			    	}
			    }
			   });
		   	
		   	TimeChooseBuider.create().show();
	    }
	});  

	
	// Localize the controls
	spnTeacher = (Spinner) timeclassdetail.findViewById(R.id.spinnerTeacher);
	spnClassName = (Spinner) timeclassdetail.findViewById(R.id.spinnerClassName);
	spnLocation = (Spinner) timeclassdetail.findViewById(R.id.spinnerLocation);
	
	ArrayAdapter<String> arrayAdapterTeacher = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teacherNames);
	ArrayAdapter<String> arrayAdapterClass = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classNames);
	ArrayAdapter<String> arrayAdapterLocation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, locationNames);
	
	final ArrayAdapter<String> spinnerArrayAdapterTeacher = arrayAdapterTeacher;
	final ArrayAdapter<String> spinnerArrayAdapterClass = arrayAdapterClass;
	final ArrayAdapter<String> spinnerArrayAdapterLocation = arrayAdapterLocation;
		
	spinnerArrayAdapterTeacher.setDropDownViewResource(android.R.layout.simple_spinner_item);
	spinnerArrayAdapterClass.setDropDownViewResource(android.R.layout.simple_spinner_item);
	spinnerArrayAdapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_item);
	
	spnTeacher.setAdapter(spinnerArrayAdapterTeacher);
	spnClassName.setAdapter(spinnerArrayAdapterClass);
	spnLocation.setAdapter(spinnerArrayAdapterLocation);
	

	// Configurar ações dos botões do popup
	spnTeacher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
			
			if(posicao > 0)
			{
				TeacherId = (int) spnTeacher.getSelectedItemId();
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {

		}
	});
	
	// Configurar ações dos botões do popup
		spnClassName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
				
				if(posicao > 0)
				{
					ClassId = (int) spnClassName.getSelectedItemId();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		

		// Configurar ações dos botões do popup
			spnLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
					
					if(posicao > 0)
					{
						LocationId = (int) spnLocation.getSelectedItemId();
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {

				}
			});
	
	  helpBuilder.setPositiveButton(getResources().getString(R.string.button_save),
	   new DialogInterface.OnClickListener() {

	    public void onClick(DialogInterface dialog, int which) {
	   
	    	TimeNumber = 1; // Criar função para buscar número do horário
	    	
	    	// inserir o horário
	    	if(WeekDay>0 && 
	    	   TimeNumber>0 && 
	    	   TeacherId >0 && 
	    	   LocationId >0 && 
	    	   ClassId >0 &&
	    	   StartTime != null &&
	    	   EndTime != null)
	    	{
	    		try
   		    	{		   		    	
	   		    	dbConnection = new TimeClassManager(getApplicationContext());
	   		    			   		    	
	   		    	long result =  dbConnection.insertTimeClass(WeekDay, TimeNumber, StartTime, EndTime, TeacherId, LocationId, ClassId);
	   		    	
	   		    	result +=1;
   		    	}
   		    	catch(SQLException sqle)
   		    	{
   		    		Toast.makeText(WeekDayActivity.this, "Não foi possível salvar essa localização!"+sqle.getMessage(),
   		 				Toast.LENGTH_SHORT).show();
   		    	}    
	    		
	    		createTimeClassListView(getApplicationContext(), WeekDay);
	    		
	    	}
	    }
	   });

	  helpBuilder.setNegativeButton(getResources().getString(R.string.button_cancel), new DialogInterface.OnClickListener() {

	  public void onClick(DialogInterface dialog, int which) {
	   // Do nothing
	  }
	 }); 

	  
	  	  
	  // Exibir janela em modo pop up 
	 AlertDialog helpDialog = helpBuilder.create();
	 helpDialog.show();  
	}
@Override
public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	// TODO Auto-generated method stub
	
	
	 
	 ShowTimeClassWindow(0,arg2+1);
	
}
	

}
