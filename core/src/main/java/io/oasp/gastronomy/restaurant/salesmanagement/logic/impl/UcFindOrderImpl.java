package io.oasp.gastronomy.restaurant.salesmanagement.logic.impl;

import javax.inject.Named;

import io.oasp.gastronomy.restaurant.general.logic.base.AbstractUc;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderState;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.base.UcFindOrder;

@Named
public class UcFindOrderImpl extends AbstractUc implements UcFindOrder {
  private OrderEto createDummyOrderById(Long id) {

    OrderEto o1 = new OrderEto();
    o1.setId(id);
    o1.setModificationCounter(1);
    o1.setRevision(Integer.valueOf(3));
    o1.setState(OrderState.OPEN);
    return o1;
  }

  @Override
  public OrderEto findOrderById(Long orderId) {

    return createDummyOrderById(orderId);
  }

}
