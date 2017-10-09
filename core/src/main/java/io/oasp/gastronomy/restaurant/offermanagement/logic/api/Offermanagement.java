package io.oasp.gastronomy.restaurant.offermanagement.logic.api;

import java.util.List;

import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;

/**
 * @author DBIENIEK
 *
 */
public interface Offermanagement {

  /**
   * @param id
   * @return
   */
  OfferEto getOffer(long id);

  /**
   * @param offer
   * @return
   */
  OfferEto saveOffer(OfferEto offer);

  /**
   * @return
   */
  List<OfferEto> getAllOffers();

  /**
   * @return
   */
  List<OfferEto> getAvailableOffers();
}
