
package com.example.expensetracker.service;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        expenseRepository.findAll().forEach(expenses::add);
        return expenses;
    }

    public Expense getExpenseById(String id) {
        return expenseRepository.findById(id).orElse(null);
    }

    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }

    public Expense updateExpense(String id, Expense updatedExpense) {
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
}
