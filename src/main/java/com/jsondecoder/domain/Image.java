package com.jsondecoder.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="Images")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
	
	private String url;
    private int width;
    private int height;
    private int is_primary;
    @JsonProperty("image_id")
    private int id;
    
    
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getIs_primary() {
		return is_primary;
	}
	public void setIs_primary(int is_primary) {
		this.is_primary = is_primary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Image [url=" + url + ", width=" + width + ", height=" + height + ", is_primary=" + is_primary + ", id="
				+ id + "]";
	}

    
}
