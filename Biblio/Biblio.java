import java.util.ArrayList;

class Auteur {

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
	// Completer la classe Auteur ici
	private String nom;
	private boolean prix;
	
	public Auteur(String nom, boolean prix) {
		this.nom = nom;
		this.prix = prix;
	}
	
	public String getNom() {return nom;}
	public boolean getPrix() {
		return prix;
	}
	
}

class Oeuvre
{
 	// Completer la classe Oeuvre ici
	private String titre;
	private Auteur auteur;
	private String langue;
	
	public Oeuvre(String titre, Auteur auteur) {
		this.titre = titre;
		this.auteur = auteur;
		this.langue = "francais";
	}
	
	public Oeuvre(String titre, Auteur auteur, String langue) {
		this.titre = titre;
		this.auteur = auteur;
		this.langue = langue;
	}
	
	public Auteur getAuteur() {return auteur;}
	public String getTitre() {return titre;}
	public String getLangue() {return langue;}
	
	public void afficher() {
		System.out.println(titre +", " + auteur.getNom() +", en " + langue);
	}
	
}

// completer les autres classes ici

class Exemplaire {
	private Oeuvre oeuvre;
	 public Exemplaire(Oeuvre oeuvre) {
		 this.oeuvre = oeuvre;
		 System.out.println("Nouvel exemplaire -> " + oeuvre.getTitre() +", " + oeuvre.getAuteur().getNom() +", en " + oeuvre.getLangue());
	 }
	 
	 public Exemplaire(Exemplaire autreExemple) {
		 this.oeuvre = autreExemple.oeuvre;
		 System.out.println("Copie d'un exemplaire de -> " + oeuvre.getTitre() +", " + oeuvre.getAuteur().getNom() +", en " + oeuvre.getLangue());
	 }
	 public Oeuvre getOeuvre() {return oeuvre;};
	 
	 public void afficher() {
		 System.out.print("Un exemplaire de ");
		 oeuvre.afficher();
	}
}

class Bibliotheque {
	private String nom;
	private ArrayList<Exemplaire> exemplaires = new ArrayList<Exemplaire>();
	
	public Bibliotheque(String nom) {
		this.nom = nom;
		System.out.println("La bibliothèque " + this.nom +" est ouverte !");
	}
	
	public String getNom() {return nom;}
	
	public int getNbExemplaires() {
		return exemplaires.size();
	}
	
	public void stocker(Oeuvre exemple, int n) {
		int x = n;
		while (x > 0) {
			exemplaires.add(new Exemplaire(exemple));
			x--;
		}
	}
	public void stocker(Oeuvre exemple) {
			stocker(exemple, 1);
	}
	
	public ArrayList<Exemplaire> listerExemplaires() {
			return exemplaires;
	}
	public ArrayList<Exemplaire> listerExemplaires(String langue) {
		ArrayList<Exemplaire> listExemplaire = new ArrayList<Exemplaire>();
		for(Exemplaire exemple: exemplaires) {
			if (exemple.getOeuvre().getLangue() == langue) {
				listExemplaire.add(exemple);
			}
		}
		return listExemplaire;
	}
	
	public int compterExemplaires(Oeuvre oeuvre) {
		int n = 0;
		for(Exemplaire exemple: exemplaires) {
			if (exemple.getOeuvre() == oeuvre) {
				n++;
			}
		}
		return n;		
	}
	
	
	public void afficherAuteur() {
		succesAtores();
	}
	public void afficherAuteur(boolean prix) {
		if (prix) {
			succesAtores();
		} else {
			for(Exemplaire exemple: exemplaires) {
				if (!exemple.getOeuvre().getAuteur().getPrix()) {
					System.out.println(exemple.getOeuvre().getAuteur().getNom());
				}
			}
		}
	}
	
	private void succesAtores() {
		for(Exemplaire exemple: exemplaires) {
			if (exemple.getOeuvre().getAuteur().getPrix()) {
				System.out.println(exemple.getOeuvre().getAuteur().getNom());
			}
		}
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

public class Biblio {

    public static void afficherExemplaires(ArrayList<Exemplaire> exemplaires) {
        for (Exemplaire exemplaire : exemplaires) {
            System.out.print("\t");
            exemplaire.afficher();
        }
    }

    public static void main(String[] args) {
        // create and store all the exemplaries
        Auteur a1 = new Auteur("Victor Hugo", false);
        Auteur a2 = new Auteur("Alexandre Dumas", false);
        Auteur a3 = new Auteur("Raymond Queneau", true);

        Oeuvre o1 = new Oeuvre("Les Miserables", a1, "francais");
        Oeuvre o2 = new Oeuvre("L\'Homme qui rit", a1, "francais");
        Oeuvre o3 = new Oeuvre("Le Comte de Monte-Cristo", a2, "francais");
        Oeuvre o4 = new Oeuvre("Zazie dans le metro", a3, "francais");
        Oeuvre o5 = new Oeuvre("The count of Monte-Cristo", a2, "anglais");

        Bibliotheque biblio = new Bibliotheque("municipale");
        biblio.stocker(o1, 2);
        biblio.stocker(o2);
        biblio.stocker(o3, 3);
        biblio.stocker(o4);
        biblio.stocker(o5);

        // ...
        System.out.println("La bibliotheque " + biblio.getNom() + " offre ");
        afficherExemplaires(biblio.listerExemplaires());
        String langue = "anglais";
        System.out.println("Les exemplaires en " + langue + " sont  ");
        afficherExemplaires(biblio.listerExemplaires(langue));
        System.out.println("Les auteurs a succes sont  ");
        biblio.afficherAuteur();
        System.out.print("Il y a " + biblio.compterExemplaires(o3) + " exemplaires");
        System.out.println(" de  " + o3.getTitre());
    }
}
