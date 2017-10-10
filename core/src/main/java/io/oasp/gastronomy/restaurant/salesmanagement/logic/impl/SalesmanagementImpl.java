package io.oasp.gastronomy.restaurant.salesmanagement.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import io.oasp.gastronomy.restaurant.general.logic.base.AbstractComponentFacade;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderPositionState;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.OrderEntity;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.OrderPositionEntity;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.dao.OrderEntityDao;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.dao.OrderPositionDao;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.Salesmanagement;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderSearchCriteriaTo;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.base.UcFindOrder;

/**
 * @author PKALINIA
 *
 */
@Named
@Transactional
public class SalesmanagementImpl extends AbstractComponentFacade implements Salesmanagement {
  @Inject
  private UcFindOrder ucFindOrder;

  @Inject
  private OrderEntityDao orderEntityDao;

  @Inject
  private OrderPositionDao orderPostionDao;

  @Override
  public List<OrderEto> findOrders(OrderSearchCriteriaTo orderSearchCriteriaTo) {

    List<OrderEto> list = new ArrayList<>();
    List<OrderEntity> listOfEntities = this.orderEntityDao
        .findOrderByTableAndOrderState(orderSearchCriteriaTo.getTableId(), orderSearchCriteriaTo.getOrderState());
    for (OrderEntity entity : listOfEntities) {
      list.add(getBeanMapper().map(entity, OrderEto.class));
    }
    return list;
  }

  @Override
  public OrderEto findOrderById(Long orderId) {

    return this.ucFindOrder.findOrderById(orderId);
  }

  @Override
  public OrderEto addNewOrder(OrderEto order) {

    OrderEntity orderEntity = this.orderEntityDao.createOrder(getBeanMapper().map(order, OrderEntity.class));
    return getBeanMapper().map(orderEntity, OrderEto.class);
  }

  @Override
  public OrderEto changeOrderStatus(Long orderId) {

    return this.ucFindOrder.findOrderById(orderId);
  }

  @Override
  public OrderPositionEntity setOrderPositionStatus(Long id, OrderPositionState orderState) {

    return getBeanMapper().map(this.orderPostionDao.setOrderPositionStatus(id, orderState), OrderPositionEntity.class);
  }

}
