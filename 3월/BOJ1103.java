

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1103 {
	static int n;
	static int m;
	static int[][] arr, dp;
	static boolean flag = false;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		char c;
		arr = new int[n][m];
		dp = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			for(int k = 0; k < m; k++) {
				dp[i][k] = -2;
			}
		}
		
		for(int i = 0; i < n; i++) {
			str[0] = br.readLine();
			for(int k = 0; k < m; k++) {
				c = str[0].charAt(k);
				System.out.println(c);
				if(c == 'H') {
					arr[i][k] = -1;
				}
				else {
					arr[i][k] = (int)c - (int)'0';
				}
			}
		}
		
		for(int i = 0; i < n; i++)
			System.out.println(Arrays.toString(arr[i]));
		
		int result = dfs(0, 0);
		if(result == -1)
			System.out.println(result);
		else
			System.out.println(result+1);
	}

	private static int dfs(int idxI, int idxK) {
		dp[idxI][idxK] = -1;
		System.out.println("idxI, idxK : "+idxI+", "+idxK);
		System.out.println("arr : "+arr[idxI][idxK]);
		System.out.println("dp : "+dp[idxI][idxK]);
		int[] di = {0, 0, 1, -1};
		int[] dk = {1, -1, 0, 0};
		int[] cnt = new int[4];
		int newI, newK;
		for(int i = 0; i < 4; i++) {
			newI = idxI + di[i]*arr[idxI][idxK];
			newK = idxK + dk[i]*arr[idxI][idxK];
			System.out.println("newI, K : "+newI+", "+newK);
			if(newI<0||newK<0||newI>=n||newK>=m||arr[newI][newK] == -1) {
				cnt[i] = 0;
			}
			else {
				System.out.println("newI, K : "+newI+", "+newK);
				System.out.println("dp : "+dp[newI][newK]);
				if(dp[newI][newK] == -2)
					cnt[i] = dfs(newI, newK) + 1;
				else {
					cnt[i] = -1;
					flag = true;
				}
			}
		}
		//무한루프일 때
		if(flag == true) {
			return -1;
		}
		
		//무한루프가 아닐 때
		int max = Math.max(cnt[0], cnt[1]);
		max = Math.max(max, cnt[2]);
		max = Math.max(max, cnt[3]);
		
		return dp[idxI][idxK] = max;
	}

}
