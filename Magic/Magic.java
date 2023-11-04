import java.util.ArrayList;


class Jeu {
	private ArrayList<Carte> cartes = new ArrayList<Carte>();
	private int nombre;
	public Jeu(int nombre) {
		this.nombre = nombre;
	}
	
	public void piocher(Carte carte) {
		if (cartes.size() < nombre ) {
			cartes.add(carte);
		}
	}
	public void afficher() {
		for(Carte carte: cartes) {
			carte.afficher();
		}
	}
	public void joue() {
		System.out.println("Je joue une carte ...");
		System.out.println("la carte joué est : ");
		for(Carte carte: cartes) {
			if (carte != null) {
				carte.afficher();
				carte = null;
				break;
			}
		}		
	}
}

abstract class Carte{
	
	protected Double cout;
	public Carte(double cout) {
		this.cout = cout;
	}
	public abstract void afficher();
}


class Terrain  extends Carte {
	private char couleur;
	public Terrain(char couleur) {
		super(0);
		this.couleur = couleur;
		System.out.println("un nouveau Terrain ");
	}
	public void afficher() {
		System.out.println("un Terrain "+ couleur);
	}
}

class Creature extends Carte{
	private int pointDegats;
	private int pointVie;
	private String nom;
	public Creature(double cout, String nom, int pointDegats, int pointVie) {
		super(cout);
		this.pointDegats = pointDegats;
		this.pointVie = pointVie;
		this.nom = nom;
		System.out.println("Une nouvelle creature ");
	}
	public void afficher() {
		System.out.println("Une creature " + nom + " " +pointDegats+ "/" + pointVie);
	}
}

class Sortilege extends Carte{
	private String explication;
	private String nom;
	public Sortilege(double cout, String nom, String explication) {
		super(cout);
		this.explication = explication;
		this.nom = nom;
		System.out.println("un Sortilege de plus ");
		
	}
	public void afficher() {
		System.out.println("Un Sortilege "+ nom);
	}
	
}

class Magic {
	public static void main(String[] args) {
		Jeu maMain = new Jeu(10);
		maMain.piocher(new Terrain('b'));
		maMain.piocher(new Creature(6, "Golem", 4, 6));
		maMain.piocher(new Sortilege(1, "Croissance Gigantesque",
		"La créature ciblée gagne +3/+3 jusqu'à la fin du tour"));
		System.out.println("Là, j'ai en stock :");
		maMain.afficher();
		maMain.joue();
	}
}