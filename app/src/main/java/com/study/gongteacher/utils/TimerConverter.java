package com.study.gongteacher.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimerConverter {

    //long type 의 millis 데이터를 스톱워치 형식의 String 데이터로 반환
    public String convertStopWatch(long millis){

        int hour = (int) (millis/1000/60/60);
        int seconds = (int) (millis/1000);
        int min = (int) (millis/1000/60);


        String timeString = "";
        String hourSting ="";
        String minString ="";
        String secString ="";

        if (0<hour){
            hourSting = chDigitsNum(hour);
            timeString +=hourSting+":";
        }

        if (0<min){
            minString = chDigitsNum(min)+" : ";
        }else{
            minString = "00:";
        }

        timeString += minString;

        if (0<seconds) {
            secString = chDigitsNum(seconds);
        }else{
            secString += "00";
        }
        timeString+=secString;

        return timeString;
    }

    //시간이 한자리수일때 앞에 0을 붙여 자릿수를 변경해주는 함수
    private String chDigitsNum(long time){
        String timeString = String.valueOf(time);

        if (timeString.length()<=1){
            timeString = 0+String.valueOf(time);
        }

        return timeString;
    }


}
