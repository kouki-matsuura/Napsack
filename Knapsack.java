import java.util.*;
import java.util.Scanner;

public class Knapsack {

	int[] size; //品の大きさ
	int[] value;//品の価値
	int N;//品の種類の数
	
	public Knapsack(int[] size,int[] value) {
		//sizeとvalueの要素の個数が等しいことを確認
		if(size.length != value.length) {
			throw new IllegalArgumentException("'size'と'value'の要素数が一致していません");
		}
		
		//品の種類の数をセットする
		this.N = size.length;
		
		//配列sizeの複製を作成。
		this.size = (int[])size.clone();
		
		//配列valueの複製を作成してフィールドvalueにセットする
		this.value = (int[])value.clone();
	}
	
	public void solve(int m) {
		
		//現時点でナップザックに詰め込んだ品物の価値の合計
		int[] total = new int[m+1];
		
		//最後に選んだ品物
		int[] choice = new int[m+1];
		Arrays.fill(choice, -1);//全要素を-1に初期化
		
		//品物iを入れたときの価値の合計
		int repackTotal;
		
		//ナップザックの大きさを表示
		System.out.println("ナップザックの大きさは"+m);
		
		//品物0~iまでを考慮に入れる
		for(int i=0;i<N;i++) {
			//大きさｊのナップザックにたいして、品物を詰め込んでみる
			for(int j=size[i];j<=m;j++) {
			//もし、品物iを入れたとすると、価値の合計はいくらになるかを計算して、変数repackTotalに入れる
				repackTotal = total[j-size[i]] + value[i];
				
				//もし、品物iをいれたほうがいれないより価格がおおきくなるのなら、品物iを入れる。
				if(repackTotal > total[j]) {
					total[j] = repackTotal;
					choice[j] = i;
				}
			}
			
			//配列total,choiceの中身を表示
			System.out.println("i = "+i);
			System.out.print("total =");
			for(int j=0;j<=m;j++) {
				System.out.print(total[j]+" ");
			}
			System.out.println();
			System.out.print("choice = ");
			for(int j=0;j<=m;j++) {
				System.out.print(choice[j]+" ");
			}
			System.out.println();
		}
		
		
		//どの品物をナップザックに入れたかを表示する
		for(int i=m; choice[i] >= 0; i -= size[choice[i]]) {
			System.out.print("品物"+choice[i]+"、価値"+value[choice[i]]+"を詰め込む");
			System.out.println();
		}
		System.out.print("価値の合計 = "+total[m]);
		
	}
	
	
	
	//メインプログラム
	public static void main(String args[]) {
		
		//ナップザック問題を解決するためのオブジェクトを生成する
		Knapsack knapsack = 
				new Knapsack(
						new int[] {2,3,5,7,9},
						new int[] {2,4,7,11,14});
		System.out.println("入力してください");
		Scanner sc = new Scanner(System.in);
		
		int size = sc.nextInt();
		
		knapsack.solve(size);
		
		
	}
}
