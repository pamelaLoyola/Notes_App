package com.loyola.labcalificado03.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.loyola.labcalificado03.R;
import com.loyola.labcalificado03.adapters.NoteAdapter;
import com.loyola.labcalificado03.models.Note;
import com.loyola.labcalificado03.models.User;
import com.loyola.labcalificado03.repositories.NoteRepository;
import com.loyola.labcalificado03.repositories.UserRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REGISTER_FORM_REQUEST = 100;
    private RecyclerView notesList;
    private TextView fullname;
    private FloatingActionButton btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullname = findViewById(R.id.message_txt);
        btn_add = findViewById(R.id.btn_float_add);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        Long id = sp.getLong("id",0);

        User user = UserRepository.Load(id);

        if (user == null){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
            return;
        }

        fullname.setText("Bienvenido "+user.getUsername());

        notesList = (RecyclerView) findViewById(R.id.notes_list);
        notesList.setLayoutManager(new LinearLayoutManager(this));

        List<Note> notes = NoteRepository.list();
        notesList.setAdapter(new NoteAdapter(notes));


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRegisterNote();
            }
        });

    }

    public void callRegisterNote(){
        startActivityForResult(new Intent(this,NoteActivity.class),REGISTER_FORM_REQUEST);
    }

    // return from NoteRegisterActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // refresh data
        NoteAdapter adapter = (NoteAdapter)notesList.getAdapter();

        List<Note> notes = NoteRepository.list();
        adapter.setNotes(notes);
        adapter.notifyDataSetChanged();

    }
}
