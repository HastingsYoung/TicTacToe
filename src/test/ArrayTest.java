package test;

public class ArrayTest {

	public static void main(String[] args) {
		int[][] a = {{1,2,3},{4,5,6},{7,8,9}};
		int[][] b = new int[3][3];
		b = a.clone();
		for (int[] is : b) {
			for (int i : is) {
				System.out.println(i);
			}
		}
	}

}
