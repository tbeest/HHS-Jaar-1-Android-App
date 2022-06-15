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

    EditText userIdET, passwordIdET, validatePasswordIdET;
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
        userIdET = findViewById(R.id.userId);
        String userStr = userIdET.getText().toString();
        passwordIdET = findViewById(R.id.passwordId);
        String passwordStr = passwordIdET.getText().toString();
        validatePasswordIdET = findViewById(R.id.validatePasswordId);
        String validatePasswordStr = validatePasswordIdET.getText().toString();

//        registerId.setOnClickListener(new View.OnClickListener() {
//            @Override
        //public void onClick (View v){

        if (Validate.allFieldsEmpty(userStr, passwordStr, validatePasswordStr)) {
            Toast.makeText(getApplicationContext(), "Field is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Validate.checkIfEqual(passwordStr, validatePasswordStr)) {
            Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
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




/*else if (!user.getPassword().equals(validatePasswordStr)){
        Toast.makeText(getApplicationContext(), "Password does not match!", Toast.LENGTH_SHORT).show();
        System.out.println("kom ik hier?");*/

/*        EditText usernameEt = findViewById(R.id.userId);
        String username = usernameEt.getText().toString();

        EditText passwordEt = findViewById(R.id.passwordId);
        String password = passwordEt.getText().toString();

        EditText validatePasswordEt = findViewById(R.id.validatePasswordId);
        String validatePassword = validatePasswordEt.getText().toString();

        //User user = new User(username, password);

        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);

        if (!password.equals(validatePassword)) {
            Toast.makeText(this, R.string.passwordsNotMatch, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.passwordsDoMatch, Toast.LENGTH_SHORT).show();
        }

        if (password.isEmpty()) {
            Toast.makeText(this, R.string.passwordsDoMatch, Toast.LENGTH_SHORT).show();
        }
    }
}*/
