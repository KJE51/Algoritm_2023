import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA6782 {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine()) + 1;
		long n, tmp;
		int ans;
		StringBuilder sb = new StringBuilder();


		for (int tc = 1; tc < testCase; tc++) {
			n = Long.parseLong(br.readLine());
			sb.append("#").append(tc).append(" ");
			ans = 0;
			while(n != 2) {
				tmp = (long) Math.sqrt(n);
				// 제곱근이라면
				if(tmp * tmp == n) {
					// n에 루트를 씌워 변화시키기
					n = tmp;
					ans++;
				}else {
					// 현재 n이 tmp^2가 아니라면, n 다음의 제곱수는 (tmp+1)^2
					// (tmp+1)^2까지 필요한 횟수 더한 후, n 갱신
					ans += (tmp+1)*(tmp+1) - n;
					n = tmp + 1;
					ans++;
				}
			}
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}

}