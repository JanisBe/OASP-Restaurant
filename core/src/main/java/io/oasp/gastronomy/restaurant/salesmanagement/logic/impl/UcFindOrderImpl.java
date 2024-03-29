package io.oasp.gastronomy.restaurant.salesmanagement.logic.impl;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import io.oasp.gastronomy.restaurant.general.logic.base.AbstractUc;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.dao.OrderDao;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.base.UcFindOrder;

@Named
public class UcFindOrderImpl extends AbstractUc implements UcFindOrder {

  @Autowired
  private OrderDao orderDao;

  @Override
  public OrderEto findOrderById(Long orderId) {

    return getBeanMapper().map(this.orderDao.findOne(orderId), OrderEto.class);
  }

}
