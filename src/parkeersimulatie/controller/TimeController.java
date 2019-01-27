package parkeersimulatie.controller;

import mvc.Controller;
import mvc.View;

public class TimeController extends Controller {

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    public TimeController() {

    }

    public int getDay() {
        return day;
    }

    public int getHour(){
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


    @Override
    protected boolean event(View view, int event_id) {
        return false;
    }
}
