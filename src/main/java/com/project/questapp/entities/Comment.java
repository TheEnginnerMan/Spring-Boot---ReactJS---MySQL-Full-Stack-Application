package com.project.questapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "comment")
public class Comment {

	@Id
	private Integer id;
	private Integer postId;
	private Integer userId;
	@Lob
	@Column(columnDefinition = "text")
	private String text;
}
