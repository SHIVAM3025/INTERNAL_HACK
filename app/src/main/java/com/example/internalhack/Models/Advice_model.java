package com.example.internalhack.Models;

public class Advice_model {

    String first;
    String second;

    public Advice_model()
    {
            //Empty Constructor
    }

    public Advice_model(String first, String second) {
        this.first = first;
        this.second = second;
    }

    //Getter


    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    //Setter


    public void setFirst(String first) {
        this.first = first;
    }

    public void setSecond(String second) {
        this.second = second;
    }
}
