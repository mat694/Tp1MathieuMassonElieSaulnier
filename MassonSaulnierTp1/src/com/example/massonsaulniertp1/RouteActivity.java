package com.example.massonsaulniertp1;

import java.util.ArrayList;

import parser.Stop;
import parser.StopTimes;
import parser.Trips;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RouteActivity extends ActionBarActivity {
	String tripID;
	ArrayList<StopTimes> listeDeStopTimes;
	ArrayList<String> listAfficher;
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_route);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null)
			tripID = bundle.getString("IDTrip");
		else
			tripID = "";

	setWidget();
		setListViewAdapter();
	}

	private void setWidget() {	
		listAfficher = new ArrayList<String>();
		listView = (ListView) findViewById(R.id.listViewRoute);
		listeDeStopTimes = trouverStopTimeSelonID(StopTimes.trouverStopTimes(this.getResources()));
	}

	private ArrayList<StopTimes> trouverStopTimeSelonID(ArrayList<StopTimes> listDeStopTimes) {
		ArrayList<StopTimes> stopTime = new ArrayList<StopTimes>();
		for (StopTimes st : listDeStopTimes) {
			if (st.trip_id.equals(tripID)) {
				stopTime.add(st);
				listAfficher.add(st.arrival_time);
			}

		}
		return stopTime;

	}

	private void setListViewAdapter() {
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listAfficher);
		listView.setAdapter(arrayAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.route, menu);
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
