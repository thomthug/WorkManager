package com.negafuma.gui.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "subworks")
@Table(name = "subworks")
public class SubWork implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="subwork_id")
	private Integer subWorkId;

	@Column(name="subwork_title")
	private String subWorkTitle;

	@Column(name="subwork_description",columnDefinition="TEXT")
	private String subWorkDescription;

	@Column(name="subwork_creation_date")
	private Date subWorkCreationDate;

	@Column(name="subwork_tag",length = 1)
	private String subWorkTag;

	/** Joins **/
	@ManyToOne
	@JoinColumn(name="medium_id")
	private Medium medium;

	@ManyToOne
	@JoinColumn(name="work_id")
	private Work work;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "subworks_pictures",
            joinColumns = { @JoinColumn(name = "subwork_id") },
            inverseJoinColumns = { @JoinColumn(name = "picture_id") }
        )
	private Set<Picture> picturesCollection;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "subworks_nationalities",
            joinColumns = { @JoinColumn(name = "subwork_id") },
            inverseJoinColumns = { @JoinColumn(name = "nationality_id") }
        )
	private Set<Nationality> nationalityCollection;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "subworks_categories",
            joinColumns = { @JoinColumn(name = "subwork_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
        )
	private Set<Category> categoryCollection;

	/**** constructor ****/
	public SubWork() {
		this.medium = new Medium();
		this.work = new Work();
	}

	/*** getter & setter ***/

	public Medium getMedium() {
		return medium;
	}

	public Integer getSubWorkId() {
		return subWorkId;
	}

	public void setSubWorkId(Integer subWorkId) {
		this.subWorkId = subWorkId;
	}

	public String getSubWorkTitle() {
		return subWorkTitle;
	}

	public void setSubWorkTitle(String subWorkTitle) {
		this.subWorkTitle = subWorkTitle;
	}

	public String getSubWorkDescription() {
		return subWorkDescription;
	}

	public void setSubWorkDescription(String subWorkDescription) {
		this.subWorkDescription = subWorkDescription;
	}

	public Date getSubWorkCreationDate() {
		return subWorkCreationDate;
	}

	public void setSubWorkCreationDate(Date subWorkCreationDate) {
		this.subWorkCreationDate = subWorkCreationDate;
	}

	public String getSubWorkTag() {
		return subWorkTag;
	}

	public void setSubWorkTag(String subWorkTag) {
		this.subWorkTag = subWorkTag;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public void setMedium(Medium medium) {
		this.medium = medium;
	}

	public Set<Picture> getPicturesCollection() {
		return picturesCollection;
	}

	public void setPicturesCollection(Set<Picture> picturesCollection) {
		this.picturesCollection = picturesCollection;
	}

	public Set<Nationality> getNationalityCollection() {
		return nationalityCollection;
	}

	public void setNationalityCollection(Set<Nationality> nationalityCollection) {
		this.nationalityCollection = nationalityCollection;
	}
}
