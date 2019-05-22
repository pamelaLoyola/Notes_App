package com.loyola.labcalificado03.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.loyola.labcalificado03.R;
import com.loyola.labcalificado03.models.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Note> notes;

    public NoteAdapter(List<Note> notes){
        this.notes = notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView content;
        public TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_text);
            content = (TextView) itemView.findViewById(R.id.content_text);
            date = (TextView) itemView.findViewById(R.id.date_text);
        }
    }

    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteAdapter.ViewHolder viewHolder, int position) {
        Note note = this.notes.get(position);
        Date date_new = new Date();
        date_new.setTime(note.getDate());
        String d= new SimpleDateFormat("dd/MM/yyyy").format(date_new);

        viewHolder.title.setText(note.getTitle());
        viewHolder.content.setText(note.getContent());
        viewHolder.date.setText(d);
    }

    @Override
    public int getItemCount() {
        return this.notes.size();
    }

}
