package com.example.nerdlauncher;

import android.support.v4.app.Fragment;

public class NerdLauncherActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new NerdLauncherFragment();
	}
}
