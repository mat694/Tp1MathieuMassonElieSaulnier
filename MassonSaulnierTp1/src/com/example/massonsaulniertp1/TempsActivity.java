package com.example.massonsaulniertp1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import parser.StopTimes;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TempsActivity extends ActionBarActivity {
	ListView listViewTemps;
	String stopID;
	ArrayList<StopTimes> ListeStopTimes;
	ArrayList<String> ListeDesTempsStop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temps);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null)
			stopID = bundle.getString("IDStop");
		else
			stopID = "";

		setWidget();
	}

	private void setWidget() {
		listViewTemps = (ListView) findViewById(R.id.listViewTemps);
		ListeStopTimes = StopTimes.trouverStopTimes(this.getResources());
		Collections.sort(ListeStopTimes, new Comparator<StopTimes>() {
			@Override
			public int compare(StopTimes s1, StopTimes s2) {
				return Integer.parseInt(s1.stop_sequence)
						- Integer.parseInt(s2.stop_sequence);
			}
		});
		ListeDesTempsStop = ComparerStopIDaStopTime();
		
		setAdapter();
	}

	private void setAdapter() {
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, ListeDesTempsStop);
		listViewTemps.setAdapter(arrayAdapter);
	}

	private ArrayList<String> ComparerStopIDaStopTime() {
		ArrayList<String> listDeTemps = new ArrayList<String>();
		for (StopTimes st : ListeStopTimes) {
			if (st.stop_id.equals(stopID))
				if(!listDeTemps.contains(st.arrival_time))
				listDeTemps.add(st.arrival_time);

		}Collections.sort(listDeTemps);
		return listDeTemps;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.temps, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
