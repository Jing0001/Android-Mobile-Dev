package edu.northeastern.numad22fa_jingfeng;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        TextView txt = (TextView) findViewById(R.id.txt);
        Button A = (Button) findViewById(R.id.button9);
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("A");
            }
        });
        Button B = (Button) findViewById(R.id.button10);
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("B");
            }
        });
        Button C = (Button) findViewById(R.id.button11);
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("C");
            }
        });
        Button D = (Button) findViewById(R.id.button12);
        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("D");
            }
        });
        Button E = (Button) findViewById(R.id.button13);
        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("E");
            }
        });
        Button F = (Button) findViewById(R.id.button14);
        F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("F");
            }
        });
    }
}