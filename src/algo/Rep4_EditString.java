package algo;

import java.util.Stack;
import java.util.Scanner;

class EditString {
   private int cost[][]; // Cost ���� ����
   private char edit[][]; // Edit ���� ����
   private int Lx, Ly; // �� ���ڿ��� ���̸� ������ ����
   private int VI, VD, VC, Vmin; // �� ������ ���/�ּҰ� ���� ����

   public EditString(int n, int m, String x, String y, int c_cost, int i_cost, int d_cost) {
      if (x.equals(y)) {
         System.out.println("�� ���ڿ��� �����ϴ�. ���α׷��� �����մϴ�."); // �� ���ڿ��� ���� ��� ����
         return;
      }

      Lx = n; // x ���ڿ��� ����
      Ly = m; // y ���ڿ��� ����

      cost = new int[Lx + 1][Ly + 1]; // ��� ���� �迭
      edit = new char[Lx + 1][Ly + 1]; // ���� ���� �迭

      cost[0][0] = 0; // ù��° �迭�� ����� 0
      edit[0][0] = '-';

      for (int i = 1; i <= Lx; i++) { // ù ��° ���� �������길 ����
         cost[i][0] = cost[i - 1][0] + 1;
         edit[i][0] = 'D';
      }
      for (int j = 1; j <= Ly; j++) { // ù��° ���� ���Կ��길 ����
         cost[0][j] = cost[0][j - 1] + 1;
         edit[0][j] = 'I';
      }

      for (int i = 1; i <= Lx; i++) {
         for (int j = 1; j <= Ly; j++) {
            if (x.charAt(i - 1) == y.charAt(j - 1)) { // ������ ���ڿ� ���� ���� Ȯ��
               cost[i][j] = cost[i - 1][j - 1];
               edit[i][j] = 'C';
            } else { // �ּ� ��� ã��
               VC = cost[i - 1][j - 1] + c_cost; // ��ü ���
               VI = cost[i][j - 1] + i_cost; // ���� ���
               VD = cost[i - 1][j] + d_cost; // ���� ���

               if (VC <= VD && VC <= VI) { // ��ü ����� �ּ��� ���
                  Vmin = VC;
                  edit[i][j] = 'C';
               } else if (VI <= VC && VI <= VD) { // ���� ����� �ּ��� ���
                   Vmin = VI;
                   edit[i][j] = 'I';
               } else if (VD <= VC && VD <= VI) { // ���� ����� �ּ��� ���
                  Vmin = VD;
                  edit[i][j] = 'D';
               }
               
               cost[i][j] = Vmin; // �ּҺ���� cost[i][j]�� ����
               
            }
         }
      }
      
      System.out.println("**** cost table ****"); // cost table ���
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
      System.out.println("**** edit table ****"); // edit table ���
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
      
      findPath(edit); // ��� ��� ���� findpath �޼ҵ� ȣ��
   }

   private void findPath(char edit[][]) { // Edit ��� ��� �޼ҵ�
      int i = Lx;
      int j = Ly;
      Stack st = new Stack(); // ���� ����
      
      while (i >= 0 && j >= 0) {
    	 if(i == 0 && j == 0) {
    		 st.push("(" + i + "," + j + ")");
    	 } else {
    		 st.push(edit[i][j]+"(" + i + "," + j + ")");
    	 }

         if (edit[i][j] == 'C') { // edit[i][j]�� ��ü -> i,j ��� ����
            i--;
            j--;
         } else if (edit[i][j] == 'D') { // edit[i][j]�� ���� -> i ����
            i--;
         } else // edit[i][j]�� ���� -> i ����
            j--;
      }
      System.out.println();
      System.out.print("Edit<"+ Lx +"," + Ly + ">: " + st.pop());
      while (!st.isEmpty()) { // Stack pop �� ����� ������� ���
         System.out.print(" -> " + st.pop());
      }
   }
}

public class Rep4_EditString {
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);

      System.out.print("�� ���ڿ��� ���̸� �Է� : ");
      int n = sc.nextInt();
      int m = sc.nextInt();
      
      System.out.print("X,Y ���ڿ��� ���� �Է� : ");
      String X = sc.next();
      String Y = sc.next();
      
      System.out.print("C(��ü), I(����), D(����)�� ���� ��ü ��� ���� �Է� : ");
      int c = sc.nextInt();
      int i = sc.nextInt();
      int d = sc.nextInt();
      
      System.out.println();
      
      EditString es = new EditString(n, m, X, Y, c, i, d);
      
      sc.close();
   }
}