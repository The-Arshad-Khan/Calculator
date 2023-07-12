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
import android.widget.Toast;

public class calci_Activity extends AppCompatActivity {

    AppCompatButton button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDivision,
            buttonMul, button10, buttonC, buttonEqual;

    ImageButton backspace;
    EditText crunchifyEditText;
    TextView edtprint;
    int var = 8520;
    int count=0;


    float mValueOne, mValueTwo;
    boolean crunchifyAddition, mSubtract, crunchifyMultiplication, crunchifyDivision;

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
        backspace=findViewById(R.id.backclear);
        buttonDivision = findViewById(R.id.divbtn);
        buttonC = findViewById(R.id.allclear);
        buttonEqual = findViewById(R.id.equalbtn);
        edtprint= findViewById(R.id.edtprint);
        crunchifyEditText = findViewById(R.id.edt1);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
//            edtprint.setShowSoftInputOnFocus(false);
            crunchifyEditText.setShowSoftInputOnFocus(false);
        }


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "1");
                count=count+1;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "2");
                count=count+1;
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "3");
                count=count+1;
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "4");
                count=count+1;
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "5");
                count=count+1;
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "6");
                count=count+1;
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "7");
                count=count+1;
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "8");
                count=count+1;
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "9");
                count=count+1;
            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "0");
                count=count+1;
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==0){
                    edtprint.setText("error");
                    crunchifyEditText.setText("");
                }
                else{
                    edtprint.setText(crunchifyEditText.getText());
                    mValueOne = Float.parseFloat(crunchifyEditText.getText() + "");
                    crunchifyAddition = true;
                    crunchifyEditText.setText(null);
                    count=0;
             }
            }
        });
        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count==0){
                    edtprint.setText("error");
                    crunchifyEditText.setText("");
                }else {
                    edtprint.setText(crunchifyEditText.getText());
                    mValueOne = Float.parseFloat(crunchifyEditText.getText() + "");
                    mSubtract = true;
                    crunchifyEditText.setText(null);
                    count=0;
                }
            }
        });
        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (count == 0) {
                    edtprint.setText("error");
                    crunchifyEditText.setText("");
                }else{
                edtprint.setText(crunchifyEditText.getText());
                mValueOne = Float.parseFloat(crunchifyEditText.getText() + "");
                crunchifyMultiplication = true;
                crunchifyEditText.setText(null);
                count = 0;
            }
            }
        });
        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==0){
                    edtprint.setText("error");
                    crunchifyEditText.setText("");
                }
                else{
                mValueOne = Float.parseFloat(crunchifyEditText.getText() + "");
                crunchifyDivision = true;
                crunchifyEditText.setText(null);
                count=0;
                }

            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(count==0){
                    crunchifyEditText.setText(" ");
                }else if(count==4) {
                    mValueOne = Float.parseFloat(crunchifyEditText.getText() + "");
                    if (var == mValueOne) {
                        startActivity(new Intent(calci_Activity.this, HomeLoginActivity.class));
                        finish();
                    }
                }else{
                        mValueTwo = Float.parseFloat(crunchifyEditText.getText() + "");
                        if (crunchifyAddition == true) {
                            mValueOne=mValueOne + mValueTwo;
                            crunchifyEditText.setText(mValueTwo+"");
                            edtprint.setText(mValueOne+ "");
                            crunchifyAddition = true;
                        }
                        if (mSubtract == true) {
                            mValueOne=mValueOne - mValueTwo;
                            edtprint.setText(mValueOne+ "");
                            crunchifyEditText.setText(mValueTwo+ "");
                            mSubtract = true;
                            edtprint.setText(null);
                        }
                        if (crunchifyMultiplication == true) {
                            mValueOne=mValueOne * mValueTwo;
                            edtprint.setText(mValueOne+ "");
                            crunchifyEditText.setText( mValueTwo+ "");
                            crunchifyMultiplication = true;
                            edtprint.setText(null);
                        }
                        if (crunchifyDivision == true) {
                            mValueOne=mValueOne/mValueTwo;
                            edtprint.setText(mValueOne+ "");
                            crunchifyEditText.setText(mValueTwo + "");
                            crunchifyDivision = true;
                            edtprint.setText(null);
                        }

                }
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText("");
                edtprint.setText("");
                mValueOne=0;
                count=0;
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + ".");
                count=0;
            }
        });


backspace.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String value=crunchifyEditText.getText().toString();
        int len=value.length();
        if(len>0){
            crunchifyEditText.setText(value.substring(0,len-1));
        }


    }
});
    }
}