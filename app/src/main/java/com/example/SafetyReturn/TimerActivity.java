package com.example.SafetyReturn;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TimerExpiredActivity extends AppCompatActivity {

    private TextView timerExpiredTextView;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_expired);

        // XML 레이아웃에서 TextView와 Button 위젯 초기화
        timerExpiredTextView = findViewById(R.id.timerExpiredTextView);
        Button cancelButton = findViewById(R.id.cancelButton);

        // 취소 버튼에 OnClickListener 설정
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 취소 버튼 클릭 시 MainActivity로 이동하고 현재 액티비티 종료
                Intent intent = new Intent(TimerExpiredActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        // 카운트다운 타이머 시작
        startCountdownTimer(1 * 60 * 1000);
    }

    // 카운트다운 타이머를 시작하는 메소드
    private void startCountdownTimer(long millisInFuture) {
        countDownTimer = new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // 1초마다 호출되며 남은 시간을 텍스트뷰에 업데이트
                updateTimerText(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                // 타이머 종료 시 메시지 발송 안내 메시지로 텍스트 설정
                timerExpiredTextView.setText("        메시지가 발송되었습니다.");
            }
        }.start();
    }

    // 텍스트뷰에 표시될 시간을 업데이트하는 메소드
    private void updateTimerText(long millisUntilFinished) {
        int seconds = (int) (millisUntilFinished / 1000) % 60;
        // 포맷에 맞추어 텍스트 설정
        String timerText = String.format(" -- %02d 초", seconds);
        timerExpiredTextView.setText("                타이머가 종료되었습니다.\n              1분이내에 취소 하지 않으면, \n등록된 비상연락망으로 메시지가 발송됩니다.\n                             " + timerText);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 액티비티가 종료될 때 카운트다운 타이머가 실행 중이면 취소
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
