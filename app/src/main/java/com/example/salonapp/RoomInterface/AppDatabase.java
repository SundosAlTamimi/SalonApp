package com.example.salonapp.RoomInterface;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.salonapp.Model.AppoimentModel;
import com.example.salonapp.Model.GetCustomerNameTable;
import com.example.salonapp.Model.GetEmployModel;
import com.example.salonapp.Model.GroupsTable;
import com.example.salonapp.Model.SettingModel;
import com.example.salonapp.Model.Type;
import com.example.salonapp.Model.UserGroupModel;
import com.example.salonapp.Model.UserInfoModel;


@Database(entities = {Type.class, AppoimentModel.class, UserInfoModel.class, UserGroupModel.class, GroupsTable.class, GetEmployModel.class, SettingModel.class , GetCustomerNameTable.class}, version = 6, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDaoType TypeTable();
    public abstract UserDaoTableAppointment AppointmentTable();
    public abstract UserDaoUserInfoTable daoUserInfoTable();
    public abstract UserDaoUserGroupTable daoUserGroupTable();
    public abstract UserDaoGroups daoGroupTable();
    public abstract UserDaoEmploy daoEmployTable();
    public abstract UserDaoSetting userDaoSetting();
    public abstract UserDaoCustomer userDaoCustomer();


    private static AppDatabase InstanceDatabase;
    private static String DatabaseName="SALONDB";


    static final Migration FROM_1_TO_2 = new Migration(54, 55) {
        @Override
        public void migrate(final SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE Repo
//                    ADD COLUMN createdAt TEXT");
        }
    };

    public static synchronized AppDatabase getInstanceDatabase(Context context) {



        if (InstanceDatabase == null) {

            InstanceDatabase = Room.databaseBuilder(context, AppDatabase.class, DatabaseName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .fallbackToDestructiveMigrationFrom(36, 37, 38, 39,40)
                    .build();

        }

        return InstanceDatabase;

    }

}

