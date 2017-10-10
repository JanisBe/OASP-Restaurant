package io.oasp.gastronomy.restaurant.salesmanagement.service.impl.rest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderPositionState;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderState;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.Salesmanagement;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderCto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderPositionEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderSearchCriteriaTo;
import io.oasp.gastronomy.restaurant.salesmanagement.service.api.rest.SalesmanagementRestService;

/**
 * @author PKALINIA
 *
 */
@Named("SalesmanagementRestService")
public class SalesmanagementRestServiceImpl implements SalesmanagementRestService {

  @Inject
  private Salesmanagement salesmanagement;

  @Override
  public OrderEto getOrder(Long id) {

    return this.salesmanagement.findOrderById(id);
  }

  @Override
  public OrderCto saveOrder(OrderCto order) {

    return this.salesmanagement.addNewOrder(order);
  }

  @Override
  public List<OrderEto> searchOrder(OrderState orderState, Long tableId) {

    OrderSearchCriteriaTo orderSearchCriteriaTo = new OrderSearchCriteriaTo();
    orderSearchCriteriaTo.setOrderState(orderState);
    orderSearchCriteriaTo.setTableId(tableId);
    return this.salesmanagement.findOrders(orderSearchCriteriaTo);
  }

  @Override
  public OrderPositionEto setOrderPositionStatus(Long tableId, OrderPositionState orderState) {

    return this.salesmanagement.setOrderPositionStatus(tableId, orderState);
  }

}
