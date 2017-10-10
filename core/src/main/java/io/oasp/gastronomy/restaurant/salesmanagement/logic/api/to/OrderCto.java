package io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to;

import java.util.List;

/**
 * @author PKALINIA
 *
 */
public class OrderCto {

  private OrderEto order;

  private List<OrderPositionEto> positions;

  /**
   * @return order
   */
  public OrderEto getOrder() {

    return this.order;
  }

  /**
   * @param order new value of {@link #getorder}.
   */
  public void setOrder(OrderEto order) {

    this.order = order;
  }

  /**
   * @return positions
   */
  public List<OrderPositionEto> getPositions() {

    return this.positions;
  }

  /**
   * @param positions new value of {@link #getpositions}.
   */
  public void setPositions(List<OrderPositionEto> positions) {

    this.positions = positions;
  }

}
