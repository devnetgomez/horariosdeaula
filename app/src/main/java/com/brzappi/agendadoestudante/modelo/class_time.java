package com.brzappi.agendadoestudante.modelo;

public class class_time {

	private int _id;
    private int idTeacher;
    private String teacherName;
    private int idClass;
    private String className;
    private String timestart;
    private String timeend;
    private int idLocation;
    private String locationName;
    private int weekDay;
    private int timeNumber;
    
   

    public class_time() {
    }

    public class_time(int IdTeacher, int IdClass, String TimeStart, String TimeEnd, int IdLocation, int WeekDay, int TimeNumber ) {
    	this.idTeacher = IdTeacher;
    	this.idClass = IdClass;
    	this.timestart = TimeStart;
    	this.timeend = TimeEnd;
    	this.idLocation = IdLocation;
    }

    public void setIdTeacher(int IdTeacher)
    {
    	this.idTeacher = IdTeacher;
    }
    
    public int getIdTeacher()
    {
    	return this.idTeacher;   	
    }
    
    
    public void setTeacherName(String Name)
    {
    	this.teacherName = Name;
    }
    
    public String getTeacherName()
    {
    	return this.teacherName;   	
    }
    
    public void setIdClass(int IdClass)
    {
    	this.idClass = IdClass;
    }
    
    public int getIdClass()
    {
    	return this.idClass;   	
    }
    
    public void setClassName(String Name)
    {
    	this.className = Name;
    }
    
    public String getClassName()
    {
    	return this.className;   	
    }
    
    
    public void setTimeStart(String TimeStart)
    {
    	this.timestart = TimeStart;
    }
    
    public String getTimeStart()
    {
    	return this.timestart;   	
    }
    
    public void setTimeEnd(String TimeEnd)
    {
    	this.timeend = TimeEnd;
    }
    
    public String getTimeEnd()
    {
    	return this.timeend;   	
    }
    
    public void setIdLocation(int IdLocation)
    {
    	this.idLocation = IdLocation;
    }
    
    public int getIdLocation()
    {
    	return this.idLocation;   	
    }
    
    public void setLocationName(String Name)
    {
    	this.locationName = Name;
    }
    
    public String getLocationName()
    {
    	return this.locationName;   	
    }
}



