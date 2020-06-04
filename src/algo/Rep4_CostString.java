package algo;

import java.util.Scanner;
import java.util.Stack;

class edit {
	char[][] editor;
	int cost[][];
	String arr[][];
	char s1[];
	char s2[];
	int size1, size2;
	Stack<String> input = new Stack<>();
	
	
	edit(int a, int b, String x, String y){
		cost = new int[a+1][b+1];
		arr = new String[a+1][b+1];
		editor = new char[a+1][b+1];
		size1 = a;
		size2 = b;
		s1 = x.toCharArray();
		s2 = y.toCharArray();
		
		for(int i = 0; i < a+1; i++) {
			arr[i][0] = pick2(i, x, a);
			for(int j = 1; j < b+1; j++) {
				arr[i][j] = pick(j, arr[i][0]);
			}
		}
		
		cost_edit();
		trace_edit();
	}
	
	String pick2(int index, String base, int n) {
		String modified = "";
		modified = base.substring(index, n);
		return modified;
	}
	
	String pick(int index, String base) {
		String modified = "";
		for(int i = index-1; i >= 0; i--) {
			modified = s2[i]+modified;
		}
		modified = modified + base;
		return modified;
	}
	
	void cost_edit() {
		for(int i = 0; i < size2 + 1; i++) {
			editor[0][i] = 'I';
			cost[0][i] = i;
		}
		
		for(int j = 0; j < size1 + 1; j++) {
			editor[j][0] = 'D';
			cost[j][0] = j;
		}
		
		for(int k = 1; k < size1 + 1; k++) {
			for(int z = 1; z < size2 + 1; z++) {
				cost[k][z] = costpick(k, z);
			}
		}
		
		System.out.println("** cost table **");
		
		for(int x = 0; x < size1 + 1; x++) {
			for(int y = 0; y < size2 + 1; y++) {
				System.out.print(cost[x][y] + " ");
			}
			System.out.println();
		}
	}
	
	int costpick(int i, int j) {
		if(arr[i][j].equals(arr[i-1][j-1])) {
			editor[i][j] = 'C';
			return cost[i-1][j-1];
		}
		else if(cost[i-1][j] > cost[i][j-1]) {
			editor[i][j] = 'I';
			return cost[i][j-1] + 1;
		}
		else {
			editor[i][j] = 'D';
			return cost[i-1][j] + 1;
		}
	}
	
	
	void trace_edit() {
		String edit_table = arr[size1][size2];
		int vertical = size1;
		int horizontal = size2;
		
		input.push(edit_table);
		
		while(!edit_table.equals(arr[0][0])) {
			if(editor[vertical][horizontal] == 'C') {
				edit_table = arr[--vertical][--horizontal];
				input.push(edit_table + " (Change)");
			}
			else if(editor[vertical][horizontal] == 'D') {
				edit_table = arr[--vertical][horizontal];
				input.push(edit_table + " (Delete)");
			}
			else{
				edit_table = arr[vertical][--horizontal];
				input.push(edit_table + " (Insert)");
			}
		}
		System.out.println("****************");
		for(int i = 0; i < edit_table.length(); i++) {
			input.pop();
			System.out.println(input.peek());
		}
	}
}

public class Rep4_CostString {

	public static void main(String[] args) { //메인함수
		
		System.out.print("두 문자열의 길이 : ");
		
		Scanner scanner = new Scanner(System.in);
		
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		
		System.out.print("두 문자열 입력 : ");
		
		String str1 = scanner.next();
		String str2 = scanner.next();
		
		edit ed = new edit(a, b, str1, str2);
		
		scanner.close();
	}
}