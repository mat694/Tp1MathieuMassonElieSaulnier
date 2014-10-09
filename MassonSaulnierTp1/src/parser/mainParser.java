package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings.System;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public  class mainParser {

	ArrayList<Agence> lstAgences = new ArrayList<Agence>();

	TextView txtAgences;
	TextView txtCalendrier;

	

		/*txtAgences = (TextView) findViewById(R.id.agences);
		txtCalendrier = (TextView) findViewById(R.id.calendrier);

		parseAgency(R.raw.agence);

		String line = "Liste des agences:\n";
		for (Agence agency : lstAgences)
			line += agency.id_agence + " " + agency.nom_agence + " "
					+ agency.url_agence + " " + agency.timezone_agence + " "
					+ agency.tel_agence + " " + agency.langue_agence + "\n";
		txtAgences.setText(line);

		parseCalendar(R.raw.calendrier);
		line = "Calendrier:\n";

		for (Calendrier calendar : lstCalendrier)
			line += calendar.id_service + " " + calendar.lundi + " "
					+ calendar.mardi + " " + calendar.mercredi + " "
					+ calendar.jeudi + " " + calendar.vendredi + " "
					+ calendar.samedi + " " + calendar.dimanche + " "
					+ calendar.date_debut + " " + calendar.fin_date
					+ "\n***********\n";
		txtCalendrier.setText(line);*/
	

	public static ArrayList<Agence> parseAgency(InputStreamReader agence) {
		ArrayList<Agence> lstAgences = new ArrayList<Agence>();
		BufferedReader rawReader = new BufferedReader(agence);
		String line = "";
		try {
			rawReader.readLine();// afin de sauter la première ligne qui
									// contient les noms des colonnes
			while ((line = rawReader.readLine()) != null) {
				lstAgences.add(new Agence(line));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstAgences;
	}

	// new InputStreamReader(this.getResources().openRawResource(rid))

	public static ArrayList<Calendrier> parseCalendar(InputStreamReader calendrier) {
		ArrayList<Calendrier> listCalendrier = new ArrayList<Calendrier>();
		BufferedReader rawReader = new BufferedReader(calendrier);
		String line = "";
		try {
			rawReader.readLine();// afin de sauter la première ligne qui
									// contient les noms des colonnes
			while ((line = rawReader.readLine()) != null) {
				listCalendrier.add(new Calendrier(line));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCalendrier;
	}
	
	

	
	
	public static ArrayList<Routes> parseRoutes(InputStreamReader calendrier) {

		ArrayList<Routes> listeRoutes = new ArrayList<Routes>();
		BufferedReader rawReader = new BufferedReader(calendrier);
		String line = "";
		try {
			rawReader.readLine();// afin de sauter la première ligne qui
									// contient les noms des colonnes
			while ((line = rawReader.readLine()) != null) {
				listeRoutes.add(new Routes(line));
			}

			line ="";
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		}
		return listeRoutes;
	}

}
