package io.oasp.gastronomy.restaurant.offermanagement.service.impl.rest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.oasp.gastronomy.restaurant.offermanagement.logic.api.Offermanagement;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.ProductEto;
import io.oasp.gastronomy.restaurant.offermanagement.service.api.rest.OffermanagementRestService;

/**
 * @author DBIENIEK
 *
 */
@Named("OffermanagementRestService")
public class OffermanagementRestServiceImpl implements OffermanagementRestService {

  private Offermanagement offerManagement;

  /**
   * This method sets the field <tt>offerManagement</tt>.
   *
   * @param offerManagement the new value of the field tableManagement
   */
  @Inject
  public void setOfferManagement(Offermanagement offerManagement) {

    this.offerManagement = offerManagement;
  }

  @Override
  public OfferEto getOffer(long id) {

    return this.offerManagement.getOffer(id);
  }

  @Override
  public OfferEto saveOffer(OfferEto offer) {

    return this.offerManagement.saveOffer(offer);
  }

  @Override
  public List<OfferEto> getAllOffers() {

    return this.offerManagement.getAllOffers();
  }

  @Override
  public List<OfferEto> getAvailableOffers() {

    return this.offerManagement.getAvailableOffers();
  }

  @Override
  public List<OfferEto> getPromotionOffers() {

    return this.offerManagement.getPromotionOffers();
  }

  @Override
  public List<ProductEto> findProductByName(String name) {

    return this.offerManagement.findProductByName(name);
  }

  @Override
  public ProductEto saveProduct(ProductEto menuItem) {

    return this.offerManagement.saveProduct(menuItem);
  }

}
