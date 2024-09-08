//package com.pairlearning.Tracker_api.payload;
//
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.Set;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.pairlearning.Tracker_api.entity.Category;
//
//import jakarta.annotation.Nullable;
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToMany;
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class TransactionDetail {
//
//	@JsonProperty("user_id")
//	private Optional<User> user;
//	
//	@JsonProperty("category_id")
//	@Nullable
//	private Set<Category> setOfCategories = new HashSet<>();
//	
//	@JsonProperty("amount")
//	@Nullable
//	@Column(name = "amount")
//	private double amount;
//	
//	@JsonProperty("note")
//	@Nullable
//	@Column(name = "note")
//	private String note;
//	
//	@JsonProperty("transaction_date")
//	@Nullable
//	@Column(name = "transaction_date")
//	private LocalDateTime time;
//}
