import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class BOJ1463 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n= sc.nextInt();
		if(n == 1) {
			System.out.println(0);
			return;
		}
		int[] dp = new int[n+1];
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(n);
		int tmp;
		while(!que.isEmpty()) {
			tmp = que.poll();
			if(tmp % 3 == 0 && dp[tmp/3] == 0) {
				dp[tmp/3] = dp[tmp]+1;
				que.add(tmp/3);
			}
			if(tmp % 2 == 0 && dp[tmp/2] == 0) {
				dp[tmp/2] = dp[tmp]+1;
				que.add(tmp/2);
			}
			if(dp[tmp-1] == 0) {
				dp[tmp-1] = dp[tmp]+1;
				que.add(tmp-1);
			}
			if(dp[1] != 0)
				break;
			
		}
		System.out.println(dp[1]);
	}

}
