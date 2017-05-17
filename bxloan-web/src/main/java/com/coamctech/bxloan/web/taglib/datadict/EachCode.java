package com.coamctech.bxloan.web.taglib.datadict;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.core.LoopTagSupport;

import com.coamctech.bxloan.commons.utils.WebAppCtxHolder;
import com.coamctech.bxloan.datadict.CodeData;
import com.coamctech.bxloan.service.DataDictLoader;

/**
 * 遍历指定codeType的CodeData
 * @param var :为CodeData的引用
 * @param varStatus : 为每个被遍历元素的状态.参照 &lt;c:forEach&gt;
 * @author xc
 */
public class EachCode extends LoopTagSupport{
	
	private DataDictLoader dd = WebAppCtxHolder.getBean(DataDictLoader.class);
	
	private String codeType;
	
	private Iterator<CodeData> iterator = null;

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	
	@Override
	protected void prepare() throws JspTagException {
		Collection<CodeData> collect = dd.getOneType(codeType);
		this.iterator = collect.iterator();
	}
	
	@Override
	protected boolean hasNext() throws JspTagException {
		return this.iterator.hasNext();
	}
	
	@Override
	protected Object next() throws JspTagException {
		return this.iterator.next();
	}
	
}
