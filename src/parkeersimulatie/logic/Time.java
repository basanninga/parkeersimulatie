package parkeersimulatie.logic;

import java.util.Objects;

public class Time extends AbstractModel {

    private int day = 0;
    private int hour = 0;
    private int minute = 0;
    private int week = 0;

    private int runningDay;


    /**
     Constructor voor tijd.
     */
    public Time() {

    }


    /**
     * Geeft de dag terug
     *
     * @return int
     */
    public int getDay() {
        return day;
    }



    /**
     * Geeft huidige dag terug
     * @return int
     */
    public int getRunningDay(){
        return runningDay;
    }

    /**
     *
     * @return huidige uur
     */
    public int getHour() {
        return hour;
    }


    /**
     Methode die de tijd voortzet.
     */
    public void advanceTime() {
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
            runningDay++;
        }
        while (day > 6) {
            day -= 7;
            week++;
        }

    }

    /**
     Methode voor het ophalen van de tijd.
     @return String
     */
    public String getClock(){
        String hourString = Objects.toString(hour);
        String miniuteString = Objects.toString(minute);
        if(hour< 10){
            hourString = "0" + hourString;

        }
        if(minute < 10){
            miniuteString = "0" + miniuteString;
        }

        return hourString + ":" + miniuteString;
    }

    /**
     Methode voor het ophalen van de huidige dag.
     @return String
     */
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

    public int getWeek(){
        return week;
    }
}
