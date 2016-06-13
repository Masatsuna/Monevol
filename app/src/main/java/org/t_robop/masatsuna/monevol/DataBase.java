package org.t_robop.masatsuna.monevol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Masatsuna on 2016/06/07.
 */
public class DataBase {
    static SQLiteDatabase mydb; //MySQLiteOpenHelperの変数
    static MySQLiteOpenHelper hlpr;
    static ContentValues values;
    static Cursor cursor;
    static String openDB = "select * from billingTable order by year desc, month desc, date desc";
    final static String TABLE_NAME = "billingTable";

/////////////////////////aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/////////////////////////////
    public DataBase(Context context) {
        hlpr = new MySQLiteOpenHelper(context);
        mydb = hlpr.getWritableDatabase();
    }

    public static void insertData(Data data, Context context) {
        hlpr = new MySQLiteOpenHelper(context);
        mydb = hlpr.getWritableDatabase();
        values = new ContentValues();
        values.put("year", data.getYear());
        values.put("month", data.getMonth());
        values.put("date", data.getDate());
        values.put("appname", data.getAppname());
        values.put("billing", data.getBilling());
        mydb.insert("billingTable", null, values);
        mydb.close();
    }

    public static ArrayList openData(Context context){
        ArrayList <Data> DataArray=new ArrayList<Data>();
        Data data = new Data();
        hlpr = new MySQLiteOpenHelper(context);
        mydb = hlpr.getWritableDatabase();
        cursor = mydb.rawQuery(openDB,null);
        while(cursor.moveToNext()) {
            data.setId(cursor.getColumnIndex("_id"));
            data.setYear(cursor.getColumnIndex("year"));
            data.setMonth(cursor.getColumnIndex("month"));
            data.setDate(cursor.getColumnIndex("date"));
            data.setAppname(cursor.getString(cursor.getColumnIndex("appname")));
            data.setBilling(cursor.getColumnIndex("billing"));
            DataArray.add(data);
        }
        mydb.close();

        return DataArray;
    }
    public static void deleteData(int id){
        mydb.delete(TABLE_NAME, "_id = " + id, null);
    }

    public static void updateData(int id,Data data){
        ContentValues cv = new ContentValues();
        if (data.getYear()!=0){
            cv.put("year",data.getYear());
        }
        if (data.getMonth()!=0){
            cv.put("month",data.getMonth());
        }
        if (data.getDate()!=0){
            cv.put("date",data.getDate());
        }
        if (data.getAppname()!=null){
            cv.put("appname",data.getAppname());
        }
        //TODO 現状の仕様だと、0円と指定された場合データが更新されない
        if (data.getBilling()!=0){
            cv.put("billing",data.getBilling());
        }
        mydb.update(TABLE_NAME, cv, "_id ="+id, null);
    }
}
