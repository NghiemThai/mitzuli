/*
 * Copyright (C) 2014 Mikel Artetxe <artetxem@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.mitzuli;

import java.util.Locale;

import android.app.Application;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;


@ReportsCrashes(formUri = Keys.ACRA_FORM_URI, formKey=Keys.ACRA_FORM_KEY)
public class Mitzuli extends Application {

    private Locale locale = null;

    @Override
    public void onCreate() {
        super.onCreate();
        final String displayLanguage = PreferenceManager.getDefaultSharedPreferences(this).getString("pref_key_display_language", SettingsFragment.DEFAULT_LANGUAGE);
        if (!displayLanguage.equals(SettingsFragment.DEFAULT_LANGUAGE)) locale = new Locale(displayLanguage);
        updateLocale();
        ACRA.init(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateLocale();
    }

    private void updateLocale() {
        if (locale != null) {
            Locale.setDefault(locale);
            final Configuration config = new Configuration(getBaseContext().getResources().getConfiguration());
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
    }

}
