package com.example.salonapp.RoomInterface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.salonapp.Model.GetEmployModel;
import com.example.salonapp.Model.GroupsTable;

import java.util.List;

@Dao
public interface UserDaoEmploy {

    @Query("SELECT * FROM EMPLOY_TABLE")
            public List<GetEmployModel> getAll();

    @Query("SELECT * FROM EMPLOY_TABLE where CODE = :CODE")
    public List<GetEmployModel> getEmployByNo(String CODE);

    @Query("SELECT *\n" +
            "FROM EMPLOY_TABLE\n" +
            "WHERE CODE  IN\n" +
            "    (SELECT EMPLOYEE_NO \n" +
            "     FROM APPOINTMENT_TABLE GROUP by EMPLOYEE_NO)")
    public List<GetEmployModel> getEmployByWork();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<GetEmployModel> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUsers(GetEmployModel... users);

    @Query("DELETE FROM EMPLOY_TABLE")
    void deleteAll();
}
