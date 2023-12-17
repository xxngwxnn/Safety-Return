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

        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);

        Button postButton = findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePost();
            }
        });
    }

    private void savePost() {
        String title = editTitle.getText().toString();
        String content = editContent.getText().toString();
        SharedPreferences preferences = getSharedPreferences("posts", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String postKey = String.valueOf(System.currentTimeMillis());
        editor.putString(postKey + "_title", title);
        editor.putString(postKey + "_content", content);
        editor.apply();
        Toast.makeText(this, "게시 완료\n제목: " + title + "\n내용: " + content, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(WriteActivity.this, BoardActivity.class);
        startActivity(intent);
    }
}
