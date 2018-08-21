package com.foxkiev.app.model.api;

import com.foxkiev.app.Constants;
import com.foxkiev.app.model.models.Cart;
import com.foxkiev.app.model.models.CartItem;
import com.foxkiev.app.model.models.CartTotal;
import com.foxkiev.app.model.models.request.CartItemWrapper;
import com.foxkiev.app.model.models.request.TransferGuestCartWrapper;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by lipcha on 03.04.18.
 */

public interface CartApi {

    @POST("guest-carts")
    Observable<String> createGuestCart();

    @GET("guest-carts/{guestCartQuoteId}")
    Observable<Cart> getGuestCart(@Path("guestCartQuoteId") String guestCartQuoteId);

    @HTTP(method = "POST", path = "guest-carts/{cartId}/items", hasBody = true)
    Observable<CartItem> addProductToGuestCart(@Path("cartId") String cartId, @Body CartItemWrapper cartItemWrapper);

    @HTTP(method = "PUT", path = "guest-carts/{cartId}/items/{itemId}", hasBody = true)
    Observable<CartItem> editGuestCartProduct(@Path("cartId") String cartId, @Path("itemId") String itemId, @Body CartItemWrapper cartItemWrapper);

    @HTTP(method = "DELETE", path = "guest-carts/{cartId}/items/{itemId}")
    Observable<Boolean> removeProductFromGuestCart(@Path("cartId") String cartId, @Path("itemId") String itemId);

    @GET("guest-carts/{cartId}/totals")
    Observable<CartTotal> getGuestCartTotal(@Path("cartId") String cartId);

    @POST("carts/mine")
    Observable<String> createCustomerCart();

    @GET("carts/mine")
    Observable<Cart> getCustomerCartId();

    @HTTP(method = "POST", path = Constants.API.REQUESTS.ADD_PRODUCT_TO_CUSTOMER_CART, hasBody = true)
    Observable<CartItem> addProductToCustomerCart(@Body CartItemWrapper cartItemWrapper);

    @HTTP(method = "PUT", path = Constants.API.REQUESTS.EDIT_CUSTOMER_CART_PRODUCT + "/{itemId}", hasBody = true)
    Observable<CartItem> editCustomerCartProduct(@Path("itemId") String itemId, @Body CartItemWrapper cartItemWrapper);

    @HTTP(method = "DELETE", path = Constants.API.REQUESTS.REMOVE_PRODUCT_FROM_CUSTOMER_CART + "/{itemId}")
    Observable<Boolean> removeProductFromCustomerCart(@Path("itemId") String itemId);

    @GET(Constants.API.REQUESTS.GET_CUSTOMER_CART_TOTAL)
    Observable<CartTotal> getCustomerCartTotal();

    @PUT(Constants.API.REQUESTS.TRANSFER_GUEST_CART)
    Observable<List<String>> transferGuestCart(@Body TransferGuestCartWrapper wrapper);
}
