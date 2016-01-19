package com.brzappi.agendadoestudante.dados;

import java.util.ArrayList;
import java.util.List;

import com.brzappi.agendadoestudante.modelo.class_time;
import com.brzappi.agendadoestudante.modelo.h_class;
import com.brzappi.agendadoestudante.modelo.h_location;
import com.brzappi.agendadoestudante.modelo.h_teacher;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TimeClassManager  extends SQLiteOpenHelper {
	
	private static final String NOME_BANCO = "horarioaula.db";
	private static final int VERSAO_SCHEMA = 1;

	public TimeClassManager(Context context) {
		super(context, NOME_BANCO, null, VERSAO_SCHEMA);		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE h_timeclass (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
				" idTeacher INTEGER, idLocation INTEGER, idClass INTEGER, weekDay INTEGER, timeNumber INTEGER, startTime TEXT, endTime TEXT);");
		
		db.execSQL("CREATE TABLE h_location (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
				" locationName TEXT);");
		
		db.execSQL("CREATE TABLE h_teacher (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
				" teacherName TEXT);");
		
		db.execSQL("CREATE TABLE h_class (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
				" className TEXT);");
		
		db.execSQL("CREATE TABLE h_user_settings (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
				" ActivateInstantTime BIT);");
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	
	// INSERT VALUES ON DATABASE
	
	public void insertTeacher(String Name) {
		
		ContentValues datavalue = new ContentValues();
		
		datavalue.put("teacherName", Name);

		getWritableDatabase().insert("h_teacher", "teacherName", datavalue);
		this.close();
		
	}
	
	public void insertLocation(String Name) {
		
		ContentValues datavalue = new ContentValues();
		
		datavalue.put("locationName", Name);

		getWritableDatabase().insert("h_location", "locationName", datavalue);
		this.close();
	}
	
	public void insertClass(String Name) {
		
		ContentValues datavalue = new ContentValues();
		
		datavalue.put("className", Name);

		getWritableDatabase().insert("h_class", "className", datavalue);
		this.close();
	}
	
	public long insertTimeClass(int WeekDay, int TimeNumber, String StartTime, String EndTime, int idTeacher, int idLocation, int idClass) {
		
		ContentValues datavalue = new ContentValues();
		
		datavalue.put("weekDay", WeekDay);
		datavalue.put("idTeacher", idTeacher);
		datavalue.put("idLocation", idLocation);
		datavalue.put("idClass", idClass);
		datavalue.put("startTime", StartTime);
		datavalue.put("endTime", EndTime);
		datavalue.put("timeNumber", TimeNumber);
		

		long result = getWritableDatabase().insert("h_timeclass", "idTeacher", datavalue);
		
		this.close();
		
		return result;
	}
	
	//UPDATE ON DATABASE	
	
	@SuppressWarnings("finally")
	public boolean updateTeacher(int id, String Name) {
		
		boolean rowUpdated = false;
		
		try
		{		
			this.getReadableDatabase().execSQL("update h_teacher set teacherName ='"+Name+"' WHERE _id="+String.valueOf(id)+" ;");
			rowUpdated = true;
		}
		catch(SQLException ex)
		{
			rowUpdated = false;
			
		}
		finally
		{
			this.close();
			return rowUpdated;
		}			
	}
	
	@SuppressWarnings("finally")
	public boolean updateClass(int id, String Name) {
		
		boolean rowUpdated = false;
		
		try
		{		
			this.getReadableDatabase().execSQL("update h_class set className ='"+Name+"' WHERE _id="+String.valueOf(id)+" ;");
			rowUpdated = true;
		}
		catch(SQLException ex)
		{
			rowUpdated = false;
			
		}
		finally
		{
			this.close();
			return rowUpdated;
		}			
	}
	
	@SuppressWarnings("finally")
	public boolean updateLocation(int id, String Name) {
		
		boolean rowUpdated = false;
		
		try
		{		
			this.getReadableDatabase().execSQL("update h_location set locationName ='"+Name+"' WHERE _id="+String.valueOf(id)+" ;");
			rowUpdated = true;
		}
		catch(SQLException ex)
		{
			rowUpdated = false;
			
		}
		finally
		{
			this.close();
			return rowUpdated;
		}			
	}
	
	@SuppressWarnings("finally")
	public boolean updateTimeClass(int id, String StartTime, String EndTime, int idClass, int idLocation, int idTeacher) {
		
		boolean rowUpdated = false;
		
		try
		{		
			this.getReadableDatabase().execSQL("update h_timeclass set "+
												"	   	startTime ='"+StartTime+"'"+
												"	   	endTime='"+EndTime+"'"+
												"		idClass= "+ String.valueOf(idClass)+
												"		idLocation= "+ String.valueOf(idLocation)+
												"		idTeacher= "+ String.valueOf(idTeacher)+
												" WHERE _id="+String.valueOf(id)+" ;");
			rowUpdated = true;
		}
		catch(SQLException ex)
		{
			rowUpdated = false;
			
		}
		finally
		{
			this.close();
			return rowUpdated;
		}			
	}
	
	
	// DELETE ON DATABASE
	@SuppressWarnings("finally")
	public boolean deleteTeacher(int id) {
		
		boolean rowDeleted = false;
		
		try
		{		
			this.getReadableDatabase().execSQL("delete from h_teacher  WHERE _id="+String.valueOf(id)+" ;");
			rowDeleted = true;
		}
		catch(SQLException ex)
		{
			rowDeleted = false;
			
		}
		finally
		{
			this.close();
			return rowDeleted;
		}			
	}
	
	@SuppressWarnings("finally")
	public boolean deleteLocation(int id) {
		
		boolean rowDeleted = false;
		
		try
		{		
			this.getReadableDatabase().execSQL("delete from h_location  WHERE _id="+String.valueOf(id)+" ;");
			rowDeleted = true;
		}
		catch(SQLException ex)
		{
			rowDeleted = false;
			
		}
		finally
		{
			this.close();
			return rowDeleted;
		}			
	}
		
	@SuppressWarnings("finally")
	public boolean deleteClass(int id) {
		
		boolean rowDeleted = false;
		
		try
		{		
			this.getReadableDatabase().execSQL("delete from h_class  WHERE _id="+String.valueOf(id)+" ;");
			rowDeleted = true;
		}
		catch(SQLException ex)
		{
			rowDeleted = false;
			
		}
		finally
		{
			this.close();
			return rowDeleted;
		}			
	}
	
	@SuppressWarnings("finally")
	public boolean deleteTimeClass(int id) {
		
		boolean rowDeleted = false;
		
		try
		{		
			this.getReadableDatabase().execSQL("delete from h_timeclass  WHERE _id="+String.valueOf(id)+" ;");
			rowDeleted = true;
		}
		catch(SQLException ex)
		{
			rowDeleted = false;
			
		}
		finally
		{
			this.close();
			return rowDeleted;
		}			
	}
	
	
	// SELECT ON DATABASE
	public List<h_teacher> getAllTeachers() {
		
		List<h_teacher> mylist = new  ArrayList<h_teacher>();
		
		Cursor result = getReadableDatabase().rawQuery( " select _id, teacherName FROM h_teacher "+														
														" ORDER BY teacherName", null);
		
		if(result != null && result.getCount() > 0)
		{
			result.moveToFirst();
			
			mylist.add(new h_teacher(Integer.parseInt(result.getString(0)), result.getString(1)));
			
			while(result.moveToNext())
			{
				mylist.add(new h_teacher(Integer.parseInt(result.getString(0)), result.getString(1)));				
			}			
		}
		

		result.close();
		this.close();
		
		return mylist;
	}	
	
	public h_teacher getTeacher(int id) {
		
		h_teacher myRow = new  h_teacher();
		
		Cursor result = getReadableDatabase().rawQuery("select _id, teacherName FROM h_teacher WHERE _id="+String.valueOf(id), null);
		
		if(result != null && result.getCount() > 0)
		{
			result.moveToFirst();
			
			 myRow = new h_teacher(Integer.parseInt(result.getString(0)), result.getString(1));		
						
		}
		
		result.close();
		this.close();
		
		return myRow;		
	}
	
	public h_teacher getTeacher(String Name) {
		
		h_teacher myRow = new  h_teacher();
		
		Cursor result = getReadableDatabase().rawQuery("select _id, teacherName FROM h_teacher teacherName teacherName = '"+Name+"'", null);
		
		if(result != null && result.getCount() > 0)
		{
			result.moveToFirst();
			
			 myRow = new h_teacher(Integer.parseInt(result.getString(0)), result.getString(1));		
						
		}
		
		result.close();
		this.close();
		
		return myRow;		
	}
	
	
	public List<h_class> getAllClasses() {
		
		 List<h_class> mylist = new  ArrayList<h_class>();
		
		Cursor result = getReadableDatabase().rawQuery("select _id, className FROM h_class ORDER BY className", null);
		
		if(result != null && result.getCount() > 0)
		{
			result.moveToFirst();
			
			mylist.add(new h_class(Integer.parseInt(result.getString(0)), result.getString(1)));
			
			while(result.moveToNext())
			{
				mylist.add(new h_class(Integer.parseInt(result.getString(0)), result.getString(1)));				
			}			
		}
		result.close();
		this.close();
		
		return mylist;

	}
	
	public h_class getClass(int id) {

		h_class myRow = new  h_class();
		
		Cursor result = getReadableDatabase().rawQuery("select _id, className FROM h_class WHERE _id="+String.valueOf(id), null);
		
		if(result != null && result.getCount() > 0)
		{
			result.moveToFirst();
			
			 myRow = new h_class(Integer.parseInt(result.getString(0)), result.getString(1));		
						
		}
		result.close();
		this.close();
		return myRow;	
 
	}
	
	
	public List<h_location> getAllLocations() {

		 List<h_location> mylist = new  ArrayList<h_location>();
			
			Cursor result =  getReadableDatabase().rawQuery("select _id, locationName FROM h_location ORDER BY locationName", null);
			
			if(result != null && result.getCount() > 0)
			{
				result.moveToFirst();
				
				mylist.add(new h_location(Integer.parseInt(result.getString(0)), result.getString(1)));
				
				while(result.moveToNext())
				{
					mylist.add(new h_location(Integer.parseInt(result.getString(0)), result.getString(1)));				
				}			
			}
			result.close();
			this.close();
			return mylist;
	}
	
	public h_location getLocation(int id) {
		
		h_location myRow = new  h_location();
		
		Cursor result = getReadableDatabase().rawQuery("select _id, locationName FROM h_location WHERE _id="+String.valueOf(id), null);
		
		if(result != null && result.getCount() > 0)
		{
			result.moveToFirst();
			
			 myRow = new h_location(Integer.parseInt(result.getString(0)), result.getString(1));		
						
		}
		result.close();
		this.close();
		return myRow;	
		
	}
	
	@SuppressWarnings("finally")	
	public  List<class_time> getAllTimeClasses() {

		 List<class_time> mylist = new  ArrayList<class_time>();
			
		 try
		 {
			Cursor result =  getReadableDatabase().rawQuery(	" select h_timeclass._id, h_timeclass.weekDay, h_timeclass.idTeacher, h_timeclass.idLocation, " +
																"		h_timeclass.idClass, h_timeclass.startTime, h_timeclass.endTime, h_timeclass.timeNumber,"+
																"		h_teacher.teacherName, h_class.className, h_location.locationName "+
																" FROM h_timeclass "+
																" LEFT OUTER JOIN h_teacher ON  h_timeclass.idTeacher = h_teacher._id "+
																" LEFT OUTER JOIN h_class ON h_timeclass.idClass = h_class._id  "+
																" LEFT OUTER JOIN h_location ON  h_timeclass.idLocation = h_location._id  "+
																" ORDER BY h_timeclass.weekDay", null);
			 
			 /*Cursor result =  getReadableDatabase().rawQuery(	
					 	" 		select h_timeclass._id, h_timeclass.weekDay, h_timeclass.idTeacher, h_timeclass.idLocation, " +
						"			h_timeclass.idClass, h_timeclass.startTime, h_timeclass.endTime, h_timeclass.timeNumber"+
						" 		FROM h_timeclass "+
						" 		ORDER BY h_timeclass.weekDay;", null);*/

			
			
			
			if(result != null && result.getCount() > 0)
			{
				result.moveToFirst();
				
				class_time myRow = new class_time(			Integer.parseInt(result.getString(result.getColumnIndex("idTeacher"))),
						Integer.parseInt(result.getString(result.getColumnIndex("idClass"))),
						result.getString(result.getColumnIndex("startTime")),result.getString(result.getColumnIndex("endTime")),
						Integer.parseInt(result.getString(result.getColumnIndex("idLocation"))),
						Integer.parseInt(result.getString(result.getColumnIndex("weekDay"))),
						Integer.parseInt(result.getString(result.getColumnIndex("timeNumber"))));
				 
				
				myRow.setTeacherName(result.getString(result.getColumnIndex("teacherName")));
				myRow.setClassName(result.getString(result.getColumnIndex("className")));
				myRow.setLocationName(result.getString(result.getColumnIndex("locationName")));				 
				
				mylist.add(myRow);
				
				while(result.moveToNext())
				{
					myRow = new class_time(				Integer.parseInt(result.getString(result.getColumnIndex("idTeacher"))),
							Integer.parseInt(result.getString(result.getColumnIndex("idClass"))),
							result.getString(result.getColumnIndex("startTime")),result.getString(result.getColumnIndex("endTime")),
							Integer.parseInt(result.getString(result.getColumnIndex("idLocation"))),
							Integer.parseInt(result.getString(result.getColumnIndex("weekDay"))),
							Integer.parseInt(result.getString(result.getColumnIndex("timeNumber"))));
					 
					myRow.setTeacherName(result.getString(result.getColumnIndex("teacherName")));
					myRow.setClassName(result.getString(result.getColumnIndex("className")));
					myRow.setLocationName(result.getString(result.getColumnIndex("locationName")));				 
					
					
					mylist.add(myRow);			
				}			
			}
		 
			result.close();
		 }catch(SQLException slqe)
		 {
			 String message = slqe.getMessage();
			 
		 }
		 finally
		 {
			this.close();
			return mylist;
		 }
	}
	
	@SuppressWarnings("finally")
	public  List<class_time> getAllTimeClasses(int _WeekDay) {


		 List<class_time> mylist = new  ArrayList<class_time>();
			
		
 			Cursor result =  getReadableDatabase().rawQuery(	" select DISTINCT h_timeclass._id,"+
																" 				  h_timeclass.weekDay,"+
																" 				  h_timeclass.idTeacher,"+
																"				  h_timeclass.idLocation, " +
																"				  h_timeclass.idClass,"+
																"				  h_timeclass.startTime, "+
																"				  h_timeclass.endTime, "+
																"				  h_timeclass.timeNumber,"+
																"				  h_teacher.teacherName, "+
																"				  h_class.className,"+
																"				  h_location.locationName "+
																" FROM h_timeclass "+
																" INNER JOIN h_teacher ON h_teacher._id = h_timeclass.idTeacher "+
																" INNER JOIN h_class ON h_class._id = h_timeclass.idClass "+
																" INNER JOIN h_location ON h_location._id = h_timeclass.idLocation "+
																" WHERE h_timeclass.weekDay= "+String.valueOf(_WeekDay)+" ORDER BY h_timeclass.weekDay ;", null);
			
			
			
			if(result != null && result.getCount() > 0)
			{
				result.moveToFirst();
				
				class_time myRow = new class_time(			Integer.parseInt(result.getString(2)),
						Integer.parseInt(result.getString(4)),
						result.getString(5),result.getString(6),
						Integer.parseInt(result.getString(3)),
						Integer.parseInt(result.getString(1)),
						Integer.parseInt(result.getString(7)));
				 
				myRow.setTeacherName(result.getString(8));
				myRow.setClassName(result.getString(9));
				myRow.setLocationName(result.getString(10));	
				
				mylist.add(myRow);
				
				while(result.moveToNext())
				{
					myRow = new class_time(			Integer.parseInt(result.getString(2)),
							Integer.parseInt(result.getString(4)),
							result.getString(5),result.getString(6),
							Integer.parseInt(result.getString(3)),
							Integer.parseInt(result.getString(1)),
							Integer.parseInt(result.getString(7)));
					 
					myRow.setTeacherName(result.getString(8));
					myRow.setClassName(result.getString(9));
					myRow.setLocationName(result.getString(10));	
					 
					mylist.add(myRow);			
				}			
			}
			result.close();
			
		 
			this.close();
			return mylist;
		
	
	}

	public  List<class_time> getAllTimeClassesFromTeacher(int teacherId) {


		 List<class_time> mylist = new  ArrayList<class_time>();
			
		
			Cursor result =  getReadableDatabase().rawQuery(	" select DISTINCT h_timeclass._id,"+
																" 				  h_timeclass.weekDay,"+
																" 				  h_timeclass.idTeacher,"+
																"				  h_timeclass.idLocation, " +
																"				  h_timeclass.idClass,"+
																"				  h_timeclass.startTime, "+
																"				  h_timeclass.endTime, "+
																"				  h_timeclass.timeNumber,"+
																"				  h_teacher.teacherName, "+
																"				  h_class.className,"+
																"				  h_location.locationName "+
																" FROM h_timeclass "+
																" INNER JOIN h_teacher ON h_teacher._id = h_timeclass.idTeacher "+
																" INNER JOIN h_class ON h_class._id = h_timeclass.idClass "+
																" INNER JOIN h_location ON h_location._id = h_timeclass.idLocation "+
																" WHERE h_timeclass.idTeacher= "+String.valueOf(teacherId)+" ORDER BY h_timeclass.weekDay,h_timeclass.timeNumber;", null);
			
			
			
			if(result != null && result.getCount() > 0)
			{
				result.moveToFirst();
				
				class_time myRow = new class_time(			Integer.parseInt(result.getString(2)),
						Integer.parseInt(result.getString(4)),
						result.getString(5),result.getString(6),
						Integer.parseInt(result.getString(3)),
						Integer.parseInt(result.getString(1)),
						Integer.parseInt(result.getString(7)));
				 
				myRow.setTeacherName(result.getString(8));
				myRow.setClassName(result.getString(9));
				myRow.setLocationName(result.getString(10));	
				
				mylist.add(myRow);
				
				while(result.moveToNext())
				{
					myRow = new class_time(			Integer.parseInt(result.getString(2)),
							Integer.parseInt(result.getString(4)),
							result.getString(5),result.getString(6),
							Integer.parseInt(result.getString(3)),
							Integer.parseInt(result.getString(1)),
							Integer.parseInt(result.getString(7)));
					 
					myRow.setTeacherName(result.getString(8));
					myRow.setClassName(result.getString(9));
					myRow.setLocationName(result.getString(10));	
					 
					mylist.add(myRow);			
				}			
			}
			result.close();
			
		 
			this.close();
			return mylist;
		
	
	}

	public  List<class_time> getAllTimeClassesFromLocation(int locationId) {


		 List<class_time> mylist = new  ArrayList<class_time>();
			
		
			Cursor result =  getReadableDatabase().rawQuery(	" select DISTINCT h_timeclass._id,"+
																" 				  h_timeclass.weekDay,"+
																" 				  h_timeclass.idTeacher,"+
																"				  h_timeclass.idLocation, " +
																"				  h_timeclass.idClass,"+
																"				  h_timeclass.startTime, "+
																"				  h_timeclass.endTime, "+
																"				  h_timeclass.timeNumber,"+
																"				  h_teacher.teacherName, "+
																"				  h_class.className,"+
																"				  h_location.locationName "+
																" FROM h_timeclass "+
																" INNER JOIN h_teacher ON h_teacher._id = h_timeclass.idTeacher "+
																" INNER JOIN h_class ON h_class._id = h_timeclass.idClass "+
																" INNER JOIN h_location ON h_location._id = h_timeclass.idLocation "+
																" WHERE h_timeclass.idLocation= "+String.valueOf(locationId)+" ORDER BY h_timeclass.weekDay, h_timeclass.timeNumber;", null);
			
			
			
			if(result != null && result.getCount() > 0)
			{
				result.moveToFirst();
				
				class_time myRow = new class_time(			Integer.parseInt(result.getString(2)),
						Integer.parseInt(result.getString(4)),
						result.getString(5),result.getString(6),
						Integer.parseInt(result.getString(3)),
						Integer.parseInt(result.getString(1)),
						Integer.parseInt(result.getString(7)));
				 
				myRow.setTeacherName(result.getString(8));
				myRow.setClassName(result.getString(9));
				myRow.setLocationName(result.getString(10));	
				
				mylist.add(myRow);
				
				while(result.moveToNext())
				{
					myRow = new class_time(			Integer.parseInt(result.getString(2)),
							Integer.parseInt(result.getString(4)),
							result.getString(5),result.getString(6),
							Integer.parseInt(result.getString(3)),
							Integer.parseInt(result.getString(1)),
							Integer.parseInt(result.getString(7)));
					 
					myRow.setTeacherName(result.getString(8));
					myRow.setClassName(result.getString(9));
					myRow.setLocationName(result.getString(10));	
					 
					mylist.add(myRow);			
				}			
			}
			result.close();
			
		 
			this.close();
			return mylist;
		
	
	}

	public  List<class_time> getAllTimeClassesFromClass(int classId) {


		 List<class_time> mylist = new  ArrayList<class_time>();
			
		
			Cursor result =  getReadableDatabase().rawQuery(	" select DISTINCT h_timeclass._id,"+
																" 				  h_timeclass.weekDay,"+
																" 				  h_timeclass.idTeacher,"+
																"				  h_timeclass.idLocation, " +
																"				  h_timeclass.idClass,"+
																"				  h_timeclass.startTime, "+
																"				  h_timeclass.endTime, "+
																"				  h_timeclass.timeNumber,"+
																"				  h_teacher.teacherName, "+
																"				  h_class.className,"+
																"				  h_location.locationName "+
																" FROM h_timeclass "+
																" INNER JOIN h_teacher ON h_teacher._id = h_timeclass.idTeacher "+
																" INNER JOIN h_class ON h_class._id = h_timeclass.idClass "+
																" INNER JOIN h_location ON h_location._id = h_timeclass.idLocation "+
																" WHERE h_timeclass.idClass= "+String.valueOf(classId)+" ORDER BY h_timeclass.weekDay, h_timeclass.timeNumber;", null);
			
			
			
			if(result != null && result.getCount() > 0)
			{
				result.moveToFirst();
				
				class_time myRow = new class_time(			Integer.parseInt(result.getString(2)),
						Integer.parseInt(result.getString(4)),
						result.getString(5),result.getString(6),
						Integer.parseInt(result.getString(3)),
						Integer.parseInt(result.getString(1)),
						Integer.parseInt(result.getString(7)));
				 
				myRow.setTeacherName(result.getString(8));
				myRow.setClassName(result.getString(9));
				myRow.setLocationName(result.getString(10));	
				
				mylist.add(myRow);
				
				while(result.moveToNext())
				{
					myRow = new class_time(			Integer.parseInt(result.getString(2)),
							Integer.parseInt(result.getString(4)),
							result.getString(5),result.getString(6),
							Integer.parseInt(result.getString(3)),
							Integer.parseInt(result.getString(1)),
							Integer.parseInt(result.getString(7)));
					 
					myRow.setTeacherName(result.getString(8));
					myRow.setClassName(result.getString(9));
					myRow.setLocationName(result.getString(10));	
					 
					mylist.add(myRow);			
				}			
			}
			result.close();
			
		 
			this.close();
			return mylist;
		
	
	}

	
	public class_time getTimeClass(int id) {

		class_time myRow = new  class_time();
		
		Cursor result = getReadableDatabase().rawQuery( " select h_timeclass._id, h_timeclass.weekDay, h_timeclass.idTeacher, h_timeclass.idLocation, " +
														"		h_timeclass.idClass, h_timeclass.startTime, h_timeclass.endTime, h_timeclass.timeNumber,"+
														"		h_teacher.teacherName, h_class.className, h_location.locationName "+
														" FROM h_timeclass "+
														" INNER JOIN h_teacher ON h_teacher._id = h_timeclass.idTeacher "+
														" INNER JOIN h_class ON h_class._id = h_timeclass.idClass "+
														" INNER JOIN h_location ON h_location._id = h_timeclass.idLocation "+
														" WHERE h_timeclass._id =" +String.valueOf(id), null);
		
		if(result != null && result.getCount() > 0)
		{
			result.moveToFirst();
			
			myRow = new class_time(			Integer.parseInt(result.getString(2)),
					Integer.parseInt(result.getString(4)),
					result.getString(5),result.getString(6),
					Integer.parseInt(result.getString(3)),
					Integer.parseInt(result.getString(1)),
					Integer.parseInt(result.getString(7)));
			 
			myRow.setTeacherName(result.getString(8));
			myRow.setClassName(result.getString(9));
			myRow.setLocationName(result.getString(10));	
		}
		result.close();
		this.close();
		return myRow;	
		
	}
	
}
