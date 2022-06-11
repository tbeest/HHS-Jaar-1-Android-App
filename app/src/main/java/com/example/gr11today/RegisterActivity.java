package com.example.gr11today;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText userId, passwordId, validatePasswordId;
    TextView loginScreenId;
    Button registerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userId = findViewById(R.id.userId);
        passwordId = findViewById(R.id.passwordId);
        validatePasswordId = findViewById(R.id.validatePasswordId);
        registerId = findViewById(R.id.registerId);
        loginScreenId = findViewById(R.id.loginScreenId);

//        registerId.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //creating user entity
//                final UserEntity userEntity = new UserEntity();
//                userEntity.setUserId(userId.getText().toString());
//                userEntity.setPasswordId(passwordId.getText().toString());
//                userEntity.setValidatePasswordId(validatePasswordId.getText().toString());
//
//            }
//        }
        loginScreenId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        }
    }
}