package io.oasp.gastronomy.restaurant.offermanagement.logic.api;

import java.util.List;

import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.ProductEto;

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

  /**
   * @return
   */
  List<OfferEto> getPromotionOffers();

  /**
   * @return
   */
  ProductEto findProductByName(String name);

  /**
   * @param menuItem
   * @return
   * @return
   */
  void saveProduct(List<ProductEto> product);

  /**
   * @return
   */
  List<ProductEto> getAllProducts();

  /**
   * @param name
   * @return
   */
  OfferEto setAsSpecial(String name);
}
