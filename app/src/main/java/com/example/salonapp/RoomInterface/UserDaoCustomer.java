package com.example.salonapp.RoomInterface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.salonapp.Model.GetCustomerNameTable;
import com.example.salonapp.Model.GetEmployModel;

import java.util.List;

@Dao
public interface UserDaoCustomer {

    @Query("SELECT * FROM CUSTOMER_NAME_TABLE")
            public List<GetCustomerNameTable> getAll();

    @Query("SELECT * FROM CUSTOMER_NAME_TABLE where CODE = :CODE")
    public List<GetCustomerNameTable> getCustomerByNo(String CODE);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<GetCustomerNameTable> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUsers(GetCustomerNameTable... users);

    @Query("DELETE FROM CUSTOMER_NAME_TABLE")
    void deleteAll();
}
