import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ12865 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int n1 = n+1;
		int k = Integer.parseInt(str[1]);
		int k1 = k+1;
		int[] weight = new int[n1];
		int[] value = new int[n1];
		for(int i = 1; i < n1; i++) {
			str = br.readLine().split(" ");
			weight[i] = Integer.parseInt(str[0]);
			value[i] = Integer.parseInt(str[1]);
		}
		
		int[][] dp = new int[n1][k+1];
		int nowW;
		int nowV;
		for(int i = 1; i < n1; i++) {
			nowW = weight[i];
			nowV = value[i];
			for(int j = 1; j < k1; j++) {
				if(j<weight[i]) {
					dp[i][j] = dp[i-1][j];
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-nowW]+nowV);
				}
			}
		}
		System.out.println(dp[n][k]);
	}

}
