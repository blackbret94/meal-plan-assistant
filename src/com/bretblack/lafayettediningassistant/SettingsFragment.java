package com.bretblack.lafayettediningassistant;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment {
	private String filename = "conTextSMSBackup";
	private DbAdapter db;
	private Activity act;
	private SharedPreferences sharedPreferences;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // open database
        //GlobalDb mApp = (GlobalDb) getActivity().getApplicationContext();
		//db = mApp.getDbAdapter();
		
		// get activity
		 act = getActivity();

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
        sharedPreferences = act.getSharedPreferences("Pref", Context.MODE_PRIVATE);
        
        // set preference listener for reset meals
        Preference pref = getPreferenceManager().findPreference("reset_meals");
        pref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                if (preference.getKey().equals("reset_meals")) {
                    resetMeals();
                    return true;
                }
                return false;
            }
        });
        
        // set preference listener for export
        /*Preference pref = getPreferenceManager().findPreference("export");
        pref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                if (preference.getKey().equals("export")) {
                    exportFavorites();
                    return true;
                }
                return false;
            }
        });*/
    }
	
	public void resetMeals(){
		if (sharedPreferences.contains(HomeFragment.MEALKEY)){
			Editor editor = sharedPreferences.edit();
			editor.putInt(HomeFragment.MEALKEY, 20);
		    editor.commit();
		}
    }
}
