package com.pairlearning.Tracker_api.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pairlearning.Tracker_api.entity.User;
import com.pairlearning.Tracker_api.exceptions.EAuthException;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> 
{
    Optional<User> findByEmail(String email);
}
