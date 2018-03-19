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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.primefaces.model.StreamedContent;

@Entity(name="pictures")
@Table(name="pictures")
public class Picture implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = -3118664009371584495L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="picture_id")
	private Integer pictureId;

	@Column(name="picture_title",unique = true)
    private String pictureTitle;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="picture_content")
    private byte[] pictureContent;

    @Column(name="picture_mime_type")
    private String pictureMimeType;

    @Column(name="picture_is_main")
    private Boolean pictureIsMain;

    /** Joins **/
    @ManyToMany(mappedBy = "picturesCollection",fetch = FetchType.EAGER)
    Collection<SubWork> worksCollection;

    /** transient **/
    transient private StreamedContent bufferedImage;

    /*** getter & setter ***/

    public Integer getPictureId() {
		return pictureId;
	}

	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}

	public String getPictureTitle() {
		return pictureTitle;
	}

	public void setPictureTitle(String pictureTitle) {
		this.pictureTitle = pictureTitle;
	}

	public byte[] getPictureContent() {
		return pictureContent;
	}

	public void setPictureContent(byte[] pictureContent) {
		this.pictureContent = pictureContent;
	}

	public String getPictureMimeType() {
		return pictureMimeType;
	}

	public void setPictureMimeType(String pictureMimeType) {
		this.pictureMimeType = pictureMimeType;
	}

	public Boolean getPictureIsMain() {
		return pictureIsMain;
	}

	public void setPictureIsMain(Boolean pictureIsMain) {
		this.pictureIsMain = pictureIsMain;
	}

	/******/
	public Collection<SubWork> getWorksCollection() {
		return worksCollection;
	}

	public void setWorksCollection(Collection<SubWork> worksCollection) {
		this.worksCollection = worksCollection;
	}

	/******/
	public StreamedContent getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(StreamedContent bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
}
