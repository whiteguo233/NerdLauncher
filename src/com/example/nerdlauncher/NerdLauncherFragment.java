package com.example.nerdlauncher;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class NerdLauncherFragment extends ListFragment {
	private static final String Tag="NerdLauncherFragment";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent=new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		PackageManager pm= getActivity().getPackageManager();
		List<ResolveInfo> activities = pm.queryIntentActivities(intent, 0);
		Log.i(Tag,"activities:"+activities.size());
		Collections.sort(activities,new Comparator<ResolveInfo>() {
			public int compare(ResolveInfo a,ResolveInfo b)
			{
				PackageManager pm=getActivity().getPackageManager();
				return String.CASE_INSENSITIVE_ORDER.compare(a.loadLabel(pm).toString(),b.loadLabel(pm).toString());
			}
		});
		ArrayAdapter<ResolveInfo> adapter=new ArrayAdapter<ResolveInfo>(getActivity(),android.R.layout.simple_list_item_1,activities){

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				PackageManager pm = getActivity().getPackageManager();
				View v=super.getView(position, convertView, parent);
				TextView tv=(TextView)v;
				ResolveInfo ri=getItem(position);
				tv.setText(ri.loadLabel(pm));
				return v;
				}
			
		};
		setListAdapter(adapter);
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		ResolveInfo resolveInfo = (ResolveInfo) l.getAdapter().getItem(position);
		ActivityInfo activityInfo = resolveInfo.activityInfo;
		if (activityInfo == null) {
			return;
		}
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setClassName(activityInfo.applicationInfo.packageName, activityInfo.name);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
	

}
