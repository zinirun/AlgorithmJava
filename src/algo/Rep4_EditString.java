package algo;

import java.util.Stack;
import java.util.Scanner;

class EditString {
   private int cost[][]; // Cost 저장 변수
   private char edit[][]; // Edit 저장 변수
   private int Lx, Ly; // 두 문자열의 길이를 저장할 변수
   private int VI, VD, VC, Vmin; // 각 연산의 비용/최소값 저장 변수

   public EditString(int n, int m, String x, String y, int c_cost, int i_cost, int d_cost) {
      if (x.equals(y)) {
         System.out.println("두 문자열이 같습니다. 프로그램을 종료합니다."); // 두 문자열이 같을 경우 종료
         return;
      }

      Lx = n; // x 문자열의 길이
      Ly = m; // y 문자열의 길이

      cost = new int[Lx + 1][Ly + 1]; // 비용 저장 배열
      edit = new char[Lx + 1][Ly + 1]; // 연산 저장 배열

      cost[0][0] = 0; // 첫번째 배열은 비용이 0
      edit[0][0] = '-';

      for (int i = 1; i <= Lx; i++) { // 첫 번째 열은 삭제연산만 적용
         cost[i][0] = cost[i - 1][0] + 1;
         edit[i][0] = 'D';
      }
      for (int j = 1; j <= Ly; j++) { // 첫번째 행은 삽입연산만 적용
         cost[0][j] = cost[0][j - 1] + 1;
         edit[0][j] = 'I';
      }

      for (int i = 1; i <= Lx; i++) {
         for (int j = 1; j <= Ly; j++) {
            if (x.charAt(i - 1) == y.charAt(j - 1)) { // 비용없이 문자열 변경 가능 확인
               cost[i][j] = cost[i - 1][j - 1];
               edit[i][j] = 'C';
            } else { // 최소 비용 찾기
               VC = cost[i - 1][j - 1] + c_cost; // 교체 비용
               VI = cost[i][j - 1] + i_cost; // 삽입 비용
               VD = cost[i - 1][j] + d_cost; // 삭제 비용

               if (VC <= VD && VC <= VI) { // 교체 비용이 최소인 경우
                  Vmin = VC;
                  edit[i][j] = 'C';
               } else if (VI <= VC && VI <= VD) { // 삽입 비용이 최소인 경우
                   Vmin = VI;
                   edit[i][j] = 'I';
               } else if (VD <= VC && VD <= VI) { // 삭제 비용이 최소인 경우
                  Vmin = VD;
                  edit[i][j] = 'D';
               }
               
               cost[i][j] = Vmin; // 최소비용을 cost[i][j]에 저장
               
            }
         }
      }
      
      System.out.println("**** cost table ****"); // cost table 출력
      System.out.print("   ");
      for (int i = 0; i <= Ly; i++) {
         System.out.print(i + "  ");
      }
      System.out.println();

      for (int i = 0; i <= Lx; i++) {
         System.out.print(i + "  ");
         for (int j = 0; j <= Ly; j++) {
            System.out.print(cost[i][j] + "  ");
         }
         System.out.println();
      }

      System.out.println();
      System.out.println("**** edit table ****"); // edit table 출력
      System.out.print("   ");
      for (int i = 0; i <= Ly; i++) { 
         System.out.print(i + "  ");
      }
      
      System.out.println();
      for (int i = 0; i <= Lx; i++) {
         System.out.print(i + "  ");
         for (int j = 0; j <= Ly; j++) {
            System.out.print(edit[i][j] + "  ");
         }
         System.out.println();
      }
      
      findPath(edit); // 경로 출력 위해 findpath 메소드 호출
   }

   private void findPath(char edit[][]) { // Edit 경로 출력 메소드
      int i = Lx;
      int j = Ly;
      Stack st = new Stack(); // 스택 선언
      
      while (i >= 0 && j >= 0) {
    	 if(i == 0 && j == 0) {
    		 st.push("(" + i + "," + j + ")");
    	 } else {
    		 st.push(edit[i][j]+"(" + i + "," + j + ")");
    	 }

         if (edit[i][j] == 'C') { // edit[i][j]가 교체 -> i,j 모두 감소
            i--;
            j--;
         } else if (edit[i][j] == 'D') { // edit[i][j]가 삭제 -> i 감소
            i--;
         } else // edit[i][j]가 삽입 -> i 감소
            j--;
      }
      System.out.println();
      System.out.print("Edit<"+ Lx +"," + Ly + ">: " + st.pop());
      while (!st.isEmpty()) { // Stack pop 후 경로의 순서대로 출력
         System.out.print(" -> " + st.pop());
      }
   }
}

public class Rep4_EditString {
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);

      System.out.print("두 문자열의 길이를 입력 : ");
      int n = sc.nextInt();
      int m = sc.nextInt();
      
      System.out.print("X,Y 문자열을 각각 입력 : ");
      String X = sc.next();
      String Y = sc.next();
      
      System.out.print("C(교체), I(삽입), D(삭제)에 대한 교체 비용 각각 입력 : ");
      int c = sc.nextInt();
      int i = sc.nextInt();
      int d = sc.nextInt();
      
      System.out.println();
      
      EditString es = new EditString(n, m, X, Y, c, i, d);
      
      sc.close();
   }
}