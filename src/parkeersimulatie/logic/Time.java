package parkeersimulatie.logic;


import java.util.Objects;

public class Time extends AbstractModel {

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    public Time() {

    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public void advanceTime() {
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

        System.out.println(day + " " + hour + ":" + minute);


    }

    public String getClock(){
        String hourString = Objects.toString(hour);
        String miniuteString = Objects.toString(minute);;
        if(hour< 10){
            hourString = "0" + hourString;

        }
        if(minute < 10){
            miniuteString = "0" + miniuteString;
        }

        return hourString + ":" + miniuteString;
    }

    public String getDayString(){
        String dayString = "";
        switch (day){
            case 0: dayString = "maandag";
                    break;
            case 1: dayString = "disndag";
                break;
            case 2: dayString = "woensdag";
                break;
            case 3: dayString = "donderdag";
                break;
            case 4: dayString = "vrijdag";
                break;
            case 5: dayString = "zaterdag";
                break;
            case 6: dayString = "zondag";
                break;
        }
        return dayString;
    }
}