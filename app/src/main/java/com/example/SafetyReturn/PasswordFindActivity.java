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

        // 사용자 입력값을 받을 EditText 위젯 초기화
        editUsername = findViewById(R.id.editUsername);
        editName = findViewById(R.id.editName);
        editBirthday = findViewById(R.id.editBirthday);

        // 비밀번호 찾기 버튼에 OnClickListener 설정
        Button findPasswordButton = findViewById(R.id.findPasswordButton);
        findPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 비밀번호 찾기 메소드 호출
                findPassword();
            }
        });
    }

    // 비밀번호 찾기 메소드
    private void findPassword() {
        // EditText에서 사용자 입력값을 가져옴
        String inputUsername = editUsername.getText().toString();
        String inputName = editName.getText().toString();
        String inputBirthday = editBirthday.getText().toString();

        // SharedPreferences에서 저장된 사용자 정보를 가져옴
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String savedUsername = preferences.getString("username", "");
        String savedName = preferences.getString("displayName", "");
        String savedBirthday = preferences.getString("birthdate", "");
        String savedPassword = preferences.getString("password", "");

        // 입력값과 저장된 값 비교
        if (inputUsername.equals(savedUsername) && inputName.equals(savedName) && inputBirthday.equals(savedBirthday)) {
            // 일치할 경우 비밀번호를 포함한 다이얼로그 표시
            showPasswordDialog(savedPassword);
        } else {
            // 불일치할 경우 실패 다이얼로그 표시
            showFindFailureDialog();
        }
    }

    // 비밀번호 표시 다이얼로그 메소드
    private void showPasswordDialog(String password) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("비밀번호 찾기 성공");
        builder.setMessage("회원님의 비밀번호는 " + password + " 입니다.")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 확인 버튼 클릭 시 메인 액티비티로 이동
                        navigateToMainActivity();
                    }
                })
                .show();
    }

    // 실패 다이얼로그 표시 메소드
    private void showFindFailureDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("실패");
        builder.setMessage("해당 정보가 존재하지 않습니다.")
                .setNegativeButton("확인", null)
                .show();
    }

    // 메인 액티비티로 이동하는 메소드
    private void navigateToMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
