package com.pairlearning.Tracker_api.repo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pairlearning.Tracker_api.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> 
{
	@Query("SELECT c FROM Category c WHERE c.user.id = :userId")
	List<Category> findCategoriesByUserId(@Param("userId") int userId);

	
	
}
