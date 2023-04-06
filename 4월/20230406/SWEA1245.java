import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA1245 {

	static int n, arr[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine())+1;
		String[] str;
		StringBuilder sb = new StringBuilder();
		double left, right, mid=0;
		double leftW;
		
		for(int tc = 1; tc < testCase; tc++) {
			sb.append('#').append(tc);
			n = Integer.parseInt(br.readLine());
			arr= new int[n][2];
			str = br.readLine().split(" ");
			
			//위치들 한번에 입력받은 후, 질량 한번에 입력받기
			for(int i = 0; i < n; i++) {
				arr[i][0] = Integer.parseInt(str[i]);
				arr[i][1] = Integer.parseInt(str[i+n]);
			}
			
			//각 구간별 서치
			for(int i = 1; i < n; i++) {
				//각 구간에 대한 이분탐색
				left = arr[i-1][0];
				right = arr[i][0];
				while(left < right && right-left > 1e-12) {
					mid = (left + right)/2;
					System.out.println(mid);
					
//					res = cal(mid, i);
					
					leftW = 0;
					
					for(int i1 = 0; i1 < i; i1++) {
						leftW += arr[i1][1]/((arr[i1][0]-mid)*(arr[i1][0]-mid));
					}
					
					for(int i1 = i; i1 < n; i1++) {
						leftW -= arr[i1][1]/((arr[i1][0]-mid)*(arr[i1][0]-mid));
					}
//					res = leftW;
					
					if(leftW == 0) {
						break;
					}
					//왼쪽이 힘이 더 클 때->더 왼쪽 탐색
					if(leftW < 0) {
						right = mid;
					}else {
					//오른쪽이 더 클 때
						left = mid;
					}
				}
				sb.append(" ").append(String.format("%.10f", mid));
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
//	//계산하기
//	private static double cal(double mid, int i) {
//		double leftW, rightW;
//		leftW = rightW = 0;
//		
//		for(int i1 = 0; i1 < i; i1++) {
//			leftW += arr[i1][1]/((arr[i1][0]-mid)*(arr[i1][0]-mid));
//		}
//		
//		for(int i1 = i; i1 < n; i1++) {
//			rightW += arr[i1][1]/((arr[i1][0]-mid)*(arr[i1][0]-mid));
//		}
//		
//		return leftW - rightW;
//	}

}
