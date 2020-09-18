package com.example.app7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class HyrjaAnim extends AppCompatActivity {
    ImageView imageAnim;
    TextView textRestoranitIm;
    Animation animationTop,animationBottom;
    private static int HAPJE_ACTIVITY= 3600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyrja_anim);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageAnim=findViewById(R.id.image_anim);
        textRestoranitIm=findViewById(R.id.textRestoranitIm);
        animationTop= AnimationUtils.loadAnimation(this,R.anim.main_top_anim);
        animationBottom= AnimationUtils.loadAnimation(this,R.anim.main_bottom_anim);
        imageAnim.setAnimation(animationTop);
        textRestoranitIm.setAnimation(animationBottom);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(HyrjaAnim.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },HAPJE_ACTIVITY);
    }
}