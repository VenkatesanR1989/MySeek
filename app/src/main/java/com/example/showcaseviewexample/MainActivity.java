package com.example.showcaseviewexample;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.showcaseviewexample.showcase.Showcase;

public class MainActivity extends AppCompatActivity {

    boolean button =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button btn1=(Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(!button)
                                        {
                                            startActivity(new Intent(MainActivity.this,secondActivity.class));                                        }
                                        else
                                        {

                                            button=false;
                                            Log.e("display", "clicked true");
                                        }

                                    }
                                });

                btn1.setOnTouchListener(new View.OnTouchListener() {

                    private Handler mHandler;

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                if (mHandler != null) return true;
                                mHandler = new Handler();
                                mHandler.postDelayed(mAction, 500);
                                break;
                            case MotionEvent.ACTION_UP:
                                if (mHandler == null) return true;
                                mHandler.removeCallbacks(mAction);
                                mHandler = null;
                                break;

                        }
                        return false;
                    }

                    Runnable mAction = new Runnable() {
                        @Override
                        public void run() {
                            Log.e("display", "Longpressed");
                            mHandler.postDelayed(this, 500);
                            button=true;
                        }
                    };

                });

                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayTuto();
            }
        });
    }

    protected void displayTuto() {
        Showcase.from(this).setListener(new Showcase.Listener() {
            @Override
            public void onDismissed() { Toast.makeText(MainActivity.this, "Tutorial dismissed", Toast.LENGTH_SHORT).show(); }
        })
                .setContentView(R.layout.tuto_showcase_tuto_sample)
                .setVisibleView(R.id.showcasetext,View.VISIBLE)
                .setFitsSystemWindows(true)
                .on(R.id.btn5)
                .addCircle()
                .withBorder()
                .onClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .on(R.id.swapable)
                .displaySwipableLeft()
                .delayed(399)
                .animated(true)
                .show();
    }

}
