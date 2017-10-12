package io.oasp.gastronomy.restaurant.offermanagement.service.api.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.ProductEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.exceptions.OrderNotValidException;

/**
 * @author DBIENIEK
 *
 */
@WebService
public interface OffermanagementWebService {

  @WebMethod
  @WebResult(name = "offer")
  OfferEto getOffer(@WebParam(name = "id") long id);

  @WebMethod
  @WebResult(name = "offers")
  List<OfferEto> getAllOffers();

  @WebMethod
  @WebResult(name = "promotionOffers")
  List<OfferEto> getPromotionOffers();

  @WebMethod
  @WebResult(name = "availableOffers")
  List<OfferEto> getAvailableOffers();

  @WebMethod
  @WebResult(name = "savedOffer")
  OfferEto saveOffer(OfferEto offer) throws OrderNotValidException;

  @WebMethod
  @WebResult(name = "specialOffer")
  OfferEto setAsSpecial(@WebParam(name = "name") String name);

  @WebMethod
  @WebResult(name = "foundProduct")
  ProductEto findProductByName(@WebParam(name = "name") String name);

  @WebMethod
  @WebResult(name = "allProducts")
  List<ProductEto> getAllProducts();

  @WebMethod
  void saveProducts(@WebParam(name = "menuItems") List<ProductEto> menuItems);
}
