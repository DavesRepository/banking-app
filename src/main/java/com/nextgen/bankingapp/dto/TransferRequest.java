package com.nextgen.bankingapp.dto;

public record TransferRequest(String username, String fromAccount, String toAccount, double amount) {
}
