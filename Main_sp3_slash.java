package kadaiK;

public class Main_sp3_slash {//part2内の斜め作業を行うクラス
	//numは攻撃側の数字、partは守備側の数字 (それぞれ1か2)
	
	public static int[][] slash(int[][] set,int num) {//縦横と同じく斜めも一列ずつ判定する。
		//[\]→右上、左下 ,[/]→左上、右下
		//それぞれ端の3マスは石の数から、1列(1マス、2マス)よりひっくり返せない
		//列の作り方が違うだけで判定方法は同じなので、sp2のvertをそのまま使う。
		
		int[][] can=new int[8][8];
		can=Main_sp1.clear(can);
		int x,y;
		
		//slashLeft	[\]	列内では左上から012...
		//y軸沿い基準↓
		for(y=5;y>0;y--) {
			can=slashLy_set3(can,slashLy(set,y),y,num);	
		}
		//x=yの列
		can=slashLxy(set,can,num);
		//x軸沿い基準→
		for(x=5;x>0;x--) {
			can=slashLx_set3(can,slashLx(set,x),x,num);
		}
		
		//slashRight [/]	列内では右上から012...
		//x軸沿い基準→
		for(x=2;x<7;x++) {
			can=slashRx_set3(can,slashRx(set,x),x,num);
		}
		//x+y=7の列
		can=slashRxy(set,can,num);
		//y軸沿い基準↓	(ここだけ数字の書き方が他の3つと異なるx,yどちらかが7)
		for(y=1;y<6;y++) {
			can=slashRy_set3(can,slashRy(set,y),y,num);
		}
		return can;
	}
	
	//L y軸基準		↓slashL関連
	public static int[] slashLy(int[][] set,int y) {//y軸基準の7から1
		int i=0,x=0;
		int[] line=new int[8-y];
		do {
			line[i]=set[x][y];
			i++;
			x++;
			y++;
		}while(y<8);
		return line;
	}
	public static int[][] slashLy_set3(int[][] can,int[] slashLy,int y,int num) {
		int[] place3=Main_sp2_can.vert(slashLy, num);	
		int i;
		if(place3!=null) {
			for(i=0;i<place3.length;i++) {
				can[place3[i]][y+place3[i]]=3;
			}
		}
		return can;
	}
	//L x=y列
	public static int[][] slashLxy(int[][] set,int[][] can,int num) {
		int i,x,y=0;
		int[] line=new int[8];	//x=yの一列
		for(x=0;x<8;x++) {
			line[x]=set[x][y];
			y++;
		}
		int[] place3=Main_sp2_can.vert(line,num);
		if(place3!=null) {
			for(i=0;i<place3.length;i++) {
				can[place3[i]][place3[i]]=3;
			}
		}
		return can;
	}
	//L x軸基準
	public static int[] slashLx(int[][] set,int x) {//注目
		int i=0,y=0;
		int[] line=new int[8-x];
		do {
			line[i]=set[x][y];
			i++;
			x++;
			y++;
		}while(x<8);
		return line;
	}
	public static int[][] slashLx_set3(int[][] can,int[] slashLx,int x,int num) {
		int[] place3=Main_sp2_can.vert(slashLx, num);//斜め一列におけるおける場所
		int i;
		if(place3!=null) {
			for(i=0;i<place3.length;i++) {
				can[x+place3[i]][place3[i]]=3;
			}
		}
		return can;
	}
	
	
	//x沿基準共に使用		↓slashR関連
	public static int[] slashRx(int[][] set,int x) {
		int i=0,y=0;
		int[] line=new int[1+x];
		do {
			line[i]=set[x][y];
			i++;
			x--;
			y++;
		}while(x>-1);
		return line;
	}
	public static int[][] slashRx_set3(int[][] can,int[] slashRx,int x,int num) {
		int[] place3=Main_sp2_can.vert(slashRx, num);//斜め一列におけるおける場所
		int i;
		if(place3!=null) {
			for(i=0;i<place3.length;i++) {
				can[x-place3[i]][place3[i]]=3;
			}
		}
		return can;
	}
	//x=y列
	public static int[][] slashRxy(int[][] set,int[][] can,int num) {
		int i,x,y=0;
		int[] line=new int[8];	
		for(x=7;x>-1;x--) {
			line[y]=set[x][y];
			y++;
		}
		int[] place3=Main_sp2_can.vert(line,num);
		if(place3!=null) {
			for(i=0;i<place3.length;i++) {
				can[7-place3[i]][place3[i]]=3;
			}
		}
		return can;
	}
	//y軸沿い基準に使用
	public static int[] slashRy(int[][] set,int y) {
		int i=0,x=7;
		int[] line=new int[8-y];
		do {
			line[i]=set[x][y];
			i++;
			x--;
			y++;
		}while(y<8);
		return line;
	}
	public static int[][] slashRy_set3(int[][] can,int[] slashRy,int y,int num) {
		int[] place3=Main_sp2_can.vert(slashRy, num);
		int i;
		if(place3!=null) {
			for(i=0;i<place3.length;i++) {
				can[7-place3[i]][y+place3[i]]=3;
			}
		}
		return can;
	}
}
