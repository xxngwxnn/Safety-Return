package com.example.SafetyReturn;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private EditText editName, editBirthday, editUsername, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editName = findViewById(R.id.editName);
        editBirthday = findViewById(R.id.editBirthday);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        Button signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp() {
        String hardcodedUsername = "testuser";
        String hardcodedPassword = "testpassword";
        String displayName = editName.getText().toString();
        String birthdate = editBirthday.getText().toString();
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        saveUserInfo(displayName, birthdate, username, password);

        if (username.equals(hardcodedUsername)) {
            Toast.makeText(this, "이미 존재하는 사용자명입니다.", Toast.LENGTH_SHORT).show();
        } else {
            showSuccessDialog();
        }
    }
    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("회원가입 성공");
        builder.setMessage("회원가입이 성공적으로 완료되었습니다.")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        navigateToMainActivity();
                    }
                })
                .show();
    }
    private void navigateToMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }

    private void saveUserInfo(String displayName, String birthdate, String username, String password) {
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("displayName", displayName);
        editor.putString("birthdate", birthdate);
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }
}
