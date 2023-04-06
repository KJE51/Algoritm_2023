import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//소스코드 보고 푼 것 - 내 코드로 다시 제출해보기
public class BOJ2098 {

	static int n, dist[][], dp[][], min, INF = Integer.MAX_VALUE / 100;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(br.readLine());
		dist = new int[n][n];
		for (int i = 0; i < n; i++) {
			str = br.readLine().split(" ");
			for (int k = 0; k < n; k++) {
				dist[i][k] = Integer.parseInt(str[k]);
			}
		}
		
		dp = new int[1<<n][n];
		for(int i = 0; i < 1<<n; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		//visited 체크할 정수, 현재 탐색중인 도시
		min = tsp(1, 0);
		System.out.println(min);
	}

	private static int tsp(int visited, int city) {
		//끝까지 왔을 때
		if(visited == ((1<<n)-1)) {
			//마지막 도시에서 도착지로 가는 길 없을 때 
			if(dist[city][0] == 0)
				return INF;
			return dist[city][0];
		}
		
		// 만약 이미 계산했었을 때
		if(dp[visited][city] != -1) {
			return dp[visited][city];
		}
		
		// 최댓값으로 바꿔줌
		dp[visited][city] = INF;
		
		for(int i = 0; i < n; i++) {
			//방문한 적 있을 때
			if((visited & (1<<i))!= 0)
				continue;
			//도시가 연결 안 될 때
			if(dist[city][i] == 0)
				continue;
			
			int res = tsp(visited | (1<<i), i) + dist[city][i];
			dp[visited][city] = Math.min(res, dp[visited][city]);
		}
		return dp[visited][city];
	}

}
