package io.oasp.gastronomy.restaurant.salesmanagement.logic.api;

import java.util.List;

import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.BillEto;
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

  List<OrderEto> findAllOrders();

  OrderEto changeOrderStatus(Long orderId);

  OrderCto addNewOrder(OrderCto orderCto);

  OrderPositionEto deliverOrderPosition(Long id);

  OrderPositionEto prepareOrderPosition(Long id);

  List<BillEto> findAllBills();

  BillEto findBillById(Long billId);

  BillEto createBill(List<Long> orderPositionIds);

  Money payBill(Long billId, Money toPay, Money tip);

}
