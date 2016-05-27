package test;

public class Alphatest {
	
	public static void main(String[] args){
		
		String text = "addplayer ";
		print(text);
		text = "addaiplayer ";
		print(text);
	}
	private static void print(String text){
		StringBuilder sB;
		for(int i = 0;i < 10;i++){
			sB = new StringBuilder();
			for(int j = 0;j < 3;j++){
				char c = 'A';
				c = (char)(c + (int)(Math.random()*26));
				sB.append(c);
			}
			for(int k = 0;k < 3;k++){
				char c = 'a';
				c = (char)(c + (int)(Math.random() * 26));
				sB.append(c);
			}
			System.out.println(text + sB.toString()+",dsa,wqe");
		}
	}
}
