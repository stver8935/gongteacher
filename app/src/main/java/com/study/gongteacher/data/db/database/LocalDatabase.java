package com.study.gongteacher.data.db.database;

import android.content.Context;

import androidx.annotation.WorkerThread;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.study.gongteacher.data.db.dao.UserDao;
import com.study.gongteacher.data.dto.User;


public abstract class LocalDatabase extends RoomDatabase {

    private static LocalDatabase localDatabase;

    private static String DbName = "local_database";
    @WorkerThread
    public abstract UserDao userdao();

    private static LocalDatabase initialize(Context context){
        localDatabase = Room.databaseBuilder(context.getApplicationContext(), LocalDatabase.class,DbName).fallbackToDestructiveMigration().build();
        return localDatabase;
    }

    public static LocalDatabase getInstance(Context context){
        if(localDatabase != null){
            return initialize(context);
        }else{
            return localDatabase;
        }
    }

    public static void destroyInstance(){
        localDatabase = null;
    }

    public static void addUser(final LocalDatabase localDB , final User user){
        Thread thread = new Thread(){
            @Override
            public void run(){
                super.run();
                localDB.userdao().inserAll(user);
            }
        };
        thread.start();
    }


    public static void dropTable(LocalDatabase localDB){
        localDB.userdao().dropTable();
    }

}
