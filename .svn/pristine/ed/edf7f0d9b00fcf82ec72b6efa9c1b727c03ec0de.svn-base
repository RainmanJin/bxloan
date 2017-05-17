package com.coamctech.bxloan.service.model.workflow;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
 * 查询已办列表VO
 * @author xc
 */
public class CompleteTaskSearchVO {
	
	public Object[] toArray(){
		List<Object> result = new ArrayList<Object>();
		PropertyDescriptor[] propDescArr = BeanUtils.getPropertyDescriptors(this.getClass());
		for (PropertyDescriptor propDesc : propDescArr) {
			Method read = propDesc.getReadMethod();
			read.setAccessible(Boolean.TRUE);
			try {
				Object prop = read.invoke(this);
				result.add(prop);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return result.toArray();
	}
	
}
