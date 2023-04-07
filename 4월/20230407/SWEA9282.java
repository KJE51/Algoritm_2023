import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 초콜릿을 잘랐을 때, 두 조각의 건포도 차이가 최소가 되어야 함(최대한 균등한 건포도 갯수가 되도록 잘라야 함)
 * */
public class SWEA9282 {
	
	static int n, m, arr[][], ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine()) + 1;
		StringBuilder sb = new StringBuilder();
		String[] str;
		
		for(int tc = 1; tc < testCase; tc++) {
			sb.append('#').append(tc).append(" ");
			str = br.readLine().split(" ");
			n = Integer.parseInt(str[0]);
			m = Integer.parseInt(str[1]);
			
			int sum = 0;
			arr = new int[n][m];
			for(int i = 0; i < n; i++) {
				str = br.readLine().split(" ");
				for(int k = 0;k < m;k++) {
					arr[i][k] = Integer.parseInt(str[k]);
					sum += arr[i][k];
				}
			}
			
			//초콜릿 조각 시작-끝+1 i 인덱스, 시작-끝+1 k 인덱스, 현재 초콜릿 조각의 총 크기
			cut(0, n-1, 0, m-1, sum);
			
			ans = 0;
			sb.append(ans).append('\n');
		}
	}

	private static void cut(int startI, int endI, int startK, int endK, int sum) {
		//조각이 최소 크기만큼 쪼개졌을 때
		if(startI-endI == 1 && startK-endK==1) {
			return;
		}
		
		int sumI,sumK, tmp, tmpI, tmpK;
		sumI = sumK = sum;
		tmp = 0;
		//가로로 자르기
		//가로로 한줄씩 보면서 탐색
		for(int k = startK; k < endK; k++) {
			for(int i = startI; i <= endI; i++) {
				tmp += arr[i][k];
			}
			//차이의 최솟값이 갱신될 때
			if(Math.abs(sum - sumK-sumK) < Math.abs(sum - tmp - tmp)) {
				
			}
			
		}
		
		//세로로 자르기
		//세로로 한줄씩 보면서 탐색
		
	}

}
