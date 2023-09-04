package kadaiK;

public class Main_sp2_can {	//攻撃時に、自分の石(黒、白)が置ける位置を判断するクラス。
	//numは攻撃側の数字、partは守備側の数字 (それぞれ1か2)
	
	public static int[][] understand(int[][] set,int num) {	//置くことができるか場所に3を入れ返すクラス
		//全マスを一気に判定することはできないので、縦横斜め一列ずつ判定する。
		
		int[][] set_yx=Main_sp1.reverse(set);
		int[][] can=new int[8][8]; 
		can=Main_sp1.clear(can);
		int x,y,i;
		
		//縦方向を判断する
		int[][] canV=new int[8][8];
		for(x=0;x<8;x++) {
			int[] cV=vert(set[x],num);
			if(cV!=null) {						//cV→can Vertical 3(設置可能)マスがなければ置かない。
				for(i=0;i<cV.length;i++) {
					canV[x][cV[i]]=3;
				}
			}
		}
		can=Main_sp1.set_3(set,canV);
		
		//横方向を判断する
		int[][] canH=new int[8][8];
		for(y=0;y<8;y++) {	//横向きでも盾と同じ関数が使える。
			int[] cH=vert(set_yx[y],num);
			if(cH!=null) {
				for(i=0;i<cH.length;i++) {
					canH[y][cH[i]]=3;
				}
			}
		}
		can=Main_sp1.set_3(can,Main_sp1.reverse(canH));
		
		//斜め[\][/]方向	後に8をすべてlengthに変更する可能性がある。
		int[][] canslash=Main_sp3_slash.slash(set,num);
		can=Main_sp1.set_3(can,canslash);
		
		return can;
	}
	
	
	
	public static int[] vert(int[] sort,int num) {//受け取った一列の状態から3(設置可能マス)を置くべ位置を判断する
		//盤面と同じく、列状態でも両側から一気に判断することは難しいので、up(上から),down(下から)と分けて判定する。
		
		int[] upplace;
		int[] downplace;
		int[] down=new int[sort.length];
		int i;
		upplace=firm(sort,num);	//up
		
		//列を逆向きを作る
		for(i=0;i<sort.length;i++) {
			down[i]=sort[(sort.length-1)-i];
		}
		downplace=firm(down,num);	//down
		downplace=reset(downplace,sort.length); //逆向きにしたdownをもとのupの向きに戻す。
		
		int[] cV=match(upplace,downplace);	//合わせる。
		return cV;
	}
	
	public static int[] firm(int[] sort,int num) {//重要な、列の数字(0,1,2)から3を置ける位置を判断し返す。
		//置けるマスは0の状態なはずなので、0の時にその先の状態を確認する。可能なら0の位置を記録する。
		
		int i,j,k=0;
		int[] point=new int[8];
		int part,count=0;
		if(num==1) {	//相手の数字
			part=2;	
		}
		else {
			part=1;
		}
		
		for(i=0;i<sort.length-2;i++) {	//2マスは何も起こらないから確認しない
			if(sort[i]==0) {
				j=i+1;					//判断するのは0の次のマス
				if(j<7) {						
					if(sort[j]==part) {			
						j++;
						if(j<8) {				//8個目の判定
							if((sort[j]==part)&&(j!=sort.length-1)) {	//最後の1個前までは判定
								do{
									j++;
								}while((sort[j]==part)&&(j<sort.length-1));
								if(sort[j]==part) {	//whileを抜け、最後の一個までpartだった場合
									i=j;
								}
							}
							else if((sort[j]==part)&&(j==sort.length-1)) {		//後ろ2つがpartの場合
								i=j;
							}
							if(sort[j]==num) {	//whileを抜けた後の石を判断するためにelse ifではなくif
								point[k]=i;
								count++;
								k++;
								i=j;		//for文でi++になるからi=jでいい。
							}
							if(sort[j]==0){
								i=j-1;		//次はこの0マスを使って判定するからj-1
							}
						}
					}
					else if(sort[j]==num) {
						i=j;	//次のマスを判定
					}
					else {
						//次のマスが空なら次もそのまま通る。
					}
					
				}
				
				
			}
		}
		int[] place=new int[count];	//必要な分だけの配列を返す。
		for(i=0;i<count;i++) {
			place[i]=point[i];
		}
		return place;
	}
	
	public static int[] reset(int[] place,int L) {	//逆向きの配置可能マスを正しい向きにする。
		int i;
		for(i=0;i<place.length;i++) {
			place[i]=miss_adjust(place[i],L);
		}
		return place;
	}
	
	public static int miss_adjust(int miss,int L) { //縦横は8マスだが、斜めは3～8マス列だから分ける。
		int correct;
		if(L==8) {
			correct=7-miss;
		}
		else {
			correct=L-(miss+1);
		}
		return correct;
	}
	public static int[] match(int[] one,int[] another) {//違う方向おから確認した配置化の位置を数字が低い順に正しく入れていく。
		//nullは判定時に3(置ける場所)がなかったとき
		
		int[] proto=new int[8];
		int i,j=0,k=0,m,n,count=0;

		if((one.length==0)&&(another.length==0)) {
			return null;
		}
		else if(one.length==0) {
			for(i=0;i<another.length;i++) {
				proto[i]=another[i];
				count++;
			}
		}
		else if(another.length==0) {
			for(i=0;i<one.length;i++) {
				proto[i]=one[i];
				count++;
			}
		}
		else {
			for(i=0;i<8;i++) {
				if(one[j]==another[k]) {
					proto[i]=one[i];
					j++;
					k++;
					count++;
				}
				else if(one[j]<another[k]) {
					proto[i]=one[j];
					j++;
					count++;
				}
				else {
					proto[i]=another[k];
					k++;
					count++;
				}
				
				if((j==one.length)&&(k==another.length)) {
					i=7;
				}
				else if(j==one.length) {
					i++;
					for(n=k;n<another.length;n++) {
						proto[i]=another[n];
						count++;
						i++;
					}
					i=7;
				}
				else if(k==another.length) {
					i++;
					for(m=j;m<one.length;m++) {
						proto[i]=one[m];
						count++;
						i++;
					}
					i=7;
				}
			}
		}
		int[] match=new int[count];
		for(i=0;i<count;i++) {
			match[i]=proto[i];
		}
		return match;
	}
}
