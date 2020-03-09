package com.example.internalhack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.internalhack.Adapter_Patient.Patient_advice;
import com.example.internalhack.Adapter_Patient.Patient_diagnosis;
import com.example.internalhack.Adapter_Patient.Patient_presciption;
import com.example.internalhack.Adapter_Patient.Patient_symptoms;
import com.example.internalhack.Models.Advice_model;
import com.example.internalhack.Models.Alarm_Model;
import com.example.internalhack.Models.Diagnosis_model;
import com.example.internalhack.Models.Name_model;
import com.example.internalhack.Models.Presciption_model;
import com.example.internalhack.Models.Symptoms_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Patient_display extends AppCompatActivity {

    private ImageView madd;
    private TextView mname;
    private DatabaseReference mdatabase;
    private LinearLayoutManager symptoms_linear, presciption_linear, advice_linear, diagnosis_linear;

    private Patient_advice advice_adapter;
    private Patient_diagnosis diagnosis_adapter;
    private Patient_presciption presciption_adapter;
    private Patient_symptoms symptoms_adapter;

    private int i = 0;

    private List<Advice_model> advice_model;
    private List<Diagnosis_model> diagnosis_models;
    private List<Presciption_model> presciption_models;
    private List<Symptoms_model> symptoms_models;

    private RecyclerView msymptoms, mdiagnosis, mpresciption, madvice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_display);

        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Patient_display.this);
        final View progress = getLayoutInflater().inflate(R.layout.dialog_process, null);

        mBuilder.setView(progress);

        final AlertDialog progressdialog = mBuilder.create();

        progressdialog.show();


        String[] PERMISSIONS = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.SET_ALARM
        };

        int PERMISSION_ALL = 1;
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }


        mname = findViewById(R.id.name);
        symptoms_linear = new LinearLayoutManager(Patient_display.this);
        presciption_linear = new LinearLayoutManager(Patient_display.this);
        advice_linear = new LinearLayoutManager(Patient_display.this);
        diagnosis_linear = new LinearLayoutManager(Patient_display.this);

        madd = findViewById(R.id.add_alarm);

        msymptoms = findViewById(R.id.symptoms_recycler);
        mdiagnosis = findViewById(R.id.diagnosis_recycler);
        mpresciption = findViewById(R.id.presciption_recycler);
        madvice = findViewById(R.id.advice_recycler);


        msymptoms.setLayoutManager(symptoms_linear);
        mdiagnosis.setLayoutManager(diagnosis_linear);
        mpresciption.setLayoutManager(presciption_linear);
        madvice.setLayoutManager(advice_linear);

        msymptoms.setHasFixedSize(true);
        mdiagnosis.setHasFixedSize(true);
        mpresciption.setHasFixedSize(true);
        madvice.setHasFixedSize(true);


        advice_model = new ArrayList<>();
        diagnosis_models = new ArrayList<>();
        presciption_models = new ArrayList<>();
        symptoms_models = new ArrayList<>();

        advice_adapter = new Patient_advice(Patient_display.this, advice_model);
        diagnosis_adapter = new Patient_diagnosis(Patient_display.this, diagnosis_models);
        presciption_adapter = new Patient_presciption(Patient_display.this, presciption_models);
        symptoms_adapter = new Patient_symptoms(Patient_display.this, symptoms_models);

        msymptoms.setAdapter(symptoms_adapter);
        mdiagnosis.setAdapter(diagnosis_adapter);
        mpresciption.setAdapter(presciption_adapter);
        madvice.setAdapter(advice_adapter);

        mdatabase = FirebaseDatabase.getInstance().getReference().child("python").child("isAvailable");

        mdatabase.child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot post : dataSnapshot.getChildren()) {

                    mname.setText(post.getValue(String.class).toUpperCase());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mdatabase.child("disease").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                diagnosis_models.clear();
                i = 1;

                for (DataSnapshot post : dataSnapshot.getChildren()) {
                    Diagnosis_model event = new Diagnosis_model("", "");
                    event.setSecond(post.getValue(String.class));
                    event.setFirst(Integer.toString(i));
                    i++;
                    diagnosis_models.add(event);

                }

                diagnosis_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mdatabase.child("advice").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                i = 1;
                advice_model.clear();

                for (DataSnapshot post : dataSnapshot.getChildren()) {
                    Advice_model event = new Advice_model("", "");
                    event.setSecond(post.getValue(String.class));
                    event.setFirst(Integer.toString(i));
                    ++i;
                    advice_model.add(event);

                }

                advice_adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mdatabase.child("symptoms").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                i = 1;
                symptoms_models.clear();

                for (DataSnapshot post : dataSnapshot.getChildren()) {
                    Symptoms_model event = new Symptoms_model("", "");
                    event.setSecond(post.getValue(String.class));
                    event.setFirst(Integer.toString(i));
                    ++i;
                    symptoms_models.add(event);
                }

                symptoms_adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mdatabase.child("medication").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                progressdialog.dismiss();

                presciption_models.clear();

                for (DataSnapshot post : dataSnapshot.getChildren()) {

                    mdatabase.child("medication/" + post.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot post : dataSnapshot.getChildren()) {

                                Presciption_model event = new Presciption_model(post.getKey(), "", "");
                                Name_model getValue = post.getValue(Name_model.class);

                                if (getValue.getDosage() == null) {
                                    event.setDosage("" + getValue.getDuration());
                                } else {

                                    event.setDosage(getValue.getDosage() + "\n" + getValue.getDuration());

                                }
                                event.setFrequency(getValue.getDosage_frequency());
                                presciption_models.add(event);
                                presciption_adapter.notifyDataSetChanged();
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR, 10);
                intent.putExtra(AlarmClock.EXTRA_MINUTES, 20);
                startActivity(intent);

                // Toast.makeText(Patient_display.this, "Add Alarm", Toast.LENGTH_SHORT).show();

            }
        });

    }


    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

}
