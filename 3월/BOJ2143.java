import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2143 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		int t = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine())+1;
		int cnt = 0;
	
		int[] arrA = new int[n];
		
		//i까지의 합을 저장
		str = br.readLine().split(" ");
		for(int i = 1; i < n; i++) {
			arrA[i] = Integer.parseInt(str[i-1]) + arrA[i-1];
		}
		
		//i번째까지의 합을 저장
		int m = Integer.parseInt(br.readLine())+1;
		int[] arrB = new int[m];
		str = br.readLine().split(" ");
		for(int i = 1; i < m; i++) {
			arrB[i] = Integer.parseInt(str[i-1])+arrB[i-1];
		}
		
//		System.out.println();
//		System.out.println(Arrays.toString(arrA));
//		System.out.println(Arrays.toString(arrB));
		for(int i1 = 1; i1 < n;i1++) {
			for(int i2 = 0; i2<i1; i2++) {
				for(int k1 = 1; k1 < m;k1++) {
					for(int k2 = 0; k2<k1; k2++) {
						if(t == arrA[i1]-arrA[i2]+arrB[k1]-arrB[k2]) {
//							System.out.println("A:"+i2+"에서 "+i1+" B:"+k2+"에서 "+k1);
//							System.out.println((arrA[i1]-arrA[i2])+ " 와 "+(arrB[k1]-arrB[k2]));
							cnt++;
						}
					}
				}
			}
		}
		System.out.println(cnt);
	}

}
