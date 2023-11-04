

class Client{
	private String name;
	private String ville;
	private Boolean masculin;
	private double soldesEpargne;
	private double soldesPriver;
	
	public Client(String nam, String vile, boolean sex, double soldePriver, double soldeEpargne) {
		name = nam;
		ville = vile;
		masculin = sex;
		soldesEpargne = soldeEpargne;
		soldesPriver = soldePriver;
	}
	
	public String getName() {
		return name;
	}
	public String getVille() {
		return ville;
	}
	public double getSoldeEpargne() {
		return soldesEpargne;
	}
	public double getsoldePriver() {
		return soldesPriver;
	}
	public void setsoldesEpargne(double soldeEparg) {
		soldesEpargne = soldeEparg;
	}
	public void setsoldesPiver(double soldePriver) {
		soldesPriver = soldePriver;
	}
	
	public void afficherClient() {
		if (masculin) {
			System.out.println("Client " + name + " de " + ville);
		} else {
			System.out.println("Cliente " + name + " de " + ville);
		}
		System.out.println(" Compte prive: " + soldesPriver + " francs");
		System.out.println(" Compte d'epargne: " + soldesEpargne + " francs");
	}
	
}

class Banqueclient{
	private String name;
	private String ville;
	private double tauxCompteEpargne = 0.02;
	private double tauxComptePriver = 0.01;
	private double soldesEpargne;
	private double soldesPriver;
	//private Boolean masculin;
	
	public Banqueclient(String nam, String vile, double soldePriver, double soldeEpargne) {
		name = nam;
		ville = vile;
		soldesEpargne = soldeEpargne;
		soldesPriver = soldePriver;
	}
	
	public void bouclerCompte() {
		bouclerCompteEpargne();
		bouclerComptePiver();
	}
	public double getSoldeEpargne() {
		return soldesEpargne;
	}
	public double getsoldePriver() {
		return soldesPriver;
	}
	
	private void bouclerCompteEpargne() {
		double interet = soldesEpargne * tauxCompteEpargne;
		soldesEpargne =  interet + soldesEpargne;
	}
	
	private void bouclerComptePiver() {
		double interet = soldesPriver * tauxComptePriver;
		soldesPriver =  interet + soldesPriver;
	}
	
}


public class Banque {
	public static void main(String[] args) {
		Client client1 = new Client("Ligth", "Lagos", true, 1000.0, 2000.0);
		Client client2 = new Client("Sara", "Paris", false, 3000.0, 4000.0);
		
		Banqueclient bc1 = new Banqueclient(client1.getName(), client1.getVille(), client1.getsoldePriver(), client1.getSoldeEpargne());
		Banqueclient bc2 = new Banqueclient(client2.getName(), client2.getVille(),  client2.getsoldePriver(), client2.getSoldeEpargne());
		
		//Donnees avant le bouclement des comptes:
		System.out.println("Donnees avant le bouclement des comptes:");
		client1.afficherClient();
		client2.afficherClient();
		
		// A present bouclage du compte
		bc1.bouclerCompte();
		bc2.bouclerCompte();
		//tranfert nouveau solde chez le client
		client1.setsoldesEpargne(bc1.getSoldeEpargne());
		client1.setsoldesPiver(bc1.getsoldePriver());
		client2.setsoldesEpargne(bc2.getSoldeEpargne());
		client2.setsoldesPiver(bc2.getsoldePriver());
		
		//Donnees apres le bouclement des comptes:
		System.out.println("Donnees apres le bouclement des comptes:");
		client1.afficherClient();
		client2.afficherClient();
	}

}
