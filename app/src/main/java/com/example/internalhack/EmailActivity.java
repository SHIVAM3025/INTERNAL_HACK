package com.example.internalhack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EmailActivity extends AppCompatActivity {

    private Button menter;
    private EditText email;
    private TextView mskip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        menter = findViewById(R.id.enter);
        email = findViewById(R.id.editEmail);
        mskip = findViewById(R.id.skip);

        mskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EmailActivity.this , DISPLAY_ACTIVITY.class);
                intent.putExtra("Email" , "not_given");
                startActivity(intent);

            }
        });


        menter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EmailActivity.this , DISPLAY_ACTIVITY.class);
                intent.putExtra("Email" , email.getText().toString());
                startActivity(intent);

            }
        });


    }
}
