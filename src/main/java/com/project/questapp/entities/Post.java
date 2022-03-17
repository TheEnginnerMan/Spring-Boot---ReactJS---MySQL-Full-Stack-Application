package com.project.questapp.entities;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Post {
	@Id
	private Integer id;

	// private Integer userId; user entity classı olduğundan dolayı userId
	// kullanılmayacak
	// FetchType.Lazy kullanılmasının nedeni User objesini databaseden hemen
	// çekmemesi için.Post objesi çekildiğinde ilgili user i getirmesi
	// engelleniyor.FetchType.EAGER yazılırsa post çağrıldığında user objeside
	// çağrılır.
	// @JoinColumn(name="user_id") database de post tablosu içersindeki user_id
	// alanının user tablosuna bağlandığını belirlemek için kullanılır.
	// nullable= false boş geçilemez anlamına gelir.
	// @OnDelete(action = OnDeleteAction.CASCADE) bunun kullanılmasının nedeni user
	// tablosundan bir user silindiğinde bu userin bütün postlarını post tablosundan
	// sil anlamına geliyor.Çünkü user silindiğinde onunla ilgili postlar havada
	// kalır.
	// @JsonIgnore kullanım nedeni Serialization kısmında problem yaratmaması için
	// kullanıldığı yerdeki alanı ignore eder
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;

	private String title;

	// Resim, dosya gibi büyük boyutlu veri türlerini ifade etmek için kullanılır.
	@Lob
	@Column(columnDefinition = "text") // hibernatin mysqlde Stringi text olarak algılaması için yapıldı yoksa
										// varchar(255) alırdı
	private String text;

}
