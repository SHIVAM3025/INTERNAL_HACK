package com.example.internalhack.Models;

import android.content.Intent;

import java.util.ArrayList;

public class Alarm_Model {

    private String Medicine;
    private String frequency;

    public Alarm_Model(String medicine, String frequency) {
        Medicine = medicine;
        this.frequency = frequency;
    }

    public int no_of_days(String du)
    {
        String[] b = du.split(" ", -2);

        int days = 0;
        for(int i=0;i<b.length;i++)
        {
            String before;
            String s = b[i];
            switch(s)
            {
                case "week": case "weeks": case "Week": case "Weeks":
                before = b[i-1];
                switch(before)
                {
                    case "1":case "one":case "One": case "a": case "A": days = 7; break;
                    case "2":case "two":case "Two": days = 14; break;
                    case "3":case "three":case "Three": days = 21; break;
                    case "4":case "four":case "Four": days = 28; break;
                    case "5":case "five":case "Five": days = 35; break;
                    case "6":case "six":case "Six": days = 42; break;
                    case "7":case "seven":case "Seven": days = 49; break;
                    case "8":case "eight":case "Eight": days = 56; break;
                    case "9":case "nine":case "Nine": days = 63; break;
                    case "10":case "ten":case "Ten": days = 70; break;
                    default:
                        int x = Integer.parseInt(before);
                        days = x * 7;
                }
                break;

                case "day": case "days": case "Day": case "Days":
                before = b[i-1];
                switch(before)
                {
                    case "1":case "one":case "One": case "a": case "A": days = 1; break;
                    case "2":case "two":case "Two": days = 2; break;
                    case "3":case "three":case "Three": days = 3; break;
                    case "4":case "four":case "Four": days = 4; break;
                    case "5":case "five":case "Five": days = 5; break;
                    case "6":case "six":case "Six": days = 6; break;
                    case "7":case "seven":case "Seven": days = 7; break;
                    case "8":case "eight":case "Eight": days = 8; break;
                    case "9":case "nine":case "Nine": days = 9; break;
                    case "10":case "ten":case "Ten": days = 10; break;
                    default:
                        int x = Integer.parseInt(before);
                        days = x;
                }
                break;
                case "month": case "months": case "Month": case "Months":
                before = b[i-1];
                switch(before)
                {
                    case "1":case "one":case "One": case "a": case "A": days = 1*30; break;
                    case "2":case "two":case "Two": days = 2*30; break;
                    case "3":case "three":case "Three": days = 3*30; break;
                    case "4":case "four":case "Four": days = 4*30; break;
                    case "5":case "five":case "Five": days = 5*30; break;
                    case "6":case "six":case "Six": days = 6*30; break;
                    case "7":case "seven":case "Seven": days = 7*30; break;
                    case "8":case "eight":case "Eight": days = 8*30; break;
                    case "9":case "nine":case "Nine": days = 9*30; break;
                    case "10":case "ten":case "Ten": days = 10*30; break;
                    case "11":case "eleven":case "Eleven": days = 11*30; break;
                    case "12":case "twelve":case "Twelve": days = 12*30; break;
                    default:
                        int x = Integer.parseInt(before);
                        days = x*30;
                }
                break;

                case "year": case "years": case "Year": case "Years":
                before = b[i-1];
                switch(before)
                {
                    case "1":case "one":case "One": case "a": case "A": days = 1*365; break;
                    case "2":case "two":case "Two": days = 2*365; break;
                    case "3":case "three":case "Three": days = 3*365; break;
                    case "4":case "four":case "Four": days = 4*365; break;
                    case "5":case "five":case "Five": days = 5*365; break;
                    case "6":case "six":case "Six": days = 6*365; break;
                    case "7":case "seven":case "Seven": days = 7*365; break;
                    case "8":case "eight":case "Eight": days = 8*365; break;
                    case "9":case "nine":case "Nine": days = 9*365; break;
                    case "10":case "ten":case "Ten": days = 10*365; break;
                    case "11":case "eleven":case "Eleven": days = 11*365; break;
                    case "12":case "twelve":case "Twelve": days = 12*365; break;
                    default:
                        int x = Integer.parseInt(before);
                        days = x*365;
                }
                break;
            }
        }
        //System.out.println(days);

        return days;

    }

