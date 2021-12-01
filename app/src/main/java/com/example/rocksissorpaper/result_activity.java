package com.example.rocksissorpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class result_activity extends AppCompatActivity {
    Button playagain, gofirst;
    TextView score, bestscore;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        gofirst = findViewById(R.id.gofirst);
        playagain = findViewById(R.id.playagain);
        score = findViewById(R.id.score);
        bestscore = findViewById(R.id.bestscore);

        Intent intent = getIntent();
        int scores = intent.getIntExtra("score", 0);

        score.setText("점수 : "+scores);

        SharedPreferences preferences = getSharedPreferences("PREFS", MODE_PRIVATE);
        int bestscores = preferences.getInt("bestscore",0);

        if(scores>bestscores){
            SharedPreferences.Editor editor = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
            editor.putInt("bestscore", scores);
            editor.apply();
            bestscore.setText("" + scores);
        }else{
            bestscore.setText(""+bestscores);
        }

        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(result_activity.this, GameScreen.class));
                finish();
            }
        });

        gofirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(result_activity.this, MainActivity.class));
                finish();
            }
        });
    }

}