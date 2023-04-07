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
        double res;
         
        for(int tc = 1; tc < testCase; tc++) {
            sb.append('#').append(tc);
            n = Integer.parseInt(br.readLine());
            arr= new int[n][2];
            str = br.readLine().trim().split(" ");
             
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
                    res = cal(mid, i);
                    if(res == 0) {
                        break;
                    }
                    //오른쪽 힘이 더 클 때->더 왼쪽으로 가야함
                    if(res < 0) {
                        right = mid;
                    }else {
                    //왼쪽이 더 클 때->더 오른쪽으로 가야함
                        left = mid;
                    }
                }
                sb.append(" ").append(String.format("%.10f", mid));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
     
    //왼쪽과 오른쪽의 인력 합 계산하기
    private static double cal(double mid, int idx) {
        double left, right;
        left = right = 0;
        for(int i = 0; i < idx; i++) {
            left += arr[i][1]/((arr[i][0]-mid)*(arr[i][0]-mid));
        }
        for(int i = idx; i < n; i++) {
            right += arr[i][1]/((arr[i][0]-mid)*(arr[i][0]-mid));
        }
        return left - right;
    }
 
}