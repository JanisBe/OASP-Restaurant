package io.oasp.gastronomy.restaurant.salesmanagement.logic.api;

import java.util.List;

import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderSearchCriteriaTo;

/**
 * @author PKALINIA
 *
 */
public interface Salesmanagement {

  List<OrderEto> findOrders(OrderSearchCriteriaTo orderSearchCriteriaTo);

}
