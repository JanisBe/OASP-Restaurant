package io.oasp.gastronomy.restaurant.offermanagement.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import io.oasp.gastronomy.restaurant.general.logic.base.AbstractComponentFacade;
import io.oasp.gastronomy.restaurant.offermanagement.common.api.datatype.OfferState;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.Offermanagement;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;

/**
 * @author DBIENIEK
 *
 */
@Named
public class OffermanagementImpl extends AbstractComponentFacade implements Offermanagement {

  @Override
  public OfferEto getOffer(long id) {

    OfferEto returnedOffer = new OfferEto();
    returnedOffer.setDescription("This is Offer with id:" + id);
    return returnedOffer;
  }

  @Override
  public OfferEto saveOffer(OfferEto offer) {

    if (isValidOffer(offer)) {
      return offer;
    }
    return new OfferEto();
  }

  private boolean isValidOffer(OfferEto offer) {

    return offer.getDrinkId() != null || offer.getMealId() != null || offer.getSideDishId() != null;
  }

  @Override
  public List<OfferEto> getAllOffers() {

    java.util.List<OfferEto> result = new ArrayList<>();

    OfferEto dummyOffer = new OfferEto();
    dummyOffer.setDescription("Test");
    dummyOffer.setId(Long.valueOf(101));
    dummyOffer.setState(OfferState.NORMAL);

    OfferEto dummyOfferTwo = new OfferEto();
    dummyOfferTwo.setDescription("Test2");
    dummyOfferTwo.setId(Long.valueOf(102));
    dummyOfferTwo.setState(OfferState.SOLDOUT);

    result.add(dummyOffer);
    result.add(dummyOfferTwo);
    return result;
  }

  @Override
  public List<OfferEto> getAvailableOffers() {

    List<OfferEto> result = new ArrayList<>();
    for (OfferEto offer : getAllOffers()) {
      if (!offer.getState().isSoldout()) {
        result.add(offer);
      }
    }
    return result;
  }

}
