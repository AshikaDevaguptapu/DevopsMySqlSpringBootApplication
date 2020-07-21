package com.springboot.mysql.docker.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.mysql.docker.exceptions.InvalidTransationReferenceException;
import com.springboot.mysql.docker.model.Transaction;
import com.springboot.mysql.docker.service.TransactionService;

@Controller
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	
	@PostMapping("/create")
	@ResponseBody 
	public int createTransaction(@RequestBody Transaction transaction) {
		//System.out.println("Heyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy"+transaction.getTransid());
		transactionService.saveTransaction(transaction);
		return transaction.getTransid();
	}
	
	@GetMapping("/viewAll")
	public String viewAllTransactions(Model md) {
		Iterable<Transaction> translist=transactionService.getTransactionHistory();
		//System.out.println("i am a list callling details pageeeeeeeeeeeeeeeeeeeeeeeeeee");
		md.addAttribute("viewAll",translist);
		return "details";
	}
	
	@GetMapping("/view/{id}")
	@ResponseBody 
	public Transaction viewTransactionById(@PathVariable ("id") Long id) {
		Optional<Transaction> transaction = transactionService.getTransaction(id);
		if(transaction.isPresent()) {
			return transaction.get();
		}
		throw new InvalidTransationReferenceException("Invalid transaction ");
  }
	
	@RequestMapping("/details")
	@ResponseBody
	public String getDetails()
	{
		return "dummy details";
	}
	
}
