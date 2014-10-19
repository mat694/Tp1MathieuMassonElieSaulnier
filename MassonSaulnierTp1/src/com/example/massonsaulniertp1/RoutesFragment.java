package com.example.massonsaulniertp1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import parser.Routes;
import parser.Trips;
import multipleListView.TripStopTime;
import multipleListView.listviewAdapter;
import static multipleListView.Constant.NUMEROLIGNE_COLUMN;
import static multipleListView.Constant.PREMIEREDIRECTION_COLUMN;
import static multipleListView.Constant.DEUXIEMEDIRECTION_COLUMN;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class RoutesFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */

	@SuppressWarnings("rawtypes")
	private ArrayList<HashMap> list;
	ListView lview;
	private ArrayList<Trips> listeDeTrips;
	private ArrayList<Routes> listeDeRoute;
	private static final String ARG_SECTION_NUMBER = "section_number";
	private ArrayList<TripStopTime> listeDesTripsStopTime;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static RoutesFragment newInstance(int sectionNumber) {
		RoutesFragment fragment = new RoutesFragment();

		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_route, container,
				false);

		setWidget(rootView);
		populateList();
		listviewAdapter adapter = new multipleListView.listviewAdapter(this,
				list, inflater);
		lview.setAdapter(adapter);
		return rootView;
	}

	private void setWidget(View rootView) {
		lview = (ListView) rootView.findViewById(R.id.listview);
		listeDeRoute = Routes.trouverRoutes(this.getResources());
		
		listeDeTrips = trouverLesTripsDifferent(Trips.trouverTrips(this
				.getResources()));
		Collections.sort(listeDeTrips, new Comparator<Trips>() {
			@Override
			public int compare(Trips t1, Trips t2) {
				return Integer.parseInt(t1.route_id)
						- Integer.parseInt(t2.route_id);
			}
		});
		listeDesTripsStopTime = peuplerListePourAdapter(listeDeTrips, rootView);
	}

	private ArrayList<Trips> trouverLesTripsDifferent(
			ArrayList<Trips> listeDeTrips) {
		Boolean prochain = false;
		ArrayList<String> listeTrouver = new ArrayList<String>();
		ArrayList<Trips> NouvelleListeDeTrips = new ArrayList<Trips>();		
		for (Trips t : listeDeTrips) {
			if (!listeTrouver.contains(t.route_id)) {
				listeTrouver.add(t.route_id);
				NouvelleListeDeTrips.add(t);
				prochain = true;
			} else {
				if (prochain && t.route_id.equals(listeTrouver.get(listeTrouver.size()-1))) {
					prochain = false;
					NouvelleListeDeTrips.add(t);
				}
			}

		}
		return NouvelleListeDeTrips;
	}

	private ArrayList<TripStopTime> peuplerListePourAdapter(ArrayList<Trips> listeDeTrip , View rootView){
		ArrayList<TripStopTime> listeDeTripStopTime = new ArrayList<TripStopTime>();
		try{
			TripStopTime TST;
			for(int i =0 ; i<= listeDeTrip.size();i=i+2){
				TST = new TripStopTime(listeDeTrip.get(i), listeDeTrip.get(i+1), trouverNumeroLigne(listeDeTrip.get(i).route_id), rootView.getContext());
				listeDeTripStopTime.add(TST);
			}
		}
		catch(Exception e){
			
		}
		return listeDeTripStopTime;
	}

	private String trouverNumeroLigne(String routeID){
		for(Routes rID : listeDeRoute)
		{
			if(rID.route_id.equals(routeID))
				return rID.route_long_name;
		}
		return "";
		
		
	}
	private void populateList() {
		list = new ArrayList<HashMap>();	
	for(TripStopTime tst : listeDesTripsStopTime)	{
		HashMap<String, String> temp = new HashMap<String, String>();
		temp.put(NUMEROLIGNE_COLUMN, tst.getNumeroLigne());
		temp.put(PREMIEREDIRECTION_COLUMN, tst.getDirection1());
		temp.put(DEUXIEMEDIRECTION_COLUMN, tst.getDirection2());

		list.add(temp);
	}

	}

}