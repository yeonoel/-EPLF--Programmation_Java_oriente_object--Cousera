import java.util.ArrayList;

class Timbre {

    public static final int ANNEE_COURANTE = 2016;
    public static final int VALEUR_TIMBRE_DEFAUT = 1;
    public static final String PAYS_DEFAUT = "Suisse";
    public static final String CODE_DEFAUT = "lambda";

    public static final int BASE_1_EXEMPLAIRES = 100;
    public static final int BASE_2_EXEMPLAIRES = 1000;
    public static final double PRIX_BASE_1 = 600;
    public static final double PRIX_BASE_2 = 400;
    public static final double PRIX_BASE_3 = 50;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
    protected String code;
    protected int annee;
    protected String paysOrigine;
    protected double valeurFiscale;
    public Timbre(String code, int annee, String paysOrigine, double valeurFiscale) {
    	this.code = code;
    	this.annee = annee;
    	this.paysOrigine = paysOrigine;
    	this.valeurFiscale = valeurFiscale;
    }
    public Timbre(String code, int annee, String paysOrigine) {
    	this.code = code;
    	this.annee = annee;
    	this.paysOrigine = paysOrigine;
    	this.valeurFiscale = VALEUR_TIMBRE_DEFAUT;
    }
    public Timbre(String code, int annee) {
    	this.code = code;
    	this.annee = annee;
    	this.paysOrigine = PAYS_DEFAUT;
    	this.valeurFiscale = VALEUR_TIMBRE_DEFAUT;
    }
    public Timbre(String code) {
    	this.code = code;
    	this.annee = ANNEE_COURANTE;
    	this.paysOrigine = PAYS_DEFAUT;
    	this.valeurFiscale = VALEUR_TIMBRE_DEFAUT;
    }
    public Timbre() {
    	this.code = CODE_DEFAUT;
    	this.annee = ANNEE_COURANTE;
    	this.paysOrigine = PAYS_DEFAUT;
    	this.valeurFiscale = VALEUR_TIMBRE_DEFAUT;
    }
    
    protected double vente() {
    	
    	 if ( age() < 5) {
    		 return valeurFiscale;
    	 }
    	 return valeurFiscale * age() * 2.5;
    }
    
    public String toString() {
    	String s = "Timbre de code "+ code +" datant de " +annee+ " (provenance " +paysOrigine + ") ";
    	s += "ayant pour valeur faciale " +valeurFiscale+ " francs";
    	return s;
    }
    
    protected int age() {
    	return ANNEE_COURANTE - annee;
    }
    protected String getCode() {return code;}
    protected int getAnnee() {return annee;}
    protected String getPays() {return paysOrigine;}
    protected double getValeurFaciale() {return valeurFiscale;}

}

class Rare extends Timbre{
	private int nombreExemplaire;
	public Rare(String code, int annee, String paysOrigine, double valeurFiscale, int nombreExemplaire) {
    	super(code, annee, paysOrigine, valeurFiscale);
    	this.nombreExemplaire = nombreExemplaire;
    }
    public Rare(String code, int annee, String paysOrigine, int nombreExemplaire) {
    	super(code, annee, paysOrigine);
    	this.nombreExemplaire = nombreExemplaire;
    }
    public Rare(String code, int annee, int nombreExemplaire) {
    	super(code, annee);
    	this.nombreExemplaire = nombreExemplaire;
    }
    public Rare(String code, int nombreExemplaire) {
    	super(code);
    	this.nombreExemplaire = nombreExemplaire;
    }
    public Rare(int nombreExemplaire) {
    	super();
    	this.nombreExemplaire = nombreExemplaire;
    }
    public Rare() {
    	super();
    }
    
    public int getExemplaires() {return nombreExemplaire;}
    public String toString() {
    	String s = "Timbre de code "+ code +" datant de  " +annee+ " (provenance " +paysOrigine + ") ";
    	s += "ayant pour valeur faciale " +valeurFiscale+ " francs\n";
    	s += "Nombre d'exemplaires -> "+ nombreExemplaire;
    	return s;
    }
    
    public double vente() {
    	double prix = age()/ 10.0;
   	 if ( nombreExemplaire < 100) {
   		 prix *= 600;
   	 } else if(nombreExemplaire >= 100 && nombreExemplaire <= 1000 ) {
   		prix *= 400;
   	 } else {
   		 prix *= 50;
   	 }
   	 return prix;
   }
   

}

class Commemoratif extends Timbre{
	public Commemoratif(String code, int annee, String paysOrigine, double valeurFiscale) {
    	super(code, annee, paysOrigine, valeurFiscale);
    }
    public Commemoratif(String code, int annee, String paysOrigine) {
    	super(code, annee, paysOrigine);
    }
    public Commemoratif(String code, int annee) {
    	super(code, annee);
    }
    public Commemoratif(String code) {
    	super(code);
    }
    public Commemoratif() {
    	super();
    }
    
    public String toString() {
    	String s = "Timbre de code "+ code +" datant de " +annee+ " (provenance " +paysOrigine + ") ";
    	s += "ayant pour valeur faciale " +valeurFiscale+ " francs \n";
    	s += "Timbre celebrant un evenement";
    	return s;
    }
    
    public double vente() {
	    	
	   	 if ( age() < 5) {
	   		 return valeurFiscale * 2;
	   	 }
	   	 return 2 * valeurFiscale * age() * 2.5;
   }
    
}
/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

class Philatelie {

    public static void main(String[] args) {

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale,
        // nombre d'exemplaires
        Rare t1 = new Rare("Guarana-4574", 1960, "Mexique", 0.2, 98);

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale
        Commemoratif t2 = new Commemoratif("700eme-501", 2002, "Suisse", 1.5);
        Timbre t3 = new Timbre("Setchuan-302", 2004, "Chine", 0.2);

        Rare t4 = new Rare("Yoddle-201", 1916, "Suisse", 0.8, 3);


        ArrayList<Timbre> collection = new ArrayList<Timbre>();

        collection.add(t1);
        collection.add(t2);
        collection.add(t3);
        collection.add(t4);

        for (Timbre timbre : collection) {
            System.out.println(timbre);
            System.out.println("Prix vente : " + timbre.vente() + " francs");
            System.out.println();
        }
    }
}

