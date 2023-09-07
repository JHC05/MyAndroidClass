package com.example.funnygames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funnygames.databinding.ActivityGame2Binding;

import java.util.ArrayList;
import java.util.Random;

public class Game2 extends AppCompatActivity implements View.OnClickListener, NumberPicker.OnScrollListener {

    ActivityGame2Binding binding;

    //선택한 공의 번호 list
    ArrayList<Integer> myBall = new ArrayList<>();
    ArrayList<Integer> robotBall = new ArrayList<>();

    //공을 표시할 textview에 대한 list
    ArrayList<TextView> myBallTextView = new ArrayList<>();
    ArrayList<TextView> robotBallTextView = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGame2Binding.inflate(getLayoutInflater()); //바인딩할때 필수
        setContentView(binding.getRoot());

        //바인딩
        binding.btnAdd.setOnClickListener(this);
        binding.btnClear.setOnClickListener(this);
        binding.btnPlay.setOnClickListener(this);

        //스크롤 리스너
        binding.numberPicker.setMinValue(1);
        binding.numberPicker.setMaxValue(45);
        Random r = new Random();
        binding.numberPicker.setValue(r.nextInt(45)+1);

        binding.numberPicker.setOnScrollListener(this);

        //textview 전처리
        myBallTextView.add(binding.myBall1);
        myBallTextView.add(binding.myBall2);
        myBallTextView.add(binding.myBall3);
        myBallTextView.add(binding.myBall4);
        myBallTextView.add(binding.myBall5);
        myBallTextView.add(binding.myBall6);

        robotBallTextView.add(binding.randomBall1);
        robotBallTextView.add(binding.randomBall2);
        robotBallTextView.add(binding.randomBall3);
        robotBallTextView.add(binding.randomBall4);
        robotBallTextView.add(binding.randomBall5);
        robotBallTextView.add(binding.randomBall6);

        for (int i=0; i<6; i++){
            myBallTextView.get(i).setVisibility(View.INVISIBLE);
            robotBallTextView.get(i).setVisibility(View.INVISIBLE);
        }

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_add: //추가
                addMynumber();

                break;
            case R.id.btn_clear: //삭제
                for (int i=0; i<myBall.size(); i++){
                    myBallTextView.get(i).setText("0");
                }
                myBall.clear();

                for (int i=0; i<robotBall.size(); i++){
                    robotBallTextView.get(i).setText("0");
                }
                robotBall.clear();

                binding.numberPicker.setEnabled(true);

                break;
            case R.id.btnPlay: //번호뽑기
                addRobotNumver();
                winCount();
                binding.numberPicker.setEnabled(true);
                break;
        }

    }



    private void winCount() {
        Integer cnt=0;
        String winNum="";

        for (Integer a: myBall ) { //a에 myBall값 하나씩 저장
            if (robotBall.contains(a)) {
                cnt++;
                winNum+=a+" ";
            }
        }
        binding.txtMessage.setText("맞춘 숫자의 개수는 "+cnt.toString()+"개 입니다!\n"+winNum);

    }

    private void addRobotNumver() {
        if (robotBall.size()==6)
            robotBall.clear();

        Random r = new Random();

        while (robotBall.size()<6){
            Integer num = r.nextInt(45)+1;

            if (robotBall.contains(num)) //중복이면
                continue; //다시돌기

            robotBallTextView.get(robotBall.size()).setText(num.toString());
            robotBallTextView.get(robotBall.size()).setVisibility(View.VISIBLE);
            robotBall.add(num);
        }
    }

    private void addMynumber() {
        if (myBall.size() == 6){
            Toast.makeText(this, "6개 모두 선택하였습니다.", Toast.LENGTH_SHORT).show();
            binding.numberPicker.setEnabled(false); //사용 막기
            return;
        }

        int myNum = binding.numberPicker.getValue();

        if (myBall.contains(myNum)){
            Toast.makeText(this, "중복된 숫자입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        myBallTextView.get(myBall.size()).setText(String.valueOf(myNum));
        myBallTextView.get(myBall.size()).setVisibility(View.VISIBLE);
        myBall.add(myNum);

    }

    @Override
    public void onScrollStateChange(NumberPicker numberPicker, int i) {
        if(i != SCROLL_STATE_IDLE || binding.switch1.isChecked() == false)
            return;

        binding.btnAdd.performClick();
    }
}