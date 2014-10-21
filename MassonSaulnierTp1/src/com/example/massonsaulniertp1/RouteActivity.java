package com.example.massonsaulniertp1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import parser.Stop;
import parser.StopTimes;
import parser.Trips;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class RouteActivity extends ActionBarActivity {
	String tripID;
	ArrayList<StopTimes> listeDeStopTimes;
	ArrayList<Stop> listeDeStop;
	ArrayList<String> listAfficher;
	ArrayList<String> listeDeStopID;
	ListView listView;
	RadioGroup radioGroup;
	Button btnGo;
	EditText text;

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
		setListener();
		setListViewAdapter();
	}

	private void setListener() {
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.radioBGeneral) {
					btnGo.setVisibility(View.INVISIBLE);
					text.setVisibility(View.INVISIBLE);
					listView.setVisibility(View.VISIBLE);
				} else {
					btnGo.setVisibility(View.VISIBLE);
					text.setVisibility(View.VISIBLE);
					listView.setVisibility(View.INVISIBLE);
				}

			}
		});
		btnGo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(v.getContext(), TempsChoixActivity.class);
				if (text.getText().toString() != "") {
					i.putExtra("Arret", text.getText().toString().trim());
				} else {
					i.putExtra("Arret", "-1");
				}
				startActivity(i);

			}
		});

	}

	private void setWidget() {
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		text = (EditText) findViewById(R.id.NumeroArret);
		btnGo = (Button) findViewById(R.id.BtnChercher);
		listAfficher = new ArrayList<String>();
		listeDeStop = Stop.trouverStop(this.getResources());
		listView = (ListView) findViewById(R.id.listViewRoute);
		listeDeStopTimes = StopTimes.trouverStopTimes(this.getResources());

		Collections.sort(listeDeStopTimes, new Comparator<StopTimes>() {
			@Override
			public int compare(StopTimes s1, StopTimes s2) {
				return Integer.parseInt(s1.stop_sequence)
						- Integer.parseInt(s2.stop_sequence);
			}
		});

		listeDeStopID = trouverStopTimeSelonID(listeDeStopTimes);
		listeDeStop = trouverStopSelonID(listeDeStopID);

	}

	private ArrayList<String> trouverStopTimeSelonID(
			ArrayList<StopTimes> listDeStopTimes) {
		ArrayList<String> stopTime = new ArrayList<String>();
		for (StopTimes st : listDeStopTimes) {
			if (st.trip_id.equals(tripID)) {
				stopTime.add(st.stop_id);
			}

		}
		return stopTime;

	}

	private ArrayList<Stop> trouverStopSelonID(ArrayList<String> listeDeStopID) {
		ArrayList<Stop> stop = new ArrayList<Stop>();
		for (Stop leStop : listeDeStop) {
			if (listeDeStopID.contains(leStop.stop_id)) {
				stop.add(leStop);

				listAfficher.add(leStop.stop_name);
			}

		}
		return stop;

	}

	private void setListViewAdapter() {
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listAfficher);
		listView.setAdapter(arrayAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent i = new Intent(view.getContext(), TempsActivity.class);
				i.putExtra("IDStop", listeDeStopID.get(position));
				startActivity(i);
			}

		});

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
