package kadaiK;

import java.util.Scanner;

public abstract class Player {
	
	protected String name;	
	protected String type;			//先攻、後攻
	protected int[][] can;			//各攻撃ごとの配置可能位置が含まれた盤面
	protected int stone;			//自分の石の番号1or2
	protected String stonecolor;	//●か○
	protected int x,y;
	Scanner scan=new Scanner(System.in);
	
	Player(String ty,int number,String color) {
		type=ty;
		stone=number;
		stonecolor=color;
		System.out.println(type+"のプレイヤーの名前を入力してください。(1文字以上10文字以下)");
		name=setname();
	}
	Player(String ty,String n,int number,String color) {//チェック時に標準入力でAさん,Bさんを使用してやるため
		type=ty;
		stone=number;
		name=n;
		stonecolor=color;
	}
	
	public String setname() {
		String n=scan.nextLine();
		if(n==null) {										
			throw new IllegalArgumentException("名前がnullです。");	
		}
		if(n.length()==0) {
			throw new IllegalArgumentException("名前が0文字です。");
		}
		if(n.length()>10) {
			throw new IllegalArgumentException("名前が10文字を超えています。");
		}
		return n;
	}
	public String getname() {
		return name;
	}
	public int getx() {		//攻撃時、設置した位置のx,yを他のクラスで使うため
		return x;
	}
	public int gety() {	
		return y;
	}
	public int getstone() {	//今攻撃している人の数字は1か2か判断するため
		return stone;
	}
	
	public void display() {
		System.out.println(name+"さんは"+type+"です。");
		if(type.equals("先攻")) {
			System.out.println("先攻→●");
		}
		else if(type.equals("後攻")) {
			System.out.println("後攻→○");
		}
	}
	public int[][] attack(int[][] set,Player part) {
		//プレイヤーにわかるように、得た盤面に3を含んだものを表示させる。(setは盤面、canは3を加えもの)
		can=Main_sp1.set_3(set,Main_sp2_can.understand(set,stone));
		Main_sp1.field_display_p(can);			
		
		//置く場所がなければパスになり、盤面は3を含んでない状態で返す。
		if(Main_sp1.stone_count(can,3)==0) {
			System.out.println("'パス'");
			return set;
		}
		
		int ok=0;
		System.out.println("・"+name+"さんのターンです。");
		System.out.println("["+type+"の石色→"+stonecolor+"]");
		do {
		x=Player_sp.scan("x");	//入力値の判定
		Player_sp.error(x);		//lengthエラー確認
		y=Player_sp.scan("y");
		Player_sp.error(y);
		ok=Player_sp.judge(can, x, y);	//指定したx,yが3(設置可能マス)であるかの確認
		}while(ok==0);
		
		//指定した場所(x,y)に石を置く
		set=Player_sp.set_stone(set, x, y,stone);
		
		//ひっくり返す
		set=Main_sp4_turn.over(set,x,y,stone,part.getstone());	
		//置ける位置を非表示　(ターンを終える)
		set=Main_sp1.turnend(set);
		return set;
	}
}
