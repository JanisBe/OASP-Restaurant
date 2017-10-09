package io.oasp.gastronomy.restaurant.salesmanagement.logic.base;

import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderEto;

public interface UcFindOrder {

  OrderEto findOrderById(Long id);

}
