package com.deep1129agg.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button goButton;
    Button goButtonDifficult;
    int locCorrect;
    TextView resultTextView;
    TextView scoreTextView;
    int score;
    int numQues;
    TextView sumTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;
    boolean playContinue;
    int j;
    int bound;
    int difficulty;
    int min;

    public void playAgain(View view){
        playAgain(findViewById(R.id.timerTextView),difficulty);
    }

    public void playAgain(View view,int j){

        difficulty = j;
        playContinue = true;
        score = 0;
        numQues = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numQues));
        newQues(j);
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        new CountDownTimer( 30100, 1000){


            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+ "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
                playContinue = false;
            }
        }.start();

    }

    public void chooseAnswer(View view){

        if(playContinue==true)

        {
            if (Integer.toString(locCorrect).equals(view.getTag().toString())) {
                resultTextView.setText("Correct!");
                score++;
            } else {
                resultTextView.setText("Wrong!");
            }
            numQues++;
            scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numQues));
            newQues(difficulty);
        }
    }



    public void start(View view){

        goButton.setVisibility(View.INVISIBLE);
        goButtonDifficult.setVisibility(View.INVISIBLE);
//        tag = (Integer)view.getTag();
        playAgain( findViewById(R.id.timerTextView),0);
        gameLayout.setVisibility(View.VISIBLE);
//        Log.i("tag",Integer.toString(tag));


    }

    public void startDifficult(View view){

        goButton.setVisibility(View.INVISIBLE);
        goButtonDifficult.setVisibility(View.INVISIBLE);
//        tag = (Integer)view.getTag();
        playAgain( findViewById(R.id.timerTextView),1);
        gameLayout.setVisibility(View.VISIBLE);
//        Log.i("tag",Integer.toString(tag));


    }

    public void newQues(int j){

        Random rand = new Random();


        if (j == 0) {
            bound = 21;
            min = 0;
        }
        else {
            bound = 41;
            min = 20;
        }



        int a = rand.nextInt(bound) + min;
        int b = rand.nextInt(bound) + min;
        sumTextView.setText(Integer.toString(a)+ " + " + Integer.toString(b));

        locCorrect = rand.nextInt(4);

        answers.clear();

        for(int i=0; i<4; i++){
            if(i == locCorrect)
                answers.add(a+b);
            else{
                int wrongAns = rand.nextInt(2*bound) + min;

                while(wrongAns == a+b)
                    wrongAns = rand.nextInt(2*bound) + min;
                answers.add(wrongAns);

            }

        }


        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.sumTextView);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        goButton = findViewById(R.id.goButton);
        goButtonDifficult =findViewById(R.id.goButtonDifficult);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);
        playContinue = true;

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);



    }
}
