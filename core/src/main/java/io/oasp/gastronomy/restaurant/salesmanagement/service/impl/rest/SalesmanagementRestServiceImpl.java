package io.oasp.gastronomy.restaurant.salesmanagement.service.impl.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderState;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.Salesmanagement;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.BillEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderCto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderPositionEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderSearchCriteriaTo;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.PaymentCto;
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
  public OrderPositionEto deliverOrderPosition(Long tableId) {

    return this.salesmanagement.deliverOrderPosition(tableId);
  }

  @Override
  public OrderPositionEto prepareOrderPosition(Long tableId) {

    return this.salesmanagement.prepareOrderPosition(tableId);
  }

  @Override
  public BillEto findBill(Long billId) {

    return this.salesmanagement.findBillById(billId);
  }

  @Override
  public BillEto createBill(Collection<Long> orderPositionIds) {

    return this.salesmanagement.createBill(new ArrayList<>(orderPositionIds));
  }

  @Override
  public Money payBill(PaymentCto paymentCto) {

    return this.salesmanagement.payBill(paymentCto.getBillId(), paymentCto.getToPay(), paymentCto.getTip());
  }

  @Override
  public List<OrderEto> getOrders() {

    return this.salesmanagement.findAllOrders();
  }

  @Override
  public List<BillEto> findAllBills() {

    return this.salesmanagement.findAllBills();
  }

}
