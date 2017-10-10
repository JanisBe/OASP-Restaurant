package io.oasp.gastronomy.restaurant.offermanagement.dataaccess.impl.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.TypedQuery;

import io.oasp.gastronomy.restaurant.general.common.api.constants.NamedQueries;
import io.oasp.gastronomy.restaurant.general.dataaccess.base.dao.ApplicationMasterDataDaoImpl;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.ProductEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.ProductDao;

/**
 * @author DBIENIEK
 *
 */
@Named
public class ProductDaoImpl extends ApplicationMasterDataDaoImpl<ProductEntity> implements ProductDao {

  @Override
  public List<ProductEntity> findByName(String name) {

    TypedQuery<ProductEntity> query =
        getEntityManager().createNamedQuery(NamedQueries.GET_PRODUCT_BY_NAME, ProductEntity.class);
    query.setParameter("name", name);
    return query.getResultList();
  }

  @Override
  protected Class<ProductEntity> getEntityClass() {

    return ProductEntity.class;
  }

}
