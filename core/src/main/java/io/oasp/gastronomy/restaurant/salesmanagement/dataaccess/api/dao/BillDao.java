package io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.dao;

import io.oasp.gastronomy.restaurant.general.dataaccess.api.dao.ApplicationDao;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.BillEntity;
import io.oasp.module.jpa.dataaccess.api.MasterDataDao;

public interface BillDao extends ApplicationDao<BillEntity>, MasterDataDao<BillEntity> {

}
