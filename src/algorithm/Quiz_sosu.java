package algorithm;
//����Ʈ�����а� ������
public class Quiz_sosu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tmp, cnt=0;
		for(int i=1; i<=100; i++) {
			tmp = 0;
			for(int j=1; j<=i; j++) {
				if(i%j == 0) tmp++;
			}
			if(tmp == 2) cnt++;
		}
		System.out.println("1���� 100���� �Ҽ��� ����: " + cnt + "��");
	}

}