package com.example.massonsaulniertp1;

import java.io.InputStreamReader;
import java.util.ArrayList;

import parser.Routes;
import parser.StopTimes;
import parser.Trips;
import parser.mainParser;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LigneActivity extends ActionBarActivity {

	ListView listeArret;
	String numeroLigne;

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
		ArrayList<Routes> routes = trouverRoute();
		ArrayList<Trips> trip = trouverTrips();
		ArrayList<StopTimes> stopTime;
		String idRoute = "";
		// trouve le id de la route
		for (Routes rout : routes) {
			if (rout.route_short_name.equals(numeroLigne))
				idRoute = rout.route_id;
		}

		ArrayList<String> ListeDeTripID = new ArrayList<String>();
		// trouve le id du trip;
		for (Trips t : trip) {
			if (t.route_id.equals(idRoute))
				ListeDeTripID.add(t.trip_id);
		}
		routes = null;
		trip = null;
		ArrayList<String> listeDeStopId = new ArrayList<String>();
		stopTime = trouverStopTimes();
		for (StopTimes stopT : stopTime) {
			for (String tripID : ListeDeTripID) {
				if (tripID.equals(stopT.trip_id))
					listeDeStopId.add(stopT.stop_id);

			}
		}

		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listeDeStopId);
		listeArret.setAdapter(arrayAdapter);
	}

	private ArrayList<Routes> trouverRoute() {
		InputStreamReader resourceDeRoute = new InputStreamReader(this
				.getResources().openRawResource(R.raw.routes));
		return mainParser.parseRoutes(resourceDeRoute);
	}

	private ArrayList<StopTimes> trouverStopTimes() {
		InputStreamReader resourceDeStopTimes = new InputStreamReader(this
				.getResources().openRawResource(R.raw.stop_times1));
		return mainParser.parseStopTime(resourceDeStopTimes);
	}

	private ArrayList<Trips> trouverTrips() {
		InputStreamReader resourceDeTrips = new InputStreamReader(this
				.getResources().openRawResource(R.raw.trips));
		return mainParser.parseTrips(resourceDeTrips);
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
