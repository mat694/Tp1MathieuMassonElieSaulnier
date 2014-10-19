package parser;

import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.res.Resources;

import com.example.massonsaulniertp1.R;

public class Trips {

	public String route_id;
	public String service_id;
	public String trip_id;
	public String trip_headsign;
	public String wheelchair_accessible;



	public Trips(String str) {
		String[] temp = str.split(",");
		this.route_id = temp[0];
		this.service_id = temp[1];
		this.trip_id = temp[2];
		this.trip_headsign = temp[3];
		this.wheelchair_accessible = temp[4];
	}
	
	public static ArrayList<Trips> trouverTrips(Resources r) {
		InputStreamReader resourceDeTrips = new InputStreamReader(r.openRawResource(R.raw.trips));
		return mainParser.parseTrips(resourceDeTrips);
	}
}