package io.oasp.gastronomy.restaurant.salesmanagement.service.api.rest;

import java.util.List;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderState;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderEto;

/**
 * @author PKALINIA
 *
 */
@Path("/salesmanagement")
@Named("SalesmanagementRestService")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface SalesmanagementRestService {

  @GET
  @Path("/order/{id}/")
  OrderEto getOrder(@PathParam("id") long id);

  @POST
  @Path("/order/")
  OrderEto saveOrder(OrderEto order);

  @GET
  @Path("/order/")
  List<OrderEto> searchOrder(@PathParam("state") OrderState orderState, @PathParam("tableId") long tableId);

}
