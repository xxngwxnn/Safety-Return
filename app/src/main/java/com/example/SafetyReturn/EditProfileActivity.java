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

    // 선언 및 초기화
    private EditText editPassword;
    private TextView displayNameTextView, birthdateTextView, usernameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // XML 레이아웃 파일과 연결
        setContentView(R.layout.activity_edit_profile);

        // XML에서 정의한 위젯들을 코드와 연결
        editPassword = findViewById(R.id.editPassword);
        displayNameTextView = findViewById(R.id.displayNameTextView);
        birthdateTextView = findViewById(R.id.birthdateTextView);
        usernameTextView = findViewById(R.id.usernameTextView);

        // SharedPreferences를 통해 사용자 정보 가져오기
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);

        // 사용자 정보 가져와서 화면에 표시
        String displayName = preferences.getString("displayName", "");
        String birthdate = preferences.getString("birthdate", "");
        String username = preferences.getString("username", "");

        displayNameTextView.setText("이름: " + displayName);
        birthdateTextView.setText("생년월일: " + birthdate);
        usernameTextView.setText("아이디: " + username);

        // 저장 버튼과의 연결
        Button saveChangesButton = findViewById(R.id.saveChangesButton);
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 변경사항 저장 메소드 호출
                saveChanges();
            }
        });
    }

    // 변경사항 저장 메소드
    private void saveChanges() {
        // 입력한 새로운 비밀번호 가져오기
        String newPassword = editPassword.getText().toString();

        // SharedPreferences를 통해 사용자 정보 저장
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("password", newPassword);
        editor.apply();

        // 사용자에게 저장 완료 메시지 표시
        Toast.makeText(this, "변경이 저장되었습니다.", Toast.LENGTH_SHORT).show();

        // 현재 액티비티 종료
        finish();
    }
}
