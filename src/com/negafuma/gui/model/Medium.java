package com.negafuma.gui.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "mediums")
@Table(name = "mediums")
public class Medium  implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2596170375214344610L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="medium_id")
	private Integer mediumId;

	@Column(name="medium_title",unique = true)
    private String mediumTitle;

	@Lob
    @Basic(fetch = FetchType.LAZY)
	@Column(name="medium_logo")
    private byte[] mediumLogo;

    /** Joins **/
	@OneToMany(mappedBy = "medium")
    private Collection<SubWork> subWorksCollection;

	/*** getter & setter ***/

	public Collection<SubWork> getSubWorksCollection() {
		return subWorksCollection;
	}

	public Integer getMediumId() {
		return mediumId;
	}

	public void setMediumId(Integer mediumId) {
		this.mediumId = mediumId;
	}

	public String getMediumTitle() {
		return mediumTitle;
	}

	public void setMediumTitle(String mediumTitle) {
		this.mediumTitle = mediumTitle;
	}



	public byte[] getMediumLogo() {
		return mediumLogo;
	}

	public void setMediumLogo(byte[] mediumLogo) {
		this.mediumLogo = mediumLogo;
	}

	public void setSubWorksCollection(Collection<SubWork> subWorksCollection) {
		this.subWorksCollection = subWorksCollection;
	}
}
