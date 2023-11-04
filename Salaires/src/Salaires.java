import java.util.ArrayList;
interface Risque{
	default double prime() {
		return 200;
	}
}
abstract class Employer {
	public String nom;
	public String prenom;
	public int age;
	public String date;
	public Employer(String nom, String prenom, int age, String date) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.date = date;
	}
	abstract public double calculerSalaire();
	public String getNom() {return "Vendeur " +prenom+ " "+ nom;}
	
}

class Vendeur extends Employer{
	public int chiffreAffaire;
	public Vendeur(String nom, String prenom, int age, String date, int chiffreAffaire) {
		super(nom, prenom, age, date);
		this.chiffreAffaire = chiffreAffaire;
	}
	public double calculerSalaire() {
		return ((20.0/100.0) * chiffreAffaire) + 400;
	}
	public String getNom() {return "Vendeur " +prenom+ " "+ nom;}
}
class Technicien extends Employer{
	public int unite;
	public Technicien(String nom, String prenom, int age, String date, int unite) {
		super(nom, prenom, age, date);
		this.unite = unite;
	}
	public double calculerSalaire() {
		return unite * 5;
	}
	public String getNom() {return "Technicien " +prenom+ " "+ nom;}
}
class Representant extends Employer{
	public int chiffreAffaire;
	public Representant(String nom, String prenom, int age, String date, int chiffreAffaire) {
		super(nom, prenom, age, date);
		this.chiffreAffaire = chiffreAffaire;
	}
	public double calculerSalaire() {
		return (20.0/100.0) * chiffreAffaire + 800;
	}
	public String getNom() {return "Representant " +prenom+ " "+ nom;}
}
class Manutentionnaire extends Employer{
	public int heures;
	public Manutentionnaire(String nom, String prenom, int age, String date, int heures) {
		super(nom, prenom, age, date);
		this.heures = heures;
	}
	public double calculerSalaire() {
		return heures * 65;
	}
	public String getNom() {return "Manutentionnaire " +prenom+ " "+ nom;}
}
class TechnARisque extends Employer implements Risque {
	public int unite;
	public TechnARisque(String nom, String prenom, int age, String date, int unite) {
		super(nom, prenom, age, date);
		this.unite = unite;
	}
	public double calculerSalaire() {
		return unite * 5 + prime();
	}
	public String getNom() {return "Techn " +prenom+ " "+ nom;}
}
class ManutARisque extends Employer implements Risque{
	public int heures;
	public ManutARisque(String nom, String prenom, int age, String date, int heures) {
		super(nom, prenom, age, date);
		this.heures = heures;
	}
	public double calculerSalaire() {
		return heures * 65 + prime();
	}
	public String getNom() {return "Manu " +prenom+ " "+ nom;}
}

class Personnel {
	private ArrayList<Employer> personnels = new ArrayList<Employer>();
	public void ajouterEmploye(Employer employer) {
		if (employer != null) 
			personnels.add(employer);
	}
	public void afficherSalaires() {
		for (Employer employer : personnels) {
			System.out.println("Le "+ employer.getNom() + " gagne "+ employer.calculerSalaire() + " francs");
		}
	}
	public double salaireMoyen() {
		double count = 0;
		for (Employer employer : personnels) {
			count += employer.calculerSalaire();
		}
		return count / personnels.size();
	}
}
class Salaires {
		public static void main(String[] args) {
			Personnel p = new Personnel();
			p.ajouterEmploye(new Vendeur("Pierre", "Business", 45, "1995", 30000));
			p.ajouterEmploye(new Representant("Léon", "Vendtout", 25, "2001", 20000));
			p.ajouterEmploye(new Technicien("Yves", "Bosseur", 28, "1998", 1000));
			p.ajouterEmploye(new Manutentionnaire("Jeanne", "Stocketout", 32, "1998", 45));
			p.ajouterEmploye(new TechnARisque("Jean", "Flippe", 28, "2000", 1000));
			p.ajouterEmploye(new ManutARisque("Al", "Abordage", 30, "2001", 45));
			p.afficherSalaires();
			System.out.println("Le salaire moyen dans l'entreprise est de " + p.salaireMoyen() + " francs.");
	}
}
