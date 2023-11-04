/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
class Employe {
	private double prime = 0;
	private final String nom;
	private double revenu;
	private int taux;
	public Employe(String nom, double revenu, int taux) {
		this.nom = nom;
		this.revenu = revenu;
		if (taux < 10) {
			this.taux = 10;
		}else if (taux > 100) {
			this.taux = 100;
		}else {
			this.taux = taux;
		}
		System.out.print("Nous avons un nouvel employé : "+ nom+",");
	}
	public Employe(String nom, double revenu) {
		this.nom = nom;
		this.revenu = revenu;
		this.taux = 100;
		System.out.print("Nous avons un nouvel employé : "+ nom+",");
	}
	public String getNom(){
		return nom;
	}
	public double getRevenu() {
		return revenu;
	}
	public double getTaux(){
		return taux;
	}
	public String toString() {
		String s = nom+" :\n";
		if (prime != 0) {
			s += "  Taux d'occupation : "+taux+"%. Salaire annuel : " +String.format("%.2f", revenuAnnuel()) +" francs, Prime : "+String.format("%.2f", prime)+".\n";
		} else {
			s += "  Taux d'occupation : "+taux+"%. Salaire annuel : " +String.format("%.2f", revenuAnnuel()) +" francs.\n";
		}
		
		return s;
	}
	public double revenuAnnuel() {
		return revenu * 12 * (taux/100.0) + prime;
	}
	public void demandePrime() {
		Scanner scanner = new Scanner(System.in);
		double montant = 0;
		boolean x = true;
		int nbtentive = 0;
		double test = ((2.0/100.0) * revenuAnnuel());
		do {
			x = true;
			System.out.println("Montant de la prime souhaitée par "+nom+" ?");
			nbtentive ++;
			try {
				String input = scanner.next();
		        montant = Double.parseDouble(input);
				
			}catch(InputMismatchException e) {
				x = false;
				System.out.println("Vous devez introduire un nombre!");
				scanner.nextLine();
			}
			if (montant >  test) {
				System.out.println("Trop cher!");
			}
			
		}while ((montant >  test && nbtentive < 5) || !x );
		scanner.close();
		if ( nbtentive < 6 && montant <=  test ) {
			prime = montant;
		} else {
			prime = 0;
		}
	}
}
class Manager extends Employe{
	public final static int  FACTEUR_GAIN_CLIENT = 500;
	public final static int FACTEUR_GAIN_VOYAGE = 100;
	private int nombreVoyages;
	private int clientsApportes;
	public Manager(String nom, double revenu, int nombreVoyages, int clientsApportes, int taux) {
		super(nom, revenu, taux);
		this.nombreVoyages = nombreVoyages;
		this.clientsApportes = clientsApportes;
		System.out.println(" c'est un manager.");
	}
	public Manager(String nom, double revenu, int nombreVoyages, int clientsApportes) {
		super(nom, revenu);
		this.nombreVoyages = nombreVoyages;
		this.clientsApportes = clientsApportes;
		System.out.println(" c'est un manager.");
	}
	public String toString() {
		String s = super.toString();
		s += "  A voyagé "+nombreVoyages+" jours et apporté "+clientsApportes+" nouveaux clients.\n";
		return s;
	}
	public double revenuAnnuel() {
		return super.revenuAnnuel() + (FACTEUR_GAIN_CLIENT * clientsApportes) + (FACTEUR_GAIN_VOYAGE * nombreVoyages);
	}
}
class Testeur extends Employe{
	public final static int FACTEUR_GAIN_ERREURS = 10;
	private int nombreErreur;
	public Testeur(String nom, double revenu, int nombreErreur, int taux) {
		super(nom, revenu, taux);
		this.nombreErreur = nombreErreur;
		System.out.println(" c'est un testeur.");
	}
	public Testeur(String nom, double revenu, int nombreErreur) {
		super(nom, revenu);
		this.nombreErreur = nombreErreur;
		System.out.println(" c'est un testeur.");
	}
	public String toString() {
		String s = super.toString();
		s += "  A corrigé "+nombreErreur+" erreurs.\n";
		return s;
	}
	public double revenuAnnuel() {
		return super.revenuAnnuel() + (	FACTEUR_GAIN_ERREURS * nombreErreur);
	}
}
class Programmeur extends Employe{
	public final static int FACTEUR_GAIN_PROJETS = 200;
	private int projectAcheves;
	public Programmeur(String nom, double revenu, int projectAcheves, int taux) {
		super(nom, revenu, taux);
		this.projectAcheves = projectAcheves;
		System.out.println(" c'est un programmeur.");
	}
	public Programmeur(String nom, double revenu, int projectAcheves) {
		super(nom, revenu);
		this.projectAcheves = projectAcheves;
		System.out.println(" c'est un programmeur.");
	}
	public String toString() {
		String s = super.toString();
		if ( projectAcheves > 1) {
			s += "  A mené à bien "+projectAcheves+" projets\n";
		}else {
			s += "  A mené à bien "+projectAcheves+" projet\n";
		}
		return s;
	}
	public double revenuAnnuel() {
		return super.revenuAnnuel() + (	FACTEUR_GAIN_PROJETS * projectAcheves);
	}
}
/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
class Employes {
    public static void main(String[] args) {

        List<Employe> staff = new ArrayList<Employe>();

        // TEST PARTIE 1:

        System.out.println("Test partie 1 : ");
        staff.add(new Manager("Serge Legrand", 7456, 30, 4 ));
        staff.add(new Programmeur("Paul Lepetit" , 6456, 3, 75 ));
        staff.add(new Testeur("Pierre Lelong", 5456, 124, 50 ));

        System.out.println("Affichage des employés : ");
        for (Employe modele : staff) {
            System.out.println(modele);
        }
        // FIN TEST PARTIE 1
        // TEST PARTIE 2
        System.out.println("Test partie 2 : ");

        staff.get(0).demandePrime();

        System.out.println("Affichage après demande de prime : ");
        System.out.println(staff.get(0));

        // FIN TEST PARTIE 2
    }
}

