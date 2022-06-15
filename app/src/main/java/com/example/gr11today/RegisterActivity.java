package com.example.gr11today;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gr11today.daos.UserDao;
import com.example.gr11today.models.User;

public class RegisterActivity extends AppCompatActivity {

    EditText userIdEt, passwordIdEt, validatePasswordIdEt;
    TextView loginScreenId;
    Button registerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerId = findViewById(R.id.registerId);
        loginScreenId = findViewById(R.id.loginScreenId);

        loginScreenId.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, MainActivity.class)));

    }

    public void register(View view) {
        userIdEt = findViewById(R.id.userId);
        String userStr = userIdEt.getText().toString();
        passwordIdEt = findViewById(R.id.passwordId);
        String passwordStr = passwordIdEt.getText().toString();
        validatePasswordIdEt = findViewById(R.id.validatePasswordId);
        String validatePasswordStr = validatePasswordIdEt.getText().toString();

        if (Validate.allFieldsEmpty(userStr, passwordStr, validatePasswordStr)) {
            Toast.makeText(getApplicationContext(), "Field is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Validate.checkIfEqual(passwordStr, validatePasswordStr)) {
            Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return;
        }
        User userInDb = Database.getDatabase(getApplicationContext()).userDao().getByUsername(userStr);
        if (userInDb != null) {
            Toast.makeText(getApplicationContext(), "Username already exists!", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User();
        user.setUsername(userStr);
        user.setPassword(passwordStr);
        Database database = Database.getDatabase(getApplicationContext());
        final UserDao userDao = database.userDao();
        new Thread(() -> {
            userDao.registerUser(user);
            runOnUiThread(() -> Toast.makeText(getApplicationContext(), "User registered!", Toast.LENGTH_SHORT).show());
        }).start();
    }
}