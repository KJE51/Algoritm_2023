import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ14502 {
	static int n=7, m=7, ans=0;
	static int[][] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		arr = new int[n][m];

		//연구소 지도 만들기
		for(int i = 0; i < n; i++) {
			str = br.readLine().split(" ");
			for(int k = 0; k < m; k++) {
				arr[i][k] = Integer.parseInt(str[k]);
			}
		}
		
		ans = 0;
		//현재 좌표 / 만들어진 벽의 갯수
		makeWall(0, 0, 0);
		System.out.println(ans);
	}

	private static void makeWall(int nowI, int nowK, int cnt) {
		if(cnt == 3) {
			ans = Math.max(ans, count());
			return;
		}
		for(int k = nowK; k<m;k++) {
			if(arr[nowI][k] == 0) {
				arr[nowI][k] = 1;
				makeWall(nowI, k, cnt+1);
				arr[nowI][k] = 0;
			}
		}
		
		for(int i = nowI+1; i < n; i++) {
			for(int k = 0; k < m; k++) {
				if(arr[i][k] == 0) {
					arr[i][k] = 1;
					makeWall(i, k, cnt+1);
					arr[i][k] = 0;
				}
			}
		}
	}

	//안전구역 크기 세기
	private static int count() {
		boolean[][] visited = new boolean[n][m];
		//bfs
		int nowI, nowK, newI, newK,tmp1, tmp = 0;
		int[] di = {0, 0, 1, -1};
		int[] dk = {1, -1, 0, 0};
		boolean flag;
		Queue<Integer> queI = new LinkedList<Integer>();
		Queue<Integer> queK = new LinkedList<Integer>();
		
		for(int i = 0; i < n; i++) {
			for(int k= 0; k < m; k++) {
				if(arr[i][k] != 1 && !visited[i][k]) {
					flag = false;
					if(arr[i][k] == 2)
						flag = true;
					tmp1= 1;
					queI.add(i);
					queK.add(k);
					visited[i][k] = true;
					while(!queI.isEmpty()) {
						nowI = queI.poll();
						nowK = queK.poll();
						for(int d = 0; d < 4; d++) {
							newI = nowI + di[d];
							newK = nowK + dk[d];
							//범위 벗어나거나, 벽이거나, 이미 탐색했을 때
							if(newI<0||newK<0||newI>=n||newK>=m||arr[newI][newK] == 1||visited[newI][newK]) {
								continue;
							}
							visited[newI][newK] = true;
							queI.add(newI);
							queK.add(newK);
							tmp1++;
							if(arr[newI][newK] == 2)
								flag = true;
						}
					}
					if(!flag) {
						tmp = tmp + tmp1;
				}}
			}
		}
		return tmp;
	}

}
