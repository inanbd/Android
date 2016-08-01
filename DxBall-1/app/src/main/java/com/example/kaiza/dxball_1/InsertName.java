package com.example.kaiza.dxball_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kaizar on 5/17/2016.
 */
public class InsertName extends Activity implements View.OnClickListener{

    private Database db = new Database(this);
    private Button btnSave;
    private EditText editText;
    private TextView textView;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertname);

        this.btnSave=(Button) findViewById(R.id.button2);
        this.editText = (EditText) findViewById(R.id.editText);
        this.textView = (TextView) findViewById(R.id.txtScore);

        this.score=MainActivity.score;

        textView.setText(String.valueOf(score));
        MainActivity.score=0;

       // saveData();
    }

    public void saveData(){
        btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        boolean b =db.InsertScore(editText.getText().toString(),textView.getText().toString());
                        if(b){
                            Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Database Error", Toast.LENGTH_LONG).show();
                        }

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }
                }
        );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button2:
                boolean b =db.InsertScore(editText.getText().toString(),textView.getText().toString());
                if(b){
                    Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Database Error", Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

        }
    }
}
