package com.brzappi.agendadoestudante;

import java.util.ArrayList;
import java.util.List;

import com.brzappi.agendadoestudante.R;
import com.brzappi.agendadoestudante.modelo.h_class;
import com.brzappi.agendadoestudante.modelo.h_location;
import com.brzappi.agendadoestudante.modelo.h_teacher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TeachersListAdapter extends BaseAdapter {

	private final int TypeTeacher = 1;
	private final int TypeClass = 2;
	private final int TypeLocation = 3;
	private int classType;
	
    private List<h_teacher> _datateacher;
    private List<h_class> _dataclass;
    private List<h_location> _datalocation;
    
    Context _c;
    
    TeachersListAdapter (List<h_teacher> teacherList, List<h_class> classList, List<h_location> locationList, Context c){
        
    	if(teacherList != null)
    	{
			_datateacher = teacherList;
			this.classType = TypeTeacher;
    	}	
    	else if(classList != null)
    	{
			_dataclass = classList;
			this.classType = TypeClass;
    	}    	
    	else if(locationList != null)
    	{
			_datalocation = locationList;
			this.classType = TypeLocation;
    	}    	
        _c = c;
    }
    
    public int getCount() {

    	int list_count =0;
    	
    	switch (this.classType) {
    	
		case TypeTeacher: list_count = _datateacher.size(); break;

		case TypeClass: list_count = _dataclass.size(); break;
		
		case TypeLocation: list_count = _datalocation.size(); break;	
		}
       
    	return list_count;
    }
    
    public Object getItem(int position) {
         
    	Object list_item = null;
    	
    	switch (this.classType) {
    	
		case TypeTeacher: list_item = _datateacher.get(position); break;

		case TypeClass: list_item = _dataclass.get(position); break;
		
		case TypeLocation: list_item = _datalocation.get(position); break;	
		}
       
    	return list_item;
    }
 
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
   
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
         View view = convertView;
         if (view == null)
         {
            LayoutInflater vi = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R.layout.layout_simplelistrow, null);
         }
         
         switch (this.classType) {
     	
 		case TypeTeacher:
 			
 			h_teacher itemteacher = _datateacher.get(position);
         	
 	         ((TextView) view.findViewById(R.id.textGenericDescription)).setText(itemteacher.getName());
 	         ; break;

 		case TypeClass:
 			h_class itemclass = _dataclass.get(position);
         	
	         ((TextView) view.findViewById(R.id.textGenericDescription)).setText(itemclass.getName());
	         ; break;	         
 		
 		case TypeLocation: 

 			h_location itemlocation = _datalocation.get(position);
         	
	         ((TextView) view.findViewById(R.id.textGenericDescription)).setText(itemlocation.getName());
	         ; break;
	         
 		}
                               
                        
        return view;
}
}