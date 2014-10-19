package parser;

import java.io.InputStreamReader;

import android.content.res.Resources;
import android.view.View;

import com.example.massonsaulniertp1.R;

public class Fare {
	public String fare_id;
	public String price;
	public String currency_type;
	public String payment_method;


	public Fare(String str) {
		String[] temp = str.split(",");

		this.fare_id = temp[0];
		this.price = temp[1];
		this.currency_type = temp[2];
		this.payment_method = temp[3];
		
	}
	public static double trouverFare(Resources r, String numeroLigne) {
		InputStreamReader resourceDeFare = new InputStreamReader(r.openRawResource(R.raw.fare_attributes));
		return mainParser.parseFare(Integer.parseInt(numeroLigne),
				resourceDeFare);
	}
}
