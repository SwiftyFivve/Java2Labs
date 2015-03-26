package com.jordanweaver.reviewassignment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by jordanweaver on 3/25/15.
 */
public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener{

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
        Preference clickPreference = findPreference("PREF_CLICKABLE");
        Preference deletePreference = findPreference("PREF_DELETE");
        final ListPreference listPreference = (ListPreference) findPreference("PREF_LIST");

        clickPreference.setOnPreferenceClickListener(this);
        deletePreference.setOnPreferenceClickListener(this);

//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        final SharedPreferences.Editor edit = prefs.edit();
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Log.e("List Pref", newValue+"");
                if(newValue.equals("XML")){
                    listPreference.setValueIndex(0);

                } else if (newValue.equals("JSON")){
                    preference.setDefaultValue(newValue);
                    listPreference.setValueIndex(1);
                }

                return false;
            }
        });
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {

        ListPreference listPreference = (ListPreference) findPreference("PREF_LIST");

        DataBaseHelper helper = new DataBaseHelper(getActivity());

        if (preference == findPreference("PREF_CLICKABLE")){
            Log.e("This clickable worked", listPreference.getValue());

            if(listPreference.getValue().equals("XML")){




            } else if (listPreference.getValue().equals("JSON")){



            }
        }

        if (preference == findPreference("PREF_DELETE")){
            Log.e("This works", "DELETE");

        }

        return false;
    }

}
