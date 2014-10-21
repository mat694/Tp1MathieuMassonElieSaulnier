package com.example.massonsaulniertp1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import parser.StopTimes;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TempsChoixActivity extends ActionBarActivity {
	String numeroArret = "";
	ListView listViewTempsChoisi;
	ArrayList<String> listeDesArretTrier;
	ListView lv;
	ArrayList<String>  listeDarret;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temps_choix);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null)
			numeroArret = bundle.getString("Arret");
		else
			numeroArret = "";
		setWidget();
		listeDesArretTrier = trouverTempsArret();
		setAdapter();

	}

	private void setWidget() {
		listViewTempsChoisi = (ListView) findViewById(R.id.listViewTempsChoisi);

	}

	private ArrayList<String> trouverTempsArret() {
		ArrayList<String> listeDarrets = new ArrayList<String>();
		for (int i = 0; i < 24; i++) {

			listeDarrets.add(i + ":00");
		}

		return listeDarrets;

	}

	private void setAdapter() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listeDesArretTrier);
		listViewTempsChoisi.setAdapter(adapter);

		listViewTempsChoisi.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent intent = new Intent(view.getContext(),
						Heures3Activity.class);
				intent.putExtra("numeroArret", numeroArret );
				intent.putExtra("heure", position );
				startActivity(intent);
				

				

			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.temps_choix, menu);
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
