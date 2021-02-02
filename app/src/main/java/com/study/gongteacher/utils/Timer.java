package com.study.gongteacher.utils;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

public class Timer extends Thread {

    private Handler handler;
    //설정 시간
    private long millis;
    private TimerConverter timerConverter;

    public Timer(Handler handler ,long millis){
        this.millis = millis;
        this.handler = handler;
        timerConverter = new TimerConverter();
    }

    @Override
    public void run() {

        Log.d("timer","run");
        int maxseconds = (int) (millis/1000);

        while(!isInterrupted()) {

            try {


                for (int i =0 ; i<maxseconds; i++){

                Thread.sleep(1000);
                millis -= 1000;

                Log.d("timer", "run: "+millis);
                Bundle bundle = new Bundle();
                Message message = new Message();

                bundle.putString("type","timer");
                bundle.putLong("time",millis);

                message.setData(bundle);
                handler.sendMessage(message);
                }
                interrupt();

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

            } catch (Exception e) {

                e.printStackTrace();

            }

        }



    }
}
