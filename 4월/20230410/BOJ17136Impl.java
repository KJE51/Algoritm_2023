import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ17136Impl {

	static int[][] arr = new int[10][10];
	static int paperN, ans;
	static int[] papers = { 0, 5, 5, 5, 5, 5 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		paperN = 0;

		for (int i = 0; i < 10; i++) {
			str = br.readLine().split(" ");
			for (int k = 0; k < 10; k++) {
				arr[i][k] = Integer.parseInt(str[k]);
				if (arr[i][k] == 1)
					paperN++;
			}
		}

		//정답은 임의의 큰 수로 둠
		ans = 99;

		if (paperN < 3) {
			System.out.println(paperN);
			return;
		} else {
			display();
		}

		if (ans == 99)
			ans = -1;

		System.out.println(ans);
	}

	private static void display() {
		//백트레킹, 색종이 다 썼을 때
		int tmp= 25 - papers[1] - papers[2] - papers[3] - papers[4] - papers[5];
		if(tmp >= ans)
			return;
		
		
		boolean flag = true;
		
		//배열 탐색하며 1인 곳 찾기
		for(int i = 0; i < 10; i++) {
			for(int k = 0; k < 10; k++) {
				
				//1인 곳 발견시 색종이 큰 것부터 두기
				if(arr[i][k] == 1) {
					
					for(int c = 5; c > 0; c--) {
						//색종이 모자랄땐 넘어가기
						if(papers[c] == 0)
							continue;
						//범위 초과할 때
						if(i + c > 10 || k + c > 10)
							continue;
						
						//색종이 하나씩 놓아보기
						flag = true;
						for(int i1 = 0; i1 < c; i1++) {
							for(int k1 = 0; k1 < c; k1++) {
								if(arr[i+i1][k+k1] != 1) {
									flag = false;
									break;
								}
							}
							if(!flag)
								break;
						}
						
						
						//색종이 놓을 수 있을 때
						if(flag) {
							//색종이 배치, 갯수 조절
							for(int i1 = 0; i1 < c; i1++) {
								for(int k1 = 0; k1 < c; k1++) {
									arr[i+i1][k+k1] = 2;
								}
							}
							papers[c]--;
							//dfs
							display();
							//맵 되돌리기, 갯수 조절
							papers[c]++;
							for(int i1 = 0; i1 < c; i1++) {
								for(int k1 = 0; k1 < c; k1++) {
									arr[i+i1][k+k1] = 1;
								}
							}
						}
					}
					return;
				}
			}
		}
		
		// 다 가렸을 때, ans
		ans = Math.min(ans, tmp);
	}
}
