package org.t_robop.masatsuna.monevol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Masatsuna on 2016/06/07.
 */
public class DataBase {
    static SQLiteDatabase mydb; //MySQLiteOpenHelperの変数
    static MySQLiteOpenHelper hlpr;
    static ContentValues values;
    static Cursor cursor;
    static String openDB = "select * from billingTable order by year desc, month desc, date desc";

    public DataBase(Context context) {
        hlpr = new MySQLiteOpenHelper(context);
        mydb = hlpr.getWritableDatabase();
    }

    public static void insertData(int year, int month, int date, String appName, int billing , Context context) {
        hlpr = new MySQLiteOpenHelper(context);
        mydb = hlpr.getWritableDatabase();
        values = new ContentValues();
        values.put("year", year);
        values.put("month", month);
        values.put("date", date);
        values.put("appname", appName);
        values.put("billing", billing);
        mydb.insert("billingTable", null, values);
        mydb.close();
    }

    public static String [][] openData(){
        String[][] data;
        data = new String[5][];
        cursor = mydb.rawQuery(openDB,null);
        if(cursor.moveToNext()) {
            int i = 0;
            data[i][0] = cursor.getString(cursor.getColumnIndex("year"));
            data[i][1] = cursor.getString(cursor.getColumnIndex("month"));
            data[i][2] = cursor.getString(cursor.getColumnIndex("date"));
            data[i][3] = cursor.getString(cursor.getColumnIndex("appname"));
            data[i][4] = cursor.getString(cursor.getColumnIndex("billing"));
            i++;
        }

        return data;
    }
}
