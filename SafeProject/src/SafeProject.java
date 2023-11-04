import java.util.Scanner;
import java.util.ArrayList;


class SafeProject {
	
	private final static int NB_PROJECTS = 3;
	
	public static void main(String[] args) {
		ArrayList<Project> projects = new ArrayList<Project>();
		
		do {
			Project project = new Project();
			try {
				project.readProject();
			}catch(WrongDurationException e) {
				System.out.println("Duree incorrecte kk");
			}
			catch(NameTooLongException e) {
				System.out.println("longueure trop longue");
			}
			projects.add(project);
		} while (projects.size() < NB_PROJECTS);
	}

}

class Project {
	Scanner scanner = new Scanner(System.in);
	private String name = null;
	private String subject = null;
	private int duration = -1;

	public Project() {}
	
	public String readString() throws NameTooLongException {
		String str = scanner.nextLine();
		int nameLeng = str.length();
		if (nameLeng > 50 ) {
			throw new NameTooLongException("Le nom du project est trop long");
		}
		return str;
	}
	
	public int readInt() throws WrongDurationException {
		String str = scanner.nextLine();
		int strNumber;
		try {
			strNumber = Integer.parseInt(str);
		}catch(NumberFormatException e) {
			throw new WrongDurationException("La duree doit etre un entier");
		}
		if (strNumber<= 0) {
			throw new WrongDurationException("La duree doit etre positive");
		}
		return strNumber;
	}
	
	public void readProject() throws NameTooLongException, WrongDurationException {
		
		
		do {

			System.out.println("Donnez le nom du projet : ");
			try {
				name = readString();
			}catch (NameTooLongException e) {
				System.out.println(e.getMessage());
			}
		}while(name == null);
		
		
		do {
			System.out.println("Donnez le sujet du projet : ");
			
			try {
				subject = readString();
			}catch(NameTooLongException e) {
				System.out.println(e.getMessage());
			}
		}while(subject == null);

		do {
			System.out.println("Donnez la durée du projet : ");
				
				try {
					duration = readInt();
				}catch(WrongDurationException e) {
					System.out.println(e.getMessage());
				}
		}while (duration < 0);
		
	}

}

class WrongDurationException extends Exception {
	public WrongDurationException() {
		super("La durée n'est pas correcte");
	}

	public WrongDurationException(String message) {
		super(message);
	}
}

class NameTooLongException extends Exception {
	public NameTooLongException() {
		super("Le nom du projet est trop long");
	}

	public NameTooLongException(String message) {
		super(message);
	}
}

