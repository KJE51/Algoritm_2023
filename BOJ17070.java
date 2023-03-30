import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ17070 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int n1 = n + 1;
		boolean[][] arr = new boolean[n1][n1];
		int[][] ga = new int[n1][n1];
		int[][] se = new int[n1][n1];
		int[][] dae = new int[n1][n1];
		String[] str;
		for (int i = 0; i < n; i++) {
			str = br.readLine().split(" ");
			for (int k = 0; k < n; k++) {
				if (str[k].equals("1")) {
					arr[i + 1][k + 1] = true;
				}
			}
		}

		for (int i = 2; i < n1; i++) {
			if (arr[1][i]) {
				break;
			}
			ga[1][i] = 1;
		}

		for (int i = 2; i < n1; i++) {
			for (int k = 2; k < n1; k++) {
				if (arr[i][k])
					continue;
				se[i][k] = se[i - 1][k] + dae[i - 1][k];
				if (!arr[i][k - 1] && !arr[i - 1][k])
					dae[i][k] = ga[i - 1][k - 1] + dae[i - 1][k - 1] + se[i - 1][k - 1];
				ga[i][k] = ga[i][k - 1] + dae[i][k - 1];
			}
		}
//		
//		for(int i = 1; i < n1; i++) {
//			System.out.println(Arrays.toString(ga[i]));
//		}
//		System.out.println();
//		for(int i = 1; i < n1; i++) {
//			System.out.println(Arrays.toString(se[i]));
//		}
//		System.out.println();
//		for(int i = 1; i < n1; i++) {
//			System.out.println(Arrays.toString(dae[i]));
//		}
//		System.out.println();
//		
		System.out.println(ga[n][n] + se[n][n] + dae[n][n]);
	}

}
