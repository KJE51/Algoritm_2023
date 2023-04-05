import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA4014 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine()) + 1;
		StringBuilder sb = new StringBuilder();
		String[] str;
		int n, x, arr[][], ans, pre, tmp;
		boolean flag;

		for (int tc = 1; tc < testCase; tc++) {
			str = br.readLine().split(" ");
			n = Integer.parseInt(str[0]);
			x = Integer.parseInt(str[1]);
			arr = new int[n][n];

			for (int i = 0; i < n; i++) {
				str = br.readLine().split(" ");
				for (int k = 0; k < n; k++) {
					arr[i][k] = Integer.parseInt(str[k]);
				}
			}

			ans = 0;

			// 가로방향 활주로 가능한 횟수
			for (int i = 0; i < n; i++) {
				flag = true;
				// 만약 여기까지 경사로를 놓을 때, 놓을 수 있는 길이
				pre = 1;
				for (int k = 1; k < n; k++) {
					tmp = arr[i][k] - arr[i][k - 1];
					if (tmp == 0) {
						pre++;
					}

					// 이전이랑 틀릴 때
					else {
						// 1 이상 차이나서 경사로 세우지 못할 때
						if (tmp < -1 || tmp > 1) {
							flag = false;
							break;
						}

						// 왼쪽이 더 높을때
						// 오른쪽으로 하나씩 진행시켜나가며 경사로를 놓을 수 있는지 확인
						if (tmp < 0) {
							pre = 1;
							k++;
							// 마지막 끝 인덱스에서 true로 두고 하면 오류남
							flag = false;
							while (k < n) {
								if (arr[i][k] == arr[i][k - 1]) {
									pre++;
									if (pre == x) {
										flag = true;
										break;
									}
								} else {
									break;
								}
								k++;
							}
							pre = 0;
						}

						// 오른쪽이 더 높을때
						// 지금까지 경사로를 놓을 수 있는 길이 확인
						else {
							if (pre < x) {
								flag = false;
								break;
							}
							pre = 1;
						}
						if (!flag)
							break;
					}
				}
				if (flag) {
					ans++;
				}
			}
			// 세로방향 활주로 가능한 횟수
			for (int i = 0; i < n; i++) {
				flag = true;
				pre = 1;
				for (int k = 1; k < n; k++) {
					tmp = arr[k][i] - arr[k - 1][i];
					if (tmp == 0) {
						pre++;
					}

					// 이전이랑 틀릴 때
					else {
						// 1 이상 차이나서 경사로 세우지 못할 때
						if (tmp < -1 || tmp > 1) {
							flag = false;
							break;
						}

						// 위가 더 높을때
						if (tmp < 0) {
							pre = 1;
							k++;
							// 마지막 끝 인덱스에서 true로 두고 하면 오류남
							flag = false;
							while (k < n) {
								if (arr[k][i] == arr[k - 1][i]) {
									pre++;
									if (pre == x) {
										flag = true;
										break;
									}
								} else {
									break;
								}
								k++;
							}
							pre = 0;
						}

						// 아래가 더 높을때
						else {
							if (pre < x) {
								flag = false;
								break;
							}
							pre = 1;
						}
						if (!flag)
							break;
					}
				}
				if (flag) {
					ans++;
				}
			}
			sb.append('#').append(tc).append(" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}
}
