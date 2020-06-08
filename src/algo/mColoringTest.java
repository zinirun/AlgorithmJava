package algo;

public class mColoringTest {

	public static void main(String[] args) {
		int n = 4 ;     // 정점의갯수, 교재의 예
		int m = 2 ;     // 색의 수
		int adjM[][] = new int[n+1][n+1] ;  // 그래프의 인접행렬
		
		for(int i=1; i<=n; i++)
			for(int j=1; j<=n; j++)
				adjM[i][j] = 0 ;        // 인접행렬의 초기화
		
		adjM[1][2] = 1 ;   adjM[2][1] = 1 ;    // 무방향 간선이 있음을 의미(교재의 예)
		adjM[2][3] = 1 ;   adjM[3][2] = 1 ;
		adjM[3][4] = 1 ;   adjM[4][3] = 1 ;
		adjM[4][1] = 1 ;   adjM[1][4] = 1 ;

		mColoringClass mc = new mColoringClass(adjM, n, m) ;
	}

}
