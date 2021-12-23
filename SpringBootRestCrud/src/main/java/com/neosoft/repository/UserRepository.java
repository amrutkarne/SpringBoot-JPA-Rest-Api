package com.neosoft.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.entity.User;
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	/*
	 * @Query("FROM User ORDER BY dateOfBirth ASC") List<User>
	 * findAllOrderByDateOfBirthAsc();
	 * 
	 * @Query("FROM User ORDER BY joiningDate ASC") List<User>
	 * findAllOrderByJoiningDateAsc();
	 * 
	 * @Query("FROM User") List<User> findAllOrderByFirstNameAsc(Sort sort);
	 */
	
	
	
}
