package org.t_robop.masatsuna.monevol;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;

import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_graph);

        BarChart barChart= (BarChart) findViewById(R.id.bar);

        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setEnabled(true);
        barChart.setDrawGridBackground(true);
        barChart.setDrawBarShadow(false);
        barChart.setEnabled(true);

        barChart.setTouchEnabled(true);
        barChart.setPinchZoom(true);
        barChart.setDoubleTapToZoomEnabled(true);

        barChart.setHighlightEnabled(true);
        barChart.setDrawHighlightArrow(true);
        barChart.setHighlightEnabled(true);

        barChart.setScaleEnabled(true);

        barChart.getLegend().setEnabled(true);

        //X軸周り
        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setSpaceBetweenLabels(0);

        barChart.setData(createBarChartData());

        barChart.invalidate();
        // アニメーション
        barChart.animateY(2000, Easing.EasingOption.EaseInBack);



        //setContentViewより前にWindowにActionBar表示を設定
      //  getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        //setContentView(R.layout.activity_graph);
    }



    // BarChartの設定
    private BarData createBarChartData() {
        ArrayList<BarDataSet> barDataSets = new ArrayList<>();

        // X軸
        ArrayList<String> xValues = new ArrayList<>();
        xValues.add("1月");
        xValues.add("2月");
        xValues.add("3月");
        xValues.add("4月");
        xValues.add("5月");
        xValues.add("6月");

        // valueA
        ArrayList<BarEntry> valuesA = new ArrayList<>();
        valuesA.add(new BarEntry(100, 0));
        valuesA.add(new BarEntry(200, 1));
        valuesA.add(new BarEntry(300, 2));
        valuesA.add(new BarEntry(400, 3));
        valuesA.add(new BarEntry(500, 4));
        valuesA.add(new BarEntry(600, 5));

        BarDataSet valuesADataSet = new BarDataSet(valuesA, "A");
        valuesADataSet.setColor(ColorTemplate.COLORFUL_COLORS[3]);

        barDataSets.add(valuesADataSet);

        // valueB
        ArrayList<BarEntry> valuesB = new ArrayList<>();
        valuesB.add(new BarEntry(200, 0));
        valuesB.add(new BarEntry(300, 1));
        valuesB.add(new BarEntry(400, 2));
        valuesB.add(new BarEntry(500, 3));
        valuesB.add(new BarEntry(600, 4));
        valuesB.add(new BarEntry(700, 5));

        BarDataSet valuesBDataSet = new BarDataSet(valuesB, "B");
        valuesBDataSet.setColor(ColorTemplate.COLORFUL_COLORS[4]);

        barDataSets.add(valuesBDataSet);

        BarData barData = new BarData(xValues, barDataSets);
        return barData;
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
