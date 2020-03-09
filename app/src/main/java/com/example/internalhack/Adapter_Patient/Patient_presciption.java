package com.example.internalhack.Adapter_Patient;

import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internalhack.Models.Alarm_Model;
import com.example.internalhack.Models.Presciption_model;
import com.example.internalhack.R;

import java.util.List;

public class Patient_presciption extends RecyclerView.Adapter<Patient_presciption.HoldView>{


    private Context mcontext;
    private List<Presciption_model> models;

    public Patient_presciption(Context mcontext, List<Presciption_model> models) {
        this.mcontext = mcontext;
        this.models = models;
    }

    @NonNull
    @Override
    public HoldView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.patient_display , parent , false);
        return new Patient_presciption.HoldView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HoldView holder, int position) {

        final Presciption_model uploadCurrent = models.get(position);

        holder.mmedicine.setText(uploadCurrent.getMedicine());
        holder.mdosage.setText(uploadCurrent.getDosage());
        holder.mfrequency.setText(uploadCurrent.getFrequency());

        holder.alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Alarm_Model alarm_model = new Alarm_Model(uploadCurrent.getMedicine() , uploadCurrent.getFrequency());

                int DAY = alarm_model.no_of_days(uploadCurrent.getFrequency());

                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR , 10);
                intent.putExtra(AlarmClock.EXTRA_MINUTES , 30);
                intent.putExtra(AlarmClock.EXTRA_MESSAGE , uploadCurrent.getMedicine());
                mcontext.startActivity(intent);

            }
        });
        holder.mmedicine.setEnabled(false);
        holder.mdosage.setEnabled(false);
        holder.mfrequency.setEnabled(false);

    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class HoldView extends RecyclerView.ViewHolder{

        EditText mmedicine , mdosage , mfrequency;
        ImageView alarm;

        public HoldView(@NonNull View itemView) {
            super(itemView);

            alarm = itemView.findViewById(R.id.add_alarm);
            mmedicine = itemView.findViewById(R.id.medicine);
            mdosage = itemView.findViewById(R.id.dosage);
            mfrequency = itemView.findViewById(R.id.frequency);

        }
    }



}
