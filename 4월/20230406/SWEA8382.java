import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA8382 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine()) + 1;
		String[] str;
		int x1, x2, y1, y2, ans, ansX, ansY;
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc < testCase; tc++) {
			str = br.readLine().split(" ");
			x1 = Integer.parseInt(str[0]);
			y1 = Integer.parseInt(str[1]);
			x2 = Integer.parseInt(str[2]);
			y2 = Integer.parseInt(str[3]);
			
			ansX = Math.abs(x1 - x2);
			ansY = Math.abs(y1 - y2);
			
			//둘 다 홀수거나 둘 다 짝수일 시, 둘 중 큰 값 * 2
			if(ansX%2 == ansY%2) {
				ans = Math.max(ansY, ansX);
				ans = ans * 2;
			}
			//홀-짝 일 시, 큰 값 * 2 - 1
			else {
				ans = Math.max(ansY, ansX);
				ans = ans * 2 - 1;
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}

	
}
