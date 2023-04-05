import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA5643 {
	/**
	 * 해당 노드와 연결된 노드의 갯수가 n-1개일 때 순서를 알 수 있음
	 * 플루이드 워샬을 사용해 본인과 해당 노드가 연결되어있는지 확인 가능
	 * */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine())+1;
		String[] str;
		StringBuilder sb = new StringBuilder();
		
		int n, n1, m, a, b, ans;
		int[][] arr;
		
		for(int tc = 1; tc < testCase; tc++) {
			n = Integer.parseInt(br.readLine());
			n1 = n +1;
			m = Integer.parseInt(br.readLine());
			
			//인접 행렬을 통해 그래프 구현
			arr = new int[n1][n1];
			
			//그래프 초기화 - 모든 간선 최댓값으로, i==k일 시는 0
			for(int i = 1; i < n1; i++) {
				for(int k = 1; k < n1; k++) {
					if(i!= k)
						arr[i][k] = 9999;
				}
			}
			
			//입력받기
			for(int i = 0; i < m; i++) {
				str = br.readLine().split(" ");
				a = Integer.parseInt(str[0]);
				b = Integer.parseInt(str[1]);
				arr[a][b] = 1;
			}
			
			//플로이드 워샬을 통해 구현
			//경유지, 출발, 도착 순서
			for(int mid = 1; mid < n1; mid++) {
				for(int s = 1; s < n1; s++) {
					for(int e = 1; e < n1; e++) {
						arr[s][e] = Math.min(arr[s][e], arr[s][mid]+arr[mid][e]);
					}
				}
			}
			
			ans = 0;
			boolean flag = true;
			//플루이드 워샬 배열에서 나와 해당 노드가 이어질 수 없을 때는 순서를 알 수 없음
			for(int i = 1; i < n1; i++) {
				flag = true;
				for(int k = 1; k < n1; k++) {
					if(arr[i][k] == 9999 && arr[k][i] == 9999) {
						flag = false;
						break;
					}
				}
				if(flag)
					ans++;
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}

}
