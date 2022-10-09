package edu.northeastern.numad22fa_jingfeng;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;


public class activity5 extends AppCompatActivity {
    private static final String TAG = "activity5";
    private Button buttonStartThread;
    private TextView txt;
    private TextView found;
    private Handler mainHandler = new Handler();
    private volatile Boolean stopThread = false;
    private int cur;
    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        txt = findViewById(R.id.txt);
        found = findViewById(R.id.f);
        buttonStartThread = findViewById(R.id.button_start_thread);
    }

    public void startThread(View view) {
        stopThread = false;
        ExampleThread runnable = new ExampleThread(100000);
        new Thread(runnable).start();
    }

    public void stopThread(View view) {
        stopThread = true;
    }

    @Override
    public void onBackPressed() {
        count++;
        if (count == 2){
            super.onBackPressed();
        }else{
            Toast.makeText(getBaseContext(),"Are you sure to go back?", Toast.LENGTH_SHORT).show();
        }
    }

    private static boolean isPrime(int n){
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i ++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    class ExampleThread implements Runnable {
        int ceiling;
        ExampleThread(int ceiling) {
            this.ceiling = ceiling;
        }
        @Override
        public void run() {
            for (int i = 3; i < ceiling; i += 2) {
                cur = i;
                if (stopThread)
                    return;
                buttonStartThread.post(new Runnable() {
                    @Override
                    public void run() {
                        txt.setText("Current Prime Number: " + String.valueOf(cur));
                    }
                });
                if (isPrime(i)) {
                    buttonStartThread.post(new Runnable() {
                        @Override
                        public void run() {
                            found.setText("Latest Prime Found: " + String.valueOf(cur));
                        }
                    });
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

