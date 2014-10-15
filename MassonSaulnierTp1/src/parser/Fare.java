package parser;

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
}
