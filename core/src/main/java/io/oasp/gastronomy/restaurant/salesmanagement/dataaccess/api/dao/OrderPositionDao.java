package io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.dao;

import io.oasp.gastronomy.restaurant.general.dataaccess.api.dao.ApplicationDao;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderPositionState;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.ProductOrderState;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.OrderPositionEntity;
import io.oasp.module.jpa.dataaccess.api.MasterDataDao;

public interface OrderPositionDao extends ApplicationDao<OrderPositionEntity>, MasterDataDao<OrderPositionEntity> {

  OrderPositionEntity setOrderPositionStatus(Long id, OrderPositionState orderState);

  OrderPositionEntity setOrderedDrinkStatus(Long id, ProductOrderState orderState);

}
