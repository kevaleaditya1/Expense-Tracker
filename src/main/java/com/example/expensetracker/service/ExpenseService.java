package com.example.expensetracker.service;

import com.example.expensetracker.model.Expense;
import org.springframework.stereotype.Service;
import com.example.expensetracker.repository.ExpenseRepository;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
    public Expense updateExpense(Long id, Expense updatedExpense) {
        Expense existingExpense = expenseRepository.findById(id).orElse(null);

        if (existingExpense != null) {
            existingExpense.setTitle(updatedExpense.getTitle());
            existingExpense.setAmount(updatedExpense.getAmount());
            existingExpense.setCategory(updatedExpense.getCategory());
            existingExpense.setDescription(updatedExpense.getDescription());
            existingExpense.setDate(updatedExpense.getDate());

            return expenseRepository.save(existingExpense);
        }

        return null;
    }
    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
}
