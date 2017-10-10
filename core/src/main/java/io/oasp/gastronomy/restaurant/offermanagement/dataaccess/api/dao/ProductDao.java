package io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao;

import io.oasp.gastronomy.restaurant.general.dataaccess.api.dao.ApplicationDao;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.ProductEntity;
import io.oasp.module.jpa.dataaccess.api.MasterDataDao;

/**
 * @author DBIENIEK
 *
 */
public interface ProductDao extends ApplicationDao<ProductEntity>, MasterDataDao<ProductEntity> {

  ProductEntity findByName(String name);
}
