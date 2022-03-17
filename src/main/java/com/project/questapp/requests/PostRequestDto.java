package com.project.questapp.requests;

import lombok.Data;

@Data
public class PostRequestDto {

	private Integer id;
	private String text;
	private String title;
	private Integer userId;
}
