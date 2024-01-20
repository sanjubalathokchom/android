package com.example.mysharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegistrationPage extends AppCompatActivity {

    EditText txtUsername, txtPassword, txtEmail, txtPhone, txtCourse, txtAge;
    RadioGroup rdGrp;
    Button registerButton;
    String gender="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtCourse = findViewById(R.id.txtCourse);
        txtAge = findViewById(R.id.txtAge);
        rdGrp = findViewById(R.id.rdGrp);
        registerButton = findViewById(R.id.btnRegister);

        rdGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rdMale: gender="Male";break;
                    case R.id.rdFemale: gender="Female";break;
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String email = txtEmail.getText().toString().trim();
                String phone = txtPhone.getText().toString().trim();
                String course = txtCourse.getText().toString().trim();
                String age = txtAge.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty() || course.isEmpty() || age.isEmpty() || gender.isEmpty()) {
                    Toast.makeText(RegistrationPage.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6 || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*\\d.*")) {
                    Toast.makeText(RegistrationPage.this, "Password must contain at least 6 characters including upper and lower case letters and a number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (phone.length() != 10) {
                    Toast.makeText(RegistrationPage.this, "Phone number must be 10 digits", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() || !email.matches(".*@xyz\\.(COM|EDU|IN|ORG).*")) {
                    Toast.makeText(RegistrationPage.this, "Invalid email format", Toast.LENGTH_SHORT).show();
                    return;
                }


                SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("username", username);
                editor.putString("password", password);
                editor.putString("course", course);
                editor.putString("gender", gender);
                editor.putString("email", email);
                editor.putString("phone", phone);
                editor.putString("age", age);
                editor.apply();

                Toast.makeText(RegistrationPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
