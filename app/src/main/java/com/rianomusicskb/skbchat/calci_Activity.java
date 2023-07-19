package com.rianomusicskb.skbchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class calci_Activity extends AppCompatActivity {

    AppCompatButton button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9,button10,
            buttonAdd, buttonSub,
            buttonDivision,
            buttonMul, buttonC, buttonEqual,modulerbtn,btnmod,openparenthesisbtn,closparenthesisbtn;

    ImageButton backspace;
    TextView outputedt;
    EditText inputtxt;
    String var = "8520";
    int count=0;
    String data;
    String finalresult="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calci);
        button0 = findViewById(R.id.btn0);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button5 = findViewById(R.id.btn5);
        button6 = findViewById(R.id.btn6);
        button7 = findViewById(R.id.btn7);
        button8 = findViewById(R.id.btn8);
        button9 = findViewById(R.id.btn9);
        button10 =findViewById(R.id.decimal);
        buttonAdd =findViewById(R.id.addbtn);
        buttonSub = findViewById(R.id.subbtn);
        buttonMul = findViewById(R.id.mulbtn);
        buttonDivision = findViewById(R.id.divbtn);

        backspace=findViewById(R.id.backclear);
        buttonC = findViewById(R.id.allclear);
        buttonEqual = findViewById(R.id.equalbtn);

        inputtxt = findViewById(R.id.inputtxt);
        outputedt = findViewById(R.id.outputedt);


        btnmod=findViewById(R.id.btnmod);
        modulerbtn=findViewById(R.id.modularbtn);

        openparenthesisbtn=findViewById(R.id.openparenthesisbtn);
        closparenthesisbtn=findViewById(R.id.closparenthesisbtn);


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
//            edtprint.setShowSoftInputOnFocus(false);
            inputtxt.setShowSoftInputOnFocus(false);
        }


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputtxt.setText(data + "1");
                data=inputtxt.getText().toString();
                count=count+1;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=inputtxt.getText().toString();
                inputtxt.setText(data + "2");
                count=count+1;
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=inputtxt.getText().toString();
                inputtxt.setText(data + "3");
                count=count+1;
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=inputtxt.getText().toString();
                inputtxt.setText(data + "4");
                count=count+1;
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=inputtxt.getText().toString();
                inputtxt.setText(data + "5");
                count=count+1;
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=inputtxt.getText().toString();
                inputtxt.setText(data + "6");
                count=count+1;
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=inputtxt.getText().toString();
                inputtxt.setText(data + "7");
                count=count+1;
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=inputtxt.getText().toString();
                inputtxt.setText(data + "8");

                count=count+1;
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=inputtxt.getText().toString();
                inputtxt.setText(data + "9");
                count=count+1;
            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=inputtxt.getText().toString();
                inputtxt.setText(data + "0");
                count=count+1;
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outputedt.setText("");
                inputtxt.setText("");
                finalresult="";
                data="";

                count=0;
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=inputtxt.getText().toString();
                inputtxt.setText(data + ".");
                count=count+1;
            }
        });



        //operation button...

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==0){
                    inputtxt.setText("+");
                    outputedt.setText("Error");
                }
                else{
                    data=inputtxt.getText().toString();
                    inputtxt.setText(data + "+");
                    outputedt.setText(finalresult+"");
                    count=0;
             }
            }
        });
        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count==0){
                    inputtxt.setText("-");
                    outputedt.setText("Error");
                }else {
                    data=inputtxt.getText().toString();
                    inputtxt.setText(data + "-");
                    outputedt.setText(finalresult+"");
                    count=0;
//                    inputtxt.setText(outputedt.getText());
//                    outputedt.setText(outputedt.getText() + " * ");
//                    mValueOne = Float.parseFloat(outputedt.getText() + "");
//                    mSubtract = true;
//                    outputedt.setText(null);
//                    count=0;
                }
            }
        });
        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (count == 0) {
                    inputtxt.setText("*");
                    outputedt.setText("Error");
                }else{

                    data=inputtxt.getText().toString();
                    inputtxt.setText(data + "×");
                    outputedt.setText(finalresult+"");
                    count=0;
//                inputtxt.setText(outputedt.getText());
//                mValueOne = Float.parseFloat(outputedt.getText() + "");
//                crunchifyMultiplication = true;
//                outputedt.setText(null);
//                count = 0;
            }
            }
        });
        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==0){
                    inputtxt.setText("÷");
                    outputedt.setText("Error");
                }
                else{

                    data=inputtxt.getText().toString();
                    inputtxt.setText(data + "÷");
                    outputedt.setText(finalresult+"");
                    count=0;
//                mValueOne = Float.parseFloat(outputedt.getText() + "");
//                crunchifyDivision = true;
//                outputedt.setText(null);
//                count=0;
                }

            }
        });

        modulerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==0){
                    inputtxt.setText("%");
                    outputedt.setText("Error");
                }else{
                    data=inputtxt.getText().toString();
                    inputtxt.setText(data + "%");
                    outputedt.setText(finalresult+"");
                    count=count+1;
                }

            }
        });

        openparenthesisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count==0){
                    inputtxt.setText("(");
                    outputedt.setText("Error");
                    count++;
                }else{
                    inputtxt.setText(data + "(");
                    data=inputtxt.getText().toString();
                    count=0;
                }

            }
        });

        closparenthesisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(count==0){
                    inputtxt.setText(")");
                    outputedt.setText("Error");
                    count++;
                }else{
                    data=inputtxt.getText().toString();
                    inputtxt.setText(data + ")");
                    count=0;
                }

            }
        });

        btnmod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==0){
                    outputedt.setText("");


                }else{
                    float mValueOne = Float.parseFloat(inputtxt.getText() + "");
                    data=inputtxt.getText().toString();
                    if (mValueOne<0) {
                        inputtxt.setText("-(" + mValueOne + ")");
                        data = inputtxt.getText().toString();

                    } else {
                        inputtxt.setText("(" + data + ")");
                        data = inputtxt.getText().toString();
                    }
                }

            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                data=inputtxt.getText().toString();
                if(count==0){
                    outputedt.setText(" ");
                }else if(data.equals(var)) {
                        startActivity(new Intent(calci_Activity.this, lr.class));
                        finish();
                }else{

                    data=data.replaceAll("×","*");
                    data=data.replaceAll("÷","/");
                    data=data.replaceAll("%","/100");
                    Context rhino=Context.enter();
                    rhino.setOptimizationLevel(-1);

                    Scriptable scriptable=rhino.initStandardObjects();
                    finalresult=rhino.evaluateString(scriptable,data,"javascript",1,null).toString();
                    outputedt.setText(finalresult);
                    inputtxt.setText(finalresult);
                    count=count+1;
                }


