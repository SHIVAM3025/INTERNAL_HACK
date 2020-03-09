package com.example.internalhack.Models;

public class Name_model {

    String dosage_frequency;
    String duration;
    String dosage;

    public Name_model()
    {
        //Empty Constructor
    }

    public Name_model( String dosage , String dosage_frequency, String duration) {
        this.dosage_frequency = dosage_frequency;
        this.duration = duration;
        this.dosage = dosage;
    }

    public String getDosage() {
        return dosage;
    }

    public String getDosage_frequency() {
        return dosage_frequency;
    }

    public String getDuration() {
        return duration;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setDosage_frequency(String dosage_frequency) {
        this.dosage_frequency = dosage_frequency;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}