package com.nextgen.bankingapp.dto;

import java.math.BigDecimal;

public class AccountDTO {
    private String accountNumber;
    private BigDecimal balance;

    public AccountDTO() {
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AccountDTO)) return false;
        final AccountDTO other = (AccountDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$accountNumber = this.getAccountNumber();
        final Object other$accountNumber = other.getAccountNumber();
        if (this$accountNumber == null ? other$accountNumber != null : !this$accountNumber.equals(other$accountNumber))
            return false;
        final Object this$balance = this.getBalance();
        final Object other$balance = other.getBalance();
        if (this$balance == null ? other$balance != null : !this$balance.equals(other$balance)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AccountDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $accountNumber = this.getAccountNumber();
        result = result * PRIME + ($accountNumber == null ? 43 : $accountNumber.hashCode());
        final Object $balance = this.getBalance();
        result = result * PRIME + ($balance == null ? 43 : $balance.hashCode());
        return result;
    }

    public String toString() {
        return "AccountDTO(accountNumber=" + this.getAccountNumber() + ", balance=" + this.getBalance() + ")";
    }
}
