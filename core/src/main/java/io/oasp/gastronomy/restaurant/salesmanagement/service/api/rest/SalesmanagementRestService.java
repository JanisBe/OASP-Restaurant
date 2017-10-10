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

import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderPositionState;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderState;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderCto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderPositionEto;

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
  OrderEto getOrder(@PathParam("id") Long id);

  @POST
  @Path("/order/")
  OrderCto saveOrder(OrderCto order);

  @GET
  @Path("/order/")
  List<OrderEto> searchOrder(@PathParam("state") OrderState orderState, @PathParam("tableId") Long tableId);

  @POST
  @Path("/orderpayment/{id}/")
  OrderCto payForOrder(OrderCto order);

  @POST
  @Path("/changeStatus")
  OrderPositionEto setOrderPositionStatus(@PathParam("tableId") Long tableId,
      @PathParam("orderState") OrderPositionState orderState);
}
