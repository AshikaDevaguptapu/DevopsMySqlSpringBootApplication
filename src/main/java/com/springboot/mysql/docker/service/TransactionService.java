

package com.springboot.mysql.docker.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mysql.docker.model.Transaction;
import com.springboot.mysql.docker.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository transrepo;

	public void saveTransaction(Transaction transaction) {
		//System.out.println("I am in repository"+transaction.getFromaccount());
		transrepo.save(transaction);
		
	}

	public Iterable<Transaction> getTransactionHistory() {
		// TODO Auto-generated method stub
		return transrepo.findAll();
	}

	public Optional<Transaction> getTransaction(Long id) {
		// TODO Auto-generated method stub
		return transrepo.findById(id);
		
	}

}
