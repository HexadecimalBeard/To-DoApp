package com.exampleapp.to_do;

public class NoteData  {


    private String titleText;
    private String noteText;
    private String timeText;



    public NoteData(){


    }

    public NoteData(String title,String note,String timeText){

        this.titleText=title;
        this.noteText=note;
        this.timeText=timeText;

    }

    public String getTimeText() {
        return timeText;
    }

    public void setTimeText(String timeText) {
        this.timeText = timeText;
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
