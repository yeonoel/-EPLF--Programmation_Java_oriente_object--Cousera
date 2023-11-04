import java.util.ArrayList;
import java.util.Random;

class Postulant{
    private String nom;
    private int nbElecteurs;

    public Postulant(String unNom, int unNbElecteurs){
        nom = unNom;
        nbElecteurs = unNbElecteurs;
    }
    public Postulant(String unNom){
        this(unNom, 0);
    }
    // Constructeur de copie
    public Postulant(Postulant autrePostulant){
        this.nom = autrePostulant.nom;
        this.nbElecteurs = autrePostulant.nbElecteurs;
    }

    public String getNom(){
        return nom;
    }
    public int getVotes(){
        return nbElecteurs;
    }

    public void elect(){
        nbElecteurs++;
    }
    public void init(){
        nbElecteurs = 0;
    }
    public String toString(){
        return getNom();
    }
}

abstract class Vote{
    private String nomPostulant;
    private int dateEffective;
    private int dateLimite;

    abstract boolean estInvalide();

    public Vote(String unNom, int uneDateEffect, int uneDateLim){
        nomPostulant = unNom;
        dateEffective = uneDateEffect;
        dateLimite = uneDateLim;
    }

    public int getDate(){
        return dateEffective;
    }
    public int getDateLimite(){
        return dateLimite;
    }
    public String getPostulant(){
        return nomPostulant;
    }

    public String toString(){
        String res = "pour " + getPostulant() + " -> ";
        if (!(estInvalide())){
            return res + "valide";
        } else {
            return res + "invalide";
        }
    }
}

class BulletinPapier extends Vote{
    private boolean estSigne;

    public BulletinPapier(String unNom, int uneDateEffect, int uneDateLim, boolean signe){
        super(unNom, uneDateEffect, uneDateLim);
        estSigne = signe;
    }

    public boolean getSigne(){
        return estSigne;
    }
    @Override
    boolean estInvalide() {
        return !getSigne();
    }

    @Override
    public String toString(){
        return "vote par bulletin papier " + super.toString();
    }
}
interface CheckBulletin{
    public boolean checkDate();
}
class BulletinCourrier extends BulletinPapier implements CheckBulletin {

    public BulletinCourrier(String unNom, int uneDateEffect, int uneDateLim, boolean signe){
        super(unNom, uneDateEffect, uneDateLim, signe);
    }
    @Override
    public boolean checkDate(){
        return !(super.getDate() > super.getDateLimite());
    }
    @Override
    public boolean estInvalide() {
        return super.estInvalide() || !checkDate();
    }
    @Override
    public String toString(){
        return "envoi par courrier d'un " + super.toString();
    }
}
class BulletinElectronique extends Vote implements CheckBulletin{

    public BulletinElectronique(String unNom, int uneDateEffect, int uneDateLim){
        super(unNom, uneDateEffect, uneDateLim);
    }
    @Override
    public boolean checkDate(){
        return !(super.getDate() > super.getDateLimite() - 2);
    }
    @Override
    boolean estInvalide() {
        return !(checkDate());
    }
    @Override
    public String toString(){
        return "vote electronique " + super.toString();
    }
    
}

class Scrutin{
    private ArrayList<Postulant> postulants;
    private ArrayList<Vote> votes;
    private int maxVotants;
    private int dateScrutin;

    public Scrutin(ArrayList<Postulant> listPostulants, int unMax, int uneDate, boolean initNbVotants){
        postulants = new ArrayList<Postulant>();
        for (Postulant pAutre : listPostulants){
            if (initNbVotants){
                Postulant p = new Postulant(pAutre);
                p.init();
                postulants.add(p);
            } else {
                postulants.add(new Postulant(pAutre));
            }
            
        }
        maxVotants = unMax;
        dateScrutin = uneDate;
        votes = new ArrayList<Vote>();
    }
    public Scrutin(ArrayList<Postulant> listPostulants, int unMax, int uneDate){
        this(listPostulants, unMax, uneDate, true);
    }
    public Scrutin(int unMax, int uneDate){
        this(new ArrayList<Postulant>(), unMax, uneDate, true);
    }

    public int getMaxVotants(){
        return maxVotants;
    }
    public int getDateScrutin(){
        return dateScrutin;
    }

