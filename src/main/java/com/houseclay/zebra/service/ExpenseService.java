package com.houseclay.zebra.service;

import com.houseclay.zebra.dto.ExpenseDTO;
import com.houseclay.zebra.model.expense.Expense;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface ExpenseService {
    public String addExpense(ExpenseDTO expenseDTO, String loggedInUser);
    public List<Expense> fetchAll(String loggedInUser);
    public String approveExpense(UUID uuid,String loggedInUser);
    public String reimburseExpense(UUID uuid,String loggedInUser);
    public String editExpense(ExpenseDTO expenseDTO,String loggedInUser);
    public void deleteExpense(UUID uuid,String loggedInUser);
}
