import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class SWEA2383T {

	static int N, cnt, ans;
	static final int M = 1, W = 2, D = 3, C = 4; // 이동중, 대기, 내려가는 중, 완료
	static ArrayList<Person> plist;
	static int[][] slist;
	static int[] selected;

	static class Person implements Comparable<Person> {
		int r, c, arrivalTime, downCnt, status;

		public Person(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Person o) {
			return Integer.compare(this.arrivalTime, o.arrivalTime);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine())+1;
		StringBuilder sb = new StringBuilder();
		String[] str;
		int n;

		for (int tc = 1; tc < testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			ans = 9999999;

			// 계단과 사람 입력받아서 리스트로 바꾸기
			n = Integer.parseInt(br.readLine());
			plist = new ArrayList<Person>();
			slist = new int[2][];

			for (int i = 0, k = 0; i < n; i++) {
				str = br.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					// 사람일 때
					if (str[j].equals("1")) {
						plist.add(new Person(i, j));
						continue;
					}
					if (str[j].equals("0"))
						continue;

					// 계단일 때
					slist[k++] = new int[] { i, j, Integer.parseInt(str[j]) };
				}
			}

			cnt = plist.size();
			selected = new int[cnt]; // 선택한 계단 인덱스 넣기
			// 모든 사람에게 계단 배정
			divide(0);
			
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}

	// power set
	static void divide(int index) {
		if (index == cnt) {
			int res = go();
			if(ans > res) ans = res;
			return;
		}
		// 계단 0 선택
		selected[index] = 0;
		divide(index + 1);
		// 계단 1 선택
		selected[index] = 1;
		divide(index + 1);
	}

	// 선택된 게단에 따라 사람들의 리스트 만들고 내려가기 처리 후 시간 계산
	static int go() {
		ArrayList<Person>[] list = new ArrayList[] { new ArrayList<Person>(), new ArrayList<Person>() };

		for (int i = 0; i < cnt; i++) {
			Person p = plist.get(i);
			int no = selected[i];
			p.arrivalTime = Math.abs(p.r - slist[no][0]) + Math.abs(p.c - slist[no][1]);
			list[no].add(p);
		}
		
		int timeA = 0, timeB = 0;
		if(list[0].size()>0) {
			timeA = processDown(list[0], slist[0][2]);
		}
		if(list[1].size()>0) {
			timeB = processDown(list[1], slist[1][2]);
		}
		
		return timeA>timeB?timeA:timeB;

	}

	static int processDown(ArrayList<Person> list, int height) {
		
		Collections.sort(list); // 계단 입구까지 도착시간이 빠른 순으로 정렬
		int size = list.size()+3;
		int[] D = new int[size];
		for(int i = 3; i < size; i++) {
			Person p = list.get(i - 3);
			if(D[i-3]<=p.arrivalTime + 1) {
				D[i] = p.arrivalTime + 1 + height;
			}else {
				D[i] = D[i-3]+height;
			}
		}
		return D[size-1];
	}
}
