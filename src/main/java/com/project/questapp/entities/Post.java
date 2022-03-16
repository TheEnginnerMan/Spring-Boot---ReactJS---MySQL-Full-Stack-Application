package com.project.questapp.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;

public class Post {
	@Id
	private Integer id;
	private Integer userId;
	private String title;
	@Lob			//Resim, dosya gibi büyük boyutlu veri türlerini ifade etmek için kullanılır.
	@Column(columnDefinition = "text") //hibernatin mysqlde Stringi text olarak algılaması için yapıldı yoksa varchar(255) alırdı
	private String text;

}
