package io.oasp.gastronomy.restaurant.offermanagement.service.api.rest;

import java.util.List;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.oasp.gastronomy.restaurant.general.common.api.RestService;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.ProductEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.exceptions.OrderNotValidException;

/**
 * @author DBIENIEK
 *
 */
@Path("/offermanagement/v1")
@Named("OffermanagementRestService")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface OffermanagementRestService extends RestService {

  @GET
  @Path("/offer/{id}/")
  OfferEto getOffer(@PathParam("id") long id);

  @GET
  @Path("/offer/")
  List<OfferEto> getAllOffers();

  @GET
  @Path("/offer/promotion/")
  List<OfferEto> getPromotionOffers();

  @GET
  @Path("/offer/available/")
  List<OfferEto> getAvailableOffers();

  @POST
  @Path("/offer/")
  OfferEto saveOffer(OfferEto offer) throws OrderNotValidException;

  @POST
  @Path("/offer/setAsSpecial/")
  OfferEto setAsSpecial(@QueryParam("name") String name);

  @GET
  @Path("/offer/menu/{name}/")
  ProductEto findProductByName(@PathParam("name") String name);

  @GET
  @Path("/offer/menu/")
  List<ProductEto> getAllProducts();

  @POST
  @Path("/offer/menu/")
  void saveProducts(List<ProductEto> menuItems);
}
