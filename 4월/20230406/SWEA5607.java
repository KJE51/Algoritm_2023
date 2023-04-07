import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA5607 {
	
	static long[] fac;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine())+1;
		String[] str;
		int n, r, q = 1234567891;
		
		for(int tc = 1; tc < testCase; tc++) {
			str = br.readLine().split(" ");
			n = Integer.parseInt(str[0]);
			r = Integer.parseInt(str[1]);
			
			// 팩토리얼 미리 계산하기
			fac = new long[n+1];
			fac[0] = 1;
			for(int i = 1; i <= n; i++)
				fac[i] = fac[i-1] * i % q;
			
			//nCr 계산해서 출력하기
			System.out.printf("#%d %d\n", tc, cal(n, r, q));
		}
	}

	//nCr 계산하는 메소드
	private static long cal(int n, int r, int p) {
		if(r == 0)
			return 1L;
		return (fac[n] * power(fac[r], p-2, p) % p * power(fac[n-r], p-2, p)%p)%p;
	}

	//거듭제곱 계산하는 메소드
	private static long power(long x, long y, long p) {
		long res = 1L;
		x = x % p;
		while(y > 0) {
			if(y % 2 == 1)
				res = (res * x) % p;
			y = y >> 1;
			x = (x * x) % p;
		}
		return res;
	}

}
