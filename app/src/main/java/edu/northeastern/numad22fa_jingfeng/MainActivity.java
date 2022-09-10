package edu.northeastern.numad22fa_jingfeng;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(view -> {
            Log.i("MyApp", "This is message");
            Toast.makeText(getApplicationContext(), "Jing Feng, feng.jing1@northeastern.edu", Toast.LENGTH_SHORT).show();
        });
    }
}