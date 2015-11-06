package com.jsondecoder.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="Participants")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Participant {

	@JsonProperty("person_id")
	private int id;
	@JsonProperty("person_name")
	private String name;
	@JsonProperty("person_date")
	private String birth;
	@JsonProperty("person_url")
	private String url;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "Participant [id=" + id + ", name=" + name + ", birth=" + birth + ", url=" + url + "]";
	}
}