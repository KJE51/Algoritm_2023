import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2636 {
	/**
	 * 가장 바깥쪽 테두리는 무조건 0, 바깥쪽 공기와 맞닿은 치즈는 한시간 후 녹음
	 * 가장 바깥쪽 임의의 한 정점에서 그래프 탐색(dfs, bfs 다 상관없을 것 같으나 bfs로 구현)
	 * BFS를 반복하며 가장 바깥쪽 치즈를 없애주는 연산을 반복
	 * */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int m = Integer.parseInt(str[1]);
		int[][] arr = new int[n][m];
		boolean[][] visited;
		int cnt, size, nowI, nowK, newI, newK, cheeseCnt = 0;

		// input 받기, 현재 치즈 숫자 받기
		for (int i = 0; i < n; i++) {
			str = br.readLine().split(" ");
			for (int k = 0; k < m; k++) {
				arr[i][k] = Integer.parseInt(str[k]);
				if (arr[i][k] == 1)
					cheeseCnt++;
			}
		}

		Queue<Integer> queI = new LinkedList<Integer>();
		Queue<Integer> queK = new LinkedList<Integer>();
		
		int[] di = {0, 0, 1, -1};
		int[] dk = {1, -1, 0, 0};
		
		//현재 시간에 줄어들 치즈 양
		size = 0;
		cnt = 0;
		while(cheeseCnt - size != 0) {
			visited = new boolean[n][m];
			queI.add(0);
			queI.add(n/2);
			while(!queI.isEmpty()) {
				
			}
			cnt++;
		}
		System.out.println(cnt);
		System.out.println(cheeseCnt);
	}
}
