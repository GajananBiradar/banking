package com.houseclay.zebra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ExpenseDTO {

    private String expenseType;
    private String expenseMadeBy;
    private String amount;
    private String date;
    private String notes;

}
