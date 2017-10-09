package io.oasp.gastronomy.restaurant.salesmanagement.service.impl.rest;

import java.util.List;

import javax.inject.Named;

import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderState;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderEto;
import io.oasp.gastronomy.restaurant.salesmanagement.service.api.rest.SalesmanagementRestService;

/**
 * @author PKALINIA
 *
 */
@Named("SalesmanagementRestService")
public class SalesmanagementRestServiceImpl implements SalesmanagementRestService {

  @Override
  public OrderEto getOrder(long id) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public OrderEto saveOrder(OrderEto order) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<OrderEto> searchOrder(OrderState orderState, long tableId) {

    // TODO Auto-generated method stub
    return null;
  }

}
