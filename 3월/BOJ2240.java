import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2240 {
	static int n, k, dp[][][], arr[];

	public static void main(String[] args) throws IOException {
		String[] str;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		k = Integer.parseInt(str[1]);
		dp = new int[k+1][n][2];
		arr = new int[n];
		
		//떨어지는 사과 순서 입력받기
		for(int i= 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine()) -1;
		}
		
		//현재 탐색하는 idx, 현재 위치, 남은 k
		dfs(0, 0, 0);
	}

	private static void dfs(int idx, int lr, int cnt) {
		// TODO Auto-generated method stub
		
	}

}
