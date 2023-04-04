import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2146 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] str;
		int[][] arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			str = br.readLine().split(" ");
			for (int k = 0; k < n; k++) {
				arr[i][k] = Integer.parseInt(str[k]);
			}
		}

		// BFS를 사용해 섬에 번호를 매긴 후, arr에 표시된 섬 번호 이름 바꿈
		// 편의상 번호는 2부터 시작함
		Queue<Integer> queI = new LinkedList<Integer>();
		Queue<Integer> queK = new LinkedList<Integer>();
		int[] di = { 0, 0, 1, -1 };
		int[] dk = { 1, -1, 0, 0 };

		int nowI, nowK, newI, newK, islandN = 2;
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				// 섬에 번호가 없을 시, 번호 매김
				if (arr[i][k] == 1) {
					arr[i][k] = islandN;
					queI.add(i);
					queK.add(k);
					while (!queI.isEmpty()) {
						nowI = queI.poll();
						nowK = queK.poll();
						for (int d = 0; d < 4; d++) {
							newI = nowI + di[d];
							newK = nowK + dk[d];
							if (newI < 0 || newK < 0 || newI >= n || newK >= n || arr[newI][newK] != 1) {
								continue;
							}
							queI.add(newI);
							queK.add(newK);
							arr[newI][newK] = islandN;
						}
					}
					islandN++;
				}
			}
		}

//		for(int i = 0; i < n; i++)
//			System.out.println(Arrays.toString(arr[i]));

		int startIsland, cnt, size, ans = 999999;
		boolean flag = true;
		boolean[][] visited = new boolean[n][n];
		boolean[][] visitedBFS;

		// 다리를 시작할 수 있는 좌표를 모두 찾아서 그 자리에서 BFS를 통해 얼마나 긴 다리를 만들 수 있는지 확인.
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				if (arr[i][k] != 0) {
					flag = true;
					for (int d = 0; d < 4; d++) {
						newI = i + di[d];
						newK = k + dk[d];
						// 범위를 벗어나거나, 이미 탐색했을 때
						if (newI < 0 || newK < 0 || newI >= n || newK >= n || visited[newI][newK]
								|| arr[newI][newK] != 0) {
							continue;
						}

						// 시작하는 섬 저장
						startIsland = arr[i][k];
						visited[newI][newK] = true;
						visitedBFS = new boolean[n][n];
						visitedBFS[newI][newK] = true;

						// BFS 시작
						queI.add(newI);
						queK.add(newK);
						cnt = 0;
						while (!queI.isEmpty()) {
							size = queI.size();
							cnt++;
							while (size-- > 0) {
								nowI = queI.poll();
								nowK = queK.poll();
								for (int d1 = 0; d1 < 4; d1++) {
									newI = nowI + di[d1];
									newK = nowK + dk[d1];
									if (newI < 0 || newK < 0 || newI >= n || newK >= n || visitedBFS[newI][newK]) {
										continue;
									}
									// 바다일 때는 큐에 넣기
									if (arr[newI][newK] == 0) {
										queI.add(newI);
										queK.add(newK);
										visitedBFS[newI][newK] = true;
									}
									// 다른 섬에 도착했을 때, ans 바꾼 후 break;
									else if (arr[newI][newK] != startIsland) {
										ans = Math.min(cnt, ans);
										break;
									}
								}
								if (cnt >= ans)
									break;
							}
							if (cnt >= ans)
								break;
						}

						queI.clear();
						queK.clear();

					}
				}
			}
		}

		System.out.println(ans);

	}

}
