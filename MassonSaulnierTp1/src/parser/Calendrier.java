package parser;

public class Calendrier {

	public String id_service;
	public String lundi;
	public String mardi;
	public String mercredi;
	public String jeudi;
	public String vendredi;
	public String samedi;
	public String dimanche;
	public String date_debut;
	public String fin_date;

	public Calendrier(String str) {
		String[] temp = str.split(",");

		this.id_service = temp[0];
		this.lundi = temp[1];
		this.mardi = temp[2];
		this.mercredi = temp[3];
		this.jeudi = temp[4];
		this.vendredi = temp[5];
		this.samedi = temp[6];
		this.dimanche = temp[7];
		this.date_debut = temp[8];
		this.fin_date = temp[9];

	}
}