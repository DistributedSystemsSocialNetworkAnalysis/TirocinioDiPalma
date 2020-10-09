import java.util.ArrayList;

public class ParametriUtente {

	private String identificatore;
	private int numeroTweet;
	private ArrayList<String> testi;
	private ArrayList<String> date;
	
	public ParametriUtente(String ide, int nt, ArrayList<String> t, ArrayList<String> d) {
		this.identificatore = ide;
		this.numeroTweet = nt;
		this.testi = t;
		this.date = d;
	}
	
	public String getIde() {
		return this.identificatore;
	}
	
	public int getNTweet() {
		return this.numeroTweet;
	}
	
	public ArrayList<String> getTesti(){
		return this.testi;
	}
	
	public ArrayList<String> getDate(){
		return this.date;
	}
	
	public void aggiugiTweet() {
		this.numeroTweet++;
	}
	
	public void aggiungiData(String s) {
		this.date.add(s);
	}
	
	public void aggiungiTesto(String s) {
		this.testi.add(s);
	}
	
}
