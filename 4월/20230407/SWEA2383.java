import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SWEA2383 {

	static class Stair {
		int len;
		int i;
		int k;

		public Stair(int len, int i, int k) {
			super();
			this.len = len;
			this.i = i;
			this.k = k;
		}

		@Override
		public String toString() {
			return "Stair [len=" + len + ", i=" + i + ", k=" + k + "]";
		}
	}
	
	static class People{
		int i;
		int k;
		int stair1;
		int stair2;
		
		public People(int i, int k) {
			super();
			this.i = i;
			this.k = k;
		}
	}

	static ArrayList<Stair> stairs;
	static ArrayList<People> peoples;
	static int peoN, res;
	static boolean[] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		String[] str;
		int n;
		
		for(int tc = 1; tc < testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			
			//계단과 사람 입력받아서 리스트로 바꾸기
			n = Integer.parseInt(br.readLine());
			for(int i = 0; i < n; i++) {
				str = br.readLine().split(" ");
				for(int k = 0; k < n; k++) {
					//사람일 때
					if(str[k].equals("1")) {
						peoples.add(new People(i, k));
					}
					if(str[k].equals("0"))
						continue;
				
					//계단일 때
					stairs.add(new Stair(Integer.parseInt(str[k]), i, k));
				}
			}
			
			peoN = peoples.size();
			People tmpP;
			int s1I = stairs.get(0).i, s1K = stairs.get(0).k, s2I = stairs.get(1).i, s2K = stairs.get(1).k;
			//각 계단과 사람 사이의 거리 계산하기
			for(int i = 0; i < peoN; i++) {
				tmpP = peoples.get(i);
				tmpP.stair1 = Math.abs(tmpP.i - s1I) + Math.abs(tmpP.k - s1K);
				tmpP.stair2 = Math.abs(tmpP.i - s2I) + Math.abs(tmpP.k - s2K);
			}
			
			
			visited = new boolean[peoN];
			res = 999999;
			//계단이 하나일 때 - 조합 돌릴 필요 없음, 그냥 계산하기
			if(stairs.size() == 1) {
				res = countTime();
				sb.append(res).append('\n');
				continue;
			}
			
			//부분집합 돌려서 뽑아서 계산하기
		}
	}

	private static int countTime() {
		int cnt = 0;
		
		return cnt;
	}

}
