import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1149 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
//		int[][] arr = new int[n][3];
		int a, b, c;
		int a1, b1, c1;
		a1 = b1 = c1 = 0;
		for (int i = 0; i < n; i++) {
			str = br.readLine().split(" ");
			a = Integer.parseInt(str[0]);
			b = Integer.parseInt(str[1]);
			c = Integer.parseInt(str[2]);
			a1 = a1 + Math.min(b, c);
			b1 = b1 + Math.min(a, c);
			c1 = c1 + Math.min(a, b);
			System.out.println(a1 + " "+b1+" "+c1);
		}
		a = Math.min(a1, b1);
		System.out.println(Math.min(a, c1));
		
	}
}
