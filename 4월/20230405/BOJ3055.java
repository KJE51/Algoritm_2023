import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ3055 {

	// 좌표를 저장할 class
	static class Data {
		int i;
		int k;

		public Data(int i, int k) {
			super();
			this.i = i;
			this.k = k;
		}

		@Override
		public String toString() {
			return "Data [i=" + i + ", k=" + k + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int m = Integer.parseInt(str[1]);

		int[][] arr = new int[n][m];

		// 입력받기
		// 집 위치는 따로 저장
		int homeI, homeK, size;
		homeI = homeK = -1;

		// 물과 고슴도치의 위치를 저장할 que
		Queue<Data> waters = new LinkedList<>();
		Queue<Data> dos = new LinkedList<>();

		// 물 : -1, 빈칸 : 0, 고슴도치 위치 : 1, 돌은 -2
		for (int i = 0; i < n; i++) {
			str[0] = br.readLine();
			for (int k = 0; k < m; k++) {
				if (str[0].charAt(k) == '*') {
					arr[i][k] = -1;
					waters.add(new Data(i, k));
				} else if (str[0].charAt(k) == 'D') {
					homeI = i;
					homeK = k;
				} else if (str[0].charAt(k) == 'S') {
					arr[i][k] = 1;
					dos.add(new Data(i, k));
				}else if (str[0].charAt(k) == 'X') {
					arr[i][k] = -2;
				}
			}
		}
		
		// arr를 업데이트 시켜가며 함.
		// 물은 -1로 업데이트, 고슴도치가 간 위치는 1로 업데이트.
		// 먼저 물을 채운 후, 고슴도치를 이동시킴.
		Data data;
		int newI, newK;
		int[] di = { 0, 0, 1, -1 };
		int[] dk = { 1, -1, 0, 0 };
		int cnt = 1;
		while (!dos.isEmpty()) {
			// 물 채우기, 단계별로 진행.
			size = waters.size();
			while (size-- > 0) {
				data = waters.poll();
				for (int d = 0; d < 4; d++) {
					newI = data.i + di[d];
					newK = data.k + dk[d];
					// 범위를 벗어나거나 이미 물이 채워져있을 때
					if (newI < 0 || newK < 0 || newI >= n || newK >= m || arr[newI][newK] == -1||arr[newI][newK] == -2) {
						continue;
					}
					// 집에는 물 못 채움
					if (newI == homeI && newK == homeK) {
						continue;
					}
					// 물 채우기
					arr[newI][newK] = -1;
					waters.add(new Data(newI, newK));
				}
			}
			
			size = dos.size();
			while (size-- > 0) {
				data = dos.poll();
				for (int d = 0; d < 4; d++) {
					newI = data.i + di[d];
					newK = data.k + dk[d];
					// 범위를 벗어나거나 이미 물이 채워져있거나, 이미 이동했을 때
					if (newI < 0 || newK < 0 || newI >= n || newK >= m || arr[newI][newK] != 0) {
						continue;
					}
					// 고슴도치 이동시키기
					arr[newI][newK] = 1;
					dos.add(new Data(newI, newK));
					if (newI == homeI && newK == homeK) {
						System.out.println(cnt);
						return;
					}
				}
			}
			cnt++;
		}
		System.out.println("KAKTUS");
	}
}
