package com.sa.web;

import java.util.List;

public class Page<T> {
	//��ǰ�ڼ�ҳ
	private int pageNo;
	
	//��ǰҳ��List
	private List<T> list;
	
	//ÿҳ��ʾ�ж�������¼
	private int pageSize = 10;
	
	//���ж�������¼
	private long totalItemNumber;//�������ͣ���Ϊ�����ݿ��в������long��
	
	//����������Ҫ��pageNo���г�ʼ��
	public Page(int pageNo) {
		super();
		this.pageNo = pageNo;
	}
	
	//��ҪУ��,��ֹҳ�治�Ϸ�
	public int getPageNo() {
		if(pageNo > getTotalPageNumber())
			pageNo = getTotalPageNumber();
		if(pageNo <= 0)
			pageNo = 1;
		return pageNo;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}
	
	public List<T> getList() {
		return list;
	}
	
	//��ȡ��ҳ��
	public int getTotalPageNumber() {
		int totalPageNumber = (int) (totalItemNumber / pageSize);
		if(totalItemNumber % pageSize != 0)
			totalPageNumber++;
		return totalPageNumber;
	}
	
	public void setTotalItemNumber(int totalItemNumber) {
		this.totalItemNumber = totalItemNumber;
	}
	
	public boolean isHasNext() {
		if(getPageNo() < getTotalPageNumber())
			return true;
		return false;
	}
	
	public boolean isHasPrev() {
		if(getPageNo() ==1)
			return false;
		return true;
	}
	
	public int getPrevPage() {
		if(isHasPrev())
			return getPageNo()-1;
		return getPageNo();
	}
	
	public int getNextPage() {
		if(isHasNext())
			return getPageNo()+1;
		return getPageNo();
	}
}
