package com.example.gr11today;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gr11today.models.User;
import com.example.gr11today.tasks.TaskOverviewActivity;

public class MainActivity extends AppCompatActivity {

    EditText userIdEt, passwordIdEt;
    TextView registerAccount;
    Button signInId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userIdEt = findViewById(R.id.userId);
        passwordIdEt = findViewById(R.id.passwordId);
        registerAccount = findViewById(R.id.registerAccount);
        signInId = findViewById(R.id.signInId);

        registerAccount.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RegisterActivity.class)));
        signInId.setOnClickListener(v -> loginIn(v));
    }

    public void loginIn(View view) {
        userIdEt = findViewById(R.id.userId);
        String userStr = userIdEt.getText().toString();
        passwordIdEt = findViewById(R.id.passwordId);
        String passwordStr = passwordIdEt.getText().toString();


        if (!Validate.validateInputStringNotNullNotEmpty(userStr)) {
            Toast.makeText(this, R.string.emptyUsernameField, Toast.LENGTH_SHORT).show();
        } else if (!Validate.validateInputStringNotNullNotEmpty(passwordStr)) {
            Toast.makeText(this, R.string.emptyPasswordField, Toast.LENGTH_SHORT).show();
            return;
        }

        User userInDb = Database.getDatabase(getApplicationContext()).userDao().getByUsername(userStr);
        if (userInDb == null) {
            Toast.makeText(this, R.string.accountDoesntExist, Toast.LENGTH_SHORT).show();
            return;
        }

        if (userInDb.getUsername().equals(userStr) &&
            userInDb.getPassword().equals(passwordStr)) {
            Intent intent = new Intent(this, TaskOverviewActivity.class);
            startActivity(intent);
        }
    }
}