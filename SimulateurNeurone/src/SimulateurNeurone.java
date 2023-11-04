import java.util.ArrayList;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
class Position {
	private double x;
	private double y;
	public Position() {
		x = 0;
		y = 0;
	}
	public Position(double unX, double unY) {
		x = unX;
		y = unY;
	}
	public double getX() {return x; }
	public double getY() {return y; }
	public String toString() {
		return "(" + x +", "+ y + ")";
	}
}

class Neurone {
	private ArrayList<Neurone> connexions = new ArrayList<Neurone>();

	private Position position;
	protected double signal;
	protected double attenuation;
	public Neurone(Position unePosition, double unAttenuation) {
		position = unePosition;
		attenuation = unAttenuation;
		signal = 0;
	}
	public Position getPosition() {
		return position;
	}
	public ArrayList<Neurone> getConnexionsArray() {
		return connexions;
	}
	public int getNbConnexions() {
		return connexions.size();
	}
	public Neurone getConnexion( int index) {
		return connexions.get(index);
	}
	public double getAttenuation(){
		return attenuation;
	}
	public double getSignal() {
		return this.signal;
	}
	public void connexion(Neurone n) {
		connexions.add(n);
	}
	public void recoitStimulus(double stimulus) {
		this.signal = stimulus * attenuation;
		 for (Neurone connexion : connexions) {
		        connexion.recoitStimulus(this.signal);
		    }
	}
	
	public String toString() {
		String s = "";
		if (getNbConnexions() == 0) {
			s = "Le neurone en position " + position+" avec attenuation "+ attenuation + " sans connexion\n";
		}else {
			s = "Le neurone en position " + position+ " avec attenuation "+ attenuation + " en connexion avec\n";
			for (Neurone connexion: connexions) {
				s +="  - un neurone en position " + connexion.getPosition()+ "\n";
			}
		}
		
		return s;
	}
}

class NeuroneCumulatif  extends Neurone{
	public NeuroneCumulatif(Position unePosition, double unAttenuation) {
		super(unePosition, unAttenuation);
	}
	public void recoitStimulus(double stimulus) {
		this.signal = this.signal+ stimulus * attenuation;
		 for (Neurone connexion : getConnexionsArray()) {
		        connexion.recoitStimulus(this.signal);
		    }
	}
}

class Cerveau {
	private ArrayList<Neurone> neurones = new ArrayList<Neurone>();
	
	public int getNbNeurones() {
		return neurones.size();
	}
	public Neurone getNeurone(int index) {
		return neurones.get(index);
	}
	public void ajouterNeurone(Position pos, double attenuation) {
		neurones.add(new Neurone(pos, attenuation));
	}
	public void ajouterNeuroneCumulatif(Position pos, double attenuation) {
		neurones.add(new NeuroneCumulatif(pos, attenuation));
	}
	public void stimuler(int index, double stimulus) {
		getNeurone(index).recoitStimulus(stimulus);
	}
	public void creerConnexions() {
		for(int i = 0; i < getNbNeurones(); ++i) {
			if (i == 0 && getNbNeurones() > 1) {
		        neurones.get(0).connexion(neurones.get(1));
		    }
			if (i == 0 && getNbNeurones() > 2) {
		        neurones.get(0).connexion(neurones.get(2));
		    }
			
			if (i % 2 != 0) {
				if (i < getNbNeurones() - 2 ) {
					neurones.get(i).connexion(neurones.get(i + 1));
			        neurones.get(i + 1).connexion(neurones.get(i + 2));
				}
			}
		}
	}
	public double sonder(int index) {
		return getNeurone(index).getSignal();
	}
	public String toString() {
			String s = "\n*----------*\n\n";
			s += "Le cerveau contient "+getNbNeurones()+ " neurone(s)\n";
			for (Neurone neurone: neurones) {
				s += neurone.toString();
				s += "\n";
			}
			
			s += "*----------*";
			return s;
		
	}
}
/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
public class SimulateurNeurone {

    public static void main(String[] args) {
        // TEST DE LA PARTIE 1
        System.out.println("Test de la partie 1:");
        System.out.println("--------------------");

        Position position1 = new Position(0, 1);
        Position position2 = new Position(1, 0);
        Position position3 = new Position(1, 1);

        Neurone neuron1 = new Neurone(position1, 0.5);
        Neurone neuron2 = new Neurone(position2, 1.0);
        Neurone neuron3 = new Neurone(position3, 2.0);

        neuron1.connexion(neuron2);
        neuron2.connexion(neuron3);
        neuron1.recoitStimulus(10);

        System.out.println("Signaux : ");
        System.out.println(neuron1.getSignal());
        System.out.println(neuron2.getSignal());
        System.out.println(neuron3.getSignal());

        System.out.println();
        System.out.println("Premiere connexion du neurone 1");
        System.out.println(neuron1.getConnexion(0));


        // FIN TEST DE LA PARTIE 1

        // TEST DE LA PARTIE 2
        System.out.println("Test de la partie 2:");
        System.out.println("--------------------");

        Position position5 = new Position(0, 0);
        NeuroneCumulatif neuron5 = new NeuroneCumulatif(position5, 0.5);
        neuron5.recoitStimulus(10);
        neuron5.recoitStimulus(10);
        System.out.println("Signal du neurone cumulatif  -> " + neuron5.getSignal());

        // FIN TEST DE LA PARTIE 2

        // TEST DE LA PARTIE 3
        System.out.println();
        System.out.println("Test de la partie 3:");
        System.out.println("--------------------");
        Cerveau cerveau = new Cerveau();

        // parametres de construction du neurone:
        // la  position et le facteur d'attenuation
        cerveau.ajouterNeurone(new Position(0,0), 0.5);
        cerveau.ajouterNeurone(new Position(0,1), 0.2);
        cerveau.ajouterNeurone(new Position(1,0), 1.0);

        // parametres de construction du neurone cumulatif:
        // la  position et le facteur d'attenuation
        cerveau.ajouterNeuroneCumulatif(new Position(1,1), 0.8);
        cerveau.creerConnexions();
        cerveau.stimuler(0, 10);

        System.out.println("Signal du 3eme neurone -> " + cerveau.sonder(3));
        System.out.println(cerveau);
        // FIN TEST DE LA PARTIE 3
    }
}
