package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "INFO_VALUES", schema = WD_SCHEMA)
public class InfoValues implements java.io.Serializable {

	private Long id;
	private String value;
	private Long subjectId;
	private String name;


	/** default constructor */
	public InfoValues() {
	}

	/** minimal constructor */
	public InfoValues(Long id) {
		this.id = id;
	}

	@Id
	@SequenceGenerator(name = "generator", sequenceName="SEQ_APPROVAL_APPLY_INFO_VALUES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "VALUE", length = 2000)
	public String getValue() {
		return this.value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Column(name = "SUBJECT_ID", precision = 12, scale = 0)
	public Long getSubjectId() {
		return this.subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	@Column(name = "NAME", length = 200)
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

}