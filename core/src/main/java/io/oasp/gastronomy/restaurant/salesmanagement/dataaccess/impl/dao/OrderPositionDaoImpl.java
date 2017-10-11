package io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.impl.dao;

import javax.inject.Named;

import io.oasp.gastronomy.restaurant.general.dataaccess.base.dao.ApplicationMasterDataDaoImpl;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderPositionState;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.ProductOrderState;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.OrderPositionEntity;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.dao.OrderPositionDao;

/**
 * @author PKALINIA
 *
 */
@Named
public class OrderPositionDaoImpl extends ApplicationMasterDataDaoImpl<OrderPositionEntity>
    implements OrderPositionDao {

  @Override
  protected Class<OrderPositionEntity> getEntityClass() {

    return OrderPositionEntity.class;
  }

  @Override
  public OrderPositionEntity setOrderPositionStatus(Long id, OrderPositionState orderState) {

    OrderPositionEntity orderPositionEntity = findOne(id);
    orderPositionEntity.setState(orderState);
    return save(orderPositionEntity);
  }

  @Override
  public OrderPositionEntity setOrderedDrinkStatus(Long id, ProductOrderState orderState) {

    OrderPositionEntity orderPositionEntity = findOne(id);
    orderPositionEntity.setDrinkState(orderState);
    return save(orderPositionEntity);
  }

}
