

class Fleur {
	private String text1;
	private String text2;
	
	public Fleur(String string1, String string2) {
		text1 = string1;
		text2 = string2;
		System.out.println(text1 + " fraichement cueillie");
	}
	
	public Fleur(Fleur fleur) {
		text1 = fleur.text1;
		text2 = fleur.text2;
		System.out.print("Fragile corolle taillée ");
	}
	
	public void eclore() {
		System.out.println("veiné de " + text2);
	}
	
	public String toString() {
		return "qu'un simple souffle";
	}
}
public class Poeme {
	public static void main(String[] args)
	{
	Fleur f1 = new Fleur("Violette", "bleu");
	Fleur f2 = new Fleur(f1);
	System.out.print("dans un cristal ");
	f2.eclore();
	System.out.print("ne laissant plus ");
	System.out.println(f1);
	System.out.println(f2);
	}
}
