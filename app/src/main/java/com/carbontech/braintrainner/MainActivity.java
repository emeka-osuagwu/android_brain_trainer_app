package com.carbontech.braintrainner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rand = new Random();
    Button start_button;
    TextView question_text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start_button = (Button) findViewById(R.id.start_button);
        question_text_view = (TextView) findViewById(R.id.question_text_view);

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        String question_to_string = Integer.toString(a) + " + " + Integer.toString(b);

        question_text_view.setText(question_to_string);

    }

    public void start(View view){
        start_button.setVisibility(View.INVISIBLE);
    }
}

