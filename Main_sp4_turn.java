package kadaiK;

public class Main_sp4_turn {	//攻撃側の設置したマスを基準に、ひっくり返すクラス
	//numは攻撃側の数字、partは守備側の数字 (それぞれ1か2)
	
	public static int[][] over(int[][] set,int x,int y,int num,int part) {
		//縦方向
		set=turnV(set,vert(set[x],y,num,part),num,"x",x);
		
		//横方向
		int[][] set_yx=Main_sp1.reverse(set);
		set=turnV(set,vert(set_yx[y],x,num,part),num,"y",y);
		
		//斜め方向 [\]
		set=slashL(set,x,y,num,part);
		//斜め方向 [/]
		set=slashR(set,x,y,num,part);
		return set;
	}
	
	public static int[] vert(int[] sort,int xy,int num,int part) {//自分の位置を基準に次が相手色なら、その先が自分の色か確認する。
		int i,j,k;
		if(xy>1) {
			j=xy-1;
			if(sort[j]==part) {
				do {
					j--;
				}while((sort[j]==part)&&(j>0));
				//上が機能して終わるときj=0なはず。
				if(sort[j]==num) {
					for(i=xy-1;i>j;i--) {
						sort[i]=num;
					}
				}
			}
		}
		if(xy<sort.length-2) {
			k=xy+1;
			if(sort[k]==part) {
				do {
					k++;
				}while((sort[k]==part)&&(k<sort.length-1));
				//上が機能して終わるときk=7なはず。
				if(sort[k]==num) {
					for(i=xy+1;i<k;i++) {
						sort[i]=num;
					}
				}
			}
		}
		return sort;
	}
	public static int[][] turnV(int[][] set,int[] turnplace,int num,String xy,int XY) {//得た縦横位置を返す
		int x,y;
		if(xy.equals("x")) {
			for(y=0;y<turnplace.length;y++) {
				set[XY][y]=turnplace[y];
			}
		}
		if(xy.equals("y")) {
			for(x=0;x<turnplace.length;x++) {
				set[x][XY]=turnplace[x];
			}
		}
		
		return set;
	}
	
	public static int[][] slashL(int[][] set,int X,int Y,int num,int part){
		int[] sort;
		int i,x=X,y=Y,firstx,firsty;
		//[\] 左下部分
		if(x<y) {			
			while(x!=0) {
				x--;
				y--;
			}
			sort=new int[8-y];
			firstx=x;
			firsty=y;
			for(i=0;i<sort.length;i++) {
				sort[i]=set[x][y];
				x++;
				y++;
			}
			sort=vert(sort,X,num,part);
			for(i=0;i<sort.length;i++) {
				set[firstx][firsty]=sort[i];
				firstx++;
				firsty++;
			}
		}
		//x=y 列
		else if(x==y) {			
			sort=new int[8];
			y=0;
			for(x=0;x<sort.length;x++) {
				sort[x]=set[x][y];
				y++;
			}
			sort=vert(sort,X,num,part);	//XでもYでも
			y=0;
			for(x=0;x<sort.length;x++) {
				set[x][y]=sort[x];
				y++;
			}
		}
		//右上部分
		else {					
			while(y!=0) {
				x--;
				y--;
			}
			sort=new int[8-x];
			firstx=x;
			firsty=y;
			for(i=0;i<sort.length;i++) {
				sort[i]=set[x][y];
				x++;
				y++;
			}
			sort=vert(sort,Y,num,part);
			for(i=0;i<sort.length;i++) {
				set[firstx][firsty]=sort[i];
				firstx++;
				firsty++;
			}
		}
		return set;
	}
	
	public static int[][] slashR(int[][] set,int X,int Y,int num,int part) {
		int[] sort;
		int i,x=X,y=Y,firstx,firsty;
		//右下部分
		if(x+y>7) {			
			while(x<7) {
				x++;
				y--;
			}
			sort=new int[8-y];
			firstx=x;
			firsty=y;
			for(i=0;i<sort.length;i++) {
				sort[i]=set[x][y];
				x--;
				y++;
			}
			sort=vert(sort,7-X,num,part);
			for(i=0;i<sort.length;i++) {
				set[firstx][firsty]=sort[i];
				firstx--;
				firsty++;
			}
		}
		//x+y=7 列
		else if((x+y)==7) {			//x=y
			sort=new int[8];
			x=7;
			for(y=0;y<sort.length;y++) {
				sort[y]=set[x][y];
				x--;
			}
			sort=vert(sort,Y,num,part);	//Yor8-X
			x=7;
			for(y=0;y<sort.length;y++) {
				set[x][y]=sort[y];
				x--;
			}
		}
		//左上部分
		else {					
			while(y!=0) {
				x++;
				y--;
			}
			sort=new int[1+x];
			firstx=x;
			firsty=y;
			for(i=0;i<sort.length;i++) {
				sort[i]=set[x][y];
				x--;
				y++;
			}
			sort=vert(sort,Y,num,part);	//置いた医師が直線の番号とあっているかY+1
			for(i=0;i<sort.length;i++) {
				set[firstx][firsty]=sort[i];
				firstx--;
				firsty++;
			}
		}
		return set;
	}
	
}
