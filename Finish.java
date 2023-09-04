package kadaiK;

public class Finish {//Mainのwhile文を止める役割。

	public static int finish(int[][] set,Player p1,Player p2) {	
		int count0=Main_sp1.stone_count(set,0);
		int count1=Main_sp1.stone_count(set,1);
		int count2=Main_sp1.stone_count(set,2);
		
		//すべて埋まった場合
		if(count0==0) {
			System.out.println("\n黒:"+count1+" ,白:"+count2+"より");
			if(count1>count2) {
				System.out.println(p1.getname()+"の勝利!");
				return 1;
			}
			else if(count2>count1) {
				System.out.println(p2.getname()+"の勝利!");
				return 2;
			}
			else {
				System.out.println("引き分け!");
				return 3;
			}
		}
		
		//全染
		//すべて白→黒負け
		if(count1==0) {
			System.out.println("\n黒:"+count1+" ,白:"+count2+"より");
			System.out.println(p2.getname()+"の勝利!");
			return 2;
		}
		//すべて黒→白負け
		if(count2==0) {
			System.out.println("\n黒:"+count1+" ,白:"+count2+"より");
			System.out.println(p1.getname()+"の勝利!");
			return 1;
		}
		
		return 0;
	}
}
