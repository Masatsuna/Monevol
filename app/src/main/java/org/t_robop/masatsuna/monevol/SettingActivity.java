package org.t_robop.masatsuna.monevol;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    Intent intent;

    //テストです
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentViewより前にWindowにActionBar表示を設定
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PrefsFragment()).commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // メニューの要素を追加
        menu.add("ホーム");
        menu.add("グラフ");
        menu.add("履歴");


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

            case "ホーム" :
                intent = new Intent(SettingActivity.this, HomeActivity.class);
                startActivity(intent);
                break;

            case "グラフ" :
                intent = new Intent(SettingActivity.this, GraphActivity.class);
                startActivity(intent);
                break;

            case "履歴" :
                intent = new Intent(SettingActivity.this, RecordActivity.class);
                SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(this);
                String edittextValue = spf.getString("editText_preference", "");
                String edittextValue2 = spf.getString("editText_preference2", "");
                Log.d("tag",edittextValue);
                Log.d("tag2",edittextValue2);
                break;
        }

        return true;
    }

}
