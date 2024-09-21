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
class MySecondClass {
	private int arg1, arg2;
	public void setArg1(int arg1) {
		this.arg1 = arg1;
	}
	public void setArg2(int arg2) {
		this.arg2 = arg2;
	}
	public int getArg1() {
		return arg1;
	}
	public int getArg2() {
		return arg2;
	}
	public MySecondClass(int arg1, int arg2) {
		this.arg1 = arg1;
		this.arg2 = arg2;
	}
	public int sum() {
		this.arg1 += this.arg2;
		return this.arg1;
	}

}
	
	