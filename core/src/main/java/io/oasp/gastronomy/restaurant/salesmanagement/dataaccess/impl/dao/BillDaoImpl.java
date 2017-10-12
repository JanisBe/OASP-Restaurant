package io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.impl.dao;

import javax.inject.Named;

import io.oasp.gastronomy.restaurant.general.dataaccess.base.dao.ApplicationMasterDataDaoImpl;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.BillEntity;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.dao.BillDao;

/**
 * @author PKALINIA
 *
 */
@Named
public class BillDaoImpl extends ApplicationMasterDataDaoImpl<BillEntity> implements BillDao {

  @Override
  protected Class<BillEntity> getEntityClass() {

    return BillEntity.class;
  }

}
