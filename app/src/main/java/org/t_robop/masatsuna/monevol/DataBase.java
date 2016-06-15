package org.t_robop.masatsuna.monevol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Masatsuna on 2016/06/07.
 */

//TODO このCLASSには極力弄らないこと、弄る場合は新しくメソッドを作成し、その中だけで
public class DataBase {
    static SQLiteDatabase mydb; //MySQLiteOpenHelperの変数
    static MySQLiteOpenHelper hlpr;
    static ContentValues values;
    static Cursor cursor;
    //orderで並び順を指定 ASCで昇順  DESCで降順
    //static String openDB = "select * from billingTable order by year desc, month desc, date desc";
    static String openDB = "select * from billingTable order by _id asc";
    final static String TABLE_NAME = "billingTable";

    public DataBase(Context context) {
        hlpr = new MySQLiteOpenHelper(context);
        mydb = hlpr.getWritableDatabase();
    }

    //データを追加するメソッド
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

    //全てのデータを取得するメソッド
    public static ArrayList openData(Context context){
        ArrayList <Data> DataArray=new ArrayList<>();
        hlpr = new MySQLiteOpenHelper(context);
        mydb = hlpr.getWritableDatabase();
        cursor = mydb.rawQuery(openDB,null);
        while(cursor.moveToNext()) {
            //ここで生成しないと、同じデータが追加され続ける
            Data data = new Data();
            //ID
            data.setId(cursor.getInt(0));
            //yaer
            data.setYear(cursor.getInt(1));
            //
            data.setMonth(cursor.getInt(2));
            //
            data.setDate(cursor.getInt(3));
            //
            data.setAppname(cursor.getString(cursor.getColumnIndex("appname")));
            //
            data.setBilling(cursor.getInt(5));
            //dataを配列に追加
            DataArray.add(data);
        }
        //dbを閉じる
        mydb.close();

        return DataArray;
    }
    //年を検索するメソッド
    public static ArrayList yearSelect(int year,Context context){
        String sqlstr = "select *"
                +"from "+TABLE_NAME
                + " where year like" +"'%"+year+"%'"
                + "order by year asc, month asc, date asc";
        ArrayList <Data> DataArray=new ArrayList<>();
        hlpr = new MySQLiteOpenHelper(context);
        mydb = hlpr.getWritableDatabase();
        cursor = mydb.rawQuery(sqlstr,null);
        while(cursor.moveToNext()) {
            Data data = new Data();
            //ID
            data.setId(cursor.getInt(0));
            //yaer
            data.setYear(cursor.getInt(1));
            //
            data.setMonth(cursor.getInt(2));
            //
            data.setDate(cursor.getInt(3));
            //
            data.setAppname(cursor.getString(cursor.getColumnIndex("appname")));
            //
            data.setBilling(cursor.getInt(5));
            //listに追加
            DataArray.add(data);
        }
        mydb.close();

        return DataArray;
    }
    //年と月で検索するメソッド(ソート済み)
    public static ArrayList yearMonthSelect(int year,int month,Context context){
        String sqlstr = "select *"
                +"from "+TABLE_NAME
                + " where year = "+year+" AND month = "+month
                + " order by year asc, month asc, date asc";
        ArrayList <Data> DataArray=new ArrayList<>();
        hlpr = new MySQLiteOpenHelper(context);
        mydb = hlpr.getWritableDatabase();
        cursor = mydb.rawQuery(sqlstr,null);
        while(cursor.moveToNext()) {
            Data data = new Data();
            //ID
            data.setId(cursor.getInt(0));
            //yaer
            data.setYear(cursor.getInt(1));
            //
            data.setMonth(cursor.getInt(2));
            //
            data.setDate(cursor.getInt(3));
            //
            data.setAppname(cursor.getString(cursor.getColumnIndex("appname")));
            //
            data.setBilling(cursor.getInt(5));
            //listに追加
            DataArray.add(data);
        }
        mydb.close();

        return DataArray;
    }


    //idから一つの行を検索するメソッド
    public static Data idSelect(int id,Context context){
        String sqlstr = "select *"
                +"from "+TABLE_NAME
                +" where _id like" +"'%"+id+"%'";
        hlpr = new MySQLiteOpenHelper(context);
        mydb = hlpr.getWritableDatabase();
        cursor = mydb.rawQuery(sqlstr,null);
        cursor.moveToNext();
        Data data = new Data();
        //ID
        data.setId(cursor.getInt(0));
        //yaer
        data.setYear(cursor.getInt(1));
        //
        data.setMonth(cursor.getInt(2));
        //
        data.setDate(cursor.getInt(3));
        //
        data.setAppname(cursor.getString(cursor.getColumnIndex("appname")));
        //
        data.setBilling(cursor.getInt(5));
        mydb.close();
        return data;
    }


    //データを削除する時のメソッド
    public static void deleteData(int id,Context context) {
        hlpr = new MySQLiteOpenHelper(context);
        mydb = hlpr.getWritableDatabase();
        mydb.delete(TABLE_NAME, "_id = " + id, null);
    }

    //行のデータを更新する時のメソッド
    public static void updateData(int id,Data data,Context context){
        hlpr = new MySQLiteOpenHelper(context);
        mydb = hlpr.getWritableDatabase();
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
        mydb.close();
    }
}
