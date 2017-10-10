package io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.dao;

import java.util.List;

import io.oasp.gastronomy.restaurant.general.dataaccess.api.dao.ApplicationDao;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderState;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.OrderEntity;
import io.oasp.module.jpa.dataaccess.api.MasterDataDao;

public interface OrderDao extends ApplicationDao<OrderEntity>, MasterDataDao<OrderEntity> {

  /**
   * @param tableId
   * @param orderState
   * @return
   */
  List<OrderEntity> findOrderByTableAndOrderState(Long tableId, OrderState orderState);

  /**
   * @param entity
   * @return
   */
  OrderEntity createOrder(OrderEntity entity);

}
