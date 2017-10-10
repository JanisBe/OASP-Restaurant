package io.oasp.gastronomy.restaurant.salesmanagement.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import io.oasp.gastronomy.restaurant.general.logic.base.AbstractComponentFacade;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.OfferEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.OfferDao;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderState;
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

    // TODO Access to DAO
    List<OrderEto> list = new ArrayList<OrderEto>();
    list.add(createDummyOrder(OrderState.CLOSED));
    list.add(createDummyOrder(OrderState.OPEN));
    // end TODO
    return list;
  }

  /**
   * @return
   */
  private OrderEto createDummyOrder(OrderState state) {

    OrderEto o1 = new OrderEto();
    o1.setId(33l);
    o1.setModificationCounter(1);
    o1.setRevision(Integer.valueOf(3));
    o1.setState(state);
    return o1;
  }

  @Override
  public OrderEto findOrderById(Long orderId) {

    return this.ucFindOrder.findOrderById(orderId);
  }

  @Override
  @Transactional
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

}
