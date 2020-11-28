package com.netcracker.edu.validators;

import lombok.Getter;

/**
 * Validation report class consists of three fields,
 * which can be populated by class, which extends {@link Validator}.
 */
@Getter
public class ValidationReport {

  private final String infoMessage;
  private final String failedField;
  private final ValidationStatus status;

  /**
   * Full constructor.
   *
   * @param infoMessage informational message
   * @param failedField field of some object, which failed some checks
   * @param status validation status (see {@link ValidationStatus})
   */
  public ValidationReport(String infoMessage, String failedField, ValidationStatus status) {
    this.infoMessage = infoMessage;
    this.failedField = failedField;
    this.status = status;
  }

  /**
   * Partial constructor (sets up only {@code status} field).
   *
   * @param status status validation status (see {@link ValidationStatus})
   */
  public ValidationReport(ValidationStatus status) {
    this.infoMessage = "";
    this.failedField = "";
    this.status = status;
  }
}
