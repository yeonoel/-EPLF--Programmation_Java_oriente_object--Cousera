
public class TestCercle {
	
	public static void main(String[] args) {
		Cercle c1 = new Cercle();
		Cercle c2 = new Cercle();
		Cercle c3 = new Cercle();
		
		c1.setCenter(2.0, 5.0);
		c1.setRayon(1.0); 
		
		c2.setCenter(1.0, 2.0);
		c2.setRayon(5.0);
		
		c3.setCenter(-2.0, -5.0);
		c3.setRayon(2.25);
		
		System.out.println("Surface de c1 : " + c1.surface());
		System.out.println("Surface de c2 : " + c2.surface());
		System.out.println("Surface de c3 : " + c3.surface());
		
		afficherPosition("c1", c1, 0.0, 0.0);
		afficherPosition("c2", c2, 0.0, 0.0);
		afficherPosition("c3", c3, 0.0, 0.0);
		
	}
	
	static void afficherPosition(String nom, Cercle c, double x, double y)
	{
	System.out.print("Position du point (" + x + ", " + y + ") : ");
	if (c.estInterieur(x,y))
	{
	System.out.print("dans ");
	}
	else
	{
	System.out.print("hors de ");
	}
	System.out.println(nom);
	}

	
	 
}

class Cercle{
	private double x, y, r;
	
	public void setCenter(double x, double y) {
		this.x = x;
		this.y = y;
	};
	public void setRayon(double r ) {
		this.r = r;
	}
	
	public double surface() {
		return Math.PI * (this.r * this.r);
	}
	
	public boolean estInterieur(double x, double y) {
		double distance = Math.pow((x - this.x), 2) + Math.pow((y - this.y), 2);
		if (distance <= this.r * this.r) {
			return true;
		}
		return false;
	}
}