package com.example.SafetyReturn;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editPassword;
    private TextView displayNameTextView, birthdateTextView, usernameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editPassword = findViewById(R.id.editPassword);
        displayNameTextView = findViewById(R.id.displayNameTextView);
        birthdateTextView = findViewById(R.id.birthdateTextView);
        usernameTextView = findViewById(R.id.usernameTextView);

        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);

        String displayName = preferences.getString("displayName", "");
        String birthdate = preferences.getString("birthdate", "");
        String username = preferences.getString("username", "");


        displayNameTextView.setText("이름: " + displayName);
        birthdateTextView.setText("생년월일: " + birthdate);
        usernameTextView.setText("아이디: " + username);

        Button saveChangesButton = findViewById(R.id.saveChangesButton);
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });
    }

    private void saveChanges() {
        String newPassword = editPassword.getText().toString();

        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("password", newPassword);
        editor.apply();

        Toast.makeText(this, "변경이 저장되었습니다.", Toast.LENGTH_SHORT).show();

        finish();
    }
}
