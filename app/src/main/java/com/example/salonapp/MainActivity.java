package com.example.salonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.mbms.MbmsErrors;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.example.salonapp.DataServer.importJson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Intent appIntent;
    EditText userName,Password;
    TextView logInButton,setting;
    importJson importJsonD;
    String today="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Initialization();


    }



    private void Initialization() {

        importJsonD=new importJson(MainActivity.this);
        userName=findViewById(R.id.userName);
        Password=findViewById(R.id.password);
        logInButton=findViewById(R.id.LogInB);
        setting=findViewById(R.id.setting);



        Date currentTimeAndDate = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        today = df.format(currentTimeAndDate);

        importJsonD.getAll(today);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
                appIntent=new Intent(MainActivity.this,FirstLayoutApp.class);
                appIntent.putExtra("FF","0");
                startActivity(appIntent);

            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingInt=new Intent(MainActivity.this,SettingActivity.class);
                startActivity(settingInt);


            }
        });

    }


}

