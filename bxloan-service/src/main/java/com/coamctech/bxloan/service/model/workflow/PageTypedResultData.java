package com.coamctech.bxloan.service.model.workflow;

import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageTypedResultData<T> implements Page<T>{
	private Page<T> pager=null;

	private List<T> content = null;
	private String first;
	private String firstPage;
	private String last;
	private String lastPage;
	private String number;//number:第几条开始
	private String numberOfElements;//numberOfElements:第几条结束
	private String size;
	private String totalElements;
	private String totalPages;
	
	public void setContent(List<T> content) {
		this.content = content;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public void setLastPage(String lastPage) {
		this.lastPage = lastPage;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public void setNumberOfElements(String numberOfElements) {
		this.numberOfElements = numberOfElements;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void setTotalElements(String totalElements) {
		this.totalElements = totalElements;
	}
	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}
	

	private void checkPagerInit(){
		if(this.pager == null){
			int pageSize = Integer.parseInt(size);
			int curPageIndex = Integer.parseInt(number)/pageSize;
			int total = Integer.parseInt(this.totalElements);
			PageRequest pageReq = new PageRequest(curPageIndex, pageSize);
			this.pager = new PageImpl<T>(content, pageReq, total);
		}
	}
	
	@Override
	public int getNumber() {
		checkPagerInit();
		return pager.getNumber();
	}
	@Override
	public int getSize() {
		checkPagerInit();
		return pager.getSize();
	}
	@Override
	public int getNumberOfElements() {
		checkPagerInit();
		return pager.getNumberOfElements();
	}
	@Override
	public List<T> getContent() {
		checkPagerInit();
		return pager.getContent();
	}
	@Override
	public boolean hasContent() {
		checkPagerInit();
		return pager.hasContent();
	}
	@Override
	public Sort getSort() {
		checkPagerInit();
		return pager.getSort();
	}
	@Override
	public boolean isFirst() {
		checkPagerInit();
		return pager.isFirst();
	}
	@Override
	public boolean isLast() {
		checkPagerInit();
		return pager.isLast();
	}
	@Override
	public boolean hasNext() {
		checkPagerInit();
		return this.pager.hasNext();
	}
	@Override
	public boolean hasPrevious() {
		checkPagerInit();
		return this.pager.hasPrevious();
	}
	@Override
	public Pageable nextPageable() {
		checkPagerInit();
		return this.pager.nextPageable();
	}
	@Override
	public Pageable previousPageable() {
		checkPagerInit();
		return this.pager.previousPageable();
	}
	@Override
	public Iterator<T> iterator() {
		checkPagerInit();
		return this.pager.iterator();
	}
	@Override
	public int getTotalPages() {
		checkPagerInit();
		return this.pager.getTotalPages();
	}
	@Override
	public long getTotalElements() {
		checkPagerInit();
		return this.pager.getTotalElements();
	}
	@Override
	public boolean hasPreviousPage() {
		checkPagerInit();
		return this.pager.hasPreviousPage();
	}
	@Override
	public boolean isFirstPage() {
		checkPagerInit();
		return this.pager.isFirstPage();
	}
	@Override
	public boolean hasNextPage() {
		checkPagerInit();
		return this.pager.hasNextPage();
	}
	@Override
	public boolean isLastPage() {
		checkPagerInit();
		return this.pager.isLastPage();
	}
	
}
