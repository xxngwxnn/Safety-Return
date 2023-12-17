package com.example.SafetyReturn;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PasswordFindActivity extends AppCompatActivity {
    private EditText editUsername, editName, editBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_find);

        editUsername = findViewById(R.id.editUsername);
        editName = findViewById(R.id.editName);
        editBirthday = findViewById(R.id.editBirthday);

        Button findPasswordButton = findViewById(R.id.findPasswordButton);
        findPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPassword();
            }
        });
    }

    private void findPassword() {
        String inputUsername = editUsername.getText().toString();
        String inputName = editName.getText().toString();
        String inputBirthday = editBirthday.getText().toString();

        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String savedUsername = preferences.getString("username", "");
        String savedName = preferences.getString("displayName", "");
        String savedBirthday = preferences.getString("birthdate", "");
        String savedPassword = preferences.getString("password", "");

        if (inputUsername.equals(savedUsername) && inputName.equals(savedName) && inputBirthday.equals(savedBirthday)) {
            showPasswordDialog(savedPassword);
        } else {
            showFindFailureDialog();
        }
    }
    private void showPasswordDialog(String password) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("비밀번호 찾기 성공");
        builder.setMessage("회원님의 비밀번호는 " + password + " 입니다.")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        navigateToMainActivity();
                    }
                })
                .show();
    }
    private void showFindFailureDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("실패");
        builder.setMessage("해당 정보가 존재하지 않습니다.")
                .setNegativeButton("확인", null)
                .show();
    }
    private void navigateToMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }

}