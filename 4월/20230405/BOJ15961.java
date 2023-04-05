import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15961 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int d = Integer.parseInt(str[1]);
		int k = Integer.parseInt(str[2]);
		int c = Integer.parseInt(str[3]);
		// 초밥 접시 입력받을 배열
		int[] arr = new int[n];

		// 초밥 입력받기
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(br.readLine());

		// 현재 먹을 수 있는 초밥의 수 종류 표시할 변수
		int cnt = 0;
		// 현재 먹고있는 초밥 표시할 배열
		int[] now = new int[d + 1];

		// 처음 초기값 입력해주기.
		for (int i = 0; i < k; i++) {
			if (now[arr[i]] == 0) {
				cnt++;
			}
			now[arr[i]]++;
		}

		// 최댓값 저장할 변수
		int max = cnt;

		// 쿠폰으로 먹을 수 있다면 하나 더 먹기
		if (now[c] == 0)
			max = max + 1;

		int nk = n + k;
		int n_1 = n - 1;
		int minus, plus;
		int bonus = 0;
		
		// 현재 값에서 하나 더하고 처음꺼 하나 빼면서 계산
		for (int i = k; i < nk; i++) {
			minus = i - k;
			plus = i;
			
			// 맨 뒤 초밥이 인덱스 벗어났을 때 : 되돌려줌
			if (plus > n_1)
				plus = plus - n;
			
			// 맨 앞 초밥 뺄 때 현재 있는 갯수에서 빼주고, 해당 초밥 갯수가 하나도 안 남았다면 가짓수에서도 빼주기
			now[arr[minus]]--;
			if (now[arr[minus]] == 0) {
				cnt--;
			}
			
			// 초밥 더하기. 현재 먹는 초밥 배열과 가짓수 수정
			now[arr[plus]]++;
			if(now[arr[plus]] == 1)
				cnt++;
			
			// 보너스 초밥
			if(now[c] == 0)
				bonus = 1;
			else
				bonus = 0;
			
			// 최댓값 갱신될 때
			if(max < cnt + bonus) {
				max = cnt + bonus;
				// 모든 가짓수를 다 먹거나, 접시 + 1가지를 먹을 때
				if(max == d || max == k+1) {
					break;
				}
			}
		}
		System.out.println(max);
	}

}
