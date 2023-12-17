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
    // 선언 및 초기화
    private ArrayList<String> postList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // XML 레이아웃 파일과 연결
        setContentView(R.layout.activity_board);

        // ArrayList와 ArrayAdapter 초기화
        postList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, postList);

        // ListView와 Adapter 연결
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // 길게 클릭 시 삭제 다이얼로그 표시
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteDialog(position);
                return true;
            }
        });

        // 글 작성 화면으로 이동하는 버튼과의 연결
        Button moveToWriteButton = findViewById(R.id.moveToWriteButton);
        moveToWriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoardActivity.this, WriteActivity.class);
                startActivity(intent);
            }
        });

        // 메인 화면으로 이동하는 버튼과의 연결
        Button moveToMainActivityButton = findViewById(R.id.moveToMainActivityButton);
        moveToMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToMainActivity();
            }
        });

        // 게시물 정보 불러오기
        loadPosts();
    }

    // 메인 화면으로 이동하는 메소드
    private void moveToMainActivity() {
        Intent intent = new Intent(BoardActivity.this, MainActivity.class);
        startActivity(intent);
        // 현재 액티비티를 종료하여 뒤로 가기 버튼을 눌렀을 때 이전 화면으로 가도록 함
        finish();
    }

    // 게시물 정보 불러오기 메소드
    private void loadPosts() {
        // SharedPreferences를 통해 게시물 정보 가져오기
        SharedPreferences preferences = getSharedPreferences("posts", MODE_PRIVATE);
        Map<String, ?> allEntries = preferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().endsWith("_title")) {
                // 게시물 제목과 내용 가져와서 리스트에 추가
                String title = entry.getValue().toString();
                String contentKey = entry.getKey().replace("_title", "_content");
                String content = preferences.getString(contentKey, "");
                postList.add("제목: " + title + "\n내용: " + content);
            }
        }
        // Adapter에게 데이터 변경을 알리고 갱신
        adapter.notifyDataSetChanged();
    }

    // 삭제 다이얼로그 표시 메소드
    private void showDeleteDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("게시물 삭제");
        builder.setMessage("게시물을 삭제하시겠습니까?");
        builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 게시물 삭제 메소드 호출
                deletePost(position);
            }
        });
        builder.setNegativeButton("취소", null);
        builder.show();
    }

    // 게시물 삭제 메소드
    private void deletePost(int position) {
        // SharedPreferences에서 게시물 정보 삭제
        SharedPreferences preferences = getSharedPreferences("posts", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // 선택된 게시물의 제목 추출
        String title = postList.get(position).split("\n")[0].substring(4); // "제목: " 제거

        // SharedPreferences에서 해당 제목을 가진 게시물 찾아 삭제
        for (Map.Entry<String, ?> entry : preferences.getAll().entrySet()) {
            if (entry.getKey().endsWith("_title") && entry.getValue().equals(title)) {
                String postKey = entry.getKey().replace("_title", "");
                editor.remove(postKey + "_title");
                editor.remove(postKey + "_content");
                editor.apply();
                break;
            }
        }

        // 리스트에서 선택된 게시물 삭제하고 Adapter에게 변경을 알리고 갱신
        postList.remove(position);
        adapter.notifyDataSetChanged();
    }
}
