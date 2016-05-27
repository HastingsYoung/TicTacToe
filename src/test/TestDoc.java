package test;

public class TestDoc {
	
	int num;
	
	public static void main(String[] args) {

		TestDoc testDoc = new TestDoc();
		testDoc.num = 100;
		while(testDoc.num > 0){
			System.out.println("addplayer t" + testDoc.num + ",f" + testDoc.num + ",g" + testDoc.num);
			System.out.println("displayplayer");
			testDoc.num--;
		}
		
		for(int i = 1; i < 100; i++){
			for(int j = i + 1; j < 40; j++){
				System.out.println("playgame t" + i + ",t" + j);
				System.out.println("1 1");
				System.out.println("1 1");
				System.out.println("3 3");
				System.out.println("2 2");
				System.out.println("1 2");
				System.out.println("2 1");
				System.out.println("1 0");
			}
			for(int j = Math.max(i + 1, 40); j < 50; j++){
				System.out.println("playgame t" + i + ",t" + j);
				System.out.println("0 0");
				System.out.println("0 1");
				System.out.println("0 2");
				System.out.println("1 0");
				System.out.println("1 1");
				System.out.println("2 2");
				System.out.println("1 2");
				System.out.println("2 0");
				System.out.println("2 1");
			}
			for(int j = Math.max(i + 1, 50); j < 101; j++){
				System.out.println("playgame t" + i + ",t" + j);
				System.out.println("0 0");
				System.out.println("1 1");
				System.out.println("0 1");
				System.out.println("0 2");
				System.out.println("1 0");
				System.out.println("2 2");
				System.out.println("1 2");
				System.out.println("2 0");
			}
		}
		
		System.out.println("rankings");
		System.out.println("displayplayer");
		System.out.println("exit");
	}
}