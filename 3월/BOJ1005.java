import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1005 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int testCase= Integer.parseInt(str[0]);
		int n, k, x, y, last, tmp, idx;
		int[] time, timeAll, inCnt;
		ArrayList<Integer>[] graph;
		Queue<Integer> que = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		
		while(testCase-->0) {
			str = br.readLine().split(" ");
			//건물의 수와 건물 간선 수 입력
			n = Integer.parseInt(str[0]);
			k = Integer.parseInt(str[1]);
			time = new int[n+1];
			timeAll = new int[n+1];
			inCnt = new int[n+1];
			
			//각 건물당 건설시간 받기
			str = br.readLine().split(" ");
			for(int i = 0; i < n; i++) {
				time[i+1] = Integer.parseInt(str[i]);
			}
			
			//그래프 만들기
			graph = new ArrayList[n+1];
			for(int i = 1; i <= n; i++) {
				graph[i] = new ArrayList<>();
			}
			for(int i = 0; i < k; i++) {
				str = br.readLine().split(" ");
				x = Integer.parseInt(str[0]);
				y = Integer.parseInt(str[1]);
				graph[x].add(y);
				inCnt[y]++;
			}
			
			//마지막에 짓는 건물 입력받기
			last = Integer.parseInt(br.readLine());
			if(inCnt[last] == 0) {
//				System.out.println(time[last]);
				sb.append(time[last]).append('\n');
				continue;
			}
			
			que.clear();
			//진입차수가 0인 것들 que에 넣은 후, 걸리는 시간 수정
			for(int i = 1; i <= n; i++) {
				if(inCnt[i] == 0) {
					que.add(i);
					timeAll[i] = time[i];
				}
			}
			
			//위상정렬
			while(!que.isEmpty() || inCnt[last] != 0) {
				tmp = que.poll();
				//현재 간선들 따라가면서 총 걸리는 시간 수정
				for(int i = 0; i < graph[tmp].size();i++) {
					//현재까지 걸리는 시간 수정
					idx = graph[tmp].get(i);
					timeAll[idx] = Math.max(timeAll[idx], timeAll[tmp]+time[idx]);
					inCnt[idx]--;
					//진입차수 0일 때 큐에 추가
					if(inCnt[idx] == 0) {
						que.add(idx);
					}
				}
			}
			
			sb.append(timeAll[last]).append('\n');			
		}
		System.out.println(sb);
	}
}
