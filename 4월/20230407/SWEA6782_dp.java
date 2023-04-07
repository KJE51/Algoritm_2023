import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA6782_dp {

	// 인덱스 : 루트n, arr[][0] : 제곱값, arr[][1] : 해당 제곱값에서 2를 만들기까지 필요한 횟수(DP)
	static long[][] arr = new long[1000002][2];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine()) + 1;
		long n;
		StringBuilder sb = new StringBuilder();

		// arr에 제곱수를 채워넣음
		for (long i = 2; i < 1000002; i++) {
			arr[(int) i][0] = i * i;
		}

		for (int tc = 1; tc < testCase; tc++) {
			n = Long.parseLong(br.readLine());
			sb.append("#").append(tc).append(" ");
			
			//2를 입력받았을 경우, 바로 return
			if (n == 2) {
				sb.append(0).append('\n');
				continue;
			}
			
			// count 함수를 통해 횟수 찾기
			long ans = count(n);

			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}

	// n을 입력받았을 때, n보다 크거나 작은 제곱수의 루트값을 반환하는 함수
	private static int findN(long n) {
		int left = 2;
		int right = 1000001;
		int mid = 0;
		long tmp;
		
		// 2를 return 해야 할 때만 예외처리
		if (n == 3 || n == 4)
			return 2;
		
		//이분탐색을 통해 찾는다.
		while (right - left > 1) {
			mid = (left + right) / 2;
			tmp = arr[mid][0];
			if (tmp == n || mid == 1000001) {
				return mid;
			}

			// 더 오른쪽에서 찾기
			if (tmp < n) {
				left = mid;
			} else {
				right = mid;
			}
			
			// 더 큰 수일 때 return
			if (tmp < n && arr[mid + 1][0] > n)
				return mid + 1;
			if (tmp > n && arr[mid - 1][0] < n) {
				return mid;
			}
		}
		return 0;
	}

	// n이 2가 될 때까지 필요한 횟수를 return
	static long count(long n) {
		// n == 2, 0 리턴
		if (n == 2) {
			return 0L;
		}

		// n보다 크거나 같은 제곱수의 루트값 반환
		int idx = findN(n);

		// 저장된 dp값이 없을 때
		if (arr[idx][1] == 0) {
			// 루트값에서 해당 값까지 걸리는 시간 탐색 후 출력
			arr[idx][1] = count(idx) + 1;
			return arr[idx][1] - n + arr[idx][0];
		}

		// 저장된 dp 값이 있을 때
		else {
			// 해당 제곱수에서 갈 수 있는 횟수 + 제곱수-현재수 (+1을 통해 이동해야 하는 수)
			return arr[idx][1] - n + arr[idx][0];
		}
	}
}