package com.carbontech.braintrainner;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button start_button;
    String question_to_string;
    Random rand = new Random();
    TextView timer_text_view;
    TextView question_text_view;
    CountDownTimer countDownTimer;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer_text_view = (TextView) findViewById(R.id.timer_text_view);
        start_button = (Button) findViewById(R.id.start_button);
        question_text_view = (TextView) findViewById(R.id.question_text_view);
        Button ans_button_1 = (Button) findViewById(R.id.ans_button_1);
        Button ans_button_2 = (Button) findViewById(R.id.ans_button_2);
        Button ans_button_3 = (Button) findViewById(R.id.ans_button_3);
        Button ans_button_4 = (Button) findViewById(R.id.ans_button_4);

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        int correctAnsLocation = rand.nextInt(4);
        int inCorrectAns = rand.nextInt(41);

        question_to_string = Integer.toString(a) + " + " + Integer.toString(b);

        question_text_view.setText(question_to_string);

        for (int i = 0; i < 4; i++){

            if (i == correctAnsLocation){
                answers.add(a + b);
            }
            else
            {
                while (inCorrectAns == a + b){
                    inCorrectAns = rand.nextInt(41);
                }

                answers.add(inCorrectAns);
            }

            answers.add(rand.nextInt(4));
        }

        ans_button_1.setText(Integer.toString(answers.get(0)));
        ans_button_2.setText(Integer.toString(answers.get(1)));
        ans_button_3.setText(Integer.toString(answers.get(2)));
        ans_button_4.setText(Integer.toString(answers.get(3)));

    }

    public void startTimer(){

        countDownTimer = new CountDownTimer(1000 * 3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                long sec = millisUntilFinished * 1000;
                timer_text_view.setText(Long.toString(sec) + "s");
                Log.i("timer", "runing");
            }

            @Override
            public void onFinish() {
                timer_text_view.setText("00s");
            }
        }.start();
    }

    public void start(View view){
        start_button.setVisibility(View.INVISIBLE);
    }
}

