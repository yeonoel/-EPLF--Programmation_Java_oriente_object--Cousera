import java.util.ArrayList;
import java.util.Calendar;

class Ecole {
	protected ArrayList<Personne> personnes;
	
	public Ecole(int taille) {
		personnes = new ArrayList<Personne>();
	}
	
	protected void addPerson(Personne person) {
		if (person != null ) {
			personnes.add(person);
		}
		System.out.println("Ajout de "+ person.nom + "au tableau   taille" + personnes.size());
	}
	
	protected ArrayList<Personne> getPersonnes() {
		return personnes;
	}
	protected void affichage() {
		int nombrePersonnes = personnes.size();
		int anneeCouraante = Calendar.getInstance().get(Calendar.YEAR);
		int n = 0;
		int nbanneeTotal = 0;
		for (Personne personne: personnes) {
			nbanneeTotal = nbanneeTotal + (anneeCouraante - personne.getAnnee());
			if (personne instanceof Etudiant) {
				n++;
			}
		}
		System.out.println("Parmi les " + nombrePersonnes +" EPFLiens," + n + " sont des etudiants.");
		double moyen = nbanneeTotal / personnes.size();
		System.out.println("Ils sont ici depuis en moyenne " + moyen + " ans");
		System.out.println("Liste des EPFLiens:");
	}
	protected void list() {
		
	}
}
class Personne {
	
	protected String nom;
	protected int anneeArriver;
	public Personne(String nom, int anneeArriver) {
		this.nom = nom;
		this.anneeArriver = anneeArriver;
	}
	
	protected int getAnnee() {
		return anneeArriver;
	}
	protected String getNom() {
		return nom;
	}
	
	public void afficher() {
		System.out.println(" Nom : " + getNom());
		System.out.println(" Annee : " + getAnnee());
	}
}
class Secretaire extends Personne {
	protected String NomLabo;
	protected double salaire;
	public Secretaire(String nom, int anneeArriver, String NomLabo, double salaire) {
		super(nom, anneeArriver);
		this.NomLabo = NomLabo;
		this.salaire = salaire;
		System.out.println("Creation de la secretaire: " + nom);
	}
	
	public void afficher() {
		System.out.println("Secretaire: ");
		super.afficher();
		System.out.println(" Laboratoire: " + NomLabo);
		System.out.println(" Salaire: " + salaire);
	}
	
	
}
class Enseignant extends Personne {
	protected String nomSection;
	protected String NomLabo;
	protected double salaire;
	public Enseignant(String nom, int anneeArriver, String NomLabo, double salaire, String nomSection) {
		super(nom, anneeArriver);
		this.NomLabo = NomLabo;
		this.salaire = salaire;
		this.nomSection = nomSection;
		System.out.println("Creation de la secretaire: " + nom);
	}
	
	public void afficher() {
		System.out.println("Enseignant: ");
		super.afficher();
		System.out.println(" Laboratoire: " + NomLabo);
		System.out.println(" Salaire: " + salaire);
		System.out.println(" Section: " + nomSection);
	}
	
}
class Etudiant extends Personne {
	protected String NomSection;
	public Etudiant(String nom, int anneeArriver, String nomSection) {
		super(nom, anneeArriver);
		this.NomSection = nomSection;
	}
	
	public String getSection() {
		return NomSection;
	}
}

class EtudiantE extends Etudiant {
	protected String nomUniversite;
	public EtudiantE(String nom, int anneeArriver, String nomSection, String nomUniversite) {
		super(nom, anneeArriver, nomSection);
		this.nomUniversite = nomUniversite;
		System.out.println("Creation de l'Etudiant d'echange: " + nom);
	}
	
	public void afficher() {
		System.out.println("Etudiant Echange: ");
		super.afficher();
		System.out.println(" section: " + getSection());
		System.out.println(" uni origin: " + nomUniversite);
	
	}
}

class EtudiantR extends Etudiant {
	protected double noteMoyen;
	public EtudiantR(String nom, int anneeArriver, String nomSection, double noteMoyen) {
		super(nom, anneeArriver, nomSection);
		this.noteMoyen = noteMoyen;
		System.out.println("Creation de l'Etudiant regulier: " + nom);
	}
	
	public void afficher() {
		System.out.println("Etudiant Regulier: ");
		super.afficher();
		System.out.println(" Section: " + getSection());
		System.out.println(" Moyen : " + noteMoyen);
	
	}
}


public class Direction {
	public static void main(String[] args) {
		Ecole ecole = new Ecole(15);
		Personne etudiantR1 = new EtudiantR("Gaston Peutimide", 2023, "systèmes de communication", 6.0);
		ecole.addPerson(etudiantR1);
		Personne etudiantR2 = new EtudiantR("Yvan Rattrapeur", 2011, "systèmes de communication", 2.5);
		ecole.addPerson(etudiantR2);
		Personne etudiantE1 = new EtudiantE("Björn Borgue",2012, "informatique","KTH");
		ecole.addPerson(etudiantE1);
		Personne enseignant1 = new Enseignant("Mathieu Matheu",1998, "Mathématiques Extrêmement Pures (LMEP)", 10000, "section de physique");
		ecole.addPerson(enseignant1);
		Personne secretaire = new Secretaire("Sophie Scribona", 2005, "Machines à Taper (LMT)", 5000);
		ecole.addPerson(secretaire);

		System.out.println();
		System.out.println();
		System.out.println();
		ecole.affichage();
		for (Personne ecoles: ecole.getPersonnes()) {
			ecoles.afficher();
		}
	}

}
