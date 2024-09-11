package com.pairlearning.Tracker_api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pairlearning.Tracker_api.entity.Category;
import com.pairlearning.Tracker_api.entity.Transaction;
import com.pairlearning.Tracker_api.entity.User;
import com.pairlearning.Tracker_api.repo.CategoryRepo;
import com.pairlearning.Tracker_api.repo.TransactionRepo;
import com.pairlearning.Tracker_api.services.CategoryService;
import com.pairlearning.Tracker_api.services.TransactionService;

import io.jsonwebtoken.lang.Assert;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TrackerApiApplicationTests {
	
		@Autowired
		private CategoryRepo catRepo;
		
		@Autowired
		private TransactionRepo transRepo;
		
		@Autowired
		CategoryService cateServ;

//		@Test
//		void testcatRep() {
//			
//	      var user2 = new User(2, "test1Name", "test1Surname", "test@123", "0000", null, null, null);
//	      
//	      var category2 = new Category(1, user2, 2,null, "accessories", "this is accessories", 45000.00);
//	
//	      catRepo.save(category2);   
//			
//	
//		}
	
//		@Test
//		void testTransRep()
//		{
//		      Set<Category> setOfCat = new HashSet<>();
//		      
//		      var user3 = new User(3, "Keshav", "tomar", "keshav@12", "123", null, null, null);
//		      
//		      var category3 = new Category(2, user3, 9 ,null, "titanium", "this is t", 45000.00);
//		      
//		      setOfCat.add(category3);
//		      
//			  var trans1 = new Transaction(1, user3, setOfCat, 45000.00,  LocalDate.now().atStartOfDay());
//	
//		      transRepo.save(trans1);
//		}

	    @Test
	    public void testTransactionRepo()
	    {
	    	cateServ.getCatByUserService("cheriyal@123");
	    	System.out.println();
	    }
        

	

}
