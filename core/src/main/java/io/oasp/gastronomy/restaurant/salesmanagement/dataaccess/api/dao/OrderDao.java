package io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.dao;

import io.oasp.gastronomy.restaurant.general.dataaccess.api.dao.ApplicationDao;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.OrderEntity;
import io.oasp.module.jpa.dataaccess.api.MasterDataDao;

/**
 * @author PKALINIA
 *
 */
public interface OrderDao extends ApplicationDao<OrderEntity>, MasterDataDao<OrderEntity> {

}
