import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ4386 {
	static class Data{
		double x;
		double y;
		public Data(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Data [x=" + x + ", y=" + y + "]";
		}
	}
	
	static Data[] arr;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] str;
		arr = new Data[n];
		
		//입력받기
		for(int i = 0; i < n; i++) {
			str = br.readLine().split(" ");
			arr[i] = new Data(Double.parseDouble(str[0]), Double.parseDouble(str[1]));
		}
		
		
	}

}
