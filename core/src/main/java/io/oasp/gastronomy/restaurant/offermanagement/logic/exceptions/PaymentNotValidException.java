package io.oasp.gastronomy.restaurant.offermanagement.logic.exceptions;

import net.sf.mmm.util.exception.api.NlsRuntimeException;

/**
 * @author DBIENIEK
 *
 */
public class PaymentNotValidException extends NlsRuntimeException {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public PaymentNotValidException(String message) {
    super(message);
  }

  @Override
  public boolean isTechnical() {

    return false;
  }
}
