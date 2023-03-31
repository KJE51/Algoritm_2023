import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ4485 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		StringBuilder sb = new StringBuilder();
		int n, ans, tc = 1;;
		int[][] arr;
		while(true) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			for(int i = 0; i < n; i++) {
				str = br.readLine().split(" ");
				for(int k = 0; k < n; k++) {
					arr[i][k] = Integer.parseInt(str[k]);
				}
			}
			ans = 0;
			
			sb.append("Problem ").append(tc).append(": ").append(ans).append('\n');
			tc++;
		}
	}

}
