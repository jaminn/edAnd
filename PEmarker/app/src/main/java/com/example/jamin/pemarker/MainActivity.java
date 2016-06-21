package com.example.jamin.pemarker;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;
        final int height = size.y;

        final float[][] downE = {new float[2]};
        final float[] upE = new float[2];


        final int[] scoreA = {0};
        final int[] scoreB = {0};


        Log.d("size", "width:"+String.valueOf(width));
        Log.d("size", "height:"+String.valueOf(height));
        final ImageView robot;
        robot= (ImageView) findViewById(R.id.imageView1);
        final TextView teamA=(TextView) findViewById(R.id.teamA);
        final TextView teamB=(TextView) findViewById(R.id.teamB);

        final ImageView iv = (ImageView)findViewById(R.id.imageView1);
        RelativeLayout ll = (RelativeLayout)findViewById(R.id.full);
        ll.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {


                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN :
                        //Log.d("touchEvent", "Down");
                        //robot.setBackgroundColor(Color.parseColor("#FF0000"));

                        iv.setX(event.getX());
                        downE[0][0] =event.getX();
                        Log.d("jamin", "downE[0]"+String.valueOf(downE[0][0]));

                        iv.setY(event.getY());
                        downE[0][1] = event.getY();
                        Log.d("jamin", "downE[1]"+String.valueOf(downE[0][1]));

                        break;
                    case MotionEvent.ACTION_MOVE :
                        //Log.d("touchEvent", "Move");
                        //robot.setBackgroundColor(Color.parseColor("#00FF00"));
                        break;
                    case MotionEvent.ACTION_UP   :
                        //Log.d("touchEvent", "UP");
                        //robot.setBackgroundColor(Color.parseColor("#0000FF"));

                        iv.setX(event.getX());
                        upE[0] = event.getX();
                        Log.d("jamin", "upE[0]"+String.valueOf(upE[0]));

                        iv.setY(event.getY());
                        upE[1] = event.getY();
                        Log.d("jamin", "upE[1]"+String.valueOf(upE[1]));
                        ////점수 처리
                        if(width/2>upE[0]) {//왼쪽
                            if (100 < downE[0][1] - upE[1]) {//위로 스크롤
                                robot.setBackgroundColor(Color.parseColor("#0000FF"));
                                scoreA[0] = scoreA[0] +1;

                                Log.d("check", String.valueOf(scoreA[0]));
                                teamA.setText(Integer.toString(scoreA[0]));
                            }else if (100 < upE[1] - downE[0][1]) {//아래로 스크롤
                                robot.setBackgroundColor(Color.parseColor("#00FF00"));
                                if(0 < scoreA[0]) {
                                    scoreA[0] = scoreA[0] -1;

                                    Log.d("check", String.valueOf(scoreA[0]));
                                    teamA.setText(Integer.toString(scoreA[0]));
                                }
                            }
                        }else if(width/2 <= upE[0]){//오른쪽
                            if (100 < downE[0][1] - upE[1]) {//위로 스크롤
                                robot.setBackgroundColor(Color.parseColor("#FF0000"));
                                scoreB[0] = scoreB[0] +1;
                                teamB.setText(Integer.toString(scoreB[0]));
                            }else if (100 < upE[1] - downE[0][1]) {//아래로 스크롤
                                robot.setBackgroundColor(Color.parseColor("#FFFF00"));
                                if(0 < scoreB[0]) {
                                    scoreB[0] = scoreB[0] -1;
                                    teamB.setText(Integer.toString(scoreB[0]));
                                }
                            }
                        }
                        ////점수 처리
                        break;
                }
                return true;
            }
        });
    } // end of onCreate


} // end of class

