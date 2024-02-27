package com.jainsoham.lab05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import android.speech.tts.TextToSpeech;


public class MainActivity extends AppCompatActivity {
    String TAG = "com.jainsoham.lab05.sharedpreferences";
    LifecycleData currentRun, lifeTime;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView currentRunTV, lifeTimeTV;
    Button resetRun, resetLifetime;
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                }
            }
        });
        sharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        currentRun = new LifecycleData();
        currentRun.duration= "Current Run";
        String lifecycleDataAsString = sharedPreferences.getString("lifetime", "");
        if (lifecycleDataAsString.equals("")) {
            lifeTime = new LifecycleData();
            lifeTime.duration = "Lifetime";
        } else {
            lifeTime = LifecycleData.parseJSON(lifecycleDataAsString);
        }
        lifeTimeTV = findViewById(R.id.lifetime);
        currentRunTV = findViewById(R.id.current);
        resetRun = findViewById(R.id.reset_run);
        resetLifetime = findViewById(R.id.reset_lifetime);
        resetRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentRun = new LifecycleData();
                currentRun.duration= "Current Run";
                t1.speak("Resetting current run", TextToSpeech.QUEUE_FLUSH, null);
                displayData();
            }
        });
        resetLifetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lifeTime = new LifecycleData();
                lifeTime.duration = "Lifetime";
                t1.speak("Resetting lifetime", TextToSpeech.QUEUE_FLUSH, null);
                displayData();
            }
        });
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    private void displayData() {
        lifeTimeTV.setText(lifeTime.toString());
        currentRunTV.setText(currentRun.toString());
    }
    public void storeData(){
        editor.putString("lifetime",lifeTime.toJSON()).apply();
    }
    public void updateCount(String currentEnclosingMethod){
        currentRun.updateEvent(currentEnclosingMethod);
        lifeTime.updateEvent(currentEnclosingMethod);
        displayData();
        storeData();
    }
    @Override
    protected void onStart(){
        super.onStart();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onResume(){
        super.onResume();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onPause(){
        super.onPause();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onStop(){
        super.onStop();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
}