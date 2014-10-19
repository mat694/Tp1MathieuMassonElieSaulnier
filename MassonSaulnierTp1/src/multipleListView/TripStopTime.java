package multipleListView;

import parser.Trips;
import android.R;
import android.content.Context;

public class TripStopTime {
	private String tripID1;
	private String tripID2;
	private String Direction1;
	private String Direction2;
	private String numeroLigne;

	public String getTripID1() {
		return tripID1;
	}

	public void setTripID1(String tripID1) {
		this.tripID1 = tripID1;
	}

	public String getTripID2() {
		return tripID2;
	}

	public void setTripID2(String tripID2) {
		this.tripID2 = tripID2;
	}

	public String getDirection1() {
		return Direction1;
	}

	public void setDirection1(String direction1) {
		Direction1 = direction1;
	}

	public String getDirection2() {
		return Direction2;
	}

	public void setDirection2(String direction2) {
		Direction2 = direction2;
	}

	public String getNumeroLigne() {
		return numeroLigne;
	}

	public void setNumeroLigne(String numeroLigne) {
		this.numeroLigne = numeroLigne;
	}

	public TripStopTime(Trips t1, Trips t2, String numeroLigne, Context C) {
		this.tripID1 = t1.trip_id;
		this.tripID2 = t2.trip_id;
		this.numeroLigne = numeroLigne;
		this.Direction1 =TrouverDirection(t1, C);
		this.Direction2 =TrouverDirection(t2, C);
	}

	private String TrouverDirection(Trips t1, Context c) {
		String direction = slipDirect(t1.trip_headsign);
		if (direction.equals("N")) {
			return c.getString(com.example.massonsaulniertp1.R.string.Nord);
		} else if (direction.equals("S")) {
			return c.getString(com.example.massonsaulniertp1.R.string.Sud);
		} else if (direction.equals("O")) {
			return c.getString(com.example.massonsaulniertp1.R.string.Ouest);
		} else {
			return c.getString(com.example.massonsaulniertp1.R.string.Est);
		}

	}

	private String slipDirect(String Direction) {

		String[] temp;
		temp = Direction.split("-");
		return temp[1];

	}
}
