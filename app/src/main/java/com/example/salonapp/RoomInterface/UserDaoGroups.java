package com.example.salonapp.RoomInterface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.salonapp.Model.GroupsTable;
import com.example.salonapp.Model.Type;

import java.util.List;

@Dao
public interface UserDaoGroups {

    @Query("SELECT * FROM GROUP_TABLE")
            public List<GroupsTable> getAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<GroupsTable> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUsers(GroupsTable... users);

    @Query("DELETE FROM GROUP_TABLE")
    void deleteAll();
}
