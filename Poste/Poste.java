import java.util.ArrayList;

class Boite {
	private ArrayList<Lettres> boitelettres;
	public Boite(int nombreLetters) {
		boitelettres = new ArrayList<Lettres>();
	}
	
	public ArrayList<Lettres>  getBoitelettres() {return boitelettres;}
	public void ajouterCourrier(Lettres lette) {
		boitelettres.add(lette);
	}
	
	public double affranchir() {
		double montantTotal = 0;
		for (Lettres lette : boitelettres) {
			montantTotal += lette.affranchir();
		}
		return montantTotal;
	}
	
	public int courriersInvalides() {
		int nombreInvalid = 0;
		for (Lettres lette : boitelettres) {
			if (!lette.valide()) {
				nombreInvalid++;
			}
		}
		return nombreInvalid;
	}
	
	public void afficher() {
		for (Lettres lette : boitelettres) {
			System.out.println(lette.toString());
		}
	}
	
}


class Lettres {
	protected int poids;
	protected boolean espeditionNormal;
	protected String adresseDestination;
	public Lettres(int poids, boolean espeditionNormal,String adresseDestination) {
		this.poids = poids;
		this.espeditionNormal = espeditionNormal;
		this.adresseDestination = adresseDestination;
	}
	
	
	public String toString() {
		String s = "";
		if (!valide()) {
			s += "(Courrier invalide)\n";
		}
		s += "	Poids : " + poids + " grammes\n";
		s += "	Espress : "+ espeditionNormal + "\n";
		s += "	Destination : " +adresseDestination +"\n";
		s += "	Prix : " + affranchir() +" CHF\n";
		return s;
	}
	protected int getPoids() {return poids;}
	protected boolean getEspeditionNormal() {return espeditionNormal;}
	protected String getAdresseDestination() {return adresseDestination;}
	
	
	protected boolean espeditionNormal() {
		return espeditionNormal;
	}
	protected boolean valide() {
		return adresseDestination.length() > 0;
	}
	public double affranchissementNormal() {
		return 0;
	}
	
	public double affranchir() {
		double montant = 0;
		if (!valide()) {
			return 0;
		} else {
			montant = affranchissementNormal();
			if (espeditionNormal()) {
				montant *= 2;
			}
			return montant;
		}
		
		
	}
	
}

class Colis extends Lettres {
	private double volume;
	public Colis(int poids, boolean espeditionNormal,String adresseDestination, int volume) {
		super(poids, espeditionNormal, adresseDestination);
		this.volume = volume;
	}
	public String toString() {
		String s = "Colis \n";
		s += super.toString();
		s += "	Volume : "+ volume +"\n";
		return s;
	}
	public double getVolume() {return volume;}
	public boolean valide() {
		if (adresseDestination.isEmpty() || volume > 55) {return false;}
		return true;
	}
	public double affranchissementNormal() {
		return 0.25 * volume + poids/1000.0;
	}
	
	
	
}

class Lettre extends Lettres {
	private String format;
	public Lettre(int poids, boolean espeditionNormal,String adresseDestination, String format) {
		super(poids, espeditionNormal, adresseDestination);
		this.format = format;
	}
	
	public String toString() {
		String s = "Letrre \n";
		 s += super.toString();
		s += "	Format : "+ format + "\n";
		return s;
	}
	
	public String getFormat() {return format;}
	
	public boolean estLettres() {
			return true;
	}
	public double affranchissementNormal() {
		if (format.equals("A3")) {
			return 3.50 + (poids/1000.0);
		}
		return 2.50 + (poids/1000.0) ;
	}
}

class Publicite extends Lettres {
	public Publicite(int poids, boolean espeditionNormal,String adresseDestination) {
		super(poids, espeditionNormal, adresseDestination);
	}
		
	public double affranchissementNormal() {
		return poids/1000.0 * 5.0;
	}
	public String toString() {
		String s = "Publicité \n";
		 s += super.toString();
		return s;
	}

}
// PROGRAMME PRINCIPAL (A NE PAS MODIFIER)
class Poste {

	public static void  main(String args[]) {
		//Cr'eation d'une boite-aux-lettres
		// 30  est la capacit'e maximale de la
		// boite aux lettres
		// (pas necessaire si vous dêcidez d'utiliser
		// un ArrayList).
		Boite boite = new Boite(30);

		//Creation de divers courriers/colis..
		Lettre lettre1 = new Lettre(200, true, "Chemin des Acacias 28, 1009 Pully", "A3");
		Lettre lettre2 = new Lettre(800, false, "", "A4"); // invalide

		Publicite pub1 = new Publicite(1500, true, "Les Moilles  13A, 1913 Saillon");
		Publicite pub2 = new Publicite(3000, false, ""); // invalide

		Colis colis1 = new Colis(5000, true, "Grand rue 18, 1950 Sion", 30);
		Colis colis2 = new Colis(3000, true, "Chemin des fleurs 48, 2800 Delemont", 70); //Colis invalide !

		boite.ajouterCourrier(lettre1);
		boite.ajouterCourrier(lettre2);
		boite.ajouterCourrier(pub1);
		boite.ajouterCourrier(pub2);
		boite.ajouterCourrier(colis1);
		boite.ajouterCourrier(colis2);


		System.out.println("Le montant total d'affranchissement est de " +
						   boite.affranchir());
		boite.afficher();
		
		System.out.println("La boite contient " + boite.courriersInvalides()
						   + " courriers invalides");
	}
}
