package com.example.funnygames;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

public class Game1 extends AppCompatActivity implements View.OnClickListener {
    //공통 변수
    ImageButton btn_scissors, btn_rock, btn_paper;
    ImageView img_user_select, img_robot_select;
    TextView score_user, score_robot;
    TextView message, remainCnt;

    int img_hands[] = {R.drawable.gif_scissors, R.drawable.gif_rock, R.drawable.gif_paper};

    //이기는 경우
    //0 1 2 | 가위 바위 보  || user, robot
    int win[][] = {{0, 2}, {1, 0}, {2, 1}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        btn_scissors = findViewById(R.id.btn_scissors);
        btn_rock = findViewById(R.id.btn_rock);
        btn_paper = findViewById(R.id.btn_paper);

        img_user_select = findViewById(R.id.img_user_select);
        img_robot_select = findViewById(R.id.img_robot_select);
        score_user = findViewById(R.id.score_user);
        score_robot = findViewById(R.id.score_robot);
        message = findViewById(R.id.message);
        remainCnt = findViewById(R.id.remainCnt);

        btn_paper.setOnClickListener(this);
        btn_rock.setOnClickListener(this);
        btn_scissors.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int valUser = 0, valRobot = 0;
        //가위 바위 보 표시 | 0 1 2
        if (view.getId() == R.id.btn_scissors){
            valUser = 0;
        }
        else if (view.getId() == R.id.btn_rock){
            valUser = 1;
        }
        else if (view.getId() == R.id.btn_paper){
            valUser = 2;
        }
        img_user_select.setImageResource(img_hands[valUser]);

        //로봇이 랜덤으로 가위바위보 선택 | 0 1 2
        Random r = new Random();
        valRobot = r.nextInt(3);
        img_robot_select.setImageResource(img_hands[valRobot]);

        //============ 승패 판단 ============

        //비김
        if (valRobot == valUser) {
            try {
                shakeGifimg(img_user_select, img_hands[valUser]);
                shakeGifimg(img_robot_select, img_hands[valRobot]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //user 이김
        else if (   (valUser == win[0][0] && valRobot == win[0][1]) ||
                    (valUser == win[1][0] && valRobot == win[1][1]) ||
                    (valUser == win[2][0] && valRobot == win[2][1])){
                try {
                    shakeGifimg(img_user_select, img_hands[valUser]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //스코어 올리기
                Integer score = Integer.parseInt(score_user.getText().toString());
                score++;
                score_user.setText(score.toString());
            }

        //robot 이김
        else{
            try {
                shakeGifimg(img_robot_select, img_hands[valRobot]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //스코어 올리기
            Integer score = Integer.parseInt(score_robot.getText().toString());
            score++;
            score_robot.setText(score.toString());
        }

        //기회 차감
        isGamgeOver();

    }




    private boolean isGamgeOver() { //게임종료
        Integer cnt = Integer.parseInt(remainCnt.getText().toString()) -1;
        remainCnt.setText(cnt.toString());
        String msg = "";

        if(cnt == 0) {
            int userScore = Integer.parseInt(score_user.getText().toString());
            int robotScore = Integer.parseInt(score_robot.getText().toString());

            if (userScore > robotScore) {
                //user 이김
                msg = "축하합니다!";
            } else if (userScore < robotScore) {
                //robot 이김
                msg = "졌습니다.";
            } else {
                //비김
                msg = "비겼습니다.";
            }

            //다이얼로그 띄우기
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("=게임끝=");
            builder.setMessage(msg);

            builder.setPositiveButton("새게임", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    remainCnt.setText("10");
                    score_user.setText("0");
                    score_robot.setText("0");
                    img_user_select.setImageResource(R.drawable.img_empty);
                    img_robot_select.setImageResource(R.drawable.img_empty);
                }
            });

            builder.setNegativeButton("종료", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            builder.setCancelable(false); //다이얼로그 닫기 전까지 아무것도 못함
            builder.create();
            builder.show();

        }

        return false;
    }


    private void shakeGifimg(ImageView imgView, int img_res) throws IOException {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            Drawable decodedImg = ImageDecoder.decodeDrawable(
                    ImageDecoder.createSource(getResources(), img_res));

            imgView.setImageDrawable(decodedImg); //첫번째 프레임
            AnimatedImageDrawable ani = (AnimatedImageDrawable) decodedImg; //애니메이션 만들기
            ani.setRepeatCount(1); //반복횟수
            ani.start();

        }
    }
}