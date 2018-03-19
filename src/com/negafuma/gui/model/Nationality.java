package com.negafuma.gui.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "nationalities")
@Table(name = "nationalities")
public class Nationality  implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2596170375214344610L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="nationality_id")
	private Integer nationalityId;

	@Column(name="nationality_title",unique = true)
    private String nationalityTitle;

	@Lob
    @Basic(fetch = FetchType.LAZY)
	@Column(name="nationality_logo")
    private byte[] nationalityLogo;

    /** Joins **/
	@ManyToMany(mappedBy = "nationalityCollection")
    private Set<SubWork> subWorksCollection;

	/*** getter & setter ***/

	public Set<SubWork> getSubWorksCollection() {
		return subWorksCollection;
	}

	public Integer getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(Integer nationalityId) {
		this.nationalityId = nationalityId;
	}

	public String getNationalityTitle() {
		return nationalityTitle;
	}

	public byte[] getNationalityLogo() {
		return nationalityLogo;
	}

	public void setNationalityLogo(byte[] nationalityLogo) {
		this.nationalityLogo = nationalityLogo;
	}

	public void setNationalityTitle(String nationalityTitle) {
		this.nationalityTitle = nationalityTitle;
	}

	public void setSubWorksCollection(Set<SubWork> subWorksCollection) {
		this.subWorksCollection = subWorksCollection;
	}
}
