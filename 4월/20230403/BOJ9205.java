package y2023.m04.d03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ9205 {
	/**
	 * 플루이드 워샬 사용.
	 * 각 건물들의 좌표 받은 후, 해당 좌표 기반으로 인접 행렬 그래프 생성
	 * 가중치는 두 건물 사이의 거리, 만약 1000이 넘는다면 max로 설정
	 * 플루이드 워샬을 이용해 배열 구한 후, max값 그대로라면 갈 수 없는 곳.
	 * */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str;
		int testCase = Integer.parseInt(br.readLine());
		int n, now;
		int[] arrI, arrK;
		boolean[] visited;
		boolean[][] arr;
		Queue<Integer> que = new LinkedList<Integer>();
		
		while(testCase-->0) {
			n = Integer.parseInt(br.readLine()) + 2;
			arrI = new int[n];
			arrK = new int[n];
			arr = new boolean[n][n];
			visited = new boolean[n];
			
			//각 건물 위치 입력받기
			for(int i = 0; i < n;i++) {
				str = br.readLine().split(" ");
				arrI[i] = Integer.parseInt(str[0]);
				arrK[i] = Integer.parseInt(str[1]);
			}
			
			//1000 이하라면 간선 생성
			for(int i = 0; i < n; i++) {
				for(int k=0; k < n; k++) {
					now = Math.abs(arrI[i]-arrI[k])+Math.abs(arrK[i]-arrK[k]);
					if(now < 1001)
						arr[i][k] = true;
				}
			}
			
			//BFS로 탐색하며 n-1 인덱스(락페)까지 갈 수 있는지 확인
			que.clear();
			que.add(0);
			visited[0] = true;
			while(!que.isEmpty()) {
				now = que.poll();
				for(int i = 0; i < n;i++) {
					if(arr[now][i]&&!visited[i]) {
						que.add(i);
						visited[i] = true;
					}
				}
				//갈 수 있을 때 break
				if(visited[n-1])
					break;
			}
			
			//결과 출력
			if(!que.isEmpty())
				sb.append("happy").append("\n");
			else
				sb.append("sad").append('\n');
		}
		System.out.println(sb);
	}

}
