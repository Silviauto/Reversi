package kadaiK;

public class Main_sp1 {	//よく使う、基本的な関数を集めた。
	//numは攻撃側の数字、partは守備側の数字 (それぞれ1か2)
	
	public static int[][] first_set() {//初期盤面
		int[][] set=new int[8][8];
		set=clear(set);
		set[3][3]=1;
		set[3][4]=2;
		set[4][3]=2;
		set[4][4]=1;
		return set;
	}
	public static void Tutorial() {//石の置き方(x,y)の簡単な説明
		int[][] Tutorial=first_set();
		Tutorial[4][2]=4;
		System.out.println("[座標入力方法]");
		field_display_p(Tutorial);
		System.out.println("★位置に設置する場合は、 x=4 ,y=2　と入力してください。\n\n");
	}
	
	public static int stone_count(int[][] set,int num) {//カウント
		//1:黒 ,2:白 ,3:各プレイヤー攻撃時に設置可能なマス ,0:未配置マス
		
		int x=0,y=0,count1=0,count2=0,count3=0,count0=0;;
		for(y=0;y<8;y++) {
			for(x=0;x<8;x++) {
				if(set[x][y]==0) {
					count0++;
				}
				else if(set[x][y]==1) {
					count1++;
				}
				else if(set[x][y]==2) {
					count2++;
				}
				else if(set[x][y]==3) {
					count3++;
				}
				
				if(x==7) {
				}
			}
		}
		if(num==1) {
			return count1;
		}
		else if(num==2){
			return count2;
		}
		else if(num==3) {
			return count3;
		}
		else {
			return count0;
		}
	}
	public static void field_display_p(int[][] set) {//盤面表示
		int x=0,y=0,count1=0,count2=0;
		System.out.println("  ０１２３４５６７ →x");
		for(y=0;y<8;y++) {
			System.out.print(y);
			for(x=0;x<8;x++) {
				if(set[x][y]==0) {
					System.out.print("空");
				}
				else if(set[x][y]==1) {
					System.out.print("●");
					count1++;
				}
				else if(set[x][y]==2) {
					System.out.print("○");
					count2++;
				}
				else if(set[x][y]==3) {
					System.out.print("□");
				}
				else if(set[x][y]==4) {
					System.out.print("★");
				}
				if(x==7) {
					System.out.println("");
				}
			}
		}
		System.out.println("↓y　　[ 黒:"+count1+" ,白:"+count2+"]");
	}
	
	public static int[][] clear(int[][] set) {//nullにしないために、仮盤面を作るときは全マスに0(未設置)を入れる。
		int x,y;
		for(x=0;x<8;x++) {
			for(y=0;y<8;y++) {
				set[x][y]=0;
			}
		}
		return set;
	}
	
	public static int[][] set_3(int[][] field,int[][] place3) {//他クラスで確認した場所に、3(配置可能)を入れる。
		int x,y;
		for(y=0;y<8;y++) {
			for(x=0;x<8;x++) {
				if(place3[x][y]==3) {
					field[x][y]=place3[x][y];
				}
			}
		}
		return field;
	}
	
	public static int[][] reverse(int[][] field) {
		//配列の都合から[x][y] x→y↓になってしまう。　y↓x→で配列表示を行いたいときに使用する。
		
		int[][] reverse=new int[8][8];
		int x,y;
		for(y=0;y<8;y++) {
			for(x=0;x<8;x++) {
				reverse[y][x]=field[x][y];
			}
		}
		return reverse;
	}
	
	public static int[][] turnend(int[][] set) {
		//攻撃終了時に3(設置可能)→0(未設置)に戻す。 (プレイヤーによって3の位置は異なる＋毎攻撃ごとに変化するため)
		int x,y;
		for(y=0;y<8;y++) {
			for(x=0;x<8;x++) {
				if(set[x][y]==3) {
					set[x][y]=0;
				}
			}
		}
		return set;
	}
}
