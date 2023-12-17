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

public class IdFindActivity extends AppCompatActivity {
    // 선언 및 초기화
    private EditText editName, editBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // XML 레이아웃 파일과 연결
        setContentView(R.layout.activity_id_find);

        // XML에서 정의한 EditText들을 코드와 연결
        editName = findViewById(R.id.editName);
        editBirthday = findViewById(R.id.editBirthday);

        // 아이디 찾기 버튼과의 연결
        Button findUsernameButton = findViewById(R.id.findUsernameButton);
        findUsernameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 아이디 찾기 메소드 호출
                findUsername();
            }
        });
    }

    // 아이디 찾기 메소드
    private void findUsername() {
        // 입력된 이름과 생년월일 가져오기
        String inputName = editName.getText().toString();
        String inputBirthday = editBirthday.getText().toString();

        // SharedPreferences를 통해 사용자 정보 불러오기
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String savedName = preferences.getString("displayName", "");
        String savedBirthday = preferences.getString("birthdate", "");
        String savedUsername = preferences.getString("username", "");

        // 입력된 정보와 저장된 정보 비교
        if (inputName.equals(savedName) && inputBirthday.equals(savedBirthday)) {
            // 아이디 찾기 성공 시 다이얼로그 표시
            showUsernameDialog(savedUsername);
        } else {
            // 아이디 찾기 실패 시 다이얼로그 표시
            showFindFailureDialog();
        }
    }

    // 아이디 찾기 성공 다이얼로그 표시 메소드
    private void showUsernameDialog(String username) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("아이디 찾기 성공");
        builder.setMessage("회원님의 아이디는 " + username + " 입니다.")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 확인 버튼 클릭 시 메인 액티비티로 이동
                        navigateToMainActivity();
                    }
                })
                .show();
    }

    // 아이디 찾기 실패 다이얼로그 표시 메소드
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
        // 현재 액티비티 종료
        finish();
    }
}
