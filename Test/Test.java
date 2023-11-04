class A {

	private static int var1 = 10;
	private int var2 = 10;

	public void inc() {
		var1 += 1;
		var2 += 1;
	}

	public void print(){
		System.out.println("var1 = " + var1 + ", var2 = " + var2);
	}
}
public class Test {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A a1 = new A();
		a1.inc();
		a1.print();

		A a2 = new A();
		a2.print();
		a2.inc();
		a1.print();
		a2.print();
	}

}
