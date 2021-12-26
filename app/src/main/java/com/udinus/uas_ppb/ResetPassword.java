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

public class ResetPassword extends AppCompatActivity {

    EditText editTextCode;
    EditText editTextNewPassword;
    EditText editTextConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        editTextCode = findViewById(R.id.edt_code);
        editTextNewPassword = findViewById(R.id.edt_new_password);
        editTextConfirmPassword = findViewById(R.id.edt_confirm_password);
    }

    public void clickConfirm(View view) {
        if (TextUtils.isEmpty(editTextCode.getText().toString().trim())){
            Toast.makeText(view.getContext(),"Code cannot be empty!", Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(editTextNewPassword.getText().toString().trim())
                &&
                TextUtils.isEmpty(editTextConfirmPassword.getText().toString().trim())){
            Toast.makeText(view.getContext(), "New Password and Confirm Password cannot be empty!",Toast.LENGTH_LONG).show();
        }
        else{
            if (!editTextNewPassword.getText().toString().trim().equals(editTextConfirmPassword.getText().toString().trim())) {
                Toast.makeText(view.getContext(), "Password does not match!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(view.getContext(), "Successfully changed Password!", Toast.LENGTH_LONG).show();
                Intent i = new Intent(ResetPassword.this, Login.class);
                startActivity(i);
            }
        }
    }
}