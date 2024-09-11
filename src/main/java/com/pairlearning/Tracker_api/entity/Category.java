package com.pairlearning.Tracker_api.entity;

import org.hibernate.annotations.DynamicInsert;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "et_categories")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Integer category_id;
	
	
	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@JoinColumn(name = "cat_ref_trans")
	@Nullable
	@ManyToOne(fetch = FetchType.LAZY)
	private Integer cat_ref_trans;
	
	@Nullable
	@JoinColumn(name = "transaction_id", nullable = true)
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Transaction trans;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "category_expense")
	private Double cateExpense;
	


}