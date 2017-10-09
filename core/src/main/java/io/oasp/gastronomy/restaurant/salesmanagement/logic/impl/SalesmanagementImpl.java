package io.oasp.gastronomy.restaurant.salesmanagement.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.transaction.Transactional;

import io.oasp.gastronomy.restaurant.general.logic.base.AbstractComponentFacade;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderState;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.Salesmanagement;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderSearchCriteriaTo;

/**
 * @author PKALINIA
 *
 */
@Named
@Transactional
public class SalesmanagementImpl extends AbstractComponentFacade implements Salesmanagement {

  @Override
  public List<OrderEto> findOrders(OrderSearchCriteriaTo orderSearchCriteriaTo) {

    // TODO Access to DAO
    List<OrderEto> list = new ArrayList<OrderEto>();
    list.add(createDummyOrder(OrderState.CLOSED));
    list.add(createDummyOrder(OrderState.OPEN));
    // end TODO
    return list;
  }

  /**
   * @return
   */
  private OrderEto createDummyOrder(OrderState state) {

    OrderEto o1 = new OrderEto();
    o1.setId(33l);
    o1.setModificationCounter(1);
    o1.setRevision(Integer.valueOf(3));
    o1.setState(state);
    return o1;
  }

}
