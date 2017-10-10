package io.oasp.gastronomy.restaurant.offermanagement.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import io.oasp.gastronomy.restaurant.general.common.api.constants.PermissionConstants;
import io.oasp.gastronomy.restaurant.general.logic.base.AbstractComponentFacade;
import io.oasp.gastronomy.restaurant.offermanagement.common.api.datatype.OfferState;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.MenuItemEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.ProductEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.ProductDao;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.Offermanagement;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.ProductEto;

/**
 * @author DBIENIEK
 *
 */
@Named
@Transactional
public class OffermanagementImpl extends AbstractComponentFacade implements Offermanagement {

  private ProductDao mProductDao;

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
  @RolesAllowed(PermissionConstants.GET_ALL_OFFERS)
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

    OfferEto dummyOfferThree = new OfferEto();
    dummyOfferThree.setDescription("Test3");
    dummyOfferThree.setId(Long.valueOf(103));
    dummyOfferThree.setState(OfferState.SPECIAL);

    result.add(dummyOffer);
    result.add(dummyOfferTwo);
    result.add(dummyOfferThree);
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

  @Override
  public List<OfferEto> getPromotionOffers() {

    List<OfferEto> result = new ArrayList<>();
    for (OfferEto offer : getAllOffers()) {
      if (offer.getState().isSpecial()) {
        result.add(offer);
      }
    }
    return result;
  }

  @Override
  public List<ProductEto> findProductByName(String name) {

    return getBeanMapper().mapList(getProductDao().findByName(name), ProductEto.class);
  }

  public ProductDao getProductDao() {

    return this.mProductDao;
  }

  @Inject
  public void setProductDao(ProductDao productDao) {

    this.mProductDao = productDao;
  }

  @Override
  public ProductEto saveProduct(ProductEto menuItem) {

    MenuItemEntity persistedMenuItem = getProductDao().save(getBeanMapper().map(menuItem, ProductEntity.class));
    return getBeanMapper().map(persistedMenuItem, ProductEto.class);
  }

}
