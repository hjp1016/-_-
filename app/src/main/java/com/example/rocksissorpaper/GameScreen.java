package com.example.rocksissorpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameScreen extends AppCompatActivity {

    ImageView com_hand1, com_hand2, com_hand3;
    ImageButton button1, button2, button3;
    TextView timer, score;

    private CountDownTimer countDownTimer;
    private long timemilliseconds = 15000;

    int nowscore = 0;
    int getChand1, getChand2, getChand3;
    int getUhand1, getUhand2, getUhand3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        com_hand1 = findViewById(R.id.com_hand1);
        com_hand2 = findViewById(R.id.com_hand2);
        com_hand3 = findViewById(R.id.com_hand3);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        timer = findViewById(R.id.timer);
        score = findViewById(R.id.score);

        setChand();
        setUserhand();


        startTimer();
    }


    private void setChand(){
        getChand1 = new Random().nextInt(3) + 1;
        getChand2 = new Random().nextInt(3) + 1;
        getChand3 = new Random().nextInt(3) + 1;
        makeimage(com_hand1,getChand1);
        makeimage(com_hand2,getChand2);
        makeimage(com_hand3,getChand3);
    }


    private void makeimage(ImageView image,int a){
        switch (a) {
            case 1:
                image.setImageResource(R.drawable.paper);
                break;
            case 2:
                image.setImageResource(R.drawable.rock);
                break;
            case 3:
                image.setImageResource(R.drawable.scissor);
                break;
        }
    }

    private void makeUhandimage(ImageButton but, int a){
        switch(a){
            case 1:
                but.setImageResource(R.drawable.paper);
                break;
            case 2:
                but.setImageResource(R.drawable.rock);
                break;
            case 3:
                but.setImageResource(R.drawable.scissor);
                break;
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timemilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timemilliseconds = millisUntilFinished;
                update();
            }

            @Override
            public void onFinish() {

                Intent intent = new Intent(GameScreen.this, result_activity.class);
                intent.putExtra("score",nowscore);
                startActivity(intent);
                finish();
            }
        }.start();
    }

    private void update() {
        int seconds = (int) timemilliseconds / 1000;
        timer.setText("남은시간 : " + seconds + "초");
    }


    public void button_click(View view) {
        switch (view.getId()) {
            case R.id.button1:
                Wincheck(getUhand1, getChand1);
                break;
            case R.id.button2:
                Wincheck(getUhand2, getChand1);
                break;
            case R.id.button3:
                Wincheck(getUhand3, getChand1);
                break;

        }
    }

    public void changeset(){
        getChand1 = getChand2;
        getChand2 = getChand3;
        getChand3 = new Random().nextInt(3) + 1;
        makeimage(com_hand1,getChand1);
        makeimage(com_hand2,getChand2);
        makeimage(com_hand3,getChand3);
    }

    public void setUserhand(){
        List<Integer> userhandnum = new ArrayList<Integer>();
        for(int i = 1; i <4; i++)
            userhandnum.add(i);

        Collections.shuffle(userhandnum);

        getUhand1 = userhandnum.get(0);
        getUhand2 = userhandnum.get(1);
        getUhand3 = userhandnum.get(2);
        makeUhandimage(button1,getUhand1);
        makeUhandimage(button2,getUhand2);
        makeUhandimage(button3,getUhand3);
    }


    public void Wincheck(int Uhand, int Chand){
        int check = (3 + Uhand - Chand) % 3;
        switch(check){
            case 0:
                score.setText("점수 : " + nowscore);
                changeset();
                setUserhand();
                break;
            case 1:
                nowscore -= 2;
                score.setText("점수 : " + nowscore);
                changeset();
                setUserhand();
                break;
            case 2:
                nowscore += 1;
                score.setText("점수 : " + nowscore);
                changeset();
                setUserhand();
                break;
        }


    }
}

