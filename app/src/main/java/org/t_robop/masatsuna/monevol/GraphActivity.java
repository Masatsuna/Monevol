package org.t_robop.masatsuna.monevol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentViewより前にWindowにActionBar表示を設定
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_graph);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // メニューの要素を追加
        MenuItem actionItem = menu.add("ホーム");

        menu.add("グラフ");
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
        else if (item.getTitle().equals("購入履歴")){
            Intent intent =new Intent(getApplicationContext(),RecordActivity.class);
            startActivity(intent);
        }
        else if (item.getTitle().equals("設定")){
            Intent intent =new Intent(getApplicationContext(),SettingActivity.class);
            startActivity(intent);
        }
        return true;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.action_bar_menu,menu);
//        return true;
//    }
}
