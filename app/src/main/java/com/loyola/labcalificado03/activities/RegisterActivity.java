package com.loyola.labcalificado03.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loyola.labcalificado03.R;
import com.loyola.labcalificado03.repositories.UserRepository;

public class RegisterActivity extends AppCompatActivity {

    private EditText fullname_input;
    private EditText username_input;
    private EditText email_input;
    private EditText password_input;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullname_input = (EditText)findViewById(R.id.fullname_input);
        username_input = (EditText)findViewById(R.id.user_input);
        email_input = (EditText)findViewById(R.id.email_input);
        password_input = (EditText)findViewById(R.id.password_input);
        btn_register = (Button)findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    public void register(){
        try{
            String fullname = fullname_input.getText().toString();
            String username = username_input.getText().toString();
            String email = email_input.getText().toString();
            String password = password_input.getText().toString();

            if(fullname.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            UserRepository.create(fullname,username,email,password);

            Toast.makeText(this,"Registro exitoso :D",Toast.LENGTH_SHORT).show();

            finish();

        }catch (Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
