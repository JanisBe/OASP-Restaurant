package io.oasp.gastronomy.restaurant.salesmanagement.service.impl.rest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderState;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.Salesmanagement;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderEto;
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
  public OrderEto getOrder(long id) {

    return this.salesmanagement.findOrderById(id);
  }

  @Override
  public OrderEto saveOrder(Long tableId) {

    return this.salesmanagement.addNewOrder(tableId);
  }

  @Override
  public List<OrderEto> searchOrder(OrderState orderState, long tableId) {

    OrderSearchCriteriaTo orderSearchCriteriaTo = new OrderSearchCriteriaTo();
    orderSearchCriteriaTo.setOrderState(orderState);
    orderSearchCriteriaTo.setTableId(tableId);
    return this.salesmanagement.findOrders(orderSearchCriteriaTo);
  }

}
