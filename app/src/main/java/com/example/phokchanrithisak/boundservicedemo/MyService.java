package com.example.phokchanrithisak.boundservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyService extends Service {
    //IBinder builder java class - use to bind activities and services
    //Interface is parent of Binder and MyBinder

    IBinder binder = new MyBinder();
    public MyService(){
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }




    public int add(int x, int y) {
        int result = x * y;
        return result;
    }

}
