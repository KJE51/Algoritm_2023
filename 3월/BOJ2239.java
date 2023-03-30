import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ2239 {
	static int listSize, zeroCnt = 0;
	static int[][] arr;
	static ArrayList<Integer> zeroI = new ArrayList<>();
	static ArrayList<Integer> zeroK = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[9][9];
		String str;
		// 스도쿠 판 입력받기, 채워야 할 숫자는 zeroCnt로 몇개인지 기록
		for (int i = 0; i < 9; i++) {
			str = br.readLine();
			for (int k = 0; k < 9; k++) {
				arr[i][k] = (int) (str.charAt(k)) - '0';
				if (arr[i][k] == 0) {
					zeroI.add(i);
					zeroK.add(k);
				}
			}
		}

		listSize = zeroI.size();

		// 현재 탐색해야하는 리스트의 인덱스
		dfs(0);

	}

	private static boolean dfs(int idx) {

		// 모든 칸을 다 채웠을 때
		if (idx == listSize) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				for (int k = 0; k < 9; k++) {
					sb.append(arr[i][k]);
				}
				sb.append('\n');
			}
			System.out.println(sb);
			return true;
		}

		int idxI = zeroI.get(idx);
		int idxK = zeroK.get(idx);
		for (int i = 1; i < 10; i++) {
			arr[idxI][idxK] = i;
			//해당 칸에 숫자가 들어갈 수 있다면, dfs 탐색
			if (check(idxI, idxK, i)) {
				//dfs 결과가 true라면 true를 리턴해 끝냄
				if(dfs(idx + 1)) {
					return true;
				}
			}
			arr[idxI][idxK] = 0;
		}
		return false;
	}

	// 겹치는 숫자 있는지 탐색
	private static boolean check(int idxI, int idxK, int num) {
		// 가로 탐색
		for (int i = 0; i < 9; i++) {
			if (idxK != i && arr[idxI][i] == num) {
				return false;
			}
		}
		// 세로 탐색
		for (int i = 0; i < 9; i++) {
			if (idxI != i && arr[i][idxK] == num) {
				return false;}
		}

		// 3x3 정사각형 탐색
		int startI = idxI / 3 * 3;
		int startK = idxK / 3 * 3;
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				if (idxI != i + startI && idxK != k + startK && arr[i + startI][k + startK] == num) {
					return false;}
			}
		}
		return true;
	}

}
