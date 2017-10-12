package io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.impl.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import io.oasp.gastronomy.restaurant.general.dataaccess.base.dao.ApplicationMasterDataDaoImpl;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderPositionState;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.ProductOrderState;
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

  @Override
  public OrderPositionEntity setOrderPositionStatus(Long id, OrderPositionState orderState) {

    OrderPositionEntity orderPositionEntity = findOne(id);
    orderPositionEntity.setState(orderState);
    return save(orderPositionEntity);
  }

  @Override
  public OrderPositionEntity setOrderedDrinkStatus(Long id, ProductOrderState orderState) {

    OrderPositionEntity orderPositionEntity = findOne(id);
    orderPositionEntity.setDrinkState(orderState);
    return save(orderPositionEntity);
  }

  @Override
  public List<Long> findOrderPositionIdsWhichContainAtLeastOnePositionId(List<Long> positionIds) {

    String nativeQuery = prepareStringNativeQuery(positionIds);
    Query q = getEntityManager().createNativeQuery(nativeQuery);
    return ((List<BigInteger>) q.getResultList()).stream().map(bi -> bi.longValue()).collect(Collectors.toList());
  }

  /**
   * @param positionIds
   * @return
   */
  private String prepareStringNativeQuery(List<Long> positionIds) {

    StringBuffer sb = new StringBuffer();
    sb.append("SELECT ORDERPOSITIONSID FROM BILLORDERPOSITION INTERSECT ");
    sb.append("(");
    for (Long id : positionIds) {
      sb.append("SELECT ");
      sb.append(id);
      sb.append(" UNION ");
    }
    sb.delete(sb.length() - " UNION ".length(), sb.length());
    sb.append(")");
    return sb.toString();
  }

  @Override
  public List<OrderPositionEntity> findAllByIds(List<Long> orderPosition) {

    CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<OrderPositionEntity> query = builder.createQuery(getEntityClass());
    Root<OrderPositionEntity> root = query.from(getEntityClass());
    query.select(root);
    query.where(root.get("id").in(orderPosition));
    TypedQuery<OrderPositionEntity> typedQuery = getEntityManager().createQuery(query);
    List<OrderPositionEntity> resultList = typedQuery.getResultList();
    return resultList;
  }

}
