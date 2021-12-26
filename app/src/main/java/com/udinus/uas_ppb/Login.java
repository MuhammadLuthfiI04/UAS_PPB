package com.udinus.uas_ppb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextPassword;

    private CheckBox chkKeepLogin;

    private SharedPreferences sharedPrefs;

    private static final String KEEP_LOGIN_KEY = "key_keep_login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        editTextEmail = findViewById(R.id.edt_email);
        editTextPassword = findViewById(R.id.edt_password);

        this.sharedPrefs = this.getSharedPreferences("uas_ppb_sharedprefs", Context.MODE_PRIVATE);

        this.initComponents();

        this.autoLogin();
    }

    private void initComponents()
    {
        // Init components
        this.editTextEmail = this.findViewById(R.id.edt_email);
        this.editTextPassword = this.findViewById(R.id.edt_password);
        this.chkKeepLogin = this.findViewById(R.id.chk_keep_login);
    }
    public void clickLogin(View view) {
        if(TextUtils.isEmpty(editTextEmail.getText().toString().trim())
                &&
                TextUtils.isEmpty(editTextPassword.getText().toString().trim())){
            Toast.makeText(view.getContext(), "Email and Password cannot be empty!",Toast.LENGTH_LONG).show();
        }

        else if (TextUtils.isEmpty(editTextEmail.getText().toString().trim())){
            Toast.makeText(view.getContext(),"Email cannot be empty!", Toast.LENGTH_LONG).show();
        }

        else {
            if (!isValidEmail(editTextEmail.getText().toString().trim())) {
                Toast.makeText(view.getContext(), "Invalid Email!", Toast.LENGTH_LONG).show();
            }

            else if (TextUtils.isEmpty(editTextPassword.getText().toString())) {
                Toast.makeText(view.getContext(), "Password cannot be empty!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(view.getContext(), "Login success", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Login.this, Home.class);
                startActivity(i);

                this.makeAutoLogin();
            }
        }
    }

    private void makeAutoLogin()
    {
        SharedPreferences.Editor editor = this.sharedPrefs.edit();

        if (this.chkKeepLogin.isChecked())
        {
            editor.putBoolean(KEEP_LOGIN_KEY, true);
        }
        else
        {
            editor.remove(KEEP_LOGIN_KEY);
        }

        editor.apply();
    }

    private void autoLogin()
    {

        boolean keepLogin = this.sharedPrefs.getBoolean(KEEP_LOGIN_KEY, false);

        if(keepLogin == true)
        {
            this.chkKeepLogin.setChecked(true);
            Intent i = new Intent(Login.this, Home.class);
            startActivity(i);
        }
    }

    public void clickRegister(View view) {
        Intent i = new Intent(Login.this, Register.class);
        startActivity(i);
    }

    public void clickForgot(View view) {
        Intent i = new Intent(Login.this, ForgotPassword.class);
        startActivity(i);
    }

    public static boolean isValidEmail(CharSequence email){
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}