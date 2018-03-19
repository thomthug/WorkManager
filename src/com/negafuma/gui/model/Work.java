package com.negafuma.gui.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "works")
@Table(name = "works")
public class Work implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="work_id")
	private Integer workId;

	@Column(name="work_title", nullable=false, unique=true)
	private String workTitle;

	@Column(name="work_description",columnDefinition="TEXT")
	private String workDescription;

	/** Joins **/

	@OneToMany(mappedBy = "work", cascade=CascadeType.ALL)
    private Collection<SubWork> subWorksCollection;

	/*** getter & setter ***/

	public Integer getWorkId() {
		return workId;
	}

	public void setWorkId(Integer workId) {
		this.workId = workId;
	}

	public String getWorkTitle() {
		return workTitle;
	}

	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}

	public String getWorkDescription() {
		return workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}

	public Collection<SubWork> getSubWorksCollection() {
		return subWorksCollection;
	}

	public void setSubWorksCollection(Collection<SubWork> subWorksCollection) {
		this.subWorksCollection = subWorksCollection;
	}
}
