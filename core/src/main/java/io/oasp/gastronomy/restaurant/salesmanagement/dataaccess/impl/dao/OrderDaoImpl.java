package io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.impl.dao;

import javax.inject.Named;

import io.oasp.gastronomy.restaurant.general.dataaccess.base.dao.ApplicationMasterDataDaoImpl;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.OrderEntity;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.dao.OrderDao;

/**
 * @author PKALINIA
 *
 */
@Named
public class OrderDaoImpl extends ApplicationMasterDataDaoImpl<OrderEntity> implements OrderDao {

  @Override
  protected Class<OrderEntity> getEntityClass() {

    return OrderEntity.class;
  }

}
