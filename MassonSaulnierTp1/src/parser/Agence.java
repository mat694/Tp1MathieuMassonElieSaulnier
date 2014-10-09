package parser;

public class Agence {

	public String id_agence;
	public String nom_agence;
	public String url_agence;
	public String timezone_agence;
	public String langue_agence;
	public String tel_agence;

	public Agence(String str) {
		String[] temp = str.split(",");

		this.id_agence = temp[0];
		this.nom_agence = temp[1];
		this.url_agence = temp[2];
		this.timezone_agence = temp[3];
		this.tel_agence = temp[4];
		this.langue_agence = temp[5];
	}
}