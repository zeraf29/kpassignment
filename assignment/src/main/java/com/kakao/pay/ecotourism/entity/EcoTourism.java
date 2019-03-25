package com.kakao.pay.ecotourism.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter
@NoArgsConstructor
public class EcoTourism {
	
	@Id
	@Column(length=100)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idx;
	@Column(length=150,nullable = false)
	private String prgm_name;
	@Column(length=150,nullable = false)
	private String theme;
	@Column(length=125,nullable = false)
	private String region;
	@Column(length=255)
	private String introduce;
	@Column(length=500) //for any RDBMS
	private String specfic;
	
}