    public int minutes(String dof , int days)
    {

        String[] b =  b = dof.split(" ",-2);
        int d = 0;
        ArrayList<String> times = new ArrayList<>();

        double dosageperday = 0.0;

        for(int i=0;i<b.length;i++)
        {
//			System.out.println(b[i]);
            switch(b[i])
            {
                case "lunch": case "Lunch": times.add("1400"); d++; break;
                case "Dinner": case "dinner": case "sleep": case "Sleep":case "Sleeping": case "sleeping": times.add("2100"); d++; break;
                case "Breakfast": case "breakfast": times.add("1000"); d++; break;
            }
//			System.out.println(times.size());
        }
        dosageperday = d;

        if(d == 0)
        {
            boolean after = false;
            for(int i=0;i<b.length;i++)
            {
                switch(b[i])
                {
                    case"after":case"After":case"Before":case"before":case"Every":case"every": after = true;break;
                }
            }
            boolean gaya = false;
            for(int i=0;!after && i<b.length;i++)
            {
                String x = b[i];
                switch(x)
                {
                    case "once": case "Once": case "one": case "One": case "1": times.add("2100"); gaya = true; d++; break;
                    case "twice": case "Twice": case "two": case "Two": case "2":
                    times.add("1100");
                    times.add("2100");
                    d+=2;gaya = true; break;
                    case "thrice": case "Thrice": case "three": case "Three": case "3":
                    times.add("1100");
                    times.add("1600");
                    times.add("2100");
                    d+=3; gaya = true;break;
                    case "4": case"four":case"Four": d+=4;gaya = true; break;
                    case "5": case"five":case"Five": d+=5;gaya = true; break;
                    case "6": case"six":case"Six": d+=6; gaya = true;break;
                }
            }


            dosageperday = d;
            if (!after && gaya) {
                for(int i=0;i<b.length;i++)
                {
                    int factor = 1;
                    switch(b[i])
                    {
                        case"day":case"Day": factor = 1;    break;
                        case"week":case"Week":factor = 7;   break;
                        case"month":case"Month":factor = 30;break;
                        case"year":case"Year":factor = 365; break;
                    }
                    days /= factor;
                    dosageperday /= factor;
                }
            }

        }
        for(String t : times)
            System.out.println(t);


        return Integer.parseInt(times.get(1));

    }

    public int time(String dof , int days)
    {
        String[] b =  b = dof.split(" ",-2);
        int d = 0;
        ArrayList<String> times = new ArrayList<>();

        double dosageperday = 0.0;

        for(int i=0;i<b.length;i++)
        {
//			System.out.println(b[i]);
            switch(b[i])
            {
                case "lunch": case "Lunch": times.add("1400"); d++; break;
                case "Dinner": case "dinner": case "sleep": case "Sleep":case "Sleeping": case "sleeping": times.add("2100"); d++; break;
                case "Breakfast": case "breakfast": times.add("1000"); d++; break;
            }
//			System.out.println(times.size());
        }
        dosageperday = d;

        if(d == 0)
        {
            boolean after = false;
            for(int i=0;i<b.length;i++)
            {
                switch(b[i])
                {
                    case"after":case"After":case"Before":case"before":case"Every":case"every": after = true;break;
                }
            }
            boolean gaya = false;
            for(int i=0;!after && i<b.length;i++)
            {
                String x = b[i];
                switch(x)
                {
                    case "once": case "Once": case "one": case "One": case "1": times.add("2100"); gaya = true; d++; break;
                    case "twice": case "Twice": case "two": case "Two": case "2":
                    times.add("1100");
                    times.add("2100");
                    d+=2;gaya = true; break;
                    case "thrice": case "Thrice": case "three": case "Three": case "3":
                    times.add("1100");
                    times.add("1600");
                    times.add("2100");
                    d+=3; gaya = true;break;
                    case "4": case"four":case"Four": d+=4;gaya = true; break;
                    case "5": case"five":case"Five": d+=5;gaya = true; break;
                    case "6": case"six":case"Six": d+=6; gaya = true;break;
                }
            }


            dosageperday = d;
            if (!after && gaya) {
                for(int i=0;i<b.length;i++)
                {
                    int factor = 1;
                    switch(b[i])
                    {
                        case"day":case"Day": factor = 1;    break;
                        case"week":case"Week":factor = 7;   break;
                        case"month":case"Month":factor = 30;break;
                        case"year":case"Year":factor = 365; break;
                    }
                    days /= factor;
                    dosageperday /= factor;
                }
            }

        }
        for(String t : times)
            System.out.println(t);


        return Integer.parseInt(times.get(0));

    }

}
