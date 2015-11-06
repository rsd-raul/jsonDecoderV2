package com.jsondecoder.domain;


import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name="CHObjects")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CHObject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String title;
	@JsonProperty("date")
	private String dateObject;
	private String medium;
	private String creditline;
	private String description;
	private String gallery_text;

	@OneToMany(mappedBy="chObjects")
	private List<Participation> participants;
	@OneToMany(mappedBy="chObjects")
	private List<Map<String,Image>> images;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDateObject() {
		return dateObject;
	}
	public void setDateObject(String dateObject) {
		this.dateObject = dateObject;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}
	public String getCreditline() {
		return creditline;
	}
	public void setCreditline(String creditline) {
		this.creditline = creditline;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGallery_text() {
		return gallery_text;
	}
	public void setGallery_text(String gallery_text) {
		this.gallery_text = gallery_text;
	}
	public List<Participation> getParticipants() {
		return participants;
	}
	public void setParticipants(List<Participation> participants) {
		this.participants = participants;
	}
	public List<Map<String,Image>> getImages() {
		return images;
	}
	public void setImages(List<Map<String,Image>> images) {
		this.images = images;
	}
	
	@Override
	public String toString() {
		return "CHObject [id=" + id + ", title=" + title + ", dateObject=" + dateObject + ", medium=" + medium + ", creditline="
				+ creditline + ", description=" + description + ", gallery_text=" + gallery_text + ", participations="
				+ participants + ", images=" + images + "]";
	}

}