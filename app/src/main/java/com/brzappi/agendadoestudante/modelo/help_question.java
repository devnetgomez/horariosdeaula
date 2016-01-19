package com.brzappi.agendadoestudante.modelo;

import java.util.ArrayList;

public class help_question {
    private String mTitle;
    
    private ArrayList<String> AnswerList;
 
    public String getTitle() {
        return mTitle;
    }
 
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
 
    public ArrayList<String> getAnswerList() {
        return AnswerList;
    }
 
    public void setAnswerList(ArrayList<String> answer) {
        this.AnswerList = answer;
    }
}