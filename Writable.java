package cn.edu.tju.ex02;

import java.util.*;

public interface Writable {
	/*
	 * 用于将一个list中数据输出到一个文件中的接口
	 */
	public void write(List<Word> list, String target) throws Exception;
	
}
