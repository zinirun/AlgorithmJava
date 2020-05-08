package algo;
import java.io.File;
import java.io.FileReader;
import java.nio.CharBuffer;

class Minheap {	// 최소 히프 클래스
	Element heap[];     // 히프 트리 배열
	int size;		// 히프 원소의 개수


	Minheap(int len) 
	{
		heap = new Element[len+1];
		for(int i=0; i<len+1; i++)
		{
			heap[i] = new Element();
			heap[i].tree = new TreeNode();
		}
		size = 0;
	}
	public void Insert(Element e) // 히프 삽입 메소드
	{
		int curIndex;
		curIndex = ++size;

		while((curIndex != 1) && (e.key < heap[curIndex/2].key))
		{
			heap[curIndex] = heap[curIndex/2];
			curIndex /= 2;
		}
		heap[curIndex]= e;
	}

	public Element Remove() // 히프 삭제 메소드
	{
		int parent, child;
		Element rdata, temp;

		if(size<0)
			return null;
		rdata = heap[1];
		temp = heap[size--];
		parent = 1;
		child = 2;

		while(child <= size)
		{
			if((child < size)&& (heap[child].key > heap[child+1].key))
				child++;
			if(temp.key <= heap[child].key)
				break;
			heap[parent]= heap[child];
			parent = child;
			child *= 2;
		}

		heap[parent] = temp;
		return rdata;
	}

}

class Element {
	TreeNode tree; 
	int key; 

	Element()
	{
		tree = null;
		key = 0;
	}
	Element(Element e)
	{
		tree = e.tree;
		key = e.key;
	}
}

class TreeNode {
	char symbol;             // 알파벳(Symbol) 저장
	int weight;              // 빈도 수
	TreeNode leftChild;      // 트리의 왼쪽 자식
	TreeNode rightChild;     // 트리의 오른쪽 자식

	TreeNode(){}
	TreeNode(TreeNode left, TreeNode right)
	{
		leftChild = left;
		rightChild = right;
	}

}

class Count {
	HuffmanTree huff = new HuffmanTree();

	int i, size;
	char alphabet[] = new char[27];	// 알파벳 저장할 배열
	int count[] = new int[27];	// 빈도 수 저장할 배열

	Count()
	{
		for(i=0; i<26; i++)
			alphabet[i] = (char)(i+97);
		alphabet[i] = ' ';
		
		for(i=0; i<27; i++)
			count[i] = 0;
		size = alphabet.length;
	}

	public void countfile() throws Exception // 빈도 수 count 메소드
	{
		String path = Rep3_Huffman.class.getResource("").getPath()+"Rep3_test.txt";	// src 디렉토리의 txt 파일경로(상대경로)
		FileReader f = new FileReader(path);
		System.out.print("Read text: ");

		i=0;
		while((i=f.read()) != -1)// 문자(char)에 따라 count
		{
			char ch = (char)i;
			System.out.print(ch);

			if(ch>=97 && ch <=122)
				count[i-97]++;
			else if(ch==32)
				count[size-1]++;
			else
				continue;
		}
		System.out.println();

		f.close();

		int rsize=0;
		for(int j=0; j<count.length-rsize; j++)
		{
			if(count[j]==0) // 빈도 수가 0일 경우 배열 크기 감소
			{
				for(int k=j; k<count.length-1; k++)
				{
					count[k] = count[k+1];
					alphabet[k] = alphabet[k+1];

				}
				j--;
				rsize++; 	// 빈도 수가 0인 알파벳의 수만큼 증가
			}
		}
		size = size - rsize;	// 빈도수가 0인 알파벳 제외

	}

	public void countprint()
	{
		for(i=0; i<size; i++)	// 문자 출력
			System.out.printf("%5c",alphabet[i]);
		System.out.println();

		for(i=0; i<size; i++)	// 빈도 수 출력
			System.out.printf("%5d",count[i]);
		System.out.println();

	}
}

class HuffmanTree {
	public TreeNode MakeHuffmanTree() throws Exception
	{
		Minheap heap = new Minheap(27);
		Count count = new Count();
		Element e1, e2, e3;

		count.countfile();		// 알파벳 count
		count.countprint();	// count result 출력

		for(int i=0; i<count.size; i++)	// 빈도 수 작은 것부터 heap insert
		{
			TreeNode node =  new TreeNode();
			node.symbol = count.alphabet[i];
			e1 = new Element();
			e1.key = node.weight = count.count[i];
			e1.tree = node;
			heap.Insert(e1);
		}

		for(int i=1; i<count.size; i++)	// huffman tree 생성
		{
			e1 = new Element();
			e2 = new Element(heap.Remove());
			e3 = new Element(heap.Remove());
			TreeNode x = new TreeNode(e2.tree, e3.tree);
			e1.key = x.weight = e2.key + e3.key;
			e1.tree = x;
			heap.Insert(e1);
		}
		e1 = new Element(heap.Remove());

		return e1.tree;
	}


	public void HuffmanPrint(TreeNode root, String str) // Huffman code 출력
	{
		if(root == null)
		{
			return;
		}
		else if(root.leftChild == null && root.rightChild == null)
		{
			System.out.println(root.symbol +": "+ str);
		}
		else
		{
			String code = str;
			code += "0";
			HuffmanPrint(root.leftChild, code);
			code = str;
			code += "1";
			HuffmanPrint(root.rightChild, code);
		}
	}
}

public class Rep3_Huffman {
	public static void main(String[] args) throws Exception	{
		HuffmanTree huff = new HuffmanTree(); // Huffman Tree 선언
		String str = ""; // Code 출력할 str 초기화
		TreeNode root =new TreeNode(); 

		root = huff.MakeHuffmanTree();
		System.out.println("Huffman Code");
		huff.HuffmanPrint(root, str);
	}
}

