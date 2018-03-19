package com.negafuma.gui.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.Table;

@Entity(name = "categories")
@Table(name = "categories")
public class Category  implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2596170375214344610L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="category_id")
	private Integer categoryId;

	@Column(name="category_title",unique = true)
    private String categoryTitle;

    /** Joins **/
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@Fetch(value = FetchMode.SUBSELECT)
	@ManyToMany(mappedBy = "categoryCollection")
    private Set<SubWork> subWorksCollection;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "categories_subcategories",
            joinColumns = { @JoinColumn(name = "category_id") },
            inverseJoinColumns = { @JoinColumn(name = "subcategory_id") }
        )
	private Collection<Picture> subCategoryCollection;

	/*** getter & setter ***/

	public Set<SubWork> getSubWorksCollection() {
		return subWorksCollection;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public void setSubWorksCollection(Set<SubWork> subWorksCollection) {
		this.subWorksCollection = subWorksCollection;
	}
}
