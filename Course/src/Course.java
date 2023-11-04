import java.util.ArrayList;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
class Vehicule {
	private String nom;
	private double vitesseMax;
	private int poids;
	private int carburantNiveau;
	public Vehicule(String nom, double vitesseMax, int poids, int carburantNiveau) {
		this.nom = nom;
		this.vitesseMax = vitesseMax;
		this.poids = poids;
		this.carburantNiveau = carburantNiveau;
	}
	public Vehicule(String nom, double vitesseMax, int poids) {
		this.nom = nom;
		this.vitesseMax = vitesseMax;
		this.poids = poids;
		this.carburantNiveau = 0;
	}
	public Vehicule(String nom, double vitesseMax) {
		this.nom = nom;
		this.vitesseMax = vitesseMax;
		this.poids = 1000;
		this.carburantNiveau = 0;
	}
	public Vehicule(String nom) {
		this.nom = nom;
		this.vitesseMax = 130;
		this.poids = 1000;
		this.carburantNiveau = 0;
	}
	public Vehicule() {
		this.nom = "Anonyme";
		this.vitesseMax = 130;
		this.poids = 1000;
		this.carburantNiveau = 0;
	}
	
	public String toString() {
		return nom + " -> vitesse max = " + vitesseMax+ " km/h, poids = " +poids+ " kg";
	}
	
	public boolean meilleur(Vehicule autreVehicule) {
		return this.performance() >= autreVehicule.performance();
	}
	public String getNom(){ return nom;}
	public double getVitesseMax() { return vitesseMax;}
	public int getPoids() { return poids;}
	public int getCarburant() { return carburantNiveau;}
	public void setCarburant(int carbu) {  carburantNiveau = carbu;}
	private double performance() {
		return vitesseMax / poids;
	}
	public boolean estDeuxRoues() {
		return false;
	}

}

class Voiture extends Vehicule{
	private String categorie;
	public Voiture(String nom, double vitesseMax, int poids, int carburantNiveau, String categorie) {
		super(nom, vitesseMax, poids, carburantNiveau);
		this.categorie = categorie;
	}
	
	public String toString() {
		String s = super.toString();
		s += ", Voiture de "+ categorie;
		return s;
	}
	public String getCategorie() { return categorie;}
	public boolean estDeuxRoues() {
		return super.estDeuxRoues();
	}
}
class Moto extends Vehicule{
	private boolean sidecar;
	public Moto(String nom, double vitesseMax, int poids, int carburantNiveau, boolean sidecar) {
		super(nom, vitesseMax, poids, carburantNiveau);
		this.sidecar = sidecar;
	}
	public Moto(String nom, double vitesseMax, int poids, int carburantNiveau) {
		super(nom, vitesseMax, poids, carburantNiveau);
		this.sidecar = false;
	}
	
	public String toString() {
		String s = super.toString();
		if (sidecar) {
			s += ", Moto, avec sidecar";
		} else {
			s += ", Moto";
		}
		
		return s;
	}
	public boolean estDeuxRoues() {
		return !sidecar;
	}
	
}

class GrandPrix extends Rallye{
	private ArrayList<Vehicule> vehicules = new ArrayList<Vehicule>();
	public void ajouter(Vehicule vehicule) {
		if (vehicule != null) {
			vehicules.add(vehicule);
		}
	}
	public boolean check() {
		int index = 0,c = 0;
		for (Vehicule vehicule : vehicules) {
			if (vehicule.estDeuxRoues()) {
				index++;
			} else if(!vehicule.estDeuxRoues()){
				c++;
			}
		}
		return vehicules.size() == index || vehicules.size() == c;
		
	}
	public void run(int tours) {
		if (!check()) {
			System.out.println("Pas de Grand Prix");
			 return;

		}
		
		ArrayList<Vehicule> vehiculesArrivee = new ArrayList<Vehicule>();

		for (Vehicule vehicule : vehicules) {
			vehicule.setCarburant(vehicule.getCarburant() - tours);
			if (vehicule.getCarburant() > 0) {
				vehiculesArrivee.add(vehicule);
			}
		}
		if (vehiculesArrivee.size() == 0) {
			System.out.println("Elimination de tous les vehicules");
			return;
		}
		int index = 0;
		for (int i = 0; i < vehiculesArrivee.size(); ++i) {
			if (i == 0) {
				index = 0;
			} else {
				if (vehiculesArrivee.get(i).meilleur(vehiculesArrivee.get(index))) {
					index = i;
				}
			}
			
		}
		System.out.println("Le gagnant du grand prix est :");
		System.out.println(vehicules.get(index).toString());
		
	}
}
abstract class Rallye{
	 public abstract boolean check();
}
/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
public class Course {

    public static void main(String[] args) {

        // PARTIE 1
        System.out.println("Test partie 1 : ");
        System.out.println("----------------");
        Vehicule v0 = new Vehicule();
        System.out.println(v0);

        // les arguments du constructeurs sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        Vehicule v1 = new Vehicule("Ferrari", 300.0, 800, 30);
        Vehicule v2 = new Vehicule("Renault Clio", 180.0, 1000, 20);
        System.out.println();
        System.out.println(v1);
        System.out.println();
        System.out.println(v2);

        if (v1.meilleur(v2)) {
            System.out.println("Le premier vehicule est meilleur que le second");
        } else {
            System.out.println("Le second vehicule est meilleur que le premier");
        }
        // FIN PARTIE 1

        // PARTIE2
        System.out.println();
        System.out.println("Test partie 2 : ");
        System.out.println("----------------");

        // les trois premiers arguments sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        // le dernier argument indique la presence d'un sidecar
        Moto m1 = new Moto("Honda", 200.0, 250, 15, true);
        Moto m2 = new Moto("Kawasaki", 280.0, 180, 10);
        System.out.println(m1);
        System.out.println();
        System.out.println(m2);
        System.out.println();

        // les trois premiers arguments sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        // le dernier argument indique la categorie
        Voiture vt1 = new Voiture("Lamborghini", 320, 1200, 40, "course");
        Voiture vt2 = new Voiture("BMW", 190, 2000, 35, "tourisme");
        System.out.println(vt1);
        System.out.println();
        System.out.println(vt2);
        System.out.println();
        // FIN PARTIE 2

        // PARTIE 3
        System.out.println();
        System.out.println("Test partie 3 : ");
        System.out.println("----------------");

        GrandPrix gp1 = new GrandPrix();
        gp1.ajouter(v1);
        gp1.ajouter(v2);
        System.out.println(gp1.check());

        GrandPrix gp2 = new GrandPrix();
        gp2.ajouter(vt1);
        gp2.ajouter(vt2);
        gp2.ajouter(m2);
        System.out.println(gp2.check());

        GrandPrix gp3 = new GrandPrix();
        gp3.ajouter(vt1);
        gp3.ajouter(vt2);
        gp3.ajouter(m1);
        System.out.println(gp3.check());

        GrandPrix gp4 = new GrandPrix();
        gp4.ajouter(m1);
        gp4.ajouter(m2);
        System.out.println(gp4.check());
        // FIN PARTIE 3

        // PARTIE 4
        System.out.println();
        System.out.println("Test partie 4 : ");
        System.out.println("----------------");
        GrandPrix gp5 = new GrandPrix();
        gp5.ajouter(vt1);
        gp5.ajouter(vt2);

        System.out.println("Premiere course :");
        gp5.run(11);
        System.out.println();

        System.out.println("Deuxieme  course :");
        gp3.run(40);
        System.out.println();

        System.out.println("Troisieme  course :");
        gp2.run(11);
        // FIN PARTIE 4
    }
}

