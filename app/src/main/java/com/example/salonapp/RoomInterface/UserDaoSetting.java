package com.example.salonapp.RoomInterface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.salonapp.Model.GetEmployModel;
import com.example.salonapp.Model.SettingModel;

import java.util.List;

@Dao
public interface UserDaoSetting {

    @Query("SELECT * FROM SETTING_TABLE")
            public SettingModel getAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SettingModel> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUsers(SettingModel... users);

    @Query("DELETE FROM SETTING_TABLE")
    void deleteAll();
}
