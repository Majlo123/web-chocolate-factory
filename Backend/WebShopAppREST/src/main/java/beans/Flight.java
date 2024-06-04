package beans;

public class Flight {

	private String id;
	private String sifra;
	private String polaziste;
	private String odrediste;
	double trajanje;
	double cena;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	public String getPolaziste() {
		return polaziste;
	}
	public void setPolaziste(String polaziste) {
		this.polaziste = polaziste;
	}
	public String getOdrediste() {
		return odrediste;
	}
	public void setOdrediste(String odrediste) {
		this.odrediste = odrediste;
	}
	public double getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(double trajanje) {
		this.trajanje = trajanje;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public Flight(String id, String sifra, String polaziste, String odrediste, double trajanje, double cena) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.polaziste = polaziste;
		this.odrediste = odrediste;
		this.trajanje = trajanje;
		this.cena = cena;
	}
	public Flight() {}
}
