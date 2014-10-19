package parser;

import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.res.Resources;

import com.example.massonsaulniertp1.R;

public class StopTimes {

	public String trip_id;
	public String arrival_time;
	public String departure_time;
	public String stop_id;
	public String stop_sequence;


	public StopTimes(String str) {
		String[] temp = str.split(",");

		this.trip_id = temp[0];
		this.arrival_time = temp[1];
		this.departure_time = temp[2];
		this.stop_id = temp[3];
		this.stop_sequence = temp[4];

	}
	

	public static ArrayList<StopTimes> trouverStopTimes(Resources r) {
		InputStreamReader resourceDeStopTimes = new InputStreamReader(r.openRawResource(R.raw.stop_times));
		return mainParser.parseStopTime(resourceDeStopTimes);
	}
}