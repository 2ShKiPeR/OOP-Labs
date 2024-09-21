import myfirstpackage.*;
class MyFirstClass {
 	public static void main(String[] s) {
     		MySecondClass x = new MySecondClass(3, 5);
		System.out.println(x.sum());
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				x.setArg1(i);
				x.setArg2(j);
				System.out.print(x.sum());
				System.out.print(" ");
			}
		System.out.println();
		}
 	}
}

	
	