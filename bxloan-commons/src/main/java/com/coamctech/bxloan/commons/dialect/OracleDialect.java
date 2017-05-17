package com.coamctech.bxloan.commons.dialect;

import java.sql.Types;

import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.type.StringType;

public class OracleDialect extends Oracle10gDialect{

	public OracleDialect(){
		super();
		this.registerHibernateType(Types.NVARCHAR, StringType.INSTANCE.getName());
	}
}
