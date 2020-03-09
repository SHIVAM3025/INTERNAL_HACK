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

import com.example.internalhack.Models.Diagnosis_model;
import com.example.internalhack.R;

import java.util.List;

public class Patient_diagnosis extends RecyclerView.Adapter<Patient_diagnosis.HoldView>{

    private Context mcontext;
    private List<Diagnosis_model> modelList;

    public Patient_diagnosis(Context mcontext, List<Diagnosis_model> modelList) {
        this.mcontext = mcontext;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public HoldView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mcontext).inflate(R.layout.recycler_view_display ,parent , false);
        return new Patient_diagnosis.HoldView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HoldView holder, int position) {

        final Diagnosis_model uploadCurrent = modelList.get(position);
        holder.mfirst.setText(uploadCurrent.getFirst());
        holder.msecond.setText(uploadCurrent.getSecond());

        holder.msecond.setEnabled(false);
        holder.mdelete.setVisibility(View.GONE);


    }

    @Override
    public int getItemCount() {
        return modelList.size();
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
