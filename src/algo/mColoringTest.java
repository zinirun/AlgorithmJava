package algo;

public class mColoringTest {

	public static void main(String[] args) {
		int n = 5 ;     // �젙�젏�쓽媛��닔, 援먯옱�쓽 �삁
		int m = 3 ;     // �깋�쓽 �닔
		int adjM[][] = new int[n+1][n+1] ;  // 洹몃옒�봽�쓽 �씤�젒�뻾�젹
		
		for(int i=1; i<=n; i++)
			for(int j=1; j<=n; j++)
				adjM[i][j] = 0 ;        // �씤�젒�뻾�젹�쓽 珥덇린�솕
		
		adjM[1][2] = 1 ;   adjM[2][1] = 1 ;    // 臾대갑�뼢 媛꾩꽑�씠 �엳�쓬�쓣 �쓽誘�(援먯옱�쓽 �삁)
		adjM[2][3] = 1 ;   adjM[3][2] = 1 ;
		adjM[2][4] = 1 ;   adjM[4][2] = 1 ;
		adjM[4][5] = 1 ;   adjM[5][4] = 1 ;
		adjM[1][3] = 1 ;   adjM[3][1] = 1 ;
		adjM[3][5] = 1 ;   adjM[5][3] = 1 ;

		mColoringClass mc = new mColoringClass(adjM, n, m) ;
	}

}
