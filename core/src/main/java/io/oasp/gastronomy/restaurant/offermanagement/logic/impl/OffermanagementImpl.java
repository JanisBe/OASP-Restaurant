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
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.OfferEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.ProductEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.OfferDao;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.ProductDao;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.Offermanagement;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.ProductEto;

/**
 * @author DBIENIEK
 *
 */
@Named
public class OffermanagementImpl extends AbstractComponentFacade implements Offermanagement {

  private ProductDao mProductDao;

  private OfferDao mOfferDao;

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

    return getBeanMapper().mapList(getOfferDao().findAll(), OfferEto.class);
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
  public ProductEto findProductByName(String name) {

    return getBeanMapper().map(getProductDao().findByName(name), ProductEto.class);
  }

  public ProductDao getProductDao() {

    return this.mProductDao;
  }

  public OfferDao getOfferDao() {

    return this.mOfferDao;
  }

  @Inject
  public void setProductDao(ProductDao productDao) {

    this.mProductDao = productDao;
  }

  @Inject
  public void setOfferDao(OfferDao offerDao) {

    this.mOfferDao = offerDao;
  }

  @Override
  @Transactional
  public void saveProduct(List<ProductEto> menuItems) {

    getProductDao().save(getBeanMapper().mapList(menuItems, ProductEntity.class));
  }

  @Override
  public List<ProductEto> getAllProducts() {

    return getBeanMapper().mapList(getProductDao().findAll(), ProductEto.class);
  }

  @Override
  @RolesAllowed(PermissionConstants.SET_AS_SPECIAL)
  @Transactional
  public OfferEto setAsSpecial(String name) {

    OfferEntity foundOfferEntity = getOfferDao().findByName(name);
    if (foundOfferEntity != null) {
      foundOfferEntity.setState(OfferState.SPECIAL);
      return getBeanMapper().map(foundOfferEntity, OfferEto.class);
    }
    return null;
  }

}
