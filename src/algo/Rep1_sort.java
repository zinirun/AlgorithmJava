package algo;
//����Ʈ�����а� ������
import java.util.Scanner;

public class Rep1_sort {
	static Scanner sc = new Scanner(System.in); //Scanner ���
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("n�� �Է�: ");
		int n = sc.nextInt();
		
		int[] array = new int[n]; //n�� ��ŭ int �迭 ����
		int tmp; //������ �� �� �� ������ ���� �ӽ� ����
		
		System.out.print(n+" ���� ���� �Է�: ");
		
		for(int i=0; i<n; i++) {
			array[i] = sc.nextInt(); //�迭 ��Ҹ� �Է¹���
		}
		
		for(int i=0; i<n; i++) { //i=0���� array�� ���̸�ŭ �ݺ�
			for(int j=i+1; j<n; j++) { //i�� j(i+1�� ����)�� ��
				if(array[i] > array[j]) { //array[i]�� array[j]���� ũ�� ���� �ٲ�
					tmp = array[i];
					array[i] = array[j];
					array[j] = tmp;
				}
			}
		}
		
		System.out.print("���� ���: ");
		for(int i=0; i<n; i++) {
			System.out.print(array[i]+" "); //���ĵ� array ���� ���ʴ�� ���
		}
		
		sc.close(); //Scanner ����� �� �����Ƿ� ����(close)
	}
}