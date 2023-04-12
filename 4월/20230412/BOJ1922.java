import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1922 {
	/**
	 * MST를 이용함. 간선 중심이기 때문에 간선을 이용한 크루스칼 알고리즘을 사용.
	 * 간선의 길이가 최소인 것을 뽑고, union find를 사용해 이미 연결되었는지 확인 후, 연결되지 않았을 시 연결.
	 * */
	static class Data implements Comparable<Data>{
		int a;
		int b;
		int len;

		public Data(int a, int b, int len) {
			super();
			//작은게 a가 될 수 있도록
			if (a > b) {
				this.a = b;
				this.b = a;
			} else {
				this.a = a;
				this.b = b;
			}
			this.len = len;
		}

		@Override
		public String toString() {
			return "Data [a=" + a + ", b=" + b + ", len=" + len + "]";
		}

		@Override
		public int compareTo(Data o) {
			return this.len - o.len;
		}
	}

	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int n1 = n + 1;
		int m = Integer.parseInt(br.readLine());

		PriorityQueue<Data> pq = new PriorityQueue<>();
		String[] str;
		for (int i = 0; i < m; i++) {
			str = br.readLine().split(" ");
			pq.add(new Data(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2])));
		}

		// union find를 위한 배열 초기화
		parents = new int[n1];
		for (int i = 1; i < n1; i++)
			parents[i] = i;
		
		Data tmpD;
		int ans = 0;
		while(!pq.isEmpty()) {
			tmpD = pq.poll();
			if(union(tmpD.a, tmpD.b)) {
				ans += tmpD.len;
			}
		}
		System.out.println(ans);
	}

	static boolean union(int a, int b) {
		int ap = findP(a);
		int bp = findP(b);
		
		//이미 같은 집합일 때
		if(ap == bp)
			return false;
		
		//union
		parents[bp] = ap;
		return true;
	}
	
	static int findP(int a) {
		int p = parents[a];
		if(p == a)
			return a;
		return parents[a] = findP(p);
	}
}
