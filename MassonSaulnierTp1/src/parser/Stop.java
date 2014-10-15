package parser;

public class Stop {
	
	public String stop_id;
	public String stop_code;
	public String stop_name;
	public String stop_lat;
	public String stop_url;
	public String wheelchair_accessible;



	public Stop(String str) {
		String[] temp = str.split(",");
		this.stop_id = temp[0];
		this.stop_code = temp[1];
		this.stop_name = temp[2];
		this.stop_lat = temp[3];
		this.stop_url = temp[4];
		this.wheelchair_accessible = temp[5];
	}
}
