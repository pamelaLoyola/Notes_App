package com.loyola.labcalificado03.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loyola.labcalificado03.R;
import com.loyola.labcalificado03.models.User;
import com.loyola.labcalificado03.repositories.UserRepository;

public class LoginActivity extends AppCompatActivity {

    private EditText username_text;
    private EditText password_text;
    private Button btn_login;
    private Button btn_new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_text = (EditText)findViewById(R.id.user_text);
        password_text = (EditText)findViewById(R.id.password_text);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_new = (Button)findViewById(R.id.btn_new);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    public void register(){
        startActivity(new Intent(this,RegisterActivity.class));
    }

    public void login(){
        String username = username_text.getText().toString();
        String password = password_text.getText().toString();

        if (username.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Rellenar los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        User user= UserRepository.Login(username,password);

        if (user == null){

            Toast.makeText(this,"Clave y/o Usuario inválidos",Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        boolean success = sp.edit()
                .putString("username", username)
                .putLong("id",user.getId())
                .putBoolean("isLogged",true)
                .commit();

        goMain();
    }

    private void verifySession() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        if(sp.getBoolean("isLogged", false)){

            goMain();
        }
    }

    private void goMain(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
