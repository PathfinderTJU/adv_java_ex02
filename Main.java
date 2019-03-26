package cn.edu.tju.ex02;

public class Main {
	
	/*
	 * 测试类
	 */
	
	public static void main(String args[]) {
		FrequentSearch f = new FrequentSearch();
		try {
			f.search("了不起的盖茨比(英文版).txt", "output.txt");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
