package kadaiK;

public class Main {
	public static void main(String args[]) {
		int i,finish=0;
		int turn=0;
		
		//初期設定
		int[][] set=Main_sp1.first_set();
		
		//ゲーム説明 (場所指定方法)
		Main_sp1.Tutorial();
		
		//プレイヤー設定
		Player[] p=new Player[2];
		p[0]=new Player1();
		p[1]=new Player2();
		for(i=0;i<p.length;i++) {
			p[i].display();
		}
		
		//ゲーム開始
		//攻撃する人、p[0or1]が交互になるので、turn数の2のあまりを使う
		do {
			turn++;
			set=p[(turn+1)%2].attack(set,p[turn%2]);	//攻撃種.attack(受け側)
			finish=Finish.finish(set,p[0],p[1]);
			}while(finish==0);
	}
}
