import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ4485 {

	static class Data implements Comparable<Data> {
		int idxI;
		int idxK;
		int dis;
		public Data(int idxI, int idxK, int dis) {
			super();
			this.idxI = idxI;
			this.idxK = idxK;
			this.dis = dis;
		}
		@Override
		public int compareTo(Data o) {
			return this.dis - o.dis;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n, nowI, nowK, newI, newK, arr[][], distance[][], cnt = 1;
		boolean[][] visited;
		PriorityQueue<Data> pq = new PriorityQueue<>();
		String[] str;
		Data data;
		int[] di = { 0, 0, 1, -1 };
		int[] dk = { 1, -1, 0, 0 };

		while (true) {
			n = Integer.parseInt(br.readLine());
			if (n == 0)
				break;

			// 인접 행렬 그래프 만들기
			arr = new int[n][n];
			for (int i = 0; i < n; i++) {
				str = br.readLine().split(" ");
				for (int k = 0; k < n; k++) {
					arr[i][k] = Integer.parseInt(str[k]);
				}
			}

			distance = new int[n][n];
			visited = new boolean[n][n];

			for (int i = 0; i < n; i++) {
				for (int k = 0; k < n; k++) {
					distance[i][k] = 9999;
				}
			}
			
			//출발지 넣기
			pq.add(new Data(0, 0, arr[0][0]));
			distance[0][0] = arr[0][0];
			
			//다익스트라를 사용해 distance 업데이트 하기
			while (!pq.isEmpty()) {
				data = pq.poll();
				nowI = data.idxI;
				nowK = data.idxK;
				
				if(visited[nowI][nowK])
					continue;
				
				//visited를 탐색할 때 한다는 것 주의!!
				visited[nowI][nowK] = true;
				
				for(int d = 0; d < 4; d++) {
					newI = nowI+di[d];
					newK = nowK+dk[d];
					if(newI<0||newK<0||newI>=n||newK>=n||visited[newI][newK])
						continue;
					if(distance[newI][newK] > distance[nowI][nowK]+arr[newI][newK]) {
						//넣을 때 visited 체크 안하는 것 주의
						distance[newI][newK] = distance[nowI][nowK]+arr[newI][newK];
						pq.add(new Data(newI, newK, distance[newI][newK]));
					}
				}
			}
			
			
			sb.append("Problem ").append(cnt).append(": ").append(distance[n-1][n-1]).append('\n');
			cnt++;
		}
		System.out.println(sb);
	}

}
