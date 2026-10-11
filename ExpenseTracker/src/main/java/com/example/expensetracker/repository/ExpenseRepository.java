package com.example.expensetracker.repository;

import com.example.expensetracker.model.Expense;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ExpenseRepository extends ElasticsearchRepository<Expense, String>{
    
}