package cn.edu.tju.ex02;

public class Main {
	
	/*
	 * ������
	 */
	
	public static void main(String args[]) {
		FrequentSearch f = new FrequentSearch();
		try {
			f.search("�˲���ĸǴı�(Ӣ�İ�).txt", "output.txt");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
