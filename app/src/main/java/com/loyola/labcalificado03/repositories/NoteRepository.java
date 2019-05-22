package com.loyola.labcalificado03.repositories;

import com.loyola.labcalificado03.models.Note;
import com.loyola.labcalificado03.models.User;
import com.orm.SugarRecord;

import java.util.Date;
import java.util.List;

public class NoteRepository {

    public static List<Note> list(){
        List<Note> notes= SugarRecord.listAll(Note.class);
        return notes;
    }

    public static Note read(Long id){
        Note note = SugarRecord.findById(Note.class,id);
        return note;
    }

    public static void create(String title, String content){
        Note note = new Note();
        Date fecha = new Date();

        Long date = fecha.getTime();

        note.setTitle(title);
        note.setContent(content);
        note.setDate(date);
        SugarRecord.save(note);
    }
}
