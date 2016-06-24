package org.t_robop.masatsuna.monevol;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.PercentFormatter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import android.widget.LinearLayout;
import android.widget.ScrollView;


public class GraphActivity extends AppCompatActivity {

    static SQLiteDatabase mydb; //MySQLiteOpenHelperの変数
    static MySQLiteOpenHelper hlpr;
    static ArrayList yearMonthSelect;
    static ContentValues values;
    static Cursor cursor;
    static String openDB = "select * from billingTable order by year desc, month desc, date desc";
    DataBase db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_graph);



        // Adapterの作成
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Adapterにアイテムを追加
        adapter.add("1月");
        adapter.add("2月");
        adapter.add("3月");
        adapter.add("4月");
        adapter.add("5月");
        adapter.add("6月");
        adapter.add("7月");
        adapter.add("8月");
        adapter.add("9月");
        adapter.add("10月");
        adapter.add("11月");
        adapter.add("12月");


        Spinner spinner = (Spinner) findViewById(R.id.spinner);

// SpinnerにAdapterを設定
        spinner.setAdapter(adapter);

// レイアウトからSpinnerを取得
        Spinner item = (Spinner) findViewById(R.id.spinner);
// 選択したアイテムを取得
        String selected = (String) item.getSelectedItem();





        BarChart barChart= (BarChart) findViewById(R.id.bar);

        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setEnabled(true);
        barChart.setDrawGridBackground(true);
        barChart.setDrawBarShadow(false);
        barChart.setEnabled(true);

        barChart.setTouchEnabled(true);
        barChart.setPinchZoom(true);
        barChart.setDoubleTapToZoomEnabled(false);

        barChart.setHighlightEnabled(true);
        barChart.setDrawHighlightArrow(false);
        barChart.setHighlightEnabled(true);

        barChart.setScaleEnabled(false);

        barChart.getLegend().setEnabled(true);
        barChart.setDescription("");

        //X軸周り
        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setSpaceBetweenLabels(1);

        barChart.setData(createBarChartData());

        barChart.invalidate();
        // アニメーション
        barChart.animateY(2000, Easing.EasingOption.EaseInBack);

        PieChart pieChart = (PieChart) findViewById(R.id.pie_chart);

        pieChart.setDrawHoleEnabled(true); // 真ん中に穴を空けるかどうか
        pieChart.setHoleRadius(30f);       // 真ん中の穴の大きさ(%指定)
        pieChart.setHoleColorTransparent(true);
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setRotationAngle(270);          // 開始位置の調整
        pieChart.setRotationEnabled(true);       // 回転可能かどうか
        pieChart.getLegend().setEnabled(true);   //
        pieChart.setDescription("月の詳細額");
        pieChart.setData(createPieChartData());

        // 更新
        pieChart.invalidate();
        // アニメーション
        pieChart.animateXY(2000, 2000); // 表示アニメーション





        //setContentViewより前にWindowにActionBar表示を設定
      //  getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        //setContentView(R.layout.activity_graph);
    }



    // BarChartの設定
    private BarData createBarChartData() {
        Data testData = new Data();

        testData.setAppname("aaaaa");
        testData.setYear(2016);
        testData.setMonth(1);
        testData.setDate(2);
        testData.setBilling(1500);
        DataBase.insertData(testData,this);
        testData.setAppname("ccccc");
        testData.setYear(2016);
        testData.setMonth(1);
        testData.setDate(21);
        testData.setBilling(1500);
        DataBase.insertData(testData,this);
        testData.setAppname("bbbbb");
        testData.setYear(2016);
        testData.setMonth(1);
        testData.setDate(15);
        testData.setBilling(1500);
        DataBase.insertData(testData,this);


        testData.setAppname("aaaaa");
        testData.setYear(2016);
        testData.setMonth(2);
        testData.setDate(30);
        testData.setBilling(10);
        DataBase.insertData(testData,this);

        testData.setAppname("aaaaa");
        testData.setYear(2016);
        testData.setMonth(3);
        testData.setDate(30);
        testData.setBilling(10);
        DataBase.insertData(testData,this);

        testData.setAppname("aaaaa");
        testData.setYear(2016);
        testData.setMonth(4);
        testData.setDate(30);
        testData.setBilling(10);
        DataBase.insertData(testData,this);

        testData.setAppname("aaaaa");
        testData.setYear(2016);
        testData.setMonth(5);
        testData.setDate(30);
        testData.setBilling(10);
        DataBase.insertData(testData,this);

        testData.setAppname("aaaaa");
        testData.setYear(2016);
        testData.setMonth(6);
        testData.setDate(30);
        testData.setBilling(10);
        DataBase.insertData(testData,this);

        testData.setAppname("aaaaa");
        testData.setYear(2016);
        testData.setMonth(7);
        testData.setDate(30);
        testData.setBilling(10);
        DataBase.insertData(testData,this);

        testData.setAppname("aaaaa");
        testData.setYear(2016);
        testData.setMonth(8);
        testData.setDate(30);
        testData.setBilling(10);
        DataBase.insertData(testData,this);

        testData.setAppname("aaaaa");
        testData.setYear(2016);
        testData.setMonth(9);
        testData.setDate(30);
        testData.setBilling(10);
        DataBase.insertData(testData,this);

        testData.setAppname("aaaaa");
        testData.setYear(2016);
        testData.setMonth(10);
        testData.setDate(30);
        testData.setBilling(10);
        DataBase.insertData(testData,this);

        testData.setAppname("aaaaa");
        testData.setYear(2016);
        testData.setMonth(11);
        testData.setDate(30);
        testData.setBilling(10);
        DataBase.insertData(testData,this);


        testData.setAppname("aaaaa");
        testData.setYear(2016);
        testData.setMonth(12);
        testData.setDate(30);
        testData.setBilling(10);
        DataBase.insertData(testData,this);



        ArrayList<BarDataSet> barDataSets = new ArrayList<>();

        ArrayList<Data> data1 = DataBase.yearMonthSelect(2016,1,this);
        ArrayList<Data> data2 = DataBase.yearMonthSelect(2016,2,this);
        ArrayList<Data> data3 = DataBase.yearMonthSelect(2016,3,this);
        ArrayList<Data> data4 = DataBase.yearMonthSelect(2016,4,this);
        ArrayList<Data> data5 = DataBase.yearMonthSelect(2016,5,this);
        ArrayList<Data> data6 = DataBase.yearMonthSelect(2016,6,this);
        ArrayList<Data> data7 = DataBase.yearMonthSelect(2016,7,this);
        ArrayList<Data> data8 = DataBase.yearMonthSelect(2016,8,this);
        ArrayList<Data> data9 = DataBase.yearMonthSelect(2016,9,this);
        ArrayList<Data> data10 = DataBase.yearMonthSelect(2016,10,this);
        ArrayList<Data> data11 = DataBase.yearMonthSelect(2016,11,this);
        ArrayList<Data> data12 = DataBase.yearMonthSelect(2016,12,this);


        int num[] = new int[12];

        for (int i = 0;i<data1.size();i++){
            num[0] = num[0] +data1.get(i).getBilling();
        }
        for (int i = 0;i<data2.size();i++){
            num[1] = num[1] +data2.get(i).getBilling();
        }
        for (int i = 0;i<data3.size();i++){
            num[2] = num[2] +data3.get(i).getBilling();
        }
        for (int i = 0;i<data4.size();i++) {
            num[3] = num[3] + data4.get(i).getBilling();
        }
        for (int i = 0;i<data5.size();i++){
            num[4] = num[4] +data5.get(i).getBilling();
        }
        for (int i = 0;i<data6.size();i++){
            num[5] = num[5] +data6.get(i).getBilling();
        }
        for (int i = 0;i<data7.size();i++){
            num[6] = num[6] +data7.get(i).getBilling();
        }
        for (int i = 0;i<data8.size();i++){
            num[7] = num[7] +data8.get(i).getBilling();
        }
        for (int i = 0;i<data9.size();i++){
            num[8] = num[8] +data9.get(i).getBilling();
        }
        for (int i = 0;i<data10.size();i++){
            num[9] = num[9] +data10.get(i).getBilling();
        }
        for (int i = 0;i<data11.size();i++){
            num[10] = num[10] +data11.get(i).getBilling();
        }
        for (int i = 0;i<data12.size();i++){
            num[11] = num[11] +data12.get(i).getBilling();
        }



        Log.d("aaaaaaaa",String.valueOf(num));
        // X軸
        ArrayList<String> xValues = new ArrayList<>();


        xValues.add(String.valueOf(data1.get(0).getMonth()));
        xValues.add(String.valueOf(data2.get(0).getMonth()));
        xValues.add(String.valueOf(data3.get(0).getMonth()));
        xValues.add(String.valueOf(data4.get(0).getMonth()));
        xValues.add(String.valueOf(data5.get(0).getMonth()));
        xValues.add(String.valueOf(data6.get(0).getMonth()));
        xValues.add(String.valueOf(data7.get(0).getMonth()));
        xValues.add(String.valueOf(data8.get(0).getMonth()));
        xValues.add(String.valueOf(data9.get(0).getMonth()));
        xValues.add(String.valueOf(data10.get(0).getMonth()));
        xValues.add(String.valueOf(data11.get(0).getMonth()));
        xValues.add(String.valueOf(data12.get(0).getMonth()));


        // valueA
        ArrayList<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(num[0], 0));
        values.add(new BarEntry(num[1], 1));
        values.add(new BarEntry(num[2], 2));
        values.add(new BarEntry(num[3], 3));
        values.add(new BarEntry(num[4], 4));
        values.add(new BarEntry(num[5], 5));
        values.add(new BarEntry(num[6], 6));
        values.add(new BarEntry(num[7], 7));
        values.add(new BarEntry(num[8], 8));
        values.add(new BarEntry(num[9], 9));
        values.add(new BarEntry(num[10], 10));
        values.add(new BarEntry(num[11], 11));


        BarDataSet valuesADataSet = new BarDataSet(values, "￥");
        valuesADataSet.setColor(ColorTemplate.COLORFUL_COLORS[3]);

        barDataSets.add(valuesADataSet);

