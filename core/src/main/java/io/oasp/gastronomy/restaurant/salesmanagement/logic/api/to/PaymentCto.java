package io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to;

import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;

/**
 * @author PKALINIA
 *
 */
public class PaymentCto {

  private Long billId;

  private Money toPay;

  private Money tip;

  /**
   * @return billId
   */
  public Long getBillId() {

    return this.billId;
  }

  /**
   * @param billId new value of {@link #getbillId}.
   */
  public void setBillId(Long billId) {

    this.billId = billId;
  }

  /**
   * @return toPay
   */
  public Money getToPay() {

    return this.toPay;
  }

  /**
   * @param toPay new value of {@link #gettoPay}.
   */
  public void setToPay(Money toPay) {

    this.toPay = toPay;
  }

  /**
   * @return tip
   */
  public Money getTip() {

    return this.tip;
  }

  /**
   * @param tip new value of {@link #gettip}.
   */
  public void setTip(Money tip) {

    this.tip = tip;
  }

}
