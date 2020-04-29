package algorithm;
//소프트웨어학과 허전진

public class Rep2_MergeSort {
	private	int a[], b[] ;
    private int aSize ;
    
    public Rep2_MergeSort(int arr[], int n) { 
    	a = arr;                              
    	aSize = n;
    	b = new int[aSize+1];
    }
    
    public int[] MergeSortCall() {  
    	MergeSort(1, aSize);
    	return a ;
    }
    
    public void MergeSort(int low, int high) { //low, high로 MergeSort 수행
        if (low < high) {
        	int mid = (low + high)/2;
            MergeSort(low, mid);
            MergeSort(mid + 1, high);
            Merge(low, mid, high);
        }
    }
    public void Merge(int low, int mid, int high) {
        int h = low, i = low, j = mid+1, k;
        while ((h <= mid) && (j <= high)) {
           if (a[h] <= a[j]) { 
        	   b[i] = a[h]; 
        	   h++; }
           else { 
        	   b[i] = a[j]; 
        	   j++; 
        	   } 
           i++;
        }
        if (h > mid) 
        	for (k=j; k<=high; k++) {
        		b[i] = a[k]; 
        		i++; 
                        }
        else 
        	for (k=h; k<=mid; k++) {
                b[i] = a[k]; 
                i++;
             }
        for (k=low; k<=high; k++) 
        	a[k] = b[k];
    }
    
    public static void main(String args[]) {
		int size[] = { 1000, 2000, 3000, 4000, 5000 };
		int resultArray[][] = new int[5][];								// 정렬 후 저장 배열
		
		for (int i = 0; i < 5; i++)
			resultArray[i] = new int[size[i] + 10];						// 배열 사이즈
		
		System.out.println("MergeSort");
		System.out.println("[1000]  [2000]  [3000]  [4000]  [5000]");
		for (int i = 0; i < 10; i++) {								// 데이터 10개에 대해 테스트
			for (int j = 0; j < 5; j++)								// 각각의 크기만큼 5번 수행
				for (int k = 0; k < size[j]; k++)					// 할당받은 크기만큼 반복
					resultArray[j][k] = (int)(java.lang.Math.random() * size[j]); // 난수 입력
		
			for (int l = 0; l < 5; l++) {
				Rep2_MergeSort merge = new Rep2_MergeSort(resultArray[l], size[l]);
				long prevTime = System.nanoTime();					// 합병 정렬 전 시간
				resultArray[l] = merge.MergeSortCall();					// 합병 정렬 수행
				long doneTime = System.nanoTime();						// 합병 정렬 완료 시간
				long calcTime = doneTime - prevTime;						// 합병정렬 수행하는 시간
				System.out.print(calcTime + "  ");
			}
			System.out.println();
		}
		System.out.println();
	}
}