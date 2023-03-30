import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ1010 {

	public static void main(String[] args) throws Exception{
		// mCn
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		int n, m;
		int[][] arr = new int[31][31];
		for(int i = 0; i < 31; i++) {
			for(int j = 0; j <= i; j++) {
				if(j == 0 || j == i) {
					arr[i][j] = 1;
				}else {
					arr[i][j] = arr[i-1][j]+arr[i-1][j-1];
				}
				
			}
		}
//		for(int i = 0; i < 31;i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		while(tc-->0) {
			n = sc.nextInt();
			m = sc.nextInt();
			sb.append(arr[m][n]).append('\n');
		}
		System.out.println(sb);
	}

}
