package com.udinus.uas_ppb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        editTextEmail = findViewById(R.id.edt_email);
        editTextPassword = findViewById(R.id.edt_password);
        editTextUsername = findViewById(R.id.edt_username);
    }

    public void clickRegister(View view) {
        if (TextUtils.isEmpty(editTextEmail.getText().toString().trim())
                &&
                TextUtils.isEmpty(editTextUsername.getText().toString().trim())
                &&
                TextUtils.isEmpty(editTextPassword.getText().toString().trim())) {
            Toast.makeText(view.getContext(), "Email, Username, and Password cannot be empty!", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(editTextEmail.getText().toString().trim())) {
            Toast.makeText(view.getContext(), "Email cannot be empty!", Toast.LENGTH_LONG).show();
        }
        else {
            if (!isValidEmail(editTextEmail.getText().toString().trim())) {
                Toast.makeText(view.getContext(), "Invlid Email!", Toast.LENGTH_LONG).show();
            }
            else if (TextUtils.isEmpty(editTextEmail.getText().toString().trim())) {
                Toast.makeText(view.getContext(), "Username cannot be empty!", Toast.LENGTH_LONG).show();
            }
            else if (TextUtils.isEmpty(editTextPassword.getText().toString())) {
                Toast.makeText(view.getContext(), "Password cannot be empty!", Toast.LENGTH_LONG).show();
            } else {
                Intent i = new Intent(Register.this, Success.class);
                startActivity(i);
            }
        }
    }

    public static boolean isValidEmail(CharSequence email){
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}