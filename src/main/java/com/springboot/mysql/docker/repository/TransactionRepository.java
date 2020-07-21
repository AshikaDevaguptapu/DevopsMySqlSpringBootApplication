package com.springboot.mysql.docker.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.mysql.docker.model.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long>{
	

}
