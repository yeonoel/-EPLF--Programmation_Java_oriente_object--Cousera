class Souris {

    public static final int ESPERANCE_VIE_DEFAUT = 36;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
    
    private int poids;
    private String couleur;
    private int age;
    private int esperanceVie;
    private boolean clonee;
    
    public Souris(){
    	this.age = 0;
    	this.esperanceVie = ESPERANCE_VIE_DEFAUT;
    	
    }
    public Souris(int poids, String couleur, int age) {
    	this.poids = poids;
    	this.couleur = couleur;
    	this.age = age;
    	this.esperanceVie = ESPERANCE_VIE_DEFAUT;
    	System.out.println("Une nouvelle souris !");
    	
    }
    
    public Souris(int poids, String couleur, int age, int esperanceVie) {
    	this.poids = poids;
    	this.couleur = couleur;
    	this.age = age;
    	this.esperanceVie = esperanceVie;
    	System.out.println("Une nouvelle souris !");
    	
    }
    
    public Souris(int poids, String couleur) {
    	this.poids = poids;
    	this.couleur = couleur;
    	this.age = 0;
    	this.esperanceVie = ESPERANCE_VIE_DEFAUT;
    	System.out.println("Une nouvelle souris !");
    	
    }
    public Souris(Souris souris) {
    	this.poids = souris.poids;
    	this.couleur = souris.couleur;
    	this.age = souris.age;
    	this.esperanceVie = souris.esperanceVie * 4 / 5;
    	this.clonee = true;
    	System.out.println("Clonage d'une souris !");
    }
    
    public String toString() {
    	if (clonee) {
    		return "Une souris " + couleur + ", clonee," + " de " + age +" mois et pesant "+ poids + " grammes";
    	}
    	return "Une souris " + couleur + " de " + age +" mois et pesant "+ poids + " grammes";
    }
    
    public void vieillir() {
    	age++;
    	if (clonee) {
    		if (age  > esperanceVie / 2) {
    			couleur = "verte";
    		}
    	}
    }
   
    public void evolue() {
    	while (age < esperanceVie) {
    			vieillir();
    	}
    }
    

}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

public class Labo {

    public static void main(String[] args) {
        Souris s1 = new Souris(50, "blanche", 2);
        Souris s2 = new Souris(45, "grise");
        Souris s3 = new Souris(s2);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        s1.evolue();
        s2.evolue();
        s3.evolue();
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}