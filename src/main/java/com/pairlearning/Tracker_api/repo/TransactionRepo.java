package com.pairlearning.Tracker_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pairlearning.Tracker_api.entity.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

	@Query("Select t from Transaction t where t.user.id =: userId")
	Transaction getByTransName(int userId);
	
	

}
