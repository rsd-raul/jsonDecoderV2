package com.jsondecoder.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="Roles")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Role {
   
	@JsonProperty("role_id")
	private int id;
	@JsonProperty("role_name")
	private String name;
	@JsonProperty("role_display_name")
	private String display_name;
	@JsonProperty("role_url")
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
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", display_name=" + display_name + ", url=" + url + "]";
	}
}