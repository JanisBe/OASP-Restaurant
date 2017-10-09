package io.oasp.gastronomy.restaurant.salesmanagement.logic.api;

import java.util.List;

import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderState;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderSearchCriteriaTo;

/**
 * @author PKALINIA
 *
 */
public interface Salesmanagement {

  List<OrderEto> findOrders(OrderSearchCriteriaTo orderSearchCriteriaTo);

  /**
   * @param orderId
   * @return
   */
  OrderEto findOrderById(Long orderId);

  /**
   * @param orderEto
   * @return
   */
  OrderEto addNewOrder(OrderEto orderEto);

  /**
   * @param orderState
   * @param tableId
   * @return
   */
  List<OrderEto> findOrderByCriteria(OrderState orderState, Long tableId);

}