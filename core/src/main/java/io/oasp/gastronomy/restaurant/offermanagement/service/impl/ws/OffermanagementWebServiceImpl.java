package io.oasp.gastronomy.restaurant.offermanagement.service.impl.ws;

import java.util.List;

import javax.inject.Named;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import io.oasp.gastronomy.restaurant.offermanagement.logic.api.Offermanagement;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.ProductEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.exceptions.OrderNotValidException;
import io.oasp.gastronomy.restaurant.offermanagement.service.api.ws.OffermanagementWebService;

/**
 * @author DBIENIEK
 *
 */
@Named("OffermanagementWebService")
@WebService(endpointInterface = "io.oasp.gastronomy.restaurant.offermanagement.service.api.ws.OffermanagementWebService")
public class OffermanagementWebServiceImpl implements OffermanagementWebService {

  @Autowired
  private Offermanagement offerManagement;

  @Override
  public OfferEto getOffer(long id) {

    return this.offerManagement.getOffer(id);
  }

  @Override
  public List<OfferEto> getAllOffers() {

    return this.offerManagement.getAllOffers();
  }

  @Override
  public List<OfferEto> getPromotionOffers() {

    return this.offerManagement.getPromotionOffers();
  }

  @Override
  public List<OfferEto> getAvailableOffers() {

    return this.offerManagement.getAvailableOffers();
  }

  @Override
  public OfferEto saveOffer(OfferEto offer) throws OrderNotValidException {

    return this.offerManagement.saveOffer(offer);
  }

  @Override
  public OfferEto setAsSpecial(String name) {

    return this.offerManagement.setAsSpecial(name);
  }

  @Override
  public ProductEto findProductByName(String name) {

    return this.offerManagement.findProductByName(name);
  }

  @Override
  public List<ProductEto> getAllProducts() {

    return this.offerManagement.getAllProducts();
  }

  @Override
  public void saveProducts(List<ProductEto> menuItems) {

    this.offerManagement.saveProducts(menuItems);
  }

}
