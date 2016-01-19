package com.brzappi.agendadoestudante;

import java.util.ArrayList;

import com.brzappi.agendadoestudante.R;
import com.brzappi.agendadoestudante.modelo.class_time;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TimeClassAdapterList extends BaseAdapter {
	 
    private ArrayList<class_time> _data;
    Context _c;
    
    TimeClassAdapterList (ArrayList<class_time> data, Context c){
        _data = data;
        _c = c;
    }
   
    public int getCount() {
        // TODO Auto-generated method stub
        return _data.size();
    }
    
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return _data.get(position);
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
            view = vi.inflate(R.layout.layout_linha_horario, null);
         }
         
         class_time timeclass = _data.get(position);
         	
         ((TextView) view.findViewById(R.id.textProfessor)).setText(timeclass.getTeacherName());
         ((TextView) view.findViewById(R.id.textDisciplina)).setText(timeclass.getClassName());
         ((TextView) view.findViewById(R.id.textSala)).setText(timeclass.getLocationName());
         ((TextView) view.findViewById(R.id.textNumHorario)).setText(String.valueOf(position+1));
         ((TextView) view.findViewById(R.id.textTimStartEnd)).setText(timeclass.getTimeStart()+" - " + timeclass.getTimeEnd());
 
         ((TextView) view.findViewById(R.id.textTimeClassTasks)).setText("");
           
                                 
                        
        return view;
}
}