/*        // valueBb
        ArrayList<BarEntry> valuesB = new ArrayList<>();
        valuesB.add(new BarEntry(200, 0));
        valuesB.add(new BarEntry(300, 1));
        valuesB.add(new BarEntry(400, 2));
        valuesB.add(new BarEntry(500, 3));
        valuesB.add(new BarEntry(600, 4));
        valuesB.add(new BarEntry(700, 5));
        valuesB.add(new BarEntry(700, 6));
        valuesB.add(new BarEntry(700, 7));
        valuesB.add(new BarEntry(700, 8));
        valuesB.add(new BarEntry(700, 9));
        valuesB.add(new BarEntry(700, 10));
        valuesB.add(new BarEntry(700, 11));
        BarDataSet valuesBDataSet = new BarDataSet(valuesB, "B");
        valuesBDataSet.setColor(ColorTemplate.COLORFUL_COLORS[4]);
        barDataSets.add(valuesBDataSet);*/

        BarData barData = new BarData(xValues, barDataSets);
        return barData;
    }



    // pieChartのデータ設定
    private PieData createPieChartData() {

        Data testData = new Data();


        testData.setAppname("aaaaa");
        testData.setYear(2016);
        testData.setMonth(1);
        testData.setDate(2);
        testData.setBilling(1500);
        DataBase.insertData(testData,this);
        testData.setAppname("ccccc");
        testData.setYear(2016);
        testData.setMonth(1);
        testData.setDate(21);
        testData.setBilling(1500);
        DataBase.insertData(testData,this);
        testData.setAppname("bbbbb");
        testData.setYear(2016);
        testData.setMonth(1);
        testData.setDate(15);
        testData.setBilling(1500);
        DataBase.insertData(testData,this);

        ArrayList<PieDataSet> pieDataSets = new ArrayList<>();

        ArrayList<Data> data1 = DataBase.yearMonthSelect(2016,1,this);
        ArrayList<Data> data2 = DataBase.yearMonthSelect(2016,2,this);
        ArrayList<Data> data3 = DataBase.yearMonthSelect(2016,3,this);
        ArrayList<Data> data4 = DataBase.yearMonthSelect(2016,4,this);
        ArrayList<Data> data5 = DataBase.yearMonthSelect(2016,5,this);
        ArrayList<Data> data6 = DataBase.yearMonthSelect(2016,6,this);
        ArrayList<Data> data7 = DataBase.yearMonthSelect(2016,7,this);
        ArrayList<Data> data8 = DataBase.yearMonthSelect(2016,8,this);


        ArrayList<Entry> yVals = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();
       // xValues.add(String.valueOf(data1.get(0).getMonth())); バーのやつ

        xVals.add(String.valueOf(testData.getBilling()));
        xVals.add(String.valueOf(testData.getBilling()));
        xVals.add("C");

        yVals.add(new Entry(20, 0));

        yVals.add(new Entry(30, 1));
        yVals.add(new Entry(50, 2));

        PieDataSet dataSet = new PieDataSet(yVals, "Data");
        dataSet.setSliceSpace(5f);
        dataSet.setSelectionShift(1f);

        // 色の設定
        colors.add(ColorTemplate.COLORFUL_COLORS[0]);
        colors.add(ColorTemplate.COLORFUL_COLORS[1]);
        colors.add(ColorTemplate.COLORFUL_COLORS[2]);
        dataSet.setColors(colors);
        dataSet.setDrawValues(true);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());

        // テキストの設定
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.WHITE);
        return data;
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // メニューの要素を追加
        MenuItem actionItem = menu.add("ホーム");

        menu.add("履歴");
        menu.add("設定");


        // メニューの要素を追加して取
       // MenuItem actionItem = menu.add("Action Button");

        // SHOW_AS_ACTION_IF_ROOM:余裕があれば表示
       // actionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        // アイコンを設定
        //actionItem.setIcon(android.R.drawable.ic_menu_share);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("ホーム")){
            Intent intent =new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(intent);
        }
        else if (item.getTitle().equals("履歴")){
            Intent intent =new Intent(getApplicationContext(),RecordActivity.class);
            startActivity(intent);
        }
        else if (item.getTitle().equals("設定")){
            Intent intent =new Intent(getApplicationContext(),SettingActivity.class);
            startActivity(intent);
        }
        return true;
    }
    */




//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.action_bar_menu,menu);
//        return true;
//    }
}
