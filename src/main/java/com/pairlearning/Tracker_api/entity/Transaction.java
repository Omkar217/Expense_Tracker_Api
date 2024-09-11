package com.pairlearning.Tracker_api.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Transaction")
@Table(name = "et_transaction")
@Setter
@Getter
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
	@SequenceGenerator(name = "transaction_seq", sequenceName = "et_transactions_seq", allocationSize = 1)
	@Column(name = "transaction_id")
	private int transaction_id;
	
	@Nullable
	@JsonProperty("user_id")
	@JoinColumn(name = "user_id")
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private User user;
	
	
	@Nullable
	@JoinColumn(name = "cat_ref_trans")//referencing null values due to hibernate issues..
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private Set<Category> setOfCategories = new HashSet<>();
	
	@JsonProperty("amount")
	@Nullable
	@Column(name = "amount")
	private double amount;
	
	
	@JsonProperty("transaction_date")
	@Nullable
	@Column(name = "transaction_date")
	private LocalDateTime time;

}
