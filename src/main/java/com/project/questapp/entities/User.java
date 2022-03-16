package com.project.questapp.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "User")
public class User {
	@Id
	private Integer id;
	private String username;
	private String password;

}