package io.oasp.gastronomy.restaurant.salesmanagement.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import io.oasp.gastronomy.restaurant.general.common.api.constants.PermissionConstants;
import io.oasp.gastronomy.restaurant.general.logic.base.AbstractComponentFacade;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.OfferEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.OfferDao;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderPositionState;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.OrderEntity;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.OrderPositionEntity;
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
  private OfferDao offerofferDaoDao;

  @Inject
  private OrderPositionDao orderPositionDao;

  private OrderEntity save;

  @Override
  public List<OrderEto> findOrders(OrderSearchCriteriaTo orderSearchCriteriaTo) {

    List<OrderEntity> listOfEntities = this.orderDao.findOrderByTableAndOrderState(orderSearchCriteriaTo.getTableId(),
        orderSearchCriteriaTo.getOrderState());
    return getBeanMapper().mapList(listOfEntities, OrderEto.class);
  }

  @Override
  @RolesAllowed(PermissionConstants.FIND_ORDER)
  public OrderEto findOrderById(Long orderId) {

    return this.ucFindOrder.findOrderById(orderId);
  }

  @Override
  public OrderEto changeOrderStatus(Long orderId) {

    return this.ucFindOrder.findOrderById(orderId);
  }

  @Override
  @Transactional
  @RolesAllowed(PermissionConstants.SAVE_ORDER)
  public OrderCto addNewOrder(OrderCto orderCto) {

    OrderEntity orderEntity = getBeanMapper().map(orderCto.getOrder(), OrderEntity.class);
    List<OrderPositionEntity> orderPositions = new ArrayList<OrderPositionEntity>();
    for (OrderPositionEto eto : orderCto.getPositions()) {
      orderPositions.add(getBeanMapper().map(eto, OrderPositionEntity.class));
    }
    OrderEntity orderEntityToReturn = this.orderDao.save(orderEntity);
    List<OrderPositionEntity> orderPositionsToReturn = new ArrayList<OrderPositionEntity>();
    List<String> offerNames = orderPositions.stream().map(op -> op.getOfferName()).collect(Collectors.toList());
    List<OfferEntity> offerEntity = this.offerofferDaoDao.findOfferEntitiesGivenNames(offerNames);
    for (OrderPositionEntity entity : orderPositions) {
      entity.setOrder(orderEntityToReturn);
      entity.setOrderId(orderEntityToReturn.getId());
      entity.setOfferId(findOfferByName(entity.getOfferName(), offerEntity));
      validateOrderPosition(entity);
      orderPositionsToReturn.add(this.orderPositionDao.save(entity));
    }
    return assembleOrderCto(orderEntityToReturn, orderPositionsToReturn);
  }

  /**
   * @param entity
   */
  private void validateOrderPosition(OrderPositionEntity entity) {

    if (entity.getOfferId() == null) {
      throw new IllegalStateException("Offer id cannot be null!");
    }
    if (entity.getPrice() == null || entity.getPrice().getValue().intValue() < 0) {
      throw new IllegalStateException("Offer position price cannot be negative!");
    }
  }

  /**
   * @param offerName
   * @param offerEntity
   * @return
   */
  private Long findOfferByName(String offerName, List<OfferEntity> offerEntity) {

    return offerEntity.stream().filter(oe -> StringUtils.equals(offerName, oe.getName())).map(oe -> oe.getId())
        .findAny().orElse(null);
  }

  /**
   * @param orderEntityToReturn
   * @param orderPositionsToReturn
   * @return
   */
  private OrderCto assembleOrderCto(OrderEntity orderEntityToReturn, List<OrderPositionEntity> orderPositionsToReturn) {

    OrderCto orderCto = new OrderCto();
    orderCto.setOrder(getBeanMapper().map(orderEntityToReturn, OrderEto.class));
    List<OrderPositionEto> orderPositionEtos = new ArrayList<OrderPositionEto>();
    for (OrderPositionEntity ope : orderPositionsToReturn) {
      orderPositionEtos.add(getBeanMapper().map(ope, OrderPositionEto.class));
    }
    orderCto.setPositions(orderPositionEtos);
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

    OrderPositionEto foundPosition = getBeanMapper().map(getOrderPositionDao().find(id), OrderPositionEto.class);
    if (orderState.isOneAfter(foundPosition.getState())) {
      return getBeanMapper().map(this.orderPositionDao.setOrderPositionStatus(id, orderState), OrderPositionEto.class);
    } else {
      throw new IllegalStateException("Wrong order state!");
    }
  }

  private OrderPositionDao getOrderPositionDao() {

    return this.orderPositionDao;
  }

  private OrderDao getOrderDao() {

    return this.orderDao;
  }
}
