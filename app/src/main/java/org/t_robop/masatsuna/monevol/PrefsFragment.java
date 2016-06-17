package org.t_robop.masatsuna.monevol;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Masatsuna on 2016/06/16.
 */
public class PrefsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settinglayout);
    }
}
