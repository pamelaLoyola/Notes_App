package com.loyola.labcalificado03.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loyola.labcalificado03.R;
import com.loyola.labcalificado03.repositories.NoteRepository;

public class NoteActivity extends AppCompatActivity {

    private EditText title_txt;
    private EditText content_txt;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        title_txt = findViewById(R.id.title_text);
        content_txt = findViewById(R.id.content_text);
        btn_register = findViewById(R.id.btn_add_note);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRegisterNote();
            }
        });
    }

    public void callRegisterNote(){

        try{
            String title = title_txt.getText().toString();
            String content = content_txt.getText().toString();

            if (title.isEmpty() || content.isEmpty()){
                Toast.makeText(this, "Rellenar los campos",Toast.LENGTH_SHORT).show();
                return;
            }

            NoteRepository.create(title,content);

            Toast.makeText(this,"Nota agregada :D",Toast.LENGTH_SHORT).show();

            finish();

        }catch (Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
