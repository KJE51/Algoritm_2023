package y2023.m04.d03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1063 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int kI, kK, dI, dK, n, newKI, newKK, newDI, newDK;
		kK = str[0].charAt(0) - 'A';
		kI = str[0].charAt(1) - '1';
		dK = str[1].charAt(0) - 'A';
		dI = str[1].charAt(1) - '1';
		n = Integer.parseInt(str[2]);
		
		char kchar, dchar;
		
		int d = 0;
		int[] di = {0, 0, -1, 1, 1, 1, -1, -1};
		int[] dk = {1, -1, 0, 0, 1, -1, 1, -1};
		while(n-->0) {
			str[0] = br.readLine();
			switch(str[0]) {
			case "R":
				d = 0;
				break;
			case "L":
				d = 1;
				break;
			case "B":
				d = 2;
				break;
			case "T":
				d = 3;
				break;
			case "RT":
				d = 4;
				break;
			case "LT":
				d = 5;
				break;
			case "RB":
				d = 6;
				break;
			case "LB":
				d = 7;
				break;
			}
			
			newKI = kI + di[d];
			newKK = kK+dk[d];

			//왕이 범위 벗어날 때
			if(newKI<0 || newKK<0||newKI>7||newKK>7) {
				continue;
			}
			
			//돌과 함께 움직일 때
			if(newKI == dI && newKK == dK) {
				//돌이 범위를 벗어날 때
				newDI = dI + di[d];
				newDK = dK + dk[d];
				if(newDI<0 || newDK<0||newDI>7||newDK>7) {
					continue;
				}
				dI = newDI;
				dK = newDK;
			}
			kI = newKI;
			kK = newKK;
			
		}
		
		kchar = (char)(kK + (int)'A');
		dchar = (char)(dK + (int)'A');
		System.out.println(kchar+""+(kI+1));
		System.out.println(dchar+""+(dI+1));
	}

}
