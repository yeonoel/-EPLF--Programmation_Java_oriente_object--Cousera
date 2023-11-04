import java.util.Random;
import java.util.ArrayList;

abstract class Code{
	protected String nom;
	public Code(String nom) {
		this.nom = nom;
	}
	public abstract String code(String s);
	public abstract String decode(String s);
	public void affiche() {
		System.out.println(nom);
	}
	
}

class ACle extends Code{
	private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String cle;
	public ACle(String nom, String cle) {
		super(nom);
		this.cle = cle;
	}
	public  String code(String s) {
		return AdditionArray(ConvertirEnChiffre(s), ConvertirEnChiffre(cle));
	}
	public  String decode(String s) {
		ArrayList<Integer> sCodeChiffre = ConvertirEnChiffre(s);
		ArrayList<Integer> cleCodeChiffre = ConvertirEnChiffre(cle);
	
	}
	public  int longeur() {
		return cle.length();
	}
	public void affiche() {
		System.out.println(nom + " avec "+ cle + " comme cle");
	}
	public  ArrayList <Integer> ConvertirEnChiffre(String s) {
		ArrayList <Integer> chiffre = new ArrayList <Integer>();
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			int cAlphaIndex = ALPHABET.indexOf(c);
			chiffre.add(cAlphaIndex +1);
		}
		return chiffre;
	}
	public  String AdditionArray(ArrayList <Integer> tab1, ArrayList <Integer>tab2) {
		ArrayList <Integer> chiffre = new ArrayList <Integer>();
		String s = "";
		for (int i = 0; i < tab1.size(); ++i) {
			int addition = tab1.get(i) + tab2.get(i);
			if (addition > 26)
				addition = addition - 27;
			chiffre.add(addition);
		}
		for (int i = 0; i < chiffre.size(); ++i) {
			int c = chiffre.get(i - 1);
			s += c;
		}
		return s;
	}
	
}
class Utils {
	// genere un entier entre 1 et max (compris)
	public static int randomInt(int max) {
		Random r = new Random();
		int val = r.nextInt();
		val = Math.abs(val);
		val = val % max;
		val += 1;
		return val;
	}
}

class Secret {
	
	public static void main(String[]  args){
		String message = "COURAGEFUYONS";
		String cryptage;

		// PARTIES A DECOMMENTER AU FUR ET A MESURE SELON l'ENONCE
		
		// TEST A CLE
		Code acle1 = new ACle("a cle", "EQUINOXE");
		System.out.print("Avec le code : " );
		acle1.affiche();
		cryptage  = acle1.code(message);
		System.out.print("Codage de " + message + " : ");
		System.out.println(cryptage);
		System.out.print("Decodage de " + cryptage + " : ");
		System.out.println(acle1.decode(cryptage));
		System.out.println("-----------------------------------");
		System.out.println();
		// FIN TEST A CLE
        
		/*
		// TEST A CLE ALEATOIRE
		Code acle2 = new ACleAleatoire(5);
		System.out.print("Avec le code : " );
		acle2.affiche();
		cryptage  = acle2.code(message);
		System.out.print("Codage de " + message + " : ");
		System.out.println(cryptage);
		System.out.print("Decodage de " + cryptage + " : ");
		System.out.println(acle2.decode(cryptage));
		System.out.println("-----------------------------------");
		System.out.println();
		// FIN TEST A CLE ALEATOIRE 
		*/
		/*
		// TEST CESAR
		Code cesar1 = new Cesar("Cesar", 5);
		System.out.print("Avec le code : " );
		cesar1.affiche();
		cryptage = cesar1.code(message);
		System.out.print("Codage de " + message + " : ");
		System.out.println(cryptage);
		System.out.print("Decodage de " + cryptage + " : ");
		System.out.println(cesar1.decode(cryptage));
		System.out.println("-----------------------------------");
		System.out.println();
		// FIN TEST CESAR
		*/
		/*
		// TEST CODAGES
		System.out.println("Test CODAGES: ");
		System.out.println("------------- ");
		System.out.println();


		Code[] tab = {   // Decommentez la ligne suivante
				// si vous avez fait la classe Cesar
				new Cesar("cesar", 5),
				new ACle("a cle", "EQUINOXE") ,
				new ACleAleatoire(5),
				new ACleAleatoire(10)};
		
		Codages  codes = new Codages(tab);
		codes.test(message);
		// FIN TEST CODAGE
		*/
	}
}



