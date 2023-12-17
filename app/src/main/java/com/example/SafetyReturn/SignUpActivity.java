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

        // 사용자 입력값을 받을 EditText 위젯 초기화
        editName = findViewById(R.id.editName);
        editBirthday = findViewById(R.id.editBirthday);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);

        // 회원가입 버튼에 OnClickListener 설정
        Button signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원가입 메소드 호출
                signUp();
            }
        });
    }

    // 회원가입 메소드
    private void signUp() {
        // 미리 정의된 사용자명과 비밀번호
        String hardcodedUsername = "testuser";
        String hardcodedPassword = "testpassword";

        // EditText에서 사용자 입력값을 가져옴
        String displayName = editName.getText().toString();
        String birthdate = editBirthday.getText().toString();
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();

        // 사용자 정보를 저장하는 메소드 호출
        saveUserInfo(displayName, birthdate, username, password);

        // 이미 존재하는 사용자명인 경우 안내 메시지 출력
        if (username.equals(hardcodedUsername)) {
            Toast.makeText(this, "이미 존재하는 사용자명입니다.", Toast.LENGTH_SHORT).show();
        } else {
            // 회원가입 성공 다이얼로그 표시
            showSuccessDialog();
        }
    }

    // 회원가입 성공 다이얼로그 표시 메소드
    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("회원가입 성공");
        builder.setMessage("회원가입이 성공적으로 완료되었습니다.")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 확인 버튼 클릭 시 메인 액티비티로 이동
                        navigateToMainActivity();
                    }
                })
                .show();
    }

    // 메인 액티비티로 이동하는 메소드
    private void navigateToMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }

    // 사용자 정보를 SharedPreferences에 저장하는 메소드
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
