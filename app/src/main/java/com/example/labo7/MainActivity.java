package com.example.labo7;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.speech.tts.TextToSpeech;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech textToSp;
    private EditText edtSpeak;
    private Button btnSpeak;            //text to speech

    private TextView txvResult;  // speech to text

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvResult = (TextView) findViewById(R.id.txvResult);

        textToSp=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                    if (status==TextToSpeech.SUCCESS){
                        int res=textToSp.setLanguage(Locale.ENGLISH);
                            if (res==TextToSpeech.LANG_MISSING_DATA || res==TextToSpeech.LANG_NOT_SUPPORTED){
                                Toast.makeText(MainActivity.this,"Language not supported", Toast.LENGTH_SHORT).show();
                            }else {
                                btnSpeak.setEnabled(true);
                                textToSp.setPitch(0.6f);
                                textToSp.setSpeechRate(1.0f);
                                speak();
                            }
                    }
            }
        });

        edtSpeak=(EditText)findViewById(R.id.edt_speak);
        btnSpeak=(Button)findViewById(R.id.btn_speak);
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
    }

    private void speak(){
    String text=edtSpeak.getText().toString();

    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
        textToSp.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);

    }else{
        textToSp.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }
    }


    public void getSpeechInput(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Doesn't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txvResult.setText(result.get(0));
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {

        if(textToSp!=null){
            textToSp.stop();
            textToSp.shutdown();
        }
        super.onDestroy();
    }
}