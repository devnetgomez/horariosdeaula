package com.brzappi.agendadoestudante;

import java.util.ArrayList;
import java.util.List;






import com.brzappi.agendadoestudante.R;
import com.brzappi.agendadoestudante.dados.TimeClassManager;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;



@SuppressWarnings("deprecation")
public class Principal extends TabActivity  {

	Cursor listahorarios;

	TimeClassManager gerenciador;
	
	//interface access
	
	private Spinner spnTeacher;
	private Spinner spnClassName;
	private Spinner spnLocation;
	private TabHost TabHostPrincipal;
	
	public List<String> teacherNames = new ArrayList<String>();
	public  List<String> classNames = new ArrayList<String>();
	public  List<String> locationNames = new ArrayList<String>();
	
	
	private String nome;
	
	   public void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
	       
		   this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		   
		   setContentView(R.layout.activity_principal);	  
		   
	       TabHostPrincipal = getTabHost();
	       
	        
	        Intent TimeClassIntent = new Intent(this, WeekDayActivity.class);
	        View tabView = createTabView(this, getResources().getString(R.string.tab_header_timeclass),R.drawable.icon_timeclass);
	        TabSpec TimeClassSpec = TabHostPrincipal.newTabSpec("TimeClass").setIndicator(tabView).setContent(TimeClassIntent);
	        
	        Intent TeachersIntent = new Intent(this, TeachersClassLocationList.class).putExtra("table", "1").addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        tabView = createTabView(this, getResources().getString(R.string.tab_header_Teachers),R.drawable.icon_teacher);
	        TabSpec TeachersSpec = TabHostPrincipal.newTabSpec("Teachers").setIndicator(tabView).setContent(TeachersIntent);	
	        
	        Intent ClassesIntent = new Intent(this, TeachersClassLocationList.class).putExtra("table", "2").addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
 	        tabView = createTabView(this, getResources().getString(R.string.tab_header_Classes ),R.drawable.icon_classes);
	        TabSpec ClassesSpec = TabHostPrincipal.newTabSpec("Classes").setIndicator(tabView).setContent(ClassesIntent);	
	        
 	        Intent LocationIntent = new Intent(this, TeachersClassLocationList.class).putExtra("table", "3").addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
 	        tabView = createTabView(this, getResources().getString(R.string.tab_header_Locations),R.drawable.icon_location);
	        TabSpec LocationSpec =  TabHostPrincipal.newTabSpec("Locations").setIndicator(tabView).setContent(LocationIntent);	
	        	      
 	       TabHostPrincipal.addTab(TimeClassSpec);
 	       TabHostPrincipal.addTab(TeachersSpec);
 	       TabHostPrincipal.addTab(ClassesSpec);
 	       TabHostPrincipal.addTab(LocationSpec);	        
	       
 	       
	    }

	  
	
	   private static View createTabView(Context context, String tabText, int Icon) {
	        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab, null, false);
	        TextView tv = (TextView) view.findViewById(R.id.tabTitleText);
	        tv.setText(tabText);
	        
	        ImageView icon =  (ImageView) view.findViewById(R.id.tab_icon);
	        icon.setImageResource(Icon);
	        return view;
	    }
	   
	
}

