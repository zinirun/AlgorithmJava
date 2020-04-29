package algo;
//소프트웨어학과 허전진
import java.util.Scanner;

public class Rep1_sort {
	static Scanner sc = new Scanner(System.in); //Scanner 사용
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("n값 입력: ");
		int n = sc.nextInt();
		
		int[] array = new int[n]; //n값 만큼 int 배열 선언
		int tmp; //재정렬 할 때 값 저장을 위한 임시 변수
		
		System.out.print(n+" 개의 정수 입력: ");
		
		for(int i=0; i<n; i++) {
			array[i] = sc.nextInt(); //배열 요소를 입력받음
		}
		
		for(int i=0; i<n; i++) { //i=0부터 array의 길이만큼 반복
			for(int j=i+1; j<n; j++) { //i와 j(i+1과 같음)를 비교
				if(array[i] > array[j]) { //array[i]가 array[j]보다 크면 서로 바꿈
					tmp = array[i];
					array[i] = array[j];
					array[j] = tmp;
				}
			}
		}
		
		System.out.print("정렬 결과: ");
		for(int i=0; i<n; i++) {
			System.out.print(array[i]+" "); //정렬된 array 값을 차례대로 출력
		}
		
		sc.close(); //Scanner 사용이 더 없으므로 닫음(close)
	}
}