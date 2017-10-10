package io.oasp.gastronomy.restaurant.salesmanagement.logic.impl;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import io.oasp.gastronomy.restaurant.general.common.api.constants.PermissionConstants;
import io.oasp.gastronomy.restaurant.general.logic.base.AbstractComponentFacade;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderPositionState;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.OrderEntity;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.dao.OrderDao;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.dao.OrderPositionDao;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.Salesmanagement;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderCto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderPositionEto;
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
  private OrderDao orderDao;

  @Inject
  private OrderPositionDao orderPostionDao;

  @Override
  public List<OrderEto> findOrders(OrderSearchCriteriaTo orderSearchCriteriaTo) {

    List<OrderEntity> listOfEntities = getOrderDao().findOrderByTableAndOrderState(orderSearchCriteriaTo.getTableId(),
        orderSearchCriteriaTo.getOrderState());
    return getBeanMapper().mapList(listOfEntities, OrderEto.class);
  }

  @Override
  public OrderEto findOrderById(Long orderId) {

    return this.ucFindOrder.findOrderById(orderId);
  }

  @Override
  public OrderEto addNewOrder(OrderEto order) {

    OrderEntity orderEntity = getOrderDao().createOrder(getBeanMapper().map(order, OrderEntity.class));
    return getBeanMapper().map(orderEntity, OrderEto.class);
  }

  @Override
  public OrderEto changeOrderStatus(Long orderId) {

    return this.ucFindOrder.findOrderById(orderId);
  }

  @Override
  public OrderCto addNewOrder(OrderCto orderCto) {

    return orderCto;
  }

  @Override
  @RolesAllowed(PermissionConstants.DELIVER_ORDER)
  public OrderPositionEto deliverOrderPosition(Long id) {

    return setOrderPositionStatus(id, OrderPositionState.DELIVERED);
  }

  @Override
  @RolesAllowed(PermissionConstants.PREPARE_ORDER)
  public OrderPositionEto prepareOrderPosition(Long id) {

    return setOrderPositionStatus(id, OrderPositionState.PREPARED);
  }

  private OrderPositionEto setOrderPositionStatus(Long id, OrderPositionState orderState) {

    return getBeanMapper().map(this.orderPostionDao.setOrderPositionStatus(id, orderState), OrderPositionEto.class);
  }

  private OrderPositionDao getOrderPositionDao() {

    return this.orderPostionDao;
  }

  private OrderDao getOrderDao() {

    return this.orderDao;
  }
}
