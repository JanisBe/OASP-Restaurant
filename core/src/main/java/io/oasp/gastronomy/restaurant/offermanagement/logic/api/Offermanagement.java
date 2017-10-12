package io.oasp.gastronomy.restaurant.offermanagement.logic.api;

import java.util.List;

import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.ProductEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.exceptions.OrderNotValidException;

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
   * @throws OrderNotValidException
   */
  OfferEto saveOffer(OfferEto offer) throws OrderNotValidException;

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
  void saveProducts(List<ProductEto> product);

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
