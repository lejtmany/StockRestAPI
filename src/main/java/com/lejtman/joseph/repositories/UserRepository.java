package com.lejtman.joseph.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lejtman.joseph.model.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
}
