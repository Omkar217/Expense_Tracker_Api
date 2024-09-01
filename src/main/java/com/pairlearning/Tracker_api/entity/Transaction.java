package com.pairlearning.Tracker_api.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "et_transaction")
@Setter
@Getter
public class Transaction 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
	@SequenceGenerator(name = "transaction_seq", sequenceName = "et_transactions_seq", allocationSize = 1)
	@Column(name = "transaction_id")
	private Integer transaction_id;
	
	@JoinColumn(name = "user_id")
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private User user;
	
	@JoinColumn(name = "category_id")
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private Set<Category> setOfCategories = new HashSet<>();
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "transaction_date")
	private LocalDateTime time;

}
