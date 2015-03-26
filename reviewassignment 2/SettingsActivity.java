package java2.devaunteledee.com.reviewassignment;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by jordanweaver on 3/25/15.
 */
public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.settingsContainer,
                    SettingsFragment.newInstance(), SettingsFragment.TAG).commit();
        }
    }
}
