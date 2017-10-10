package io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.impl.dao;

import io.oasp.gastronomy.restaurant.general.dataaccess.base.dao.ApplicationMasterDataDaoImpl;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderPositionState;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.OrderPositionEntity;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.dao.OrderPositionDao;

/**
 * @author JBLATSIO
 *
 */
public class OrderPositionDaoImpl extends ApplicationMasterDataDaoImpl<OrderPositionEntity>
    implements OrderPositionDao {

  @Override
  public OrderPositionEntity setOrderPositionStatus(Long id, OrderPositionState orderState) {

    OrderPositionEntity orderToUpdate = findOne(id);
    orderToUpdate.setState(orderState);
    return save(orderToUpdate);
  }

  @Override
  protected Class<OrderPositionEntity> getEntityClass() {

    return OrderPositionEntity.class;
  }

}
