package cn.edu.tju.ex02;

public class Word implements Comparable{
	
	/*
	 * ���ڴ�ŵ��ʼ�����Ƶ�ʵĶ��󣬱�������
	 */
	
	private String key;	//������
	private Integer times;	//����Ƶ��
	
	public Word() {
		
	}
	
	public Word(String key, Integer times) {
		this.key = key;
		this.times = times;
	}

	@Override	//��дequals���������ҽ�������word����key��times�����ʱ����true
	public boolean equals(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		
		if (o instanceof Word) {
			Word that = (Word) o;
			if (this.times.equals(that.times) && this.key.equals(that.key)) {
				return true;
			}else {
				return false;
			}
		}else {
			throw new IllegalArgumentException();
		}
	}
	
	/*
	 * ��дcompareTo��������������
	 * ����times��С������Ϊ��������
	 */
	@Override	
	public int compareTo(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		
		if (o instanceof Word) {
			Word that = (Word) o;
			if (this.times.equals(that.times) && this.key.equals(that.key)) {
				return 0;
			}else {
				return -Integer.valueOf(this.times.compareTo(that.times));
			}
		}else {
			throw new IllegalArgumentException();
		}
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}
	
}
