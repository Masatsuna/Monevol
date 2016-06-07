package org.t_robop.masatsuna.monevol;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

/**
 * Created by Masatsuna on 2016/06/02.
 */
public class SQLite {

    static MySQLiteOpenHelper mydb; //MySQLiteOpenHelperの変数
    static ContentValues values;

    public void insertData(int month, int day, String appName, int billing) {
        values = new ContentValues();
        values.put(month, day, appName, billing);
    }

    //SQLiteOpenHelperの継承クラス
    public static class MySQLiteOpenHelper extends SQLiteOpenHelper {

        static final String dbName = "Billing.db";  //データベースの名前
        static final int dbVersion = 1;             //データベースのバージョン
        static final String create = "create table billingTable(month integer, day integer, appname text, billing integer)";    //データベースを作成するSQL文
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
}
