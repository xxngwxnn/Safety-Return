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

        timerHourEditText = findViewById(R.id.timerHourEditText);
        timerMinuteEditText = findViewById(R.id.timerMinuteEditText);
        timerSecondEditText = findViewById(R.id.timerSecondEditText);
        startTimerButton = findViewById(R.id.startTimerButton);
        stopTimerButton = findViewById(R.id.stopTimerButton);
        timerTextView = findViewById(R.id.timerTextView);

        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        stopTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });
    }

    private void startTimer() {
        String hourText = timerHourEditText.getText().toString().trim();
        String minuteText = timerMinuteEditText.getText().toString().trim();
        String secondText = timerSecondEditText.getText().toString().trim();

        if (hourText.isEmpty()) {
            hourText = "0";
        }

        if (minuteText.isEmpty()) {
            minuteText = "0";
        }

        if (secondText.isEmpty()) {
            secondText = "0";
        }

        int hours = Integer.parseInt(hourText);
        int minutes = Integer.parseInt(minuteText);
        int seconds = Integer.parseInt(secondText);

        long totalTimeInMillis = ((hours * 60 + minutes) * 60 + seconds) * 1000;

        countDownTimer = new CountDownTimer(totalTimeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimerText(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                updateTimerText(0);
                showTimerExpiredActivity();
            }
        }.start();
    }

    private void showTimerExpiredActivity() {
        Intent intent = new Intent(this, TimerExpiredActivity.class);
        startActivity(intent);
    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            updateTimerText(0);
        }
    }

    private void updateTimerText(long millisUntilFinished) {
        int hours = (int) (millisUntilFinished / 1000) / 3600;
        int minutes = ((int) (millisUntilFinished / 1000) % 3600) / 60;
        int seconds = (int) (millisUntilFinished / 1000) % 60;

        String timerText = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timerTextView.setText("남은 시간: " + timerText);
    }
}
