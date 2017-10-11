package io.oasp.gastronomy.restaurant.offermanagement.service.impl.rest;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import io.oasp.gastronomy.restaurant.offermanagement.logic.api.Offermanagement;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.ProductEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.exceptions.OrderNotValidException;
import io.oasp.gastronomy.restaurant.offermanagement.service.api.rest.OffermanagementRestService;

/**
 * @author DBIENIEK
 *
 */
@Named("OffermanagementRestService")
public class OffermanagementRestServiceImpl implements OffermanagementRestService {

  @Autowired
  private Offermanagement offerManagement;

  @Override
  public OfferEto getOffer(long id) {

    return this.offerManagement.getOffer(id);
  }

  @Override
  public OfferEto saveOffer(OfferEto offer) throws OrderNotValidException {

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
  public ProductEto findProductByName(String name) {

    return this.offerManagement.findProductByName(name);
  }

  @Override
  public void saveProducts(List<ProductEto> menuItems) {

    this.offerManagement.saveProducts(menuItems);
  }

  @Override
  public List<ProductEto> getAllProducts() {

    return this.offerManagement.getAllProducts();
  }

  @Override
  public OfferEto setAsSpecial(String name) {

    return this.offerManagement.setAsSpecial(name);
  }

}
