package com.example.internalhack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplitActivity extends AppCompatActivity {

    private RelativeLayout doctor_relative, patient_relative;
    private ImageView doctor_image, patient_image;
    private TextView doctor_text, patient_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (restoreDoctor()) {

            Intent patient = new Intent(SplitActivity.this, Sign.class);
            startActivity(patient);
            finish();


        } else if (restorePatient()) {
            Intent patient = new Intent(SplitActivity.this, PatientActivity.class);
            startActivity(patient);
            finish();
        }

        setContentView(R.layout.activity_split);

        doctor_relative = findViewById(R.id.doctor_relative);
        patient_relative = findViewById(R.id.patient_relative);
        doctor_image = findViewById(R.id.doctor_image);
        patient_image = findViewById(R.id.patient_image);
        doctor_text = findViewById(R.id.doctor_text);
        patient_text = findViewById(R.id.patient_text);

        patient_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PatientPath();

            }
        });


        doctor_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DoctorPath();

            }
        });

        patient_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PatientPath();


            }
        });

        doctor_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DoctorPath();

            }
        });

        doctor_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DoctorPath();

            }
        });

        patient_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PatientPath();

            }
        });
    }

    private void savePatient() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isPatient", true);
        editor.commit();


    }

    private void saveDoctor() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isDoctor", true);
        editor.commit();


    }


    private boolean restorePatient() {


        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isPatient", false);
        return isIntroActivityOpnendBefore;


    }


    private boolean restoreDoctor() {


        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isDoctor", false);
        return isIntroActivityOpnendBefore;


    }


    private void PatientPath() {
        Intent patient = new Intent(SplitActivity.this, PatientActivity.class);
        startActivity(patient);

        savePatient();

        finish();

    }

    private void DoctorPath() {
        Intent doctor = new Intent(SplitActivity.this, Sign.class);
        startActivity(doctor);

        saveDoctor();

        finish();

    }
}
