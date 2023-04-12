import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class SWEA3124 {
	/**
	 * 프림 알고리즘 사용
	 * */

	static class Data implements Comparable<Data>{
		int vertex;
		int len;
		public Data(int vertex, int len) {
			super();
			this.vertex = vertex;
			this.len = len;
		}
		@Override
		public String toString() {
			return "Data [vertex=" + vertex + ", len=" + len + "]";
		}
		@Override
		public int compareTo(Data o) {
			return this.len - o.len;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine()) + 1;
		String[] str;
		StringBuilder sb = new StringBuilder();
		int v, v1, e, a, b, len, cnt;
		long ans;
		boolean[] visited;
		ArrayList<Data>[] arr;
		PriorityQueue<Data> pq = new PriorityQueue<>();
		
		for(int tc = 1; tc < testCase;tc++) {
			sb.append('#').append(tc).append(" ");
			ans = 0;
			str = br.readLine().split(" ");
			v = Integer.parseInt(str[0]);
			v1 = v + 1;
			e = Integer.parseInt(str[1]);
			arr = new ArrayList[v1];
			for(int i = 1; i < v1; i++)
				arr[i] = new ArrayList<>();
			
			for(int i = 0; i < e; i++) {
				str = br.readLine().split(" ");
				a = Integer.parseInt(str[0]);
				b = Integer.parseInt(str[1]);
				len = Integer.parseInt(str[2]);
				arr[a].add(new Data(b, len));
				arr[b].add(new Data(a, len));
			}
			
			//현재 방문한 정점 수 체크 , 1번만 방문한 상태
			cnt = 1;
			visited = new boolean[v1];
			visited[1] = true;
			//1번 정점부터 시작
			for(int i = 0; i < arr[1].size(); i++) {
				pq.add(arr[1].get(i));
			}
			
			Data tmp;
			int now;
			while(!pq.isEmpty() && cnt < v) {
				tmp = pq.poll();
				now = tmp.vertex;
				
				//이미 방문한 적 있을 때
				if(visited[now])
					continue;
				
				//방문한 적 없을 때
				//길이 더해주기, 방문체크, cnt
				cnt++;
				ans += tmp.len;
				visited[now] = true;
				
				for(int i = 0; i < arr[now].size(); i++) {
					// 방문한 적 없고 간선 존재할 때
					if(!visited[arr[now].get(i).vertex])
						pq.add(arr[now].get(i));
				}
			}
			
			pq.clear();
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}

}
