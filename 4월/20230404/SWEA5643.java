import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA5643 {
	/**
	 * 해당 노드와 연결된 노드의 갯수가 n-1개일 때 순서를 알 수 있음
	 * */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine())+1;
		String[] str;
		StringBuilder sb = new StringBuilder();
		
		int n, n1, m, a, b, ans, size, now, newN, tmp;
		LinkedList<Integer>[] graph;
		LinkedList<Integer>[] graphB;
		Queue<Integer> que = new LinkedList<>();
		boolean[] visited;
		
		for(int tc = 1; tc < testCase; tc++) {
			n = Integer.parseInt(br.readLine());
			n1 = n +1;
			m = Integer.parseInt(br.readLine());
			ans = 0;
			
			//인접 리스트를 통해 그래프 구현
			graph = new LinkedList[n+1];
			graphB = new LinkedList[n+1];
			for(int i = 1; i < n1; i++) {
				graph[i] = new LinkedList<>();
				graphB[i] = new LinkedList<>();
			}
			
			for(int i = 0; i < m ;i++) {
				str = br.readLine().split(" ");
				a = Integer.parseInt(str[0]);
				b = Integer.parseInt(str[1]);
				graph[a].add(b);
				graphB[b].add(a);
			}
			
			visited = new boolean[n+1];
			//각 노드마다 그래프 탐색을 돌려서 만약 모든 노드를 탐색할 수 있다면 ans에 추가
			for(int i = 1; i < n1; i++) {
				tmp = 1;
				//정방향 탐색
				que.add(i);
				visited[i] = true;
				while(!que.isEmpty()) {
					now = que.poll();
					size = graph[now].size();
					for(int k = 0; k < size; k++) {
						newN = graph[now].get(k);
						if(!visited[newN]) {
							visited[newN] = true;
							que.add(newN);
							tmp++;
						}
					}
				}
				
				//역방향 탐색
				que.add(i);
				while(!que.isEmpty()) {
					now = que.poll();
					size = graphB[now].size();
					for(int k = 0; k < size; k++) {
						newN = graphB[now].get(k);
						if(!visited[newN]) {
							visited[newN] = true;
							que.add(newN);
							tmp++;
						}
					}
				}
//				System.out.println(Arrays.toString(visited));
				if(tmp == n)
					ans++;
				for(int t = 0; t < n1; t++)
					visited[t] = false;
			}
			
			
			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}

}
