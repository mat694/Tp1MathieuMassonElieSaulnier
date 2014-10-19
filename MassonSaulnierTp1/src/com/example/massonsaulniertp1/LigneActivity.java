package com.example.massonsaulniertp1;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import parser.Fare;
import parser.Routes;
import parser.Stop;
import parser.StopTimes;
import parser.Trips;
import parser.mainParser;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LigneActivity extends ActionBarActivity {

	ListView listeArret;
	String numeroLigne;
	double prix;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ligne);
		Bundle bundle = getIntent().getExtras();
		numeroLigne = bundle.getString("NumeroLigne");
		setWidget();
		populateListeArret();
	}

	private void setWidget() {
		listeArret = (ListView) findViewById(R.id.ListeLigne);

	}

	private void populateListeArret() {
		ArrayList<Trips> trip = Trips.trouverTrips(this.getResources());
		ArrayList<StopTimes> stopTime;
		ArrayList<String> ListeDeTripID = new ArrayList<String>();
		ArrayList<Stop> ListStop = new ArrayList<Stop>();
		prix = Fare.trouverFare(this.getResources(), numeroLigne);

		ListeDeTripID = trouverListTripID(trip);
		trip = null;

		stopTime = StopTimes.trouverStopTimes(this.getResources());
		ArrayList<StopTimes> listeDeStop = trouverListStopAvecID(stopTime,
				ListeDeTripID);

		stopTime = null;
		ArrayList<String> listDArret = new ArrayList<String>();
		ListStop = Stop.trouverStop(this.getResources());

		listDArret = trouverStopNom(ListStop, listeDeStop);
		Collections.sort(listDArret);
		listDArret = retirerVirgule(listDArret);
		setAdapter(listDArret);
	}

	private ArrayList<String> trouverStopNom(ArrayList<Stop> ListStop,
			ArrayList<StopTimes> ListeDeStop) {
		ArrayList<String> listDArret = new ArrayList<String>();
		for (Stop stop : ListStop) {
			for (StopTimes stopID : ListeDeStop) {

				if (stopID.stop_id.equals(stop.stop_id))
					if (Integer.parseInt(stopID.stop_sequence) < 10)
						listDArret.add("0" + stopID.stop_sequence + ", "
								+ stop.stop_name + " : " + prix + "$");
					else
						listDArret.add(stopID.stop_sequence + ", "
								+ stop.stop_name + " : " + prix + "$");
			}
		}
		return listDArret;
	}

	private ArrayList<StopTimes> trouverListStopAvecID(
			ArrayList<StopTimes> stopTime, ArrayList<String> ListeDeTripID) {
		ArrayList<StopTimes> listeDeStop = new ArrayList<StopTimes>();
		for (StopTimes stopT : stopTime) {
			for (String tripID : ListeDeTripID) {

				if (tripID.equals(stopT.trip_id))
					listeDeStop.add(stopT);
			}
		}
		return listeDeStop;

	}

	private ArrayList<String> trouverListTripID(ArrayList<Trips> listTrip) {
		ArrayList<String> ListeDeTripID = new ArrayList<String>();
		for (Trips t : listTrip) {
			if (t.route_id.equals(numeroLigne))
				ListeDeTripID.add(t.trip_id);
			if (ListeDeTripID.size() >= 1)
				break;
		}
		return ListeDeTripID;

	}

	private ArrayList<String> retirerVirgule(ArrayList<String> listeDeString) {
		String[] temp;
		for (int i = 0; i < listeDeString.size(); i++) {
			temp = listeDeString.get(i).split(",");
			listeDeString.set(i, temp[1]);
		}
		return listeDeString;
	}

	private void setAdapter(ArrayList<String> listeDeString) {
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listeDeString);
		listeArret.setAdapter(arrayAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ligne, menu);
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
