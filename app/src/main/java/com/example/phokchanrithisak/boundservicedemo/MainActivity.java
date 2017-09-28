package com.example.phokchanrithisak.boundservicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2;
    MyService mservice;
    boolean status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit1 = (EditText) findViewById(R.id.editText);
        edit2 = (EditText) findViewById(R.id.editText2);
    }

    public void bindMethod(View view){
        Intent i = new Intent(this, MyService.class);
        //connection between client & services
        bindService(i, sc, Context.BIND_AUTO_CREATE);
        status = true;
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
    }
    public void unbindMethod (View view){
        if (status==true){
            unbindService(sc);
            status = false;
            Toast.makeText(this, "Unbinded Service", Toast.LENGTH_SHORT).show();
        }
    }
    public void addMethod (View view){
        if(status){
            int num1 = Integer.parseInt(edit1.getText().toString());
            int num2 = Integer.parseInt(edit2.getText().toString());
            int result = mservice.add(num1, num2);
            Toast.makeText(this, "Sum is "+result, Toast.LENGTH_SHORT).show();
        }
    }
    //auto filled (onServicesConnected & onServicesDisconnected)
    //use to pass object while bind the services
    //virtual machine is parent, it takes care of services

    //this two function define what services should do
    ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder)service;
            mservice = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {


        }
    };
}
