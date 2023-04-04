package y2023.m04.d03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA1263 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine())+1;
		String[] str;
		int[][] arr;
		int n;
		
		//그래프 입력받기
		for(int tc = 1; tc < testCase; tc++) {
			str = br.readLine().split(" ");
			n = Integer.parseInt(str[0]);
			arr = new int[n][n];
			for(int i = 0; i < n; i++) {
				for(int k = 1; k <=n ;k++) {
					arr[i][k-1] = Integer.parseInt(str[i*n + k]);
					if(arr[i][k-1] == 0) {
						arr[i][k-1] = 9999;
					}
					if(i == k-1) {
						arr[i][k-1] = 0;
					}
				}
			}
			
			//i, j, k
			//경로, 출발, 도착 순서
			//플루이드 워샬 표 만들기
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					for(int k = 0; k < n; k++) {
						arr[j][k] = Math.min(arr[j][k], arr[j][i] + arr[i][k]);
					}
				}
			}
			
			//각 행의 값 더해서 최소 cc 찾기
			int tmp=0, min=9999;
			for(int i= 0; i < n; i++) {
				tmp= 0;
				for(int k = 0; k < n; k++) {
					tmp += arr[i][k];
				}
				if(tmp<min) {
					min = tmp;
				}
			}
			System.out.println("#"+tc+" "+min);
		}
		
	}

}
