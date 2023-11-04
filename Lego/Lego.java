/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
import java.util.ArrayList;
class Piece {
	private String nom;
	public Piece(String nom) {
		this.nom = nom;
	}
	protected String getNom() {return nom;}
	public String toString() {
		return nom;
	}
}
class Composant {
	private int quantite;
	private Piece piece;
	public Composant(Piece piece, int quantite) {
		this.piece = piece;
		this.quantite = quantite;
	}
	protected int getQuantite() {return quantite;}
	protected Piece getPiece() {return piece;}
	
}

class Simple extends Piece {
	private String orientation;
	public Simple(String nom, String orientation) {
		super(nom);
		this.orientation = orientation;
	}
	public Simple(String nom) {
		super(nom);
		this.orientation = "aucune";
	}
	public String getOrientation() {return orientation;}
	public String toString() {
		if (orientation.equals("aucune")) {
            return getNom();
        } else {
            return getNom() + " " + orientation;
        }
	}
}

class Composee extends Piece{
	private ArrayList<Piece> pieceComposee =  new ArrayList<Piece>();
	private int taille;
	
	public Composee(String nom, int taille) {
		super(nom);
		this.taille = taille;
	}
	public void construire(Piece piece) {
		if (taille() < taille) {
			pieceComposee.add(piece);	
		} else {
			System.out.println("Construction impossible");
		}
	}
	public int taille() {return pieceComposee.size();}
	public int tailleMax() {return taille;}
	public String toString() {
		String s = getNom() + " (";
		for (int i = 0; i < pieceComposee.size(); ++i) {
			s += pieceComposee.get(i).getNom();
			if (i < taille() - 1) {
				s += ",";
			}
		}
		s += ")";
		return s;
	}
	
}

class Construction {
	private ArrayList<Composant> composants =  new ArrayList<Composant>();
	private int nombreMaxiComposants;
	
	public Construction(int nombreMaxiComposants) {
		this.nombreMaxiComposants = nombreMaxiComposants;
	}
	public void ajouterComposant(Piece piece, int n) {
		Composant composant = new Composant(piece, n);
		if (taille() < nombreMaxiComposants) {
			composants.add(composant);	
		} else {
			System.out.println("Ajout de composant impossible");
		}
		
	}
	public int taille() {return composants.size();}
	public int tailleMax() {return nombreMaxiComposants;}
	public String toString() {
		String s = "";
		for (int i = 0; i < taille(); ++i) {
				int quantite = composants.get(i).getQuantite();
				s += composants.get(i).getPiece().toString() + " (quantite " + quantite + ")\n";
		}
		return s;
	}
}
/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
/*******************************************
 * Ce qui suit est propose' pour vous aider
 * dans vos tests, mais votre programme sera
 * teste' avec d'autres donnees.
 *******************************************/

class Lego {

    public static void main(String[] args) {
        // declare un jeu de construction de 10 pieces
        Construction maison = new Construction(10);

        // ce jeu a pour premier composant: 59 briques standard
        // une brique standard a par defaut "aucune" comme orientation
        maison.ajouterComposant(new Simple("brique standard"), 59);

        // declare une piece dont le nom est "porte", composee de 2 autres pieces
        Composee porte = new Composee("porte", 2);

        // cette piece composee est constituee de deux pieces simples:
        // un cadran de porte orient'e a gauche
        // un battant orient'e a gauche
        porte.construire(new Simple("cadran porte", "gauche"));
        porte.construire(new Simple("battant", "gauche"));

        // le jeu a pour second composant: 1 porte
        maison.ajouterComposant(porte, 1);

        // déclare une piece composee de 3 autres pieces dont le nom est "fenetre"
        Composee fenetre = new Composee("fenetre", 3);

        // cette piece composee est constitu'ee des trois pieces simples:
        // un cadran de fenetre (aucune orientation)
        // un volet orient'e a gauche
        // un volet orient'e a droite
        fenetre.construire(new Simple("cadran fenetre"));
        fenetre.construire(new Simple("volet", "gauche"));
        fenetre.construire(new Simple("volet", "droit"));

        // le jeu a pour troisieme composant: 2 fenetres
        maison.ajouterComposant(fenetre, 2);

        // affiche tous les composants composants (nom de la piece et quantit'e)
        // pour les pieces compos'ees : affiche aussi chaque piece les constituant
        System.out.println("Affichage du jeu de construction : ");
        System.out.println(maison);
    }
}
