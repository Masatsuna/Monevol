package org.t_robop.masatsuna.monevol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Masatsuna on 2016/06/07.
 */
public abstract class DataBase extends Context {
    static final String dbName = "Billing.db";  //データベースの名前
    static final int dbVersion = 1;             //データベースのバージョン
    static final String create = "create table billingTable(year integer, month integer, day integer, appname text, billing integer)";    //データベースを作成するSQL文
    static final String drop = "drop table billingTable";   //データベースを下ろすSQL文
    static SQLiteDatabase mydb; //MySQLiteOpenHelperの変数
    static MySQLiteOpenHelper hlpr;
    static ContentValues values;
    Cursor cursor;

    public void insertData(int year, int month, int day, String appName, int billing) {
        hlpr = new MySQLiteOpenHelper(this);
        mydb =hlpr.getWritableDatabase();
        values = new ContentValues();
        values.put("year", year);
        values.put("month", month);
        values.put("day", day);
        values.put("appName", appName);
        values.put("billing", billing);
        mydb.insert("billingTable", null, values);
        cursor = mydb.query("billingTable", new String[] {"year", "month", "day", "appName", "billing"}, null, null, null, null, null);
    }

    public String [][] opendData(){
        String[][] data;
        data = new String[5][];
        cursor = mydb.query("billingTable", new String[]{"year", "month", "day", "appname", "billing"}, null, null, null, null, null);
        while(cursor.moveToNext()) {
            int i = 0;
            data[0][i] = cursor.getString(cursor.getColumnIndex("year"));
            data[1][i] = cursor.getString(cursor.getColumnIndex("month"));
            data[2][i] = cursor.getString(cursor.getColumnIndex("day"));
            data[3][i] = cursor.getString(cursor.getColumnIndex("appName"));
            data[4][i] = cursor.getString(cursor.getColumnIndex("billing"));
            i++;
        }

        return data;
    }

    //SQLiteOpenHelperの継承クラス
    public static class MySQLiteOpenHelper extends SQLiteOpenHelper {

        //インスタンス
        public MySQLiteOpenHelper(DataBase context) {
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
