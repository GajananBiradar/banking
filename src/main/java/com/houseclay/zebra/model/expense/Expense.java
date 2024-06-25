package com.houseclay.zebra.model.expense;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.houseclay.zebra.model.common.BaseTimeStamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "expense")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Expense {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID expense_id;

    private String expenseType;

    private String expenseMadeBy; // save user email , users should be from list of users

    private String amount;

    private String date;

    private String notes;

    @Embedded private BaseTimeStamp baseTimeStamp;

    private boolean approved = false;

    private boolean approvedBy;

    private boolean isReimbursed = false;



}
