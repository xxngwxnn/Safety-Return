package com.example.SafetyReturn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView loginStatusTextView; // 로그인 상태를 나타내는 텍스트 뷰
    private Button moveToMapButton; // 지도로 이동하는 버튼
    private Button moveToTimerButton; // 타이머로 이동하는 버튼
    private Button moveToBoardButton; // 게시판으로 이동하는 버튼
    private Button loginButton; // 로그인 버튼
    private Button signUpButton; // 회원가입 버튼
    private Button editProfileButton; // 프로필 수정 버튼
    private Button logoutButton; // 로그아웃 버튼 추가

    private static final int LOGIN_REQUEST_CODE = 123; // 로그인 요청 코드

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginStatusTextView = findViewById(R.id.loginStatusTextView);
        moveToMapButton = findViewById(R.id.moveToMapButton);
        moveToTimerButton = findViewById(R.id.moveToTimerButton);
        moveToBoardButton = findViewById(R.id.moveToBoardButton);
        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signUpButton);
        editProfileButton = findViewById(R.id.editProfileButton);
        logoutButton = findViewById(R.id.logoutButton); // 로그아웃 버튼 추가

        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);
        updateLoginStatus(isLoggedIn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인 액티비티로 이동
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent, LOGIN_REQUEST_CODE);
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원가입 액티비티로 이동
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 프로필 수정 액티비티로 이동
                Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });
        moveToMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoggedIn) {
                    // 로그인 상태일 때 지도 액티비티로 이동
                    Intent intent = new Intent(MainActivity.this, MapActivity.class);
                    startActivity(intent);
                } else {
                    // 로그인 상태가 아닐 때 로그인 페이지로 안내하는 다이얼로그 표시
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("로그인 되어 있지 않음");
                    builder.setMessage("로그인 페이지로 이동하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                                    startActivity(loginIntent);
                                }
                            })
                            .setNegativeButton("취소", null)
                            .show();
                }
            }
        });
        moveToTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoggedIn) {
                    // 로그인 상태일 때 타이머 액티비티로 이동
                    Intent intent = new Intent(MainActivity.this, TimerActivity.class);
                    startActivity(intent);
                } else {
                    // 로그인 상태가 아닐 때 로그인 페이지로 안내하는 다이얼로그 표시
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("로그인 되어 있지 않음");
                    builder.setMessage("로그인 페이지로 이동하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                                    startActivity(loginIntent);
                                }
                            })
                            .setNegativeButton("취소", null)
                            .show();
                }
            }
        });
        moveToBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoggedIn) {
                    // 로그인 상태일 때 게시판 액티비티로 이동
                    Intent intent = new Intent(MainActivity.this, BoardActivity.class);
                    startActivity(intent);
                } else {
                    // 로그인 상태가 아닐 때 로그인 페이지로 안내하는 다이얼로그 표시
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("로그인 되어 있지 않음");
                    builder.setMessage("로그인 페이지로 이동하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                                    startActivity(loginIntent);
                                }
                            })
                            .setNegativeButton("취소", null)
                            .show();
                }
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그아웃 메소드 호출
                logout();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Main", "onResume");
        // SharedPreferences에서 로그인 상태를 가져와 UI 업데이트
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);
        updateLoginStatus(isLoggedIn);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE && resultCode == RESULT_OK) {
            // 로그인 액티비티에서 돌아왔을 때 로그인 상태 업데이트
            SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
            boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);
            updateLoginStatus(isLoggedIn);
        }
    }

    // 로그인 상태에 따라 UI 업데이트하는 메소드
    private void updateLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            loginStatusTextView.setText(" ");
            loginButton.setVisibility(View.GONE);
            signUpButton.setVisibility(View.GONE);
            editProfileButton.setVisibility(View.VISIBLE);
            logoutButton.setVisibility(View.VISIBLE);
            moveToMapButton.setEnabled(true);
            moveToTimerButton.setEnabled(true);
            moveToBoardButton.setEnabled(true);
        } else {
            loginStatusTextView.setText("※ 로그인 되어 있지 않음");
            loginButton.setText("Log In");
            loginButton.setVisibility(View.VISIBLE);
            signUpButton.setVisibility(View.VISIBLE);
            editProfileButton.setVisibility(View.GONE);
            logoutButton.setVisibility(View.GONE);
            moveToMapButton.setEnabled(false);
            moveToTimerButton.setEnabled(false);
            moveToBoardButton.setEnabled(false);
        }
    }

    // 로그아웃을 처리하는 메소드
    private void logout() {
        // 로그아웃 여부를 확인하는 다이얼로그 표시
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("로그아웃");
        builder.setMessage("로그아웃하시겠습니까?")
                .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 로그아웃 수행
                        performLogout();
                    }
                })
                .setNegativeButton("취소", null)
                .show();
    }

    // 실제 로그아웃을 수행하는 메소드
    private void performLogout() {
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();
        // 로그아웃 상태에 따라 UI 업데이트
        updateLoginStatus(false);
    }
}
