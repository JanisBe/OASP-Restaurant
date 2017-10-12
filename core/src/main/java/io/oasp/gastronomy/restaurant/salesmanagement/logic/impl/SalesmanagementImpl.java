package io.oasp.gastronomy.restaurant.salesmanagement.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Sets;

import io.oasp.gastronomy.restaurant.general.common.api.constants.PermissionConstants;
import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.general.logic.base.AbstractComponentFacade;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.OfferEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.OfferDao;
import io.oasp.gastronomy.restaurant.offermanagement.logic.exceptions.BillNotValidException;
import io.oasp.gastronomy.restaurant.offermanagement.logic.exceptions.OrderNotValidException;
import io.oasp.gastronomy.restaurant.offermanagement.logic.exceptions.PaymentNotValidException;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderPositionState;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderState;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.ProductOrderState;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.BillEntity;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.OrderEntity;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.OrderPositionEntity;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.dao.BillDao;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.dao.OrderDao;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.dao.OrderPositionDao;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.Salesmanagement;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.BillEto;
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
  private OfferDao offerDao;

  @Inject
  private BillDao billDao;

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
    OrderEntity orderEntityToReturn = getOrderDao().save(orderEntity);
    List<OrderPositionEntity> orderPositionsToReturn = new ArrayList<OrderPositionEntity>();
    List<String> offerNames = orderPositions.stream().map(op -> op.getOfferName()).collect(Collectors.toList());
    List<OfferEntity> offerEntity = this.offerDao.findOfferEntitiesGivenNames(offerNames);
    for (OrderPositionEntity entity : orderPositions) {
      entity.setOrder(orderEntityToReturn);
      entity.setOrderId(orderEntityToReturn.getId());
      entity.setOfferId(findOfferByName(entity.getOfferName(), offerEntity));
      validateOrderPosition(entity);
      orderPositionsToReturn.add(getOrderPositionDao().save(entity));
    }
    return assembleOrderCto(orderEntityToReturn, orderPositionsToReturn);
  }

  /**
   * @param entity
   */
  private void validateOrderPosition(OrderPositionEntity entity) {

    if (entity.getOfferId() == null) {
      throw new OrderNotValidException("Offer id cannot be null!");
    }
    if (entity.getPrice() == null || entity.getPrice().getValue().intValue() < 0) {
      throw new OrderNotValidException("Offer position price cannot be negative!");
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
      return getBeanMapper().map(getOrderPositionDao().setOrderPositionStatus(id, orderState), OrderPositionEto.class);
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

  @Override
  @RolesAllowed(PermissionConstants.PREPARE_ORDER)
  public OrderPositionEto setDrinkStatusToDelivered(Long id) {

    return setDrinkStatus(id, ProductOrderState.DELIVERED);
  }

  private OrderPositionEto setDrinkStatus(Long id, ProductOrderState orderState) {

    OrderPositionEto foundPosition = getBeanMapper().map(getOrderPositionDao().find(id), OrderPositionEto.class);
    if (orderState.isOneAfter(foundPosition.getDrinkState())) {
      return getBeanMapper().map(getOrderPositionDao().setOrderedDrinkStatus(id, orderState), OrderPositionEto.class);
    } else {
      throw new IllegalStateException("Wrong order state!");
    }
  }

  @Override
  @RolesAllowed(PermissionConstants.PREPARE_ORDER)
  public OrderPositionEto setDrinkStatusToPrepared(Long id) {

    return setDrinkStatus(id, ProductOrderState.PREPARED);
  }

  @Override
  @Transactional
  @RolesAllowed(PermissionConstants.SAVE_BILL)
  public BillEto createBill(List<Long> orderPositionIds) {

    if (orderPositionIds.size() == 0) {
      throw new BillNotValidException("Bill must contain at least one position!");
    }
    if (!this.orderPositionDao.findOrderPositionIdsWhichContainAtLeastOnePositionId(orderPositionIds).isEmpty()) {
      throw new BillNotValidException("A least one order Position is already on another bill");
    }
    BillEntity bill = new BillEntity();
    List<OrderPositionEntity> orderPositions = this.orderPositionDao.findAllByIds(orderPositionIds);
    if (orderPositionIds.size() != orderPositions.size()) {
      throw new BillNotValidException("At least one given order position id does not exist!");
    }
    bill.setOrderPositions(orderPositions);
    Money money = new Money(
        orderPositions.stream().map(op -> op.getPrice().getValue()).reduce(BigDecimal.ZERO, (a, b) -> a.add(b)));
    bill.setTotal(money);
    return getBeanMapper().map(this.billDao.save(bill), BillEto.class);
  }

  @Override
  @Transactional
  @RolesAllowed(PermissionConstants.SAVE_BILL)
  public Money payBill(Long billId, Money toPay, Money tip) {

    BillEntity bill = this.billDao.find(billId);
    validateBillPayment(toPay, tip, bill);
    Money rest = new Money(toPay.getValue().subtract(bill.getTotal().getValue()));
    bill.setPayed(true);
    bill.setTip(tip);
    updateOrderStateOfRelatedOrders(bill);
    return rest;
  }

  /**
   * @param bill
   */
  private void updateOrderStateOfRelatedOrders(BillEntity bill) {

    bill.getOrderPositions().forEach(op -> op.setState(OrderPositionState.PAYED));
    Map<OrderEntity, Set<OrderPositionState>> collect = bill.getOrderPositions().stream()
        .collect(Collectors.toMap(op -> op.getOrder(), op -> Sets.newHashSet(op.getState()), (s1, s2) -> {
          s1.addAll(s2);
          return s2;
        }));
    for (OrderEntity o : collect.keySet()) {
      Set<OrderPositionState> ops = collect.get(o);
      if (ops.size() == 1 && ops.contains(OrderPositionState.PAYED)) {
        o.setState(OrderState.CLOSED);
      }
    }
  }

  /**
   * @param toPay
   * @param tip
   * @param bill
   */
  private void validateBillPayment(Money toPay, Money tip, BillEntity bill) {

    if (bill == null) {
      throw new PaymentNotValidException("Bill with given id does not exist!");
    }
    if (bill.isPayed()) {
      throw new PaymentNotValidException("Bill is already paid!");
    }
    if (toPay == null || toPay.compareTo(bill.getTotal()) < 0) {
      throw new PaymentNotValidException("Payment amount cannot be less than total of bill");
    }
    if (tip == null || BigDecimal.ZERO.compareTo(tip.getValue()) > 0) {
      throw new PaymentNotValidException("Tip cannot be negative!");
    }
  }

  @Override
  @RolesAllowed(PermissionConstants.FIND_BILL)
  public BillEto findBillById(Long billId) {

    return getBeanMapper().map(this.billDao.find(billId), BillEto.class);
  }

  @Override
  @RolesAllowed(PermissionConstants.FIND_ORDER)
  public List<OrderEto> findAllOrders() {

    return getBeanMapper().mapList(this.orderDao.findAll(), OrderEto.class);
  }

  @Override
  @RolesAllowed(PermissionConstants.FIND_BILL)
  public List<BillEto> findAllBills() {

    return getBeanMapper().mapList(this.billDao.findAll(), BillEto.class);
  }
}
