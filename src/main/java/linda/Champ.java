package linda;

public class Champ {
	private String valeur;
	private String type;
	
	
	public Champ(String valeur, String type) {
		super();
		this.valeur = valeur;
		this.type = type;
	}
	
	public String getValeur() {
		return valeur;
	}
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
