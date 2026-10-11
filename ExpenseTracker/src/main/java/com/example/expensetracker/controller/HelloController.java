package com.example.expensetracker.controller;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class HelloController {
    private final ExpenseService expenseService;
    public HelloController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
/*    @GetMapping ("/")
    public String home(){
        return "Hello World!";
    }*/
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    /*@GetMapping("/welcome")
    public String welcome() {
        return "Welcome";
    }*/
  /*  @PostMapping("/hello")
    public String posthello(@RequestBody String user) {
        return "Hello " + user;
    }*/
   
    @GetMapping("/expenses")
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }
    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable String id) {
        return expenseService.getExpenseById(id);
    }
    @PostMapping("/expenses")
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseService.addExpense(expense);
    }
    @PutMapping("/expenses/{id}")
    public Expense updateExpense(@PathVariable String id, @RequestBody Expense expense) {
        return expenseService.updateExpense(id, expense);
    }
    @DeleteMapping("/expenses/{id}")
    public String deleteExpense(@PathVariable String id) {
        expenseService.deleteExpense(id);
        return "Expense deleted successfully";
    }
}
