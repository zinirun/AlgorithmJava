package algo;

public class mColoringClass {

	private	int G[][] ;  // 인접 행렬
	private int x[] ;
	private int n, m;    // n: 정점의 수, m: 색의 수
	
	public mColoringClass(int arr[][], int numV, int numC) {
		G = arr;
		n = numV;
		m = numC;
		x = new int [n+1];		

		for (int i=1; i<=n; i++) {
			x[i] = 0 ;			 // x[i] 의  초기화
		}
		
		System.out.println(m + " 가지 채색의 해: ");
		for(int i=1; i<=n; i++)
			System.out.print("v["+ i + "] ");
		System.out.println();
		
		mColoring(1) ;    // mColoring 함수의 첫 호출
	}
	
	private void mColoring(int k) {
	// This program was formed using the recursive backtracking  schema. 
	// The graph is represented by its boolean adjacency matrix G[1:n][1:n]. 
	// All assignments of 1,2,...,m to the vertices of the graph such that adjacent 
	// vertices are assigned distinct integers are printed. k is the index of
	// the next vertex to color.
	    do { // Generate all legal assignments for x[k].
	        NextValue(k); // Assign to x[k] a legal color.
	        if (x[k] == 0) break; // No new color possible
	        if (k == n) { // At most m colors have been used
	                      // to color the n vertices.
	        	for (int i=1; i<=n; i++) System.out.print(" "+ x[i] +"   ") ;  // 하나의 해 출력
	        	System.out.println();
	        }
		    else mColoring(k+1);
		} while(true);
	}
	
	private void NextValue(int k)
	// x[1],..., x[k-1] have been assigned integer values in
	// the range [1,m] such that adjacent vertices have distinct
	// integers. A value for x[k] is determined in the range
	// [0,m]. x[k] is assigned the next highest numbered color
	// while maintaining distinctness from the adjacent vertices
	// of vertex k. If no such color exists, then x[k] is zero.
	{
	    do {
	       x[k] = (x[k]+1) % (m+1); // Next highest color
	       if (x[k] == 0) break ;   // All colors have been used.
	       int j ;
	       for (j=1; j <= n; j++) { // Check if this color is
	                                  // distinct from adjacent colors.
	          if ((G[k][j]==1) && (x[k] == x[j])) // If (k, j) is an edge and if adj. 
	        	  							  // vertices have the same color.
	              break;
	       }
	       if (j == n+1) break; // New color found
	    } while (true); // Otherwise try to find another color.
	}
}
