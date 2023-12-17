package com.example.SafetyReturn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WriteActivity extends AppCompatActivity {

    private EditText editTitle, editContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        // XML 레이아웃에서 EditText 위젯 초기화
        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);

        // 게시 버튼에 OnClickListener 설정
        Button postButton = findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 게시 메소드 호출
                savePost();
            }
        });
    }

    // 게시글을 저장하는 메소드
    private void savePost() {
        // EditText에서 제목과 내용을 가져옴
        String title = editTitle.getText().toString();
        String content = editContent.getText().toString();

        // SharedPreferences를 사용하여 게시글 정보 저장
        SharedPreferences preferences = getSharedPreferences("posts", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        // 현재 시간을 사용하여 고유한 키 생성
        String postKey = String.valueOf(System.currentTimeMillis());
        editor.putString(postKey + "_title", title);
        editor.putString(postKey + "_content", content);
        editor.apply();

        // 저장 완료 메시지를 토스트로 표시
        Toast.makeText(this, "게시 완료\n제목: " + title + "\n내용: " + content, Toast.LENGTH_SHORT).show();

        // 게시글 작성 후 게시판 액티비티로 이동
        Intent intent = new Intent(WriteActivity.this, BoardActivity.class);
        startActivity(intent);
    }
}
