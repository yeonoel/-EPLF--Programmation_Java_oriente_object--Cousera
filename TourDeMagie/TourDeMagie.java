import java.util.Scanner;

/**
 * 
 */

/**
 * @author DELL
 *
 */
public class TourDeMagie {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Spectateur thorin = new Spectateur(); // Il était une fois un spectateur...
		thorin.arrive(); // ...qui venait voir un spectacle (!!)...
		Magicien gandalf = new Magicien(); // ...où un magicien...
		Assistant bilbo = new Assistant(); // ...et son assistant...
		gandalf.toureMagie(bilbo, thorin); 
	}

}

class Papier{
	private int age;
	private int somme;
	
	public void ecrire(int age, int somme) {
		this.age = age;
		this.somme = somme;
	}
	
	public int lireAge() {return age;}
	public int lireSomme() {return somme;}
}


class Assistant {
	private int age;
	private int somme;
	private int res;
	
	public void lirePapier(Papier build) {
		age = build.lireAge();
		somme = build.lireSomme();
	}
	
	public void calcule() {
		res = age * 2;
		res += 5;
		res *= 50;
		res += somme;
		res -= 365;
	}
	 public int annonce () {
		 System.out.println("[Assistant] J'annonce : " + res + " !" );
		 return res;
	 }
	
}

class Spectateur {
	private final Scanner clavier = new Scanner(System.in);
	
	private int age;
	private int somme;
	
	public void arrive() {;
		age = clavier.nextInt();
		
		do {
			System.out.print("Combien d'argent ai-je en poche ( 100< ) ?");
			somme = clavier.nextInt();
		} while(somme >= 100);
		System.out.println("[Spectateur] (j'ai un montant qui convient)");
	}
	
	public void ecrire(Papier build) {
		System.out.println("[Spectateur] (j'écris le papier)");
		build.ecrire(age, somme);
		
	}

}


class Magicien {
	// ce que le magicien doit deviner:
	private int ageDevine;
	private int argentDevine;
	
	
	public void toureMagie(Assistant fidele, Spectateur quidam) {
		Papier billet = new Papier();
		quidam.ecrire(billet);
		fidele.lirePapier(billet);
		fidele.calcule();
		calculer(fidele.annonce());
		annoncer();
		
	}
	
	private void calculer(int result) {
		result += 115;
		ageDevine = result / 100;
		argentDevine = result % 100;
	}
	
	private void annoncer() {
		System.out.println("[Magicien] "
		+ " - hum... je vois que vous êtes agé de "
		+ ageDevine + " ans ");
		System.out.println(" et que vous avez " + argentDevine + " francs suisses en poche !");
	}

}
