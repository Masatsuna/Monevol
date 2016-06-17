package org.t_robop.masatsuna.monevol;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    static SQLiteDatabase mydb; //MySQLiteOpenHelperの変数
    static MySQLiteOpenHelper hlpr;
    static ArrayList yearMonthSelect;
    static ContentValues values;
    static Cursor cursor;
    static String openDB = "select * from billingTable order by year desc, month desc, date desc";
    DataBase db;
    Data testData = new Data();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentViewより前にWindowにActionBar表示を設定
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_setting);
        hlpr = new MySQLiteOpenHelper(this);
        mydb = hlpr.getWritableDatabase();
        db = new DataBase(this);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // メニューの要素を追加
        menu.add("Normal item");

        // メニューの要素を追加して取
        MenuItem actionItem = menu.add("Action Button");

        // SHOW_AS_ACTION_IF_ROOM:余裕があれば表示
        actionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        // アイコンを設定
        actionItem.setIcon(android.R.drawable.ic_menu_share);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("Normal item")){
            Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    public void onClick(View view) {

        switch (view.getId()){

            case R.id.insert:
                testData.setAppname("aaaaa");
                testData.setYear(2015);
                testData.setMonth(11);
                testData.setDate(30);
                testData.setBilling(10);
                DataBase.insertData(testData,this);
                break;


            case R.id.open:
                testData.setAppname("aaaaa");
                testData.setDate(30);
                testData.setBilling(10);
                //DataBase.updateData(1,testData);
                ArrayList<Data> test=  DataBase.openData(this);
                Log.d("","");
                //DataBase.deleteData(1);
                //DataBase.openData();
                break;
        }
    }

    public void insertData(int year, int month, int date, String appName, int billing) {
        values = new ContentValues();
        values.put("year", year);
        values.put("month", month);
        values.put("date", date);
        values.put("appname", appName);
        values.put("billing", billing);
        mydb.insert("billingTable", null, values);
    }

    public static void openData(){
        ArrayList<String> data = new ArrayList<>();
        cursor = mydb.rawQuery(openDB,null);
        while(cursor.moveToNext()) {
            String year = cursor.getString(cursor.getColumnIndex("year"));
            String month = cursor.getString(cursor.getColumnIndex("month"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String appname = cursor.getString(cursor.getColumnIndex("appname"));
            String billing = cursor.getString(cursor.getColumnIndex("billing"));
            data.add(year + "|" + month + "|" + date + "|" + appname + "|" + billing);
        }

        Log.d("tag", String.valueOf(data));
    }
}
