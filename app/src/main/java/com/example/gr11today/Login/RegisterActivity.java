package com.example.gr11today.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gr11today.Database;
import com.example.gr11today.R;
import com.example.gr11today.Validate;
import com.example.gr11today.daos.UserDao;
import com.example.gr11today.models.User;
import com.example.gr11today.tasks.TaskOverviewActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText userIdEt, passwordIdEt, validatePasswordIdEt;
    private TextView loginScreenId;
    private Button registerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerId = findViewById(R.id.registerId);
        loginScreenId = findViewById(R.id.loginScreenId);

        loginScreenId.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }

    @Override
    public void onBackPressed() {
    }

    public void register(View view) {
        userIdEt = findViewById(R.id.userId);
        String userStr = userIdEt.getText().toString();
        passwordIdEt = findViewById(R.id.passwordId);
        String passwordStr = passwordIdEt.getText().toString();
        validatePasswordIdEt = findViewById(R.id.validatePasswordId);
        String validatePasswordStr = validatePasswordIdEt.getText().toString();

        if (!Validate.validateInputStringNotNullNotEmpty(userStr)) {
            Toast.makeText(this, R.string.emptyUsernameField, Toast.LENGTH_SHORT).show();
            return;
        } else if (!Validate.validateInputStringNotNullNotEmpty(passwordStr)) {
            Toast.makeText(this, R.string.emptyPasswordField, Toast.LENGTH_SHORT).show();
            return;
        } else if (!Validate.validateInputStringNotNullNotEmpty(validatePasswordStr)) {
            Toast.makeText(this, R.string.emptyValidatePasswordField, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Validate.checkRegistrationLengthField(userStr)) {
            Toast.makeText(this, R.string.fillInCorrectLengthUsername, Toast.LENGTH_SHORT).show();
            return;
        } else if (!Validate.checkRegistrationLengthField(passwordStr)) {
            Toast.makeText(this, R.string.fillInCorrectLengthPassword, Toast.LENGTH_SHORT).show();
            return;
        } else if (!Validate.checkRegistrationLengthField(validatePasswordStr)) {
            Toast.makeText(this, R.string.passwordsNotMatch, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Validate.checkIfEqual(passwordStr, validatePasswordStr)) {
            Toast.makeText(this, R.string.passwordsNotMatch, Toast.LENGTH_SHORT).show();
            return;
        }
        User userInDb = Database.getDatabase(getApplicationContext()).userDao().getByUsername(userStr);
        if (userInDb != null) {
            Toast.makeText(this, R.string.accountAlreadyExists, Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(userStr, passwordStr);;
        User.addUser(user, this);
        User.setActiveUser(Database.getDatabase(getApplicationContext()).userDao().getByUsername(userStr));

        Toast.makeText(this, R.string.fieldsFilledInCorrectly, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, TaskOverviewActivity.class));
    }
}