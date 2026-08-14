package com.nextgen.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * A structured response for GET /account/totalbalance/{username}, instead of
 * handing back a bare BigDecimal. A bare number can't carry context (which
 * user? as of when?) and can't be extended later (e.g. currency, per-account
 * breakdown) without breaking the response shape for every existing client.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalBalanceDTO {
    private String username;
    private BigDecimal totalBalance;
}
