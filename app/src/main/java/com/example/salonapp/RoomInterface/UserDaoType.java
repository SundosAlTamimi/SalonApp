package com.example.salonapp.RoomInterface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.salonapp.Model.Type;

import java.util.List;

@Dao
public interface UserDaoType {

    @Query("SELECT * FROM TYPETABLE")
            public List<Type> getAll();

    @Query("SELECT NAME FROM TYPETABLE")
    public List<String> getName();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Type> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUsers(Type... users);

    @Query("DELETE FROM TYPETABLE")
    void deleteAll();
}
