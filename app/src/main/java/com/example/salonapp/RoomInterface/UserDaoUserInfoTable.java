package com.example.salonapp.RoomInterface;



import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.salonapp.Model.UserInfoModel;

import java.util.List;

@Dao
public interface UserDaoUserInfoTable {

    @Query("SELECT * FROM USER_INFO_TABLE")
            public List<UserInfoModel> getAll();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<UserInfoModel> users);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertUsers(UserInfoModel... users);

    @Query("DELETE FROM USER_INFO_TABLE")
    void deleteAll();

    @Query("select * from USER_INFO_TABLE where UPPER(NAME)=UPPER(:user_name) and PASSWORD=:passw and ACTIVE=1")
    public List<UserInfoModel> getUserInfo(String user_name,String passw);
//    @Query("UPDATE TRANSACTION_TABLE SET Show = :show WHERE TRANS_KIND = :transKind")
//    void UpdateShow(String transKind,String show);

//    @Query("UPDATE TRANSACTION_TABLE SET Show = :show ")
//    void UpdateAllShow(String show);

//    @Query("SELECT * FROM TRANSACTION_TABLE where TRANS_KIND = :transKind")
//    public List<TransactionTable> GetIfShow(String transKind);
}
