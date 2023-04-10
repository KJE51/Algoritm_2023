import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ17136 {

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

		ans = 99;

		if (paperN < 3) {
			System.out.println(paperN);
			return;
		} else
			find(arr, 0, 0);

		if (ans == 99)
			ans = -1;

		System.out.println(ans);
	}

	private static void find(int[][] array, int idxI, int idxK) {
		int tmp= 25 - papers[1] - papers[2] - papers[3] - papers[4] - papers[5];
		if(tmp >= ans)
			return;
		
		for (int k = idxK; k < 10; k++) {
			if (array[idxI][k] == 1) {
				for (int c = 5; c >0; c--) {
					display(array, idxI, k, c);
				}
				return;
			}
		}

		for (int i = idxI + 1; i < 10; i++) {
			for (int k = 0; k < 10; k++) {
				if (array[i][k] == 1) {
					for (int c = 5; c >0; c--) {
						display(array, i, k, c);
					}
					return;
				}
			}
		}
				
		// 다 가렸을 때, ans
		ans = Math.min(ans, tmp);
	}

	private static void display(int[][] array, int idxI, int idxK, int sSize) {
		
		
		// 해당 색종이를 쓸 수 없을 때(다섯장을 다 썼을 때)
		if (papers[sSize] <1)
			return;
		
		int newI = idxI + sSize;
		int newK = idxK + sSize;
		
		if(newI > 10|| newK >10)
			return;

		// 색종이 깔 수 있는지 확인
		for (int i = idxI; i < newI; i++) {
			for (int k = idxK; k < newK; k++) {
				// 색종이를 깔 수 없을 때
				if (array[i][k] != 1)
					return;
			}
		}

		// 색종이 깔 수 있다면
		// 새 배열에 복사
		int[][] newArr = new int[10][10];
		for (int i = 0; i < 10; i++) {
			for (int k = 0; k < 10; k++) {
				newArr[i][k] = array[i][k];
			}
		}

		for (int i = idxI; i < newI; i++) {
			for (int k = idxK; k < newK; k++) {
				newArr[i][k] = sSize+1;
			}
		}
		
		papers[sSize] = papers[sSize] - 1;
		find(newArr, idxI, idxK+sSize);
		papers[sSize] = papers[sSize] + 1;
	}
}
