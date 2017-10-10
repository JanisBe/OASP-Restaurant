package io.oasp.gastronomy.restaurant.offermanagement.logic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author DBIENIEK
 *
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Order is not valid. Expected Meal or Drink or SideDish.")
public class OrderNotValidException extends IllegalStateException {

}
