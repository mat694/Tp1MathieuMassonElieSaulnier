package com.example.massonsaulniertp1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import parser.StopTimes;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Heures3Activity extends Activity {
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_heures3);
		Bundle b = getIntent().getExtras();
		lv = (ListView) findViewById(R.id.listViewHeure3);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, trouverTempsArret (b.getInt("heure"),b.getString("numeroArret")));
		lv.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.heures3, menu);
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
	private ArrayList<String> trouverTempsArret(int heure,String numero) {
		ArrayList<StopTimes> listeST = StopTimes.trouverStopTimes(this
				.getResources());
		Collections.sort(listeST, new Comparator<StopTimes>() {
			@Override
			public int compare(StopTimes s1, StopTimes s2) {

				String temps1 = s1.departure_time.split(":")[0]
						+ s1.departure_time.split(":")[1];
				String temps2 = s2.departure_time.split(":")[0]
						+ s2.departure_time.split(":")[1];
				return Integer.parseInt(temps1) - Integer.parseInt(temps2);
			}
		});
		ArrayList<String> listeDarrets = new ArrayList<String>();
		int index = 0;
		for (StopTimes st : listeST) {
			if(st.stop_id.equals(numero))
			{
				if (Integer.parseInt(st.arrival_time.split(":")[0]) > heure) {
					if (!listeDarrets.contains(st.arrival_time)) {
						listeDarrets.add(st.arrival_time);
				index++;}
			}
			if (index >= 3)
				return listeDarrets;
			}
		}
		return listeDarrets;

	}
}
