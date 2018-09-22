package com.exampleapp.to_do;

public class NoteData  {


    private String titleText;
    private String noteText;

    public NoteData(){


    }

    public NoteData(String title,String note){

        titleText=title;
        noteText=note;

    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }


}
