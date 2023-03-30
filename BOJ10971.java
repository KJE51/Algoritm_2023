import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ10971 {
	static int n;
	static int[][] arr;
	static int ans = Integer.MAX_VALUE;
	static boolean[] visited;

	/**
	 * dfs를 통해 루트를 하나씩 탐색해나가며 가장 cost가 작은 루트 찾기
	 * */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		String[] str;
		
		//각 경로별 비용을 저장할 배열
		arr = new int[n][n];
		//방문 여부를 체크할 배열
		visited = new boolean[n];
		
		//경로별 비용 입력받기
		for(int i = 0; i < n; i++) {
			str = br.readLine().split(" ");
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		//어느 지점에서 출발하든 결과에 변화 없음->편의상 0번째 도시에서 시작하기 위해 visited 처리
		visited[0] = true;
		//dfs(현재 도시, 거쳐간 도시 수, 현재 비용)
		//현재 도시도 방문했다고 가정하고 도시 수는 1로 시작
		dfs(0, 1, 0);
		
		//전역변수를 사용해 cost 저장
		System.out.println(ans);
	}

	private static void dfs(int idx, int cnt, int cost) {
		
		//기저조건, n개의 도시를 다 탐색했을 시, 탐색 종료
		if(cnt == n) {
			//조건 주의, 마지막에 탐색한 도시에서 0번 도시까지 가는 길이 있어야 함
			if(arr[idx][0] != 0) {
			ans = Math.min(ans, cost+arr[idx][0]);}
			return;
		}
		
		//cost 이용해 백트래킹.
		//지금까지의 최소값이 현재 cost보다 작다면 앞으로의 탐색이 의미가 없음->종료
		if(ans < cost) {
			return;
		}
		
		//visited 배열을 탐색하며 탐색을 하지 않았고, 길이 존재하는 곳에서 다시 dfs를 돌림
		for(int i = 0; i < n; i++) {
			if(!visited[i]&& arr[idx][i] != 0) {
				visited[i] = true;
				dfs(i, cnt+1, cost + arr[idx][i]);
				visited[i] = false;
			}
		}
	}
}
