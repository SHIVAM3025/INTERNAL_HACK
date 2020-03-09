package com.example.internalhack.Adapter_Patient;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internalhack.Models.Advice_model;
import com.example.internalhack.R;

import java.util.List;

public class Patient_advice extends RecyclerView.Adapter<Patient_advice.HoldView>{


    private Context mcontext;
    private List<Advice_model> models;

    public Patient_advice(Context mcontext, List<Advice_model> models) {
        this.mcontext = mcontext;
        this.models = models;
    }


    @NonNull
    @Override
    public HoldView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.recycler_view_display ,parent , false);
        return new Patient_advice.HoldView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HoldView holder, int position) {
        final Advice_model uploadCurrent = models.get(position);
        holder.mfirst.setText(uploadCurrent.getFirst());
        holder.msecond.setText(uploadCurrent.getSecond());

       holder.msecond.setEnabled(false);

       holder.mminus.setVisibility(View.GONE);
    }


    @Override
    public int getItemCount() {
        return  models.size();
    }

    public class HoldView extends RecyclerView.ViewHolder
    {
        EditText msecond ;
        TextView mfirst;
        ImageView mminus;

        public HoldView(@NonNull View itemView) {

            super(itemView);

            mminus =itemView.findViewById(R.id.delete);
            mfirst  =itemView.findViewById(R.id.one);
            msecond = itemView.findViewById(R.id.second);

        }
    }



}
