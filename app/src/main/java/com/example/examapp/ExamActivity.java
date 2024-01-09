package com.example.examapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamActivity extends AppCompatActivity {

    TextView txtQuestion;
    RadioGroup radioGroup;
    Button btnSubmit;
    RadioButton rbOption1, rbOption2, rbOption3, rbOption4;

    List<QuestionsListResponse> questionList;

    static final int[] result = {0};
    static final int[] totalQuestion = {0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        txtQuestion = findViewById(R.id.txtQuestion);
        radioGroup = findViewById(R.id.radioGroup);
        btnSubmit = findViewById(R.id.btnSubmit);
        rbOption1 = findViewById(R.id.rbOption1);
        rbOption2 = findViewById(R.id.rbOption2);
        rbOption3 = findViewById(R.id.rbOption3);
        rbOption4 = findViewById(R.id.rbOption4);



        final int[] i = {1};

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbOption1 == null || rbOption2 == null || rbOption3 == null || rbOption4 == null) {
                    Toast.makeText(getApplicationContext(), "Choose answer", Toast.LENGTH_LONG).show();
                }
                else {
                    Call<List<QuestionsListResponse>> getQuestion = RetrofitHelper
                            .getInstance()
                            .getApi()
                            .getData();

                    getQuestion.enqueue(new Callback<List<QuestionsListResponse>>() {
                        @Override
                        public void onResponse(Call<List<QuestionsListResponse>> call, Response<List<QuestionsListResponse>> response) {
                            List<QuestionsListResponse> questionList = response.body();

                            if (i[0] < questionList.size()) {

                                int selectionOption = radioGroup.getCheckedRadioButtonId();
                                View radioButton = findViewById(selectionOption);
                                String radioValue = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();

                                totalQuestion[0] = questionList.size();

                                txtQuestion.setText(i[0]+1 +". " + questionList.get(i[0]).getQuestion());
                                rbOption1.setText(questionList.get(i[0]).getOption1());
                                rbOption2.setText(questionList.get(i[0]).getOption2());
                                rbOption3.setText(questionList.get(i[0]).getOption3());
                                rbOption4.setText(questionList.get(i[0]).getOption4());

                                if(radioValue.equals(questionList.get(i[0] - 1).getCorrect_option()))
                                {
                                    result[0]++;
                                }

                                if(i[0] == questionList.size() -1){
                                    btnSubmit.setText("Submit");
                                }

                                i[0]++;
                                radioGroup.clearCheck();

                            } else {
                                String radioValue = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                                if(radioValue.equals(questionList.get(i[0] - 1).getCorrect_option()))
                                {
                                    result[0]++;
                                }

                                Intent intentResult = new Intent(getApplicationContext(), ResultActivity.class);
                                startActivity(intentResult);
                            }

                        }

                        @Override
                        public void onFailure(Call<List<QuestionsListResponse>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),t.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        Call<List<QuestionsListResponse>> getQuestion = RetrofitHelper
                .getInstance()
                .getApi()
                .getData();

        getQuestion.enqueue(new Callback<List<QuestionsListResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<QuestionsListResponse>> call, @NonNull Response<List<QuestionsListResponse>> response) {
                List<QuestionsListResponse> questionsList = response.body();
                assert questionsList != null;
                txtQuestion.setText("1. " + questionsList.get(0).getQuestion());
                rbOption1.setText(questionsList.get(0).getOption1());
                rbOption2.setText(questionsList.get(0).getOption2());
                rbOption3.setText(questionsList.get(0).getOption3());
                rbOption4.setText(questionsList.get(0).getOption4());
            }

            @Override
            public void onFailure(Call<List<QuestionsListResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
}