package io.oasp.gastronomy.restaurant.offermanagement.dataaccess.impl.dao;

import javax.inject.Named;
import javax.persistence.TypedQuery;

import io.oasp.gastronomy.restaurant.general.common.api.constants.NamedQueries;
import io.oasp.gastronomy.restaurant.general.dataaccess.base.dao.ApplicationMasterDataDaoImpl;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.OfferEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.OfferDao;

/**
 * @author DBIENIEK
 *
 */
@Named
public class OfferDaoImpl extends ApplicationMasterDataDaoImpl<OfferEntity> implements OfferDao {

  @Override
  public OfferEntity findByName(String name) {

    TypedQuery<OfferEntity> query =
        getEntityManager().createNamedQuery(NamedQueries.GET_OFFER_BY_NAME, OfferEntity.class);
    query.setParameter("name", name);
    return query.getSingleResult();
  }

  @Override
  protected Class<OfferEntity> getEntityClass() {

    return OfferEntity.class;
  }

}
