/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
import java.util.ArrayList;
class OptionVoyage {
	private String nom;
	private double prix;
	public OptionVoyage(String nom, double prix) {
		this.nom = nom;
		this.prix = prix;
	}
	public String getNom() {return nom;}
	public double prix() {return prix;}
	public String toString() {
		return nom + " -> " +prix()+ " CHF";
	}
}

class Transport extends OptionVoyage{
	public static final  double TARIF_LONG = 1500.0;
	public static final  double TARIF_BASE = 200.0;
	private double trajetCourt;
	public Transport(String nom, double prix, boolean trajetCourt) {
		super(nom, prix);
		if (trajetCourt) {
			this.trajetCourt = TARIF_LONG;
		} else {
			this.trajetCourt = TARIF_BASE;
		}
		
	}
	public Transport(String nom, double prix) {
		super(nom, prix);
		this.trajetCourt = TARIF_BASE;
	}
	public double prix() {
		return  super.prix() + trajetCourt;
	}
}
class Sejour extends OptionVoyage{
	private double prixNuit;
	private int nombreNuit;
	public Sejour(String nom, double prix, int nombreNuit, double prixNuit) {
		super(nom, prix);
		this.nombreNuit = nombreNuit;
		this.prixNuit = prixNuit;
	}
	public double prix() {
		return (prixNuit * nombreNuit) + super.prix();
	}
}

class KitVoyage{
	private ArrayList<OptionVoyage> kitVoyage = new ArrayList<OptionVoyage>();
	private String depart;
	private String destination;
	public KitVoyage(String depart, String destination) {
		this.depart = depart;
		this.destination = destination;
	}
	public double prix() {
		double prixTotal = 0;
		for (OptionVoyage opVoyage: kitVoyage ) {
			prixTotal += opVoyage.prix();
		}
		return prixTotal;
	}
	public String toString() {
		
		String s = "Voyage de " + depart + " à "  +destination+ ", avec pour options :\n";
		for (OptionVoyage opVoyage: kitVoyage ) {
			s += "- " +opVoyage.getNom()+ " -> " + opVoyage.prix() + " CHF\n";
		}
		s += "Prix total : " + prix() + " CHF";
		return s;

	}
	public void annuler() {
		kitVoyage.clear();
	}
	public void ajouterOption(OptionVoyage optioVoyage) {
		if (optioVoyage != null) {
	        kitVoyage.add(optioVoyage);
	    }
	}
	public int getNbOptions() {return kitVoyage.size();}
}
/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/

public class Voyage {
    public static void main(String args[]) {

        // TEST 1
        System.out.println("Test partie 1 : ");
        System.out.println("----------------");
        OptionVoyage option1 = new OptionVoyage("Séjour au camping", 40.0);
        System.out.println(option1.toString());

        OptionVoyage option2 = new OptionVoyage("Visite guidée : London by night" , 50.0);
        System.out.println(option2.toString());
        System.out.println();

        // FIN TEST 1


        // TEST 2
        System.out.println("Test partie 2 : ");
        System.out.println("----------------");

        Transport transp1 = new Transport("Trajet en car ", 50.0);
        System.out.println(transp1.toString());

        Transport transp2 = new Transport("Croisière", 1300.0);
        System.out.println(transp2.toString());

        Sejour sejour1 = new Sejour("Camping les flots bleus", 20.0, 10, 30.0);
        System.out.println(sejour1.toString());
        System.out.println();

        // FIN TEST 2


        // TEST 3
        System.out.println("Test partie 3 : ");
        System.out.println("----------------");

        KitVoyage kit1 = new KitVoyage("Zurich", "Paris");
        kit1.ajouterOption(new Transport("Trajet en train", 50.0));
        kit1.ajouterOption(new Sejour("Hotel 3* : Les amandiers ", 40.0, 5, 100.0));
        System.out.println(kit1.toString());
        System.out.println();
        kit1.annuler();

        KitVoyage kit2 = new KitVoyage("Zurich", "New York");
        kit2.ajouterOption(new Transport("Trajet en avion", 50.0, true));
        kit2.ajouterOption(new Sejour("Hotel 4* : Ambassador Plazza  ", 100.0, 2, 250.0));
        System.out.println(kit2.toString());
        kit2.annuler();

        // FIN TEST 3
    }
}

