package com.nextgen.bankingapp.exception;

import java.time.Instant;

/**
 * Uniform error payload returned for any handled exception, so API consumers
 * always get a predictable JSON error shape instead of an empty body or a
 * misleading "successful" default value (e.g. {@code 0}, {@code []}, {@code null}).
 */
public record ApiError(Instant timestamp, int status, String error, String message, String path) {
}
