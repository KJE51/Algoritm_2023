package y2023.m04.d03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA4193_userP {
	/**
	 * 4192에서 바뀐 점 : 소용돌이가 생긴다는 것 dfs를 이용하면 시간초과. bfs를 이용하기 만약 소용돌이가 존재할 시, 소용돌이를
	 * 통과할 수 있을 때까지 기다리기
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine()) + 1;
		StringBuilder sb = new StringBuilder();

		int size, startI, startK, endI, endK, cnt, queSize, nowI, nowK, newI, newK;
		int[][] arr;
		String[] str;
		Queue<Integer> queI = new LinkedList<Integer>();
		Queue<Integer> queK = new LinkedList<Integer>();
		int[] di = { 0, 0, 1, -1 };
		int[] dk = { 1, -1, 0, 0 };

		for (int tc = 1; tc < testCase; tc++) {
			size = Integer.parseInt(br.readLine());

			// 수영장 입력받기
			arr = new int[size][size];
			for (int i = 0; i < size; i++) {
				str = br.readLine().split(" ");
				for (int k = 0; k < size; k++) {
					arr[i][k] = Integer.parseInt(str[k]);
				}
			}

			// 시작, 끝 좌표 입력받기
			str = br.readLine().split(" ");
			startI = Integer.parseInt(str[0]);
			startK = Integer.parseInt(str[1]);
			str = br.readLine().split(" ");
			endI = Integer.parseInt(str[0]);
			endK = Integer.parseInt(str[1]);

			// BFS를 돌리기 위해 시작점을 que에 넣음
			queI.clear();
			queK.clear();
			queI.add(startI);
			queK.add(startK);

			cnt = 0;
			// visited를 쓰는 대신, 방문한 곳을 1로 변경
			arr[startI][startK] = 1;

			// BFS, 사방탐색
			while (!queI.isEmpty()) {
				queSize = queI.size();
				for (int i = 0; i < queSize; i++) {
					nowI = queI.poll();
					nowK = queK.poll();
					for (int d = 0; d < 4; d++) {
						newI = nowI + di[d];
						newK = nowK + dk[d];
						if (newI < 0 || newK < 0 || newI >= size || newK >= size || arr[newI][newK] == 1) {
							continue;
						}
						if (arr[newI][newK] == 0) {
							queI.add(newI);
							queK.add(newK);
							arr[newI][newK] = 1;
						}else {
							//소용돌이일 때
							if(cnt % 3 == 2) {
								queI.add(newI);
								queK.add(newK);
								arr[newI][newK] = 1;
							}else {
								queI.add(nowI);
								queK.add(nowK);
							}
						}
					}
					if (arr[endI][endK] == 1) {
						break;
					}
				}
				if (arr[endI][endK] == 1) {
					break;
				}
				cnt++;
			}
			
			cnt++;
			// 끝까지 도달하지 못했을 경우
			if (queI.isEmpty())
				cnt = -1;

			sb.append('#').append(tc).append(" ").append(cnt).append('\n');
		}
		System.out.println(sb);
	}

}
