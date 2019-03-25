package com.kakao.pay.ecotourism.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Embeddable
public class EcoTourismId implements Serializable{
	//EcoTourism 엔티티의 복합키 정의를 위한 클래스
	
	@Column(length=100)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idx;
	
	@Column(length=100)
	private String reg_code;

}
