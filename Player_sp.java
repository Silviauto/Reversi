package kadaiK;

import java.util.Scanner;

public class Player_sp {
	public static int scan(String xy) {	
		//入力無し、0～7(マス外)入力、文字の入力、1桁であるかの確認
		
		Scanner scan=new Scanner(System.in);
		int ok=0,number=100;
		do {
			System.out.println(xy+"を入力してください");
			String input=scan.nextLine();
			if((input.length()>1)||(input=="")) {
				System.out.println("入力値が正しくありません。");
			}	
			else {
				if(Character.isDigit(input.charAt(0))) {	//Stringの位置文字をcharに変換+そのchar型の文字がInt型であるか判定。
					if((Integer.parseInt(input)>=0)&&(Integer.parseInt(input)<8)) {
						number=Integer.parseInt(input);
						ok=1;
					}
					else {
						System.out.println("正しく(0<="+xy+"<=7)を入力してください");
					}
				}
				else {
					System.out.println("正しく数字を入力してください。");
				}
			}
		}while(ok==0);
		return number;
	}
	
	public static void error(int input) {	
		if(input==8) {
			throw new IllegalArgumentException("入力値が正しく反映されない、または、入力エラーの」可能性のためゲームを終了します。");
		}
	}
	
	public static int judge(int[][] can,int x,int y) {//選択された[x][y]が置くことができる場所であるか判定する。
		if(can[x][y]==3) {
			return 1;
		}
		else {
			System.out.println("※その位置には置くことができません※");
			return 0;
		}
	}
	
	public static int[][] set_stone(int[][] set,int x,int y,int stonenumber) {
		System.out.println("x,y="+x+","+y);
		set[x][y]=stonenumber;
		return set;
	}
}
