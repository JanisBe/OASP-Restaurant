package io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.dao;

import io.oasp.gastronomy.restaurant.general.dataaccess.api.dao.ApplicationDao;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.OrderPositionEntity;
import io.oasp.module.jpa.dataaccess.api.MasterDataDao;

/**
 * @author PKALINIA
 *
 */
public interface OrderPositionDao extends ApplicationDao<OrderPositionEntity>, MasterDataDao<OrderPositionEntity> {

}
