package io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao;

import io.oasp.gastronomy.restaurant.general.dataaccess.api.dao.ApplicationDao;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.OfferEntity;
import io.oasp.module.jpa.dataaccess.api.MasterDataDao;

/**
 * @author DBIENIEK
 *
 */
public interface OfferDao extends ApplicationDao<OfferEntity>, MasterDataDao<OfferEntity> {
  OfferEntity findByName(String name);
}
