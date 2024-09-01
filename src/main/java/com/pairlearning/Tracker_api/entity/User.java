package com.pairlearning.Tracker_api.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "et_users")
@Setter
@Getter
public class User implements UserDetails
{	
	    @Id
	    @Column(name  = "user_id", nullable = false)
	    private Integer user_id;

	    @Column(name = "first_name", nullable = false)
	    private String first_name;

	    @Column(name  = "last_name", unique = true, length = 100, nullable = false)
	    private String last_name;

	    @Column(name  = "email", nullable = false)
	    private String email;
	    
	    @Column(name  = "password", nullable=false)
	    private String password;
	    
	    @Transient
	    @JoinColumn(name = "category_id")
	    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	    private List<Category> listOfCategories = new ArrayList<>();                                                                                         
	    
	    @Transient
		@JoinColumn(name = "transaction_id")
		@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
		private Transaction trans;                                                                                                     
	    
		@Column(name = "totalexpense")
		private Double totalexpense;
		
	    @Override
	    public String getUsername() {
	        return email;
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return List.of();
		}
	
	

}
