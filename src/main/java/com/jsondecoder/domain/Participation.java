package com.jsondecoder.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@Entity
@Table(name="CHObjects_Participants_Roles")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Participation {
	
	@JsonUnwrapped
	private Participant participant;
	@JsonUnwrapped
	private Role role;

	public Participant getParticipant() {
		return participant;
	}
	public void setParticipant(Participant participant) {
		this.participant = participant;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "Participation [participant=" + participant + ", role=" + role + "]";
	}
}
