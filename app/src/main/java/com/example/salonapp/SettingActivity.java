package com.example.salonapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salonapp.DataServer.importJson;
import com.example.salonapp.Model.SettingModel;

public class SettingActivity extends AppCompatActivity {

    EditText ip,cono;
    TextView save;
    importJson importData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_dialog);

        Initialization();

    }

    private void Initialization() {

        ip=findViewById(R.id.ip);
        cono=findViewById(R.id.cono);
        save=findViewById(R.id.save);
        ControllClass controllClass=new ControllClass(SettingActivity.this);
         importData=new importJson(SettingActivity.this);
       SettingModel settingModel= controllClass.getSettings();
       if(settingModel!=null){

           ip.setText(""+settingModel.getIp());
           cono.setText(""+settingModel.getCono());

       }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!ip.getText().toString().equals("")){
                    if(!cono.getText().toString().equals("")){

                        SettingModel settingModel=new SettingModel();
                        settingModel.setIp(ip.getText().toString());
                        settingModel.setCono(cono.getText().toString());
                        controllClass.saveSetting(settingModel);
                        importData=new importJson(SettingActivity.this);
                        importData.getAll(controllClass.getDay());
                        finish();
         //               Toast.makeText(SettingActivity.this, "Save Successful", Toast.LENGTH_SHORT).show();


                    }else {
                        cono.setError("Required!");
                    }

                }else {
                    ip.setError("Required!");
                }

            }
        });



    }

}
