package com.pairlearning.Tracker_api.resources;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pairlearning.Tracker_api.dtos.CateRegDto;
import com.pairlearning.Tracker_api.dtos.LoginResponse;
import com.pairlearning.Tracker_api.dtos.LoginUserDto;
import com.pairlearning.Tracker_api.dtos.RegisterUserDto;
import com.pairlearning.Tracker_api.entity.Category;
import com.pairlearning.Tracker_api.entity.Transaction;
import com.pairlearning.Tracker_api.entity.User;
import com.pairlearning.Tracker_api.exceptions.ApiReqException;
import com.pairlearning.Tracker_api.exceptions.ApiExceptionHandler;
import com.pairlearning.Tracker_api.services.AuthenticationService;
import com.pairlearning.Tracker_api.services.CategorySerInt;
import com.pairlearning.Tracker_api.services.CategoryService;
import com.pairlearning.Tracker_api.services.JwtService;
import com.pairlearning.Tracker_api.services.TransactionService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users")
@RestController
public class AuthenticationController 
{	
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;
      
    private  final CategorySerInt categoryService;
    
    private final TransactionService transServ;
    
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, CategorySerInt categoryService, TransactionService transServ ) 
    {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.categoryService = categoryService;
        this.transServ = transServ;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) 
    {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) 
    {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);
                                
        LoginResponse loginResponse = new LoginResponse();
        
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        
        loginResponse.setToken(jwtToken);
        
        return ResponseEntity.ok(loginResponse);
    }
    
    @PostMapping("/categories")
    public void saveUserCategories(@RequestHeader("Authorization") String authHeader,
    	                                           @RequestBody CateRegDto cateRegDto)
    {    	
    	String token = authHeader.replace("Bearer ", "");
    	 	
        String email = jwtService.extractUsername(token);
        
        if((!cateRegDto.getTitle().isEmpty()) && (!cateRegDto.getDescription().isEmpty()) && (cateRegDto.getCateExpense() != null))
        {
           categoryService.saveTheCategory(cateRegDto.getTitle(), cateRegDto.getDescription(), cateRegDto.getCateExpense(), email);            
        }
        else
        {
        	throw new ApiReqException("partial content or empty content.");
        }
        
    }
    
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories(@RequestHeader("Authorization") String authHeader) 
    {
    	String token = authHeader.replace("Bearer ", "");
	 	
        String email = jwtService.extractUsername(token);
        
        List<Category> list = categoryService.getCatByUserService(email);
        
        return ResponseEntity.ok(list); 
    }
    
    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<?> updateCategoriesByUser(@RequestHeader("Authorization") String authHeader,  @RequestBody Category category, 
    																	                                @PathVariable("categoryId") int categoryId) 
    {
    	Optional<Category> returnedCategory;
    	
    	String token = authHeader.replace("Bearer ", "");
	 	
        jwtService.extractUsername(token);
        
        if((!category.getTitle().isEmpty()) && (!category.getDescription().isEmpty()) && (category.getCateExpense() != null))
        {
           returnedCategory =  Optional.ofNullable(categoryService.updateTheCategory(categoryId,category));            
        }
        else
        {
        	throw new ApiReqException("partial content or empty content.");
        }       
        if(returnedCategory.isPresent()) {
       	 return new ResponseEntity<>(returnedCategory.get(), HttpStatus.OK);
       }
       return new ResponseEntity<>("INVALID TRANSACTION ID", HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/transaction")
    public ResponseEntity<?> getTransactionByUserName(@RequestHeader("Authorization") String authHeader) 
    {
    	String token = authHeader.replace("Bearer ", "");
	 	
        String email = jwtService.extractUsername(token);
        
        Optional<Transaction> transaction = Optional.ofNullable(transServ.getTransactions(email));
                
        if(transaction.isPresent()) {
       	 return new ResponseEntity<>(transaction.get(), HttpStatus.OK);
       }
       return new ResponseEntity<>("INVALID TRANSACTION ID", HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getCategoriesById(@RequestHeader("Authorization") String authHeader,
    		                                                            @PathVariable Integer id) 
    {
    	String token = authHeader.replace("Bearer ", "");
	 	
        String email = jwtService.extractUsername(token);
        
        Optional<Category> category = Optional.ofNullable(categoryService.getCatByUserServiceById(email,id));
        
        if(category.isPresent()) {
        	 return new ResponseEntity<>(category.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("INVALID CATEGORY ID", HttpStatus.NOT_FOUND);
    }
    
}