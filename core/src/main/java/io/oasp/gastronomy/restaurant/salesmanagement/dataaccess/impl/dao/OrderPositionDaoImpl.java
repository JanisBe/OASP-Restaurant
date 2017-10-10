package io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.impl.dao;

import javax.inject.Named;

import io.oasp.gastronomy.restaurant.general.dataaccess.base.dao.ApplicationMasterDataDaoImpl;
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

}
