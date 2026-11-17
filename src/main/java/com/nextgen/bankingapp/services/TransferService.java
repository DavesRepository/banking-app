package com.nextgen.bankingapp.services;

import com.nextgen.bankingapp.dto.TransferRequest;
import com.nextgen.bankingapp.services.database.CurrentAccount;
import com.nextgen.bankingapp.services.database.CurrentAccountRepository;
import com.nextgen.bankingapp.services.database.SavingsAccount;
import com.nextgen.bankingapp.services.database.SavingsAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferService {

  private final CurrentAccountRepository currentAccountRepository;
  private final SavingsAccountRepository savingsAccountRepository;

  public void transfer(TransferRequest request) {
    log.info("Transfer of {} from {} to {} requested by {}",
        request.amount(), request.fromAccount(), request.toAccount(), request.username());

    CurrentAccount from = currentAccountRepository.findById(request.fromAccount()).orElseThrow();
    SavingsAccount to = savingsAccountRepository.findById(request.toAccount()).orElseThrow();

    BigDecimal amount = new BigDecimal(request.amount());
    if (from.getBalance().compareTo(amount) < 0) {
      throw new IllegalStateException("Insufficient funds on account " + from.getAccountNumber());
    }

    from.setBalance(from.getBalance().subtract(amount));
    currentAccountRepository.save(from);

    to.setBalance(to.getBalance().add(amount));
    savingsAccountRepository.save(to);

    log.info("Transfer completed, new balance of {} is {}", from.getAccountNumber(), from.getBalance());
  }
}
