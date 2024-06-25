package com.houseclay.zebra.service.impl;

import com.houseclay.zebra.dto.ExpenseDTO;
import com.houseclay.zebra.model.common.BaseTimeStamp;
import com.houseclay.zebra.model.expense.Expense;
import com.houseclay.zebra.repository.ExpenseRepository;
import com.houseclay.zebra.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;
    @Override
    public String addExpense(ExpenseDTO expenseDTO,String loggedInUser) {
        Expense expense = Expense.builder().expenseType(expenseDTO.getExpenseType())
                        .expenseMadeBy(expenseDTO.getExpenseMadeBy())
                .amount(expenseDTO.getAmount()).date(expenseDTO.getDate()).notes(expenseDTO.getNotes())
                .baseTimeStamp(BaseTimeStamp.builder().created_on(new Date()).created_by(loggedInUser).build()).build();

        try{
            expenseRepository.save(expense);
            return "Expense Data saved successfully";
        }
        catch(Exception e){
            return "Error in Saving Data";
        }

    }

    @Override
    public List<Expense> fetchAll(String loggedInUser) {
        ArrayList<Expense> expenseArrayList = new ArrayList<>();
        try{
            expenseArrayList = (ArrayList<Expense>) expenseRepository.findAll();
        }catch (Exception ex) {
            log.error("Error in fetchAll Expense Service Impl ");
        }

        return expenseArrayList;
    }

    @Override
    public String approveExpense(UUID uuid,String loggedInUser) {
        return null;
    }

    @Override
    public String reimburseExpense(UUID uuid,String loggedInUser) {
        return null;
    }

    @Override
    public String editExpense(ExpenseDTO expenseDTO,String loggedInUser) {
        return null;
    }

    @Override
    public void deleteExpense(UUID uuid,String loggedInUser) {

    }
}
