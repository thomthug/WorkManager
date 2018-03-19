package com.negafuma.gui.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "subcategories")
@Table(name = "subcategories")
public class SubCategory  implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2596170375214344610L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="subcategory_id")
	private Integer subCategoryId;

	@Column(name="subcategory_title",unique = true)
    private String subCategoryTitle;

    /** Joins **/
	@ManyToMany(mappedBy = "subCategoryCollection")
    private Collection<Category> categoryCollection;

	/*** getter & setter ***/

	public Integer getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryTitle() {
		return subCategoryTitle;
	}

	public void setSubCategoryTitle(String subCategoryTitle) {
		this.subCategoryTitle = subCategoryTitle;
	}

	public Collection<Category> getCategoryCollection() {
		return categoryCollection;
	}

	public void setCategoryCollection(Collection<Category> categoryCollection) {
		this.categoryCollection = categoryCollection;
	}
}