    public double tauxParticipation(){
        return ((double)calculerVotants() / (double)getMaxVotants()) * 100;
    }
    public int calculerVotants(){
        int res = 0;
        for (Postulant p : postulants){
            res += p.getVotes();
        }
        return res;
    }
    public void compterVotes(){
        for (Postulant p : postulants){
            p.init();
        }
        for (Vote v : votes){
            if (!(v.estInvalide())){
                String nom = v.getPostulant();
                for (Postulant p : postulants){
                    if (p.getNom().equals(nom)){
                        p.elect();
                    }
                }
            }
        }
    }
    public String gagnant(){

        int nbVotantsGagnant = 0;
        String nomGagnant = "";
        for (Postulant p : postulants){
            if (p.getVotes() >= nbVotantsGagnant){
                nbVotantsGagnant = p.getVotes();
                nomGagnant = p.getNom();
            }
        }
        return nomGagnant;
    }
    public void resultats(){
        if (calculerVotants() == 0){
            System.out.println("Scrutin annulé, pas de votants");
        } else {
            System.out.println("Taux de participation -> " + String.format("%.1f", tauxParticipation()) + " pour cent");
            System.out.println("Nombre effectif de votants -> " + calculerVotants());
            System.out.println("Le chef choisi est -> " + gagnant() + "\n");
            System.out.println("Répartition des électeurs");
            for (Postulant p : postulants){
                System.out.println(p + " -> " + String.format("%.1f", (double)p.getVotes()/calculerVotants()*100) + " pour cent des électeurs");
            }
            System.out.println();
        }
    }
    public void simuler(double tauxParticipation, int jourVote){
        int nbVotants = (int)Math.ceil(getMaxVotants() * tauxParticipation);
        for (int i=0; i<nbVotants; i++){
            int candNum = Utils.randomInt(postulants.size());
            if (i % 3 == 0){
                BulletinElectronique b = new BulletinElectronique(postulants.get(candNum).getNom(), jourVote, this.getDateScrutin());
                votes.add(b);
                System.out.println(b);
            } else {
                if (i % 3 == 1){
                    if (i % 2 == 0){
                        BulletinPapier b = new BulletinPapier(postulants.get(candNum).getNom(), jourVote, this.getDateScrutin(), true);
                        votes.add(b);
                        System.out.println(b);
                    } else {
                        BulletinPapier b = new BulletinPapier(postulants.get(candNum).getNom(), jourVote, this.getDateScrutin(), false);
                        votes.add(b);
                        System.out.println(b);
                    }
                } else {
                    if (i % 3 == 2){
                        if (i % 2 == 0){
                            BulletinCourrier b = new BulletinCourrier(postulants.get(candNum).getNom(), jourVote, this.getDateScrutin(), true);
                            votes.add(b);
                            System.out.println(b);
                        } else {
                            BulletinCourrier b = new BulletinCourrier(postulants.get(candNum).getNom(), jourVote, this.getDateScrutin(), false);
                            votes.add(b);
                            System.out.println(b);
                        }
                    }
                }
            }
        }
    }
}

/*******************************************
 * Ne pas modifier les parties fournies
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/

class Utils {

    private static final Random RANDOM = new Random();

    // NE PAS UTILISER CETTE METHODE DANS LES PARTIES A COMPLETER
    public static void setSeed(long seed) {
        RANDOM.setSeed(seed);
    }

    // génère un entier entre 0 et max (max non compris)
    public static int randomInt(int max) {
        return RANDOM.nextInt(max);
    }
}

/**
 * Classe pour tester la simulation
 */

class Votation {

    public static void main(String args[]) {
        Utils.setSeed(20000);
        // TEST 1
        System.out.println("Test partie I:");
        System.out.println("--------------");

        ArrayList<Postulant> postulants = new ArrayList<Postulant>();
        postulants.add(new Postulant("Tarek Oxlama", 2));
        postulants.add(new Postulant("Nicolai Tarcozi", 3));
        postulants.add(new Postulant("Vlad Imirboutine", 2));
        postulants.add(new Postulant("Angel Anerckjel", 4));

        // 30 -> nombre maximal de votants
        // 15 jour du scrutin
        Scrutin scrutin = new Scrutin(postulants, 30, 15, false);

        scrutin.resultats();

        // FIN TEST 1

        // TEST 2
        System.out.println("Test partie II:");
        System.out.println("---------------");

        scrutin = new Scrutin(postulants, 20, 15);
        // tous les bulletins passent le check de la date
        // les parametres de simuler sont dans l'ordre:
        // le pourcentage de votants et le jour du vote
        scrutin.simuler(0.75, 12);
        scrutin.compterVotes();
        scrutin.resultats();

        scrutin = new Scrutin(postulants, 20, 15);
        // seuls les bulletins papier non courrier passent
        scrutin.simuler(0.75, 15);
        scrutin.compterVotes();
        scrutin.resultats();

        scrutin = new Scrutin(postulants, 20, 15);
        // les bulletins electroniques ne passent pas
        scrutin.simuler(0.75, 15);
        scrutin.compterVotes();
        scrutin.resultats();
        //FIN TEST 2

    }
}