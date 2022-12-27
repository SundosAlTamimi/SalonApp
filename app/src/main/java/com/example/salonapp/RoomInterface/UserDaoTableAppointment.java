package com.example.salonapp.RoomInterface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.salonapp.Model.AppoimentModel;

import java.util.List;

@Dao
public interface UserDaoTableAppointment {

    @Query("SELECT * FROM APPOINTMENT_TABLE")
            public List<AppoimentModel> getAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<AppoimentModel> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUsers(AppoimentModel... users);

    @Query("DELETE FROM APPOINTMENT_TABLE")
    void deleteAll();

    @Query("SELECT * FROM APPOINTMENT_TABLE where EMPLOYEE_NO = :EMPLOYEE_NO ")
    List<AppoimentModel> getAppimint(String EMPLOYEE_NO);
}
