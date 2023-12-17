package com.example.SafetyReturn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Map;

public class BoardActivity extends AppCompatActivity {
    private ArrayList<String> postList;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        postList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, postList);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteDialog(position);
                return true;
            }
        });

        Button moveToWriteButton = findViewById(R.id.moveToWriteButton);
        moveToWriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoardActivity.this, WriteActivity.class);
                startActivity(intent);
            }
        });
        Button moveToMainActivityButton = findViewById(R.id.moveToMainActivityButton);
        moveToMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToMainActivity();
            }
        });
        loadPosts();
    }
    private void moveToMainActivity() {
        Intent intent = new Intent(BoardActivity.this, MainActivity.class);
        startActivity(intent);
        finish();  // 현재 액티비티를 종료하여 뒤로 가기 버튼을 눌렀을 때 이전 화면으로 가도록 함
    }
    private void loadPosts() {
        SharedPreferences preferences = getSharedPreferences("posts", MODE_PRIVATE);
        Map<String, ?> allEntries = preferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().endsWith("_title")) {
                String title = entry.getValue().toString();
                String contentKey = entry.getKey().replace("_title", "_content");
                String content = preferences.getString(contentKey, "");
                postList.add("제목: " + title + "\n내용: " + content);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void showDeleteDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("게시물 삭제");
        builder.setMessage("게시물을 삭제하시겠습니까?");
        builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deletePost(position);
            }
        });
        builder.setNegativeButton("취소", null);
        builder.show();
    }

    private void deletePost(int position) {
        SharedPreferences preferences = getSharedPreferences("posts", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        String title = postList.get(position).split("\n")[0].substring(4); // "제목: " 제거
        for (Map.Entry<String, ?> entry : preferences.getAll().entrySet()) {
            if (entry.getKey().endsWith("_title") && entry.getValue().equals(title)) {
                String postKey = entry.getKey().replace("_title", "");
                editor.remove(postKey + "_title");
                editor.remove(postKey + "_content");
                editor.apply();
                break;
            }
        }
        postList.remove(position);
        adapter.notifyDataSetChanged();
    }
}
