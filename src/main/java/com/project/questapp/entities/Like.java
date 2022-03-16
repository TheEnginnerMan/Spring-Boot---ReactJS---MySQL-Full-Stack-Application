package com.project.questapp.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "p_like")
public class Like {
	@Id
	private Integer id;
	private Integer postId;
	private Integer userId;
}
