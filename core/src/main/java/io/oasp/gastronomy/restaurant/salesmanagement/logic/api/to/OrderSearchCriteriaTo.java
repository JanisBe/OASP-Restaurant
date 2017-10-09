package io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to;

import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderState;
import io.oasp.module.jpa.common.api.to.SearchCriteriaTo;

/**
 * @author PKALINIA
 *
 */
public class OrderSearchCriteriaTo extends SearchCriteriaTo {

  private static final long serialVersionUID = -3183378438390339559L;

  private OrderState orderState;

  private long tableId;

  /**
   * @return orderState
   */
  public OrderState getOrderState() {

    return this.orderState;
  }

  /**
   * @param orderState new value of {@link #getorderState}.
   */
  public void setOrderState(OrderState orderState) {

    this.orderState = orderState;
  }

  /**
   * @return tableId
   */
  public long getTableId() {

    return this.tableId;
  }

  /**
   * @param tableId new value of {@link #gettableId}.
   */
  public void setTableId(long tableId) {

    this.tableId = tableId;
  }

}
