package algorithm;
//소프트웨어학과 허전진

public class Rep2_QuickSort {
	private	int a[];
    private int aSize ;
    
    public Rep2_QuickSort(int arr[], int n) { 
    	a = arr;                             
    	aSize = n;
		a[n+1] = Integer.MAX_VALUE ;         
     }
    
    public int[] QuickSortCall() {  
    	QuickSort(1, aSize);
    	return a ;
    }
    
    void QuickSort(int p, int q) {
	       if (p < q) { 
	            int j = Partition(a, p, q+1);
	            QuickSort(p, j-1);
	            QuickSort(j+1,q);
	       }
	   }
    
    int Partition(int a[], int m, int p) {
	       int v=a[m]; 
	       int i=m, j=p;
	       do {
	              do i++;
	              while (a[i] < v);
	              do j--;
	              while (a[j] > v);
	              if (i < j) 
	            	  Interchange(a, i, j);
	          } while (i < j);
	          a[m] = a[j]; 
	          a[j] = v; 
	          return(j);
	   }
    
    void Interchange(int a[], int i, int j) {
		 int temp = a[i];
		 a[i] = a[j];
		 a[j] = temp;
	 }
    
    public static void main(String args[]) {
		int size[] = { 1000, 2000, 3000, 4000, 5000 };
		int store[][] = new int[5][];								// 퀵정렬 후 저장될 배열
		
		for (int i = 0; i < 5; i++)
			store[i] = new int[size[i] + 10];						// 배열에 대한 사이즈
		
		System.out.println("QuickSort");
		System.out.println("[1000]  [2000]  [3000]  [4000]  [5000]");
		for (int i = 0; i < 10; i++) {								// 데이터 10개에 대해 테스트
			for (int j = 0; j < 5; j++)								// 각각의 크기만큼 5번 수행
				for (int k = 0; k < size[j]; k++)					// 할당받은 크기만큼 반복
					store[j][k] = (int)(java.lang.Math.random() * size[j]); // 난수 입력
		
			for (int l = 0; l < 5; l++) {
				Rep2_QuickSort quick = new Rep2_QuickSort(store[l], size[l]);
				long prevTime = System.nanoTime();					// 퀵 정렬 수행 전 시간
				store[l] = quick.QuickSortCall();					// 퀵 정렬 수행
				long doneTime = System.nanoTime();						// 퀵 정렬 수행 후 시간
				long calcTime = doneTime - prevTime;						// 퀵 정렬 수행 시간
				System.out.print(calcTime + "  ");
			}
			System.out.println("");
		}
		System.out.println();
	}
}