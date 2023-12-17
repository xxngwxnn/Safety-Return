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

public class LoginActivity extends AppCompatActivity {

    // 선언 및 초기화
    private EditText editUsername, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // XML 레이아웃 파일과 연결
        setContentView(R.layout.activity_login);

        // XML에서 정의한 EditText들을 코드와 연결
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);

        // 버튼과의 연결
        Button loginButton = findViewById(R.id.loginButton);
        Button idFindButton = findViewById(R.id.idfindButton);
        Button pwFindButton = findViewById(R.id.pwfindButton);

        // 로그인 버튼 클릭 이벤트
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인 메소드 호출
                login();
            }
        });

        // 아이디 찾기 화면으로 이동하는 버튼 클릭 이벤트
        idFindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, IdFindActivity.class);
                startActivity(intent);
            }
        });

        // 비밀번호 찾기 화면으로 이동하는 버튼 클릭 이벤트
        pwFindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, PasswordFindActivity.class);
                startActivity(intent);
            }
        });
    }

    // 로그인 메소드
    private void login() {
        // 입력된 아이디와 비밀번호 가져오기
        String inputUsername = editUsername.getText().toString();
        String inputPassword = editPassword.getText().toString();

        // SharedPreferences를 통해 저장된 아이디와 비밀번호 가져오기
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String savedUsername = preferences.getString("username", "");
        String savedPassword = preferences.getString("password", "");
        SharedPreferences.Editor editor = preferences.edit();

        // 입력된 아이디와 비밀번호가 저장된 정보와 일치하는지 확인
        if (inputUsername.equals(savedUsername) && inputPassword.equals(savedPassword)) {
            // 로그인 성공 시 성공 다이얼로그 표시
            showSuccessDialog();
            // 로그인 상태를 저장하여 앱 재시작 시 로그인 상태를 유지
            editor.putBoolean("isLoggedIn", true);
            editor.apply();
        } else {
            // 로그인 실패 시 실패 다이얼로그 표시
            showFailureDialog();
        }
    }

    // 로그인 성공 다이얼로그 표시 메소드
    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("로그인 성공");
        builder.setMessage("로그인이 성공적으로 완료되었습니다.")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 메인 액티비티로 이동
                        navigateToMainActivity();
                    }
                })
                .show();
    }

    // 로그인 실패 다이얼로그 표시 메소드
    private void showFailureDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("로그인 실패");
        builder.setMessage("아이디 또는 비밀번호가 올바르지 않습니다.")
                .setNegativeButton("확인", null)
                .show();
    }

    // 메인 액티비티로 이동하는 메소드
    private void navigateToMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        // 현재 액티비티 종료
        finish();
    }
}
