package com.example.internalhack.Adapter_Patient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internalhack.Models.Symptoms_model;
import com.example.internalhack.R;

import java.util.List;

public class Patient_symptoms extends RecyclerView.Adapter<Patient_symptoms.HoldView>{



    private Context mcontext;
    private List<Symptoms_model> models;

    public Patient_symptoms(Context mcontext, List<Symptoms_model> models) {
        this.mcontext = mcontext;
        this.models = models;
    }

    @NonNull
    @Override
    public HoldView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.recycler_view_display ,parent , false);
        return new Patient_symptoms.HoldView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HoldView holder, int position) {

        final Symptoms_model uploadCurrent = models.get(position);
        holder.mfirst.setText(uploadCurrent.getFirst());
        holder.msecond.setText(uploadCurrent.getSecond());

        holder.mdelete.setVisibility(View.GONE);
        holder.msecond.setEnabled(false);


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class HoldView extends RecyclerView.ViewHolder
    {
        EditText msecond ;
        TextView mfirst;
        ImageView mdelete;

        public HoldView(@NonNull View itemView) {

            super(itemView);

            mfirst  =itemView.findViewById(R.id.one);
            msecond = itemView.findViewById(R.id.second);
            mdelete = itemView.findViewById(R.id.delete);

        }
    }

}
