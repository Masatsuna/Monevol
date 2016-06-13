package org.t_robop.masatsuna.monevol;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Masatsuna on 2016/06/10.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    static final String dbName = "Billing.db";  //データベースの名前
    static final int dbVersion = 1;             //データベースのバージョン
    static final String create = "create table billingTable(_id integer primary key not null,year integer, month integer, date integer, appname text, billing integer)";    //データベースを作成するSQL文
    static final String drop = "drop table billingTable";   //データベースを下ろすSQL文


    //インスタンス
    public MySQLiteOpenHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(drop);
        onCreate(db);
    }



}
