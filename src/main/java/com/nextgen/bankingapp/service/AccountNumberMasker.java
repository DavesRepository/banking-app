package com.nextgen.bankingapp.service;

/**
 * BA-1245: account numbers must be masked before they leave the bank
 * (regulatory requirement), e.g. {@code 58.44.23.14 -> **.**.**.14}.
 * <p>
 * Deliberately a plain, static, framework-agnostic utility rather than a
 * MapStruct default method: masking is a business/compliance rule, not a
 * DTO-mapping concern, so a future gRPC service or GraphQL resolver can
 * apply the exact same rule without depending on the REST mapping layer.
 */
public final class AccountNumberMasker {

  private static final String SEGMENT_SEPARATOR = "\\.";
  private static final String MASK = "**";

  private AccountNumberMasker() {
  }

  public static String mask(String accountNumber) {
    if (accountNumber == null || accountNumber.isBlank()) {
      return accountNumber;
    }
    final String[] segments = accountNumber.split(SEGMENT_SEPARATOR);
    final StringBuilder masked = new StringBuilder();
    for (int i = 0; i < segments.length; i++) {
      if (i > 0) {
        masked.append(".");
      }
      masked.append(i == segments.length - 1 ? segments[i] : MASK);
    }
    return masked.toString();
  }
}
