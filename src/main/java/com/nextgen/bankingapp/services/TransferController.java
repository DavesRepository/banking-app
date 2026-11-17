package com.nextgen.bankingapp.services;

import com.nextgen.bankingapp.dto.TransferRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransferController {

  private final TransferService transferService;

  @PostMapping("/transfer")
  public ResponseEntity<String> transfer(@RequestBody TransferRequest request) {
    try {
      transferService.transfer(request);
      return ResponseEntity.ok("Transfer successful");
    } catch (Exception e) {
      return ResponseEntity.ok("Transfer failed: " + e.getMessage());
    }
  }
}
