package io.oasp.gastronomy.restaurant.salesmanagement.service.api.rest;

import java.util.Collection;
import java.util.List;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderState;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.BillEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderCto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderPositionEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.PaymentCto;

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

  @GET
  @Path("/order/all/")
  List<OrderEto> getOrders();

  @POST
  @Path("/order/")
  OrderCto saveOrder(OrderCto order);

  @GET
  @Path("/order/")
  List<OrderEto> searchOrder(@PathParam("state") OrderState orderState, @PathParam("tableId") Long tableId);

  @POST
  @Path("/order/deliver/")
  OrderPositionEto deliverOrderPosition(@PathParam("tableId") Long tableId);

  @POST
  @Path("/order/prepare/")
  OrderPositionEto prepareOrderPosition(@PathParam("tableId") Long tableId);

  @GET
  @Path("/bill/find/{billId}")
  BillEto findBill(@PathParam("billId") Long billId);

  @GET
  @Path("/bill/find/all")
  List<BillEto> findAllBills();

  @POST
  @Path("/bill/create")
  BillEto createBill(Collection<Long> orderPositionIds);

  @POST
  @Path("/bill/pay")
  Money payBill(PaymentCto paymentCto);

}
