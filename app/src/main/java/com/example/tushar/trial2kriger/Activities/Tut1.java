package com.example.tushar.trial2kriger.Activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tushar.trial2kriger.R;

public class Tut1 extends AppCompatActivity {
    private ImageView imageView;
    private AnimationDrawable picanim;
    private Button nxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tut1);

        imageView=(ImageView)findViewById(R.id.imageView2);

        imageView.setBackgroundResource(R.drawable.anim);
        picanim=(AnimationDrawable)imageView.getBackground();

        nxt=(Button)findViewById(R.id.nextID);

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Tut1.this,Tut2.class));
                finish();
            }
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Uncomment this for FrameAnimation to work
        picanim.start();
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //stop the animation
                //Uncomment this for FrameAnimation to work
                picanim.stop();

            }
        }, 3000); //chance to 50 for fadeAnimation
        return super.onTouchEvent(event);
    }
}

