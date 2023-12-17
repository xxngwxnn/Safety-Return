package com.example.SafetyReturn;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TimerActivity extends AppCompatActivity {

    private EditText timerHourEditText;
    private EditText timerMinuteEditText;
    private EditText timerSecondEditText;
    private Button startTimerButton;
    private Button stopTimerButton;
    private TextView timerTextView;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // XML 레이아웃에서 각 위젯 초기화
        timerHourEditText = findViewById(R.id.timerHourEditText);
        timerMinuteEditText = findViewById(R.id.timerMinuteEditText);
        timerSecondEditText = findViewById(R.id.timerSecondEditText);
        startTimerButton = findViewById(R.id.startTimerButton);
        stopTimerButton = findViewById(R.id.stopTimerButton);
        timerTextView = findViewById(R.id.timerTextView);

        // 시작 버튼 클릭 이벤트 리스너 설정
        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 타이머 시작 메소드 호출
                startTimer();
            }
        });

        // 중지 버튼 클릭 이벤트 리스너 설정
        stopTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 타이머 중지 메소드 호출
                stopTimer();
            }
        });
    }

    // 타이머 시작 메소드
    private void startTimer() {
        // 입력된 시간 값 가져오기
        String hourText = timerHourEditText.getText().toString().trim();
        String minuteText = timerMinuteEditText.getText().toString().trim();
        String secondText = timerSecondEditText.getText().toString().trim();

        // 빈 값 처리
        if (hourText.isEmpty()) {
            hourText = "0";
        }

        if (minuteText.isEmpty()) {
            minuteText = "0";
        }

        if (secondText.isEmpty()) {
            secondText = "0";
        }

        // 시간, 분, 초 값으로 변환
        int hours = Integer.parseInt(hourText);
        int minutes = Integer.parseInt(minuteText);
        int seconds = Integer.parseInt(secondText);

        // 총 밀리초 계산
        long totalTimeInMillis = ((hours * 60 + minutes) * 60 + seconds) * 1000;

        // CountDownTimer 설정
        countDownTimer = new CountDownTimer(totalTimeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // 남은 시간을 텍스트뷰에 업데이트
                updateTimerText(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                // 타이머 종료 후 TimerExpiredActivity로 이동
                updateTimerText(0);
                showTimerExpiredActivity();
            }
        }.start();
    }

    // TimerExpiredActivity로 이동하는 메소드
    private void showTimerExpiredActivity() {
        Intent intent = new Intent(this, TimerExpiredActivity.class);
        startActivity(intent);
    }

    // 타이머 중지 메소드
    private void stopTimer() {
        // CountDownTimer가 존재하면 중지하고 텍스트 업데이트
        if (countDownTimer != null) {
            countDownTimer.cancel();
            updateTimerText(0);
        }
    }

    // 타이머 텍스트 업데이트 메소드
    private void updateTimerText(long millisUntilFinished) {
        // 남은 시간을 시, 분, 초로 변환하여 텍스트뷰에 표시
        int hours = (int) (millisUntilFinished / 1000) / 3600;
        int minutes = ((int) (millisUntilFinished / 1000) % 3600) / 60;
        int seconds = (int) (millisUntilFinished / 1000) % 60;

        String timerText = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timerTextView.setText("남은 시간: " + timerText);
    }
}
