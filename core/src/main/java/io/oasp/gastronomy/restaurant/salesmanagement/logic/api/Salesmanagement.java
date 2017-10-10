package io.oasp.gastronomy.restaurant.salesmanagement.logic.api;

import java.util.List;

import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderPositionState;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderCto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderPositionEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderSearchCriteriaTo;

/**
 * @author PKALINIA
 *
 */
public interface Salesmanagement {

  List<OrderEto> findOrders(OrderSearchCriteriaTo orderSearchCriteriaTo);

  OrderEto findOrderById(Long orderId);

  OrderEto changeOrderStatus(Long orderId);

  OrderPositionEto setOrderPositionStatus(Long id, OrderPositionState orderState);

  OrderCto addNewOrder(OrderCto orderCto);

}
