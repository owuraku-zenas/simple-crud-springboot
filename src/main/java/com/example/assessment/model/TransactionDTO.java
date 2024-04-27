package com.example.assessment.model;

import lombok.Data;

@Data
public class TransactionDTO {
    private Integer senderId;
    private Integer receiverId;
    private Double amount;
}
