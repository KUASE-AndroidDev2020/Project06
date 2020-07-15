package com.example.myreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button seebacklog = findViewById(R.id.seebacklog);
        seebacklog.setOnClickListener(this);
        createNotificationChannel();
    }
    public void setReminder(View view) {
        Toast.makeText(this,"Reminder set!",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,ReminderBroadcastReceiver.class);
        PendingIntent pd = PendingIntent.getBroadcast(this,0,intent,0);
        AlarmManager alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE);
        long interval = 1000*6;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),interval,pd);

    }

private void createNotificationChannel(){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        String channelID="Your Reminder";
        String channelName="YourReminderChannel";
        String channelIDescription="Channel for Your Reminder";
        int importance= NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(channelID,channelName,importance);
        channel.setDescription(channelIDescription);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.seebacklog:
            Intent toLog= new Intent(this,Backlog.class);
            startActivity(toLog);
            break;
        }

    }
}

