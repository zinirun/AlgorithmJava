package algo;
//소프트웨어학과 32164959 허전진

public class Quiz_greedySort {
	static void GreedySort(int a[], int n) {
		int tmp;
		for(int i=0; i<n-1; i++) {
			for(int j=i+1; j<n; j++) {
				if(a[i] > a[j]) {
					tmp = a[i];
					a[i] = a[j];
					a[j] = tmp;
				}
			}
		}
	}
	static void PrintArray(int a[], int n) {
		for(int i=0; i<n; i++) {
			System.out.print(a[i]+ " ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		int[] testData = {60, 25, 30, 44, 10, 92, 51, 73, 88, 7};
		PrintArray(testData, 10);
		GreedySort(testData, 10);
		PrintArray(testData, 10);
	}

}