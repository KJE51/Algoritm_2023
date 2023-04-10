import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ16919 {
	/**
	 * 짝수초 : 폭탄 설치. 무조건 다 채워져 있음. 홀수초 : 3초전에 설치한 폭탄만 터짐. -> 계속 toggle되니까 규칙있음
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int sizeK, sizeI, times;
		sizeI = Integer.parseInt(str[0]);
		sizeK = Integer.parseInt(str[1]);
		times = Integer.parseInt(str[2]);

		int[][] arr = new int[sizeI][sizeK];

		String str1;
		for (int i = 0; i < sizeI; i++) {
			str1 = br.readLine();
			for (int k = 0; k < sizeK; k++) {
				if (str1.charAt(k) == 'O') {
					arr[i][k] = 2;
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		// 1일 때 -> 그대로 출력
		if (times == 1) {
			for (int i = 0; i < sizeI; i++) {
				for (int k = 0; k < sizeK; k++) {
					if (arr[i][k] == 0)
						sb.append('.');
					else
						sb.append('O');
				}
				sb.append('\n');
			}
			System.out.println(sb);
			return;
		}

		times = times % 4;

		// 짝수일 때 -> 다 채워서 출력
		if (times == 0 || times == 2) {
			for (int i = 0; i < sizeI; i++) {
				for (int k = 0; k < sizeK; k++) {
					sb.append('O');
				}
				sb.append('\n');
			}
			System.out.println(sb);
			return;
		}

		boolean[][] visited = new boolean[sizeI][sizeK];
		int[] di = { 0, 0, 1, -1 };
		int[] dk = { 1, -1, 0, 0 };
		int newI, newK;

		//처음꺼 터트렸을때 모양 찾기
		for (int i = 0; i < sizeI; i++) {
			for (int k = 0; k < sizeK; k++) {
				if (arr[i][k] == 2) {
					visited[i][k] = true;
					for (int d = 0; d < 4; d++) {
						newI = i + di[d];
						newK = k + dk[d];
						if (newI < 0 || newK < 0 || newI >= sizeI || newK >= sizeK)
							continue;
						visited[newI][newK] = true;
					}
				}
			}
		}

		// 처음꺼만 터진 모양
		if (times == 3) {
			for (int i = 0; i < sizeI; i++) {
				for (int k = 0; k < sizeK; k++) {
					if (visited[i][k])
						sb.append('.');
					else
						sb.append('O');
				}
				sb.append('\n');
			}
			System.out.println(sb);
			return;
		}

		//처음 터트린거 다시 터트렸을 때
		if (times == 1) {
			boolean[][] new2 = new boolean[sizeI][sizeK];

			for (int i = 0; i < sizeI; i++) {
				for (int k = 0; k < sizeK; k++) {
					if (visited[i][k] == false) {
						new2[i][k] = true;
						for (int d = 0; d < 4; d++) {
							newI = i + di[d];
							newK = k + dk[d];
							if (newI < 0 || newK < 0 || newI >= sizeI || newK >= sizeK)
								continue;
							new2[newI][newK] = true;
						}
					}
				}
			}

			for (int i = 0; i < sizeI; i++) {
				for (int k = 0; k < sizeK; k++) {
					if (new2[i][k])
						sb.append('.');
					else
						sb.append('O');
				}
				sb.append('\n');
			}
			System.out.println(sb);
			return;
		}
	}
}
