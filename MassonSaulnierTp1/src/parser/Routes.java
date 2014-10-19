package parser;

public class Routes {

	public String route_id;
	public String agency_id;
	public String route_short_name;
	public String route_long_name;
	public String route_type;
	public String route_url;

	public Routes(String str) {
		String[] temp = str.split(",");
		this.route_id = temp[0];
		this.agency_id = temp[1];
		this.route_short_name = temp[2];
		this.route_long_name = temp[3];
		this.route_type = temp[4];
		this.route_url = temp[5];
	}
	
	
}