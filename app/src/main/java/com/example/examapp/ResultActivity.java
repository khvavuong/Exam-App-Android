package com.example.examapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView txtScore = findViewById(R.id.txtScore);
        Button btnBack = findViewById(R.id.button);

        txtScore.setText("Your score is: " + ExamActivity.result[0] + "/" + ExamActivity.totalQuestion[0]);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExamActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}