import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA7699 {
	static int r, c, max, arr[][];
	static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {

		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine()) + 1;
		String[] str;

		for (int tc = 1; tc < testCase; tc++) {
			str = br.readLine().split(" ");
			r = Integer.parseInt(str[0]);
			c = Integer.parseInt(str[1]);
			arr = new int[r][c];

			// int 배열로 입력받기
			for (int i = 0; i < r; i++) {
				str[0] = br.readLine();
				for (int k = 0; k < c; k++) {
					arr[i][k] = str[0].charAt(k) - 'A';
				}
			}

			max = 0;

			// dfs를 위한 visited 초기화
			visited = new boolean[26];
			visited[arr[0][0]] = true;
			// 현재의 i, k, 본 명물의 수
			dfs(0, 0, 1);
			sb.append('#').append(tc).append(" ").append(max).append('\n');
		}
		System.out.println(sb);
	}

	private static void dfs(int nowI, int nowK, int cnt) {
		int[] di = { 0, 0, 1, -1 };
		int[] dk = { 1, -1, 0, 0 };
		int newI, newK;
		max = Math.max(cnt, max);
		for (int d = 0; d < 4; d++) {
			newI = nowI + di[d];
			newK = nowK + dk[d];
			if (newI < 0 || newK < 0 || newI >= r || newK >= c || visited[arr[newI][newK]])
				continue;
			visited[arr[newI][newK]] = true;
			dfs(newI, newK, cnt + 1);
			visited[arr[newI][newK]] = false;
		}
	}

}
