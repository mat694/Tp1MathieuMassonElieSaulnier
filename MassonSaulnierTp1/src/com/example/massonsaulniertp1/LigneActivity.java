package com.example.massonsaulniertp1;

import java.io.InputStreamReader;
import java.util.ArrayList;

import parser.Routes;
import parser.mainParser;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LigneActivity extends ActionBarActivity {

	ListView listeArret;
	int numeroLigne;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ligne);
		Bundle bundle = getIntent().getExtras();
		numeroLigne = bundle.getInt("NumeroLigne");
		setWidget();
		populateListeArret();
		
	
	}

	private void setWidget()
	{
		listeArret = (ListView) findViewById(R.id.ListeLigne);
		
		
		
	}
	
	private void populateListeArret()
	{
		ArrayList<Routes> routes = new ArrayList<Routes>();
InputStreamReader test=	new InputStreamReader(this.getResources().openRawResource(R.raw.routes));

		routes = mainParser.parseRoutes(test);
		ArrayList<String> ListeString = new ArrayList<String>();
		for(Routes rout : routes)
		{
			//if(rout.route_short_name.equals(numeroLigne +""))
			//{
			ListeString.add(rout.route_short_name  + rout.route_long_name);
					//}
		}
		 ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                 this, 
                 android.R.layout.simple_list_item_1, ListeString
                 );
		listeArret.setAdapter(arrayAdapter); 
	}
	
	
	private void setListener()
	{
		
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
