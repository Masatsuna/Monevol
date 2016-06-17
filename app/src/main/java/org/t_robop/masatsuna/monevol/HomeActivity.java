package org.t_robop.masatsuna.monevol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentViewより前にWindowにActionBar表示を設定
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_home);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // メニューの要素を追加
        menu.add("グラフ");
        menu.add("履歴");
        menu.add("設定");

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

        switch (String.valueOf(item.getTitle())){

            case "グラフ" :
                intent = new Intent(HomeActivity.this, GraphActivity.class);
                startActivity(intent);
                break;

            case "履歴" :
                intent = new Intent(HomeActivity.this, RecordActivity.class);
                startActivity(intent);
                break;

            case "設定" :
                intent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent);
                break;
        }

        return true;
    }
}
