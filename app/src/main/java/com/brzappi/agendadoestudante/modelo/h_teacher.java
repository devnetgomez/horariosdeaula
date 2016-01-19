package com.brzappi.agendadoestudante.modelo;

public class h_teacher {

    private String name;
    private int _id;
   

    public h_teacher() {
    }

    public h_teacher(int Id, String Name){
    	this._id = Id;
    	this.name = Name;
    }

    public void setId(int Id)
    {
    	this._id = Id;
    }
    
    public int getId()
    {
    	return this._id;   	
    }
    
    public void setName(String Name)
    {
    	this.name = Name;
    }
    
    public String getName()
    {
    	return this.name;   	
    }
}