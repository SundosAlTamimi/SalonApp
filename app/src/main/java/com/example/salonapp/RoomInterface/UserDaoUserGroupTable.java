package com.example.salonapp.RoomInterface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.salonapp.Model.UserGroupModel;
import com.example.salonapp.Model.UserInfoModel;

import java.util.List;

@Dao
public interface UserDaoUserGroupTable {

    @Query("SELECT * FROM USER_GROUP_TABLE")
            public List<UserGroupModel> getAll();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<UserGroupModel> users);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertUsers(UserGroupModel... users);

    @Query("DELETE FROM USER_GROUP_TABLE")
    void deleteAll();
//    @Query("UPDATE TRANSACTION_TABLE SET Show = :show WHERE TRANS_KIND = :transKind")
//    void UpdateShow(String transKind,String show);

//    @Query("UPDATE TRANSACTION_TABLE SET Show = :show ")
//    void UpdateAllShow(String show);

//    @Query("SELECT * FROM TRANSACTION_TABLE where TRANS_KIND = :transKind")
//    public List<TransactionTable> GetIfShow(String transKind);
}
