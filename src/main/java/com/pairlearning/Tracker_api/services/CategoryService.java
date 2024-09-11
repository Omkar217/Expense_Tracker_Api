package com.pairlearning.Tracker_api.services;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.pairlearning.Tracker_api.entity.Category;
import com.pairlearning.Tracker_api.entity.Transaction;
import com.pairlearning.Tracker_api.entity.User;
import com.pairlearning.Tracker_api.repo.CategoryRepo;
import com.pairlearning.Tracker_api.repo.TransactionRepo;
import com.pairlearning.Tracker_api.repo.UserRepository;
import com.pairlearning.Tracker_api.resources.AuthenticationController;

@Service
public class CategoryService implements CategorySerInt {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
	
	@Autowired
	private CategoryRepo catRepo;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private TransactionRepo transRep;
		
	@Autowired
	private UserRepository userRep;
	
	
	public Category saveTheCategory(String title, String description, Double catExpense, String email)
	{	
		    Transaction transaction = new Transaction();

			User  userObj =  (User)userDetailsService.loadUserByUsername(email); 
				
			Category category = new Category();
									
			Double total = userObj.getTotalexpense();
						
			if(total == null) {
				userObj.setTotalexpense(catExpense);				
				transaction.setAmount(catExpense);
			}else{
				total += catExpense;	
				userObj.setTotalexpense(total);
				transaction.setAmount(total);
			}
				
			category.setCateExpense(catExpense);
			
			category.setUser(userObj);
		
			category.setTitle(title);
		
			category.setDescription(description);
							
			catRepo.save(category);

			userRep.save(userObj);
			
			logger.info("Saved Categories.....");
			
			transaction.setUser(userObj);
			
			transaction.setTime(LocalDateTime.now());
												
			transRep.save(transaction);
										
			logger.info("Saved the Transactions...");
									
			return category;
	}
	
	public List<Category> getCatByUserService(String email)
	{
		User user1 =  (User) userDetailsService.loadUserByUsername(email); 
		
		Integer userId = user1.getUser_id();
		
		List<Category> catList = catRepo.findCategoriesByUserId(userId);
				
		return catList;
	}
	
	public Category updateTheCategory(Integer categoryId,Category theCategory)
	{
		Category retrievedCategory = catRepo.findById(categoryId).orElseThrow();
		
		retrievedCategory.setTitle(theCategory.getTitle());
		
		retrievedCategory.setDescription(theCategory.getDescription());
		
		retrievedCategory.setCateExpense(theCategory.getCateExpense());
        
		return catRepo.save(retrievedCategory);    
	}
	
	
	public Category getCatByUserServiceById(String email,int catId)
	{
		User user1 =  (User)userDetailsService.loadUserByUsername(email); 
		
		Integer userId = user1.getUser_id();
		
	    Category category = catRepo.findSpecificCategoryByUserId(userId, catId);
	    
	    return category;
	}

}
