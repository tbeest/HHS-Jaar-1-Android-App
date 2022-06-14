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

        loginScreenId.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, MainActivity.class)));

    }

    public void register(View view) {
        registerId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUsername(userId.getText().toString());
                user.setPassword(passwordId.getText().toString());
                if (validateInput(user)) {
                    Database database = Database.getDatabase(getApplicationContext());
//                    final UserDao userDao = database.
                } else {
                    Toast.makeText(getApplicationContext(), "Fill in all fields!", Toast.LENGTH_SHORT).show();
                }
            }

            private Boolean validateInput(User user) {
                return !user.getUsername().isEmpty() &&
                        !user.getPassword().isEmpty();
            }
        });
    }
}


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
