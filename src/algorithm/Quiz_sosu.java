package algorithm;
//소프트웨어학과 허전진
public class Quiz_sosu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tmp, cnt=0;
		for(int i=1; i<=100; i++) {
			tmp = 0;
			for(int j=1; j<=i; j++) {
				if(i%j == 0) tmp++;
			}
			if(tmp == 2) cnt++;
		}
		System.out.println("1부터 100까지 소수의 개수: " + cnt + "개");
	}

}