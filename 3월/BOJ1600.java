import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1600 {
	static class Data {
		int x;
		int y;
		int cnt;
		int k;
		public Data(int x, int y, int cnt, int k) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.k = k;
		}
		@Override
		public String toString() {
			return "Data [x=" + x + ", y=" + y + ", cnt=" + cnt + ", k=" + k + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");
		int sizeK = Integer.parseInt(str[0]);
		int sizeI = Integer.parseInt(str[1]);
		
		//3차원 visited
		boolean[][][] visited = new boolean[cnt+1][sizeI][sizeK];

		//장애물 위치 arr에 입력받기
		boolean[][] arr = new boolean[sizeI][sizeK];
		for (int i = 0; i < sizeI; i++) {
			str = br.readLine().split(" ");
			for (int k = 0; k < sizeK; k++) {
				if (str[k].equals("1"))
					arr[i][k] = true;
			}
		}
		//주의
		if(sizeK == sizeI && sizeK == 1) {
			System.out.println("0");
			return;
		}

		Queue<Data> que = new LinkedList<>();

		que.add(new Data(0, 0, 0, 0));
		visited[0][0][0] = true;

		Data tmp;
		int result = -1;
		int newI, newK;
		int[] di = { 0, 0, 1, -1 };
		int[] dk = { 1, -1, 0, 0 };
		int[] dki = { -2, -2, -1, -1, 1, 1, 2, 2 };
		int[] dkk = { 1, -1, -2, 2, -2, 2, 1, -1 };
		while (!que.isEmpty()) {
			tmp = que.poll();
//			System.out.println("que size : " + que.size() + " 현재 탐색 : "+tmp);
			// 사방탐색
			for (int i = 0; i < 4; i++) {
				newI = tmp.x + di[i];
				newK = tmp.y + dk[i];
				if (newI == sizeI - 1 && newK == sizeK - 1) {
					result = tmp.cnt + 1;
					break;
				}
				if (newI < 0 || newK < 0 || newI >= sizeI || newK >= sizeK || arr[newI][newK] || visited[tmp.k][newI][newK]) {
					continue;
				}
//				System.out.println("new : "+newI+","+newK);
				visited[tmp.k][newI][newK] = true;
				que.add(new Data(newI, newK, tmp.cnt + 1, tmp.k));

			}
			if (result != -1)
				break;
			
			// 뛰어넘기
			if (tmp.k <cnt) {
				for (int i = 0; i < 8; i++) {
					newI = tmp.x + dki[i];
					newK = tmp.y + dkk[i];
					if (newI == sizeI - 1 && newK == sizeK - 1) {
						result = tmp.cnt + 1;
						break;
					}
					if (newI < 0 || newK < 0 || newI >= sizeI || newK >= sizeK || arr[newI][newK] || visited[tmp.k+1][newI][newK]) {
						continue;
					}
//				System.out.println("new : "+newI+","+newK);
					visited[tmp.k+1][newI][newK] = true;
					que.add(new Data(newI, newK, tmp.cnt + 1, tmp.k + 1));
				}
			}

			if (result != -1)
				break;
		}
		System.out.println(result);
	}

}
