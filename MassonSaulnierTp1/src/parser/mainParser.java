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

public class mainParser {

	ArrayList<Agence> lstAgences = new ArrayList<Agence>();

	public static double parseFare(int numeroLigne, InputStreamReader fare) {
		double prix = 30;
		BufferedReader rawReader = new BufferedReader(fare);
		String line = "";
		Fare leFare;
		try {
			rawReader.readLine();
			while ((line = rawReader.readLine()) != null) {
				leFare = new Fare(line);
				if (numeroLigne != 747
						&& leFare.fare_id.equals("tarif_regulier")) {
					prix = Double.parseDouble(leFare.price);
					break;
				} else {
					prix = Double.parseDouble(leFare.price);
				}
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		return prix;
	}

	public static ArrayList<Agence> parseAgency(InputStreamReader agence) {
		ArrayList<Agence> lstAgences = new ArrayList<Agence>();
		BufferedReader rawReader = new BufferedReader(agence);
		String line = "";
		try {
			rawReader.readLine();
			while ((line = rawReader.readLine()) != null) {
				lstAgences.add(new Agence(line));
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		return lstAgences;
	}

	public static ArrayList<Calendrier> parseCalendar(
			InputStreamReader calendrier) {
		ArrayList<Calendrier> listCalendrier = new ArrayList<Calendrier>();
		BufferedReader rawReader = new BufferedReader(calendrier);
		String line = "";
		try {
			rawReader.readLine();
			while ((line = rawReader.readLine()) != null) {
				listCalendrier.add(new Calendrier(line));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCalendrier;
	}

	public static ArrayList<StopTimes> parseStopTime(InputStreamReader StopTimes) {
		ArrayList<StopTimes> listStopTimes = new ArrayList<StopTimes>();
		BufferedReader rawReader = new BufferedReader(StopTimes);
		String line = "";
		try {
			rawReader.readLine();
			while ((line = rawReader.readLine()) != null) {
				listStopTimes.add(new StopTimes(line));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listStopTimes;
	}

	public static ArrayList<Stop> parseStop(InputStreamReader Stop) {
		ArrayList<Stop> listStop = new ArrayList<Stop>();
		BufferedReader rawReader = new BufferedReader(Stop);
		String line = "";
		try {
			rawReader.readLine();
			while ((line = rawReader.readLine()) != null) {
				listStop.add(new Stop(line));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listStop;
	}

	public static ArrayList<Trips> parseTrips(InputStreamReader trips) {

		ArrayList<Trips> listeRoutes = new ArrayList<Trips>();
		BufferedReader rawReader = new BufferedReader(trips);
		String line = "";
		try {
			rawReader.readLine();//
			while ((line = rawReader.readLine()) != null) {
				listeRoutes.add(new Trips(line));
			}

			line = "";
		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
		return listeRoutes;
	}

	public static ArrayList<Routes> parseRoutes(InputStreamReader route) {

		ArrayList<Routes> listeRoutes = new ArrayList<Routes>();
		BufferedReader rawReader = new BufferedReader(route);
		String line = "";
		try {
			rawReader.readLine();
			while ((line = rawReader.readLine()) != null) {
				listeRoutes.add(new Routes(line));
			}

			line = "";
		} catch (Exception e) {

			e.printStackTrace();
		}
		return listeRoutes;
	}

}
