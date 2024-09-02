package com.pairlearning.Tracker_api.resources;

import java.awt.RenderingHints.Key;
import java.io.ObjectInputFilter.Status;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pairlearning.Tracker_api.dtos.CateRegDto;
import com.pairlearning.Tracker_api.dtos.LoginCatResp;
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
    
    @Autowired // @Autowired injection Dependency just to check not recommended on production code 
    private TransactionService TransServ;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, CategorySerInt categoryService) 
    {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.categoryService = categoryService;
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
        
        TransServ.saveUser(authenticatedUser);
                
        LoginResponse loginResponse = new LoginResponse();
        
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        
        loginResponse.setToken(jwtToken);
        
        return ResponseEntity.ok(loginResponse);
    }
    
    @PostMapping("/categories")
    public void saveUserCategories(@RequestHeader("Authorization") String authHeader,
    	                                           @RequestBody CateRegDto CateRegDto)
    {    	
    	String token = authHeader.replace("Bearer ", "");
    	 	
        String email = jwtService.extractUsername(token);
        
        if((!CateRegDto.getTitle().isEmpty()) && (!CateRegDto.getDescription().isEmpty()) && (CateRegDto.getCateExpense() != null))
        {
           Category category =  categoryService.saveCategory1(CateRegDto.getTitle(), CateRegDto.getDescription(), CateRegDto.getCateExpense(), email);
            
           TransServ.saveCategoryInTrans(category);
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
    
//    @GetMapping("/transaction/{name}")
//    public ResponseEntity<Transaction> getTransactionByUser(@RequestHeader("Authorization") String authHeader,
//    																			   @PathVariable  String user) 
//    {
//    	String token = authHeader.replace("Bearer ", "");
//	 	
//        String email = jwtService.extractUsername(token);
//        
//        List<Category> list = categoryService.getCatByUserService(email);
//        
//        Transaction t = new Transaction();
//        
//        return (ResponseEntity<Transaction>) ResponseEntity.ok(t); 
//    }
    
    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getCategoriesById(@RequestHeader("Authorization") String authHeader, @PathVariable Integer id) 
    {
    	String token = authHeader.replace("Bearer ", "");
	 	
        String email = jwtService.extractUsername(token);
        
        Optional<Category> category = Optional.ofNullable(categoryService.getCatByUserServiceById(email,id));
        
        if(category.isPresent()) 
        	return new ResponseEntity<>(category.get(), HttpStatus.OK);
        else
           return new ResponseEntity<>("NOT A VALID CATEGORY ID", HttpStatus.NOT_FOUND); 
    }
    
}