//                if(count==0){
//                    outputedt.setText(" ");
//                }else if(count==4) {
//                    mValueOne = Float.parseFloat(outputedt.getText() + "");
//                    if (var == mValueOne) {
//                        startActivity(new Intent(calci_Activity.this, lr.class));
//                        finish();
//                    }
//                }else{
//                        mValueTwo = Float.parseFloat(outputedt.getText() + "");
//                        if (crunchifyAddition == true) {
//                            mValueOne=mValueOne + mValueTwo;
//                            outputedt.setText(mValueTwo+"");
//                            inputtxt.setText(mValueOne+ "");
//                            crunchifyAddition = false;
//                        }
//                        if (mSubtract == true) {
//                            mValueOne=mValueOne - mValueTwo;
//                            inputtxt.setText(mValueOne+ "");
//                            outputedt.setText(mValueTwo+ "");
//                            mSubtract = true;
//                            inputtxt.setText(null);
//                        }
//                        if (crunchifyMultiplication == true) {
//                            mValueOne=mValueOne * mValueTwo;
//                            inputtxt.setText(mValueOne+ "");
//                            outputedt.setText( mValueTwo+ "");
//                            crunchifyMultiplication = true;
//                            inputtxt.setText(null);
//                        }
//                        if (crunchifyDivision == true) {
//                            mValueOne=mValueOne/mValueTwo;
//                            inputtxt.setText(mValueOne+ "");
//                            outputedt.setText(mValueTwo + "");
//                            crunchifyDivision = true;
//                            inputtxt.setText(null);
//                        }
//                    inputtxt.setText(null);
               // }
            }
        });




backspace.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String value= inputtxt.getText().toString();
        int len=value.length();
        if(len>0){
            inputtxt.setText(value.substring(0,len-1));
        }


    }
});
    }
}