package io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.impl.dao;

import javax.inject.Named;

import io.oasp.gastronomy.restaurant.general.dataaccess.base.dao.ApplicationMasterDataDaoImpl;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderState;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.OrderEntity;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.dao.OrderEntityDao;

/**
 * @author JBLATSIO
 *
 */
@Named
public class OrderEntityDaoImpl extends ApplicationMasterDataDaoImpl<OrderEntity> implements OrderEntityDao {

  @Override
  public OrderEntity findOrderById(Long id) {

    return findOne(id);
  }

  @Override
  public OrderEntity updateOrder(OrderEntity entity) {

    return save(entity);

  }

  @Override
  public OrderEntity findOrderByTableAndOrderState(Long tableId, OrderState orderState) {

    return null;
  }

  @Override
  protected Class<OrderEntity> getEntityClass() {

    return OrderEntity.class;
  }

  @Override
  public OrderEntity createOrder(OrderEntity entity) {

    return save(entity);
  }

}
