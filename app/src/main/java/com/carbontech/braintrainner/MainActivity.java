package com.carbontech.braintrainner;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int a;
    int b;
    int score = 0;
    int question = 0;
    int correctAnsLocation;
    boolean played = false;

    String question_to_string;
    Random rand = new Random();
    CountDownTimer countDownTimer;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    TextView timer_text_view;
    TextView notification_text_view;
    TextView question_text_view;
    TextView score_text_view;
    RelativeLayout view_2;

    Button start_button;
    Button ans_button_1;
    Button ans_button_2;
    Button ans_button_3;
    Button ans_button_4;
    Button play_again_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer_text_view = (TextView) findViewById(R.id.timer_text_view);
        question_text_view = (TextView) findViewById(R.id.question_text_view);
        notification_text_view = (TextView) findViewById(R.id.notification_text_view);
        score_text_view = (TextView) findViewById(R.id.score_text_view);
        view_2 = (RelativeLayout) findViewById(R.id.view_2);

        start_button = (Button) findViewById(R.id.start_button);
        ans_button_1 = (Button) findViewById(R.id.ans_button_1);
        ans_button_2 = (Button) findViewById(R.id.ans_button_2);
        ans_button_3 = (Button) findViewById(R.id.ans_button_3);
        ans_button_4 = (Button) findViewById(R.id.ans_button_4);
        play_again_button = (Button) findViewById(R.id.play_again_button);


        generateQuestion();
        startTimer();
    }

    public void showResult(){
        notification_text_view.setText("You scored: " + Integer.toString(score) + "/" + Integer.toString(question));
    }

    public void selectAns(View view){

        if (!played) {
            if (view.getTag().toString().equals(Integer.toString(1 + correctAnsLocation))) {
                score++;
                notification_text_view.setText("Correct!");
            } else {
                notification_text_view.setText("Wrong!");
            }
            question++;
            score_text_view.setText(Integer.toString(score) + "/" + Integer.toString(question));
            generateQuestion();
        }
    }

    public void generateQuestion(){

        a = rand.nextInt(21);
        b = rand.nextInt(21);
        correctAnsLocation = rand.nextInt(4);

        question_to_string = Integer.toString(a) + " + " + Integer.toString(b);

        answers.clear();

        question_text_view.setText(question_to_string);

        for (int i = 0; i < 4; i++){

            int inCorrectAns = rand.nextInt(41);

            if (i == correctAnsLocation){
                answers.add(a + b);
            }
            else
            {
                while (inCorrectAns == a + b){
                    inCorrectAns = rand.nextInt(41);
                }

                inCorrectAns = rand.nextInt(41);
            }

            answers.add(inCorrectAns);
        }

        ans_button_1.setText(Integer.toString(answers.get(0)));
        ans_button_2.setText(Integer.toString(answers.get(1)));
        ans_button_3.setText(Integer.toString(answers.get(2)));
        ans_button_4.setText(Integer.toString(answers.get(3)));
    }


    public void startTimer(){

        countDownTimer = new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                long sec = millisUntilFinished / 1000;
                timer_text_view.setText(Long.toString(sec) + "s");
                Log.i("timer", "runing");
            }

            @Override
            public void onFinish() {
                timer_text_view.setText("00s");
                showResult();
                played = true;
                play_again_button.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void start(View view){
        start_button.setVisibility(View.INVISIBLE);
        view_2.setVisibility(View.VISIBLE);
    }

    public void playAgain(View view){
        view.setVisibility(View.INVISIBLE);
        notification_text_view.setText("");
        countDownTimer.start();
        played = false;
        generateQuestion();
    }


}

