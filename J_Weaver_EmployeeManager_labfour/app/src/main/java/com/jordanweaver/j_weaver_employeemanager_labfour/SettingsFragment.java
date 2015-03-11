package com.jordanweaver.j_weaver_employeemanager_labfour;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

/**
 * Created by jordanweaver on 3/9/15.
 */
public class SettingsFragment extends PreferenceFragment
        implements Preference.OnPreferenceClickListener {

    public static final String TAG = "SettingsFragment.TAG";

    public static SettingsFragment newInstance(){
        SettingsFragment settingsFrag = new SettingsFragment();
        return settingsFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Preference preference = findPreference("PREF_CLICKABLE");
        preference.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        return false;
    }
}
