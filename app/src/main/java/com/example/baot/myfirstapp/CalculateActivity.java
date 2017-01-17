package com.example.baot.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Stack;


public class CalculateActivity extends AppCompatActivity {


    GridLayout gridLayout;
    // 定义16个按钮的文本
    String[] chars = new String[]
            {
                    "7" , "8" , "9" , "÷",
                    "4" , "5" , "6" , "×",
                    "1" , "2" , "3" , "-",
                    "0" , ".","=" , "+"
            };

    StringBuilder stringBuilder = new StringBuilder();
    Stack<Float> preResults = new Stack<Float>();
    String preOperation=null;
    String mutipleOrDevided = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        gridLayout = (GridLayout) findViewById(R.id.activity_calculate);
        for( int i=0; i < chars.length ; i++)
        {
            Button bn = new Button(this);
            bn.setText(chars[i]);
            // 设置该按钮的字号大小
            bn.setTextSize(40);
            // 设置按钮四周的空白区域
            bn.setPadding(5 , 35 , 5 , 35);
            // 指定该组件所在的行
            GridLayout.Spec rowSpec = GridLayout.spec(i / 4 + 2);
            // 指定该组件所在的列
            GridLayout.Spec columnSpec = GridLayout.spec(i % 4);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams(
                    rowSpec , columnSpec);
            // 指定该组件占满父容器
            params.setGravity(Gravity.FILL);


            // 设置按钮的响应逻辑

            final String s = chars[i];
            //如果是按等于号就直接计算 并各种清零
            if(s == "=" ) {
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        float temp = Float.parseFloat(stringBuilder.toString());

                        stringBuilder.delete(0, stringBuilder.length());

                        if(mutipleOrDevided!=null){
                            preResults.push(calculate (preResults.pop(),temp,mutipleOrDevided));
                            mutipleOrDevided=null;
                        }
                        if(preOperation!=null)
                        {
                            float f2= preResults.pop();
                            float f1= preResults.pop();
                            preResults.push(calculate (f1,f2,preOperation));
                        }

                        float finalResults = preResults.pop();

                        preOperation = null;
                        mutipleOrDevided = null;
                        TextView textView = (TextView) findViewById(R.id.displayText);
                        textView.setText(String.valueOf(finalResults));
                        System.out.println("按过一次等号 然后清除全部历史记录");
                    }
                });
            }
            //如果是加减的话就要看上一次的堆栈内的情况，并查看上一次记录的操作费是什么
            else if(s == "+" |s == "-" )
                bn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        float temp = Float.parseFloat(stringBuilder.toString());
                        stringBuilder.delete(0,stringBuilder.length());

                        //刚开始计算或者按过等号之后
                        if(preResults.isEmpty()) {
                            preResults.push(temp);
                        }
                        if(mutipleOrDevided ==null && preOperation!=null) {
                            preResults.push(calculate(preResults.pop(), temp, preOperation));
                        }
                        if(mutipleOrDevided!=null && preOperation!=null ){
                            preResults.push(calculate (preResults.pop(),temp,mutipleOrDevided));
                            mutipleOrDevided=null;
                            float f2= preResults.pop();
                            float f1= preResults.pop();
                            preResults.push(calculate (f1,f2,preOperation));
                        }
                        TextView textView =(TextView) findViewById(R.id.displayText);
                        textView.setText(String.valueOf(preResults.lastElement()));
                        preOperation=s;
                }
                });
            else if(s == "×" |s == "÷" )
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        float temp = Float.parseFloat(stringBuilder.toString());
                        stringBuilder.delete(0,stringBuilder.length());

                        //刚开始计算或者按过等号之后
                        if(preResults.isEmpty()) {
                            preResults.push(temp);
                        }
                        //已经有乘法或者除法在等待后续因子
                        if (mutipleOrDevided != null) {
                            // 把前面计算的结果入栈
                            preResults.push(calculate(preResults.pop(), temp, mutipleOrDevided));
                        }
                        //还在等待后续因子
                        else {
                            preResults.push(temp);
                        }
                    //此时显示立即运算的结果
                        mutipleOrDevided = s ;
                    TextView textView = (TextView) findViewById(R.id.displayText);
                    textView.setText(String.valueOf(preResults.lastElement()));
                }
                });
            else{
                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stringBuilder.append(s);
                        TextView textView =(TextView) findViewById(R.id.displayText);
                        textView.setText(stringBuilder.toString());
                    }
                });
            }
            gridLayout.addView(bn, params);

        }

    }



    public void clearAll(View view){
        if(stringBuilder.length()>0)
            this.stringBuilder.delete(0,stringBuilder.length());

        TextView textView = (TextView) findViewById(R.id.displayText);
        textView.setText("0");
        preResults.empty();
        preOperation=null;
        mutipleOrDevided = null;

        Toast.makeText(this,"all cleared, please restart calculate",Toast.LENGTH_SHORT).show();
    }

    private float calculate(float f1,float f2, String operation){
        System.out.println(String.format("这次的计算公式是%f %s %f", f1,operation,f2));

        switch(operation)
        {
            case ("+"): {
                f1 = f1 + f2;
                break;
            }
            case ("-"): {
                f1 = f1 - f2;
                break;
            }
            case ("×"): {
                f1 = f1 * f2;
                break;
            }
            case ("÷"): {
                f1 = f1 / f2;
                break;
            }
        }
        return f1;

        }

}