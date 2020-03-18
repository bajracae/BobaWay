package com.hfad.bobaway;
//
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
//import android.os.Bundle;

import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat
            implements SharedPreferences.OnSharedPreferenceChangeListener {

    EditTextPreference userPref;
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            addPreferencesFromResource(R.xml.prefs);
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            userPref = (EditTextPreference) findPreference(getString(R.string.pref_user_key));
            Log.d("editText","userPref:" + userPref);
            userPref.setSummary(userPref.getText());
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals(getString(R.string.pref_user_key))) {
                EditTextPreference preference = findPreference(key);
                preference.setSummary(preference.getText());
            }
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(this);
        }
}
