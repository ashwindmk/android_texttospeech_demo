package com.ashwin.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    TextToSpeech textToSpeech1;
    TextToSpeech textToSpeech2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText text1EditText = findViewById(R.id.text_1_edit_text);
        EditText text2EditText = findViewById(R.id.text_2_edit_text);

        Button initButton = findViewById(R.id.init_button);
        initButton.setOnClickListener(v -> {
            init();
        });

        Button testButton = findViewById(R.id.test_button);
        testButton.setOnClickListener(v -> {
            String text1 = text1EditText.getText().toString();
            if (text1.isEmpty()) {
                text1 = "one two three";
            }
            String text2 = text2EditText.getText().toString();
            if (text2.isEmpty()) {
                text2 = "hundred thousand lacs";
            }

            // These will not overlap
            int s1 = textToSpeech1.speak(text1, TextToSpeech.QUEUE_FLUSH, null, "request");
            if (s1 == TextToSpeech.SUCCESS) {
                Log.d(Constant.TAG, "MainActivity: textToSpeech1 speak success");
            } else {
                Log.e(Constant.TAG, "MainActivity: textToSpeech1 speak failed, status: " + s1);
            }

            int s2 = textToSpeech2.speak(text2, TextToSpeech.QUEUE_FLUSH, null, "request");
            if (s2 == TextToSpeech.SUCCESS) {
                Log.d(Constant.TAG, "MainActivity: textToSpeech2 speak success");
            } else {
                Log.e(Constant.TAG, "MainActivity: textToSpeech2 speak failed, status: " + s2);
            }
        });

        init();
    }

    private void init() {
        textToSpeech1 = new TextToSpeech(getApplication(), status -> {
            Log.d(Constant.TAG, "MainActivity: textToSpeech1 initialized, status: " + status);
        });

        textToSpeech2 = new TextToSpeech(getApplication(), status -> {
            Log.d(Constant.TAG, "MainActivity: textToSpeech2 initialized, status: " + status);
        });
    }
}
