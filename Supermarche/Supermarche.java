
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

class Article {
	private String name;
	private double prix;
	private boolean sold;
	
	public Article(String name, double prix, boolean sold) {
		this.name = name;
		this.prix = prix;
		this.sold = sold;
	}
	
	public String getName() {
		return name;
	}
	public double getPrix() {
		return prix;
	}
	public boolean getSold() {
		return sold;
	}
}

class Caddie {
	ArrayList<Article> caddieItems = new ArrayList<Article>();
	ArrayList<Integer> quantitys = new ArrayList<Integer>();
	
	public void remplir(Article article, int quantity) {
		caddieItems.add(article);
		quantitys.add(quantity);
	}
	
	public ArrayList<Integer> getQuanty() {
		return quantitys;
	}
	
	public ArrayList<Article> getcaddieItems() {
		return caddieItems;
	}
	
	public void afficher() {
		
		for (int i = 0; i < caddieItems.size(); ++i) {
			Article article = caddieItems.get(i);
			int quantity = quantitys.get(i);
			double somme = article.getPrix() * quantity;
			if (article.getSold()) {
				System.out.print(article.getName() + " : " + article.getPrix() + " x " + quantity + " = " + (somme / 2));
				System.out.println(" (1/2 prix)");
			} else {
				System.out.println(article.getName() + " : " + article.getPrix() + " x " + quantity + " = " + somme);
			}
			
		}
	}
}



class Caisse {
	private int numero;
	private double montantTotal;
	
	public Caisse(int numero, double montantInitiale) {
		this.numero = numero;
		this.montantTotal = montantInitiale;
	}
	
	public int getNumero() {
		return numero;
	}
	public double getmontantTotal() {
		return montantTotal;
	}
	
	public void totalCaisse() {
		System.out.println("La caisse "+ numero + " a encaisse " + montantTotal + " Frs aujourd'hui.");
	}
	
	public void scanner(Caddie caddie) {
		double somme = 0;
		double x = 0;
		afficheDateCaisse();
		caddie.afficher();
		for (int i = 0; i < caddie.getcaddieItems().size(); ++i) {
			Article article = caddie.getcaddieItems().get(i);
			int quantity = caddie.getQuanty().get(i);
			x = article.getPrix() * quantity;
			if (article.getSold()) {
				x /= 2;
			}
			somme += x;
			
		}
		montantTotal += somme;
		System.out.println();
		System.out.println("Montant à payer : " + somme);
		System.out.println("=========================================");
		System.out.println("=========================================");
	}
	
	private void afficheDateCaisse() {
		Date dateCourante = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yy");
		System.out.println(formatDate.format(dateCourante));
		System.out.println("Caisse numero "+ numero);
		System.out.println();
	}
}


public class Supermarche {

	public static void main(String[] args) {
		// Les articles vendus dans le supermarché
		Article choufleur = new Article("Chou-fleur extra", 3.50, false);
		Article roman = new Article("Les malheurs de Sophie", 16.50, true);
		Article camembert = new Article("Cremeux 100%MG", 5.80, false);
		Article cdrom = new Article("C++ en trois jours", 48.50, false);
		Article boisson = new Article("Petit-lait", 2.50, true);
		Article petitspois = new Article("Pois surgeles", 4.35, false);
		Article poisson = new Article("Sardines", 6.50, false);
		Article biscuits = new Article("Cookies de grand-mere", 3.20, false);
		Article poires = new Article("Poires Williams", 4.80, false);
		Article cafe = new Article("100% Arabica", 6.90, true);
		Article pain = new Article("Pain d'epautre", 6.90, false);

		// Les caddies du supermarché
		Caddie caddie1 = new Caddie();
		Caddie caddie2 = new Caddie();
		Caddie caddie3 = new Caddie();

		// Les caisses du supermarché
		// le premier argument est le numero de la caisse
		// le second argument est le montant initial de la caisse.
		Caisse caisse1 = new Caisse(1, 0.0);
		Caisse caisse2 = new Caisse(2, 0.0);

		// les clients font leurs achats
		// le second argument de la méthode remplir
		// correspond à une quantité

		// remplissage du 1er caddie
		caddie1.remplir(choufleur, 2);
		caddie1.remplir(cdrom, 1);
		caddie1.remplir(biscuits, 4);
		caddie1.remplir(boisson, 6);
		caddie1.remplir(poisson, 2);

		// remplissage du 2eme caddie
		caddie2.remplir(roman, 1);
		caddie2.remplir(camembert, 1);
		caddie2.remplir(petitspois, 2);
		caddie2.remplir(poires, 2);

		// remplissage du 3eme caddie
		caddie3.remplir(cafe, 2);
		caddie3.remplir(pain, 1);
		caddie3.remplir(camembert, 2);

		// Les clients passent à la caisse
		caisse1.scanner(caddie1);
		caisse1.scanner(caddie2);
		caisse2.scanner(caddie3);
		
		caisse1.totalCaisse();
		caisse2.totalCaisse();
	}
}
