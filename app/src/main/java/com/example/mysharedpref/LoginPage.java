package com.example.mysharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    EditText txtLoginUser,txtLoginPass;
    TextView txtLoginEmail,txtLoginPhone,txtLoginCourse,txtLoginGender,txtLoginAge;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        SharedPreferences sh = getSharedPreferences("UserData",MODE_PRIVATE);
        String username = sh.getString("username","");
        String password = sh.getString("password","");


        txtLoginUser = findViewById(R.id.txtLoginUser);
        txtLoginPass = findViewById(R.id.txtLoginPass);

        txtLoginEmail = findViewById(R.id.txtLoginEmail);
        txtLoginPhone = findViewById(R.id.txtLoginPhone);
        txtLoginCourse = findViewById(R.id.txtLoginCourse);
        txtLoginGender = findViewById(R.id.txtLoginGender);
        txtLoginAge = findViewById(R.id.txtLoginAge);

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtLoginUser.getText().toString().equals(username) && txtLoginPass.getText().toString().equals(password)){
                    txtLoginEmail.setText(sh.getString("email",""));
                    txtLoginPhone.setText(sh.getString("phone",""));
                    txtLoginCourse.setText(sh.getString("course",""));
                    txtLoginGender.setText(sh.getString("gender",""));
                    txtLoginAge.setText(sh.getString("age",""));
                } else {
                    Toast.makeText(getApplicationContext(),"WRONG CREDENTIALS",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}