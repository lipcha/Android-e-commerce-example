package com.foxkiev.app;
9
import java.util.Arrays;
import java.util.List;

/**
 * Created by lipcha on 20.02.18.
 */

public class Constants {

    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final String KEY_PUSH = "key_push";

    public static final String LANGUAGE_DEFAULT = "ru";
    public static final String LANGUAGE_EN = "en";

    public static final String PAYMENT_METHOD_CASH = "cashondelivery";
    public static final String PAYMENT_METHOD_BANK = "banktransfer";

    public static final String CURRENCY_CODE_EUR = "EUR";
    public static final String CURRENCY_CODE_UAH = "UAH";

    public static final class API{
        public static final String BASE_SERVER_URL = "http://192.169.88.112/";
        public static final String API_VERSION = "rest/V1/";
        public static final String CONSUMER_KEY = "";
        public static final String CONSUMER_SECRET = "";
        public static final String ACCESS_TOKEN = "";
        public static final String ACCESS_SECRET_TOKEN = "";
        public static final String NOVA_POSHTA_API_KEY = "";

        public static final String BASE_MEDIA_URL = BASE_SERVER_URL + "media/catalog/product/";

        public static final String NOVA_POSHTA_BASE_URL = "https://api.novaposhta.ua/v2.0/json/";

        public static final long READ_TIMEOUT = 30L;
        public static final long CONNECT_TIMEOUT = 30L;
        public static final long WRITE_TIMEOUT = 20L;

        public static final class REQUESTS{
            public static final String GET_CUSTOMER = "customers/me";
            public static final String BILLING_ADDRESS = "customers/me/billingAddress";
            public static final String SHIPPING_ADDRESS = "customers/me/shippingAddress";

            public static final String EDIT_BILLING_ADDRESS = "carts/mine/billing-address";
            public static final String SET_SHIPPING_INFO = "carts/mine/shipping-information";
            public static final String GET_SHIPPING_METHODS = "carts/mine/shipping-methods";
            public static final String ESTIMATE_SHIPPING_METHODS = "carts/mine/estimate-shipping-methods-by-address-id";
            public static final String SET_PAYMENT_INFO = "carts/mine/payment-information";

            public static final String CREATE_CUSTOMER_CART = "carts/mine";
            public static final String TRANSFER_GUEST_CART = "carts/mine";
            public static final String ADD_PRODUCT_TO_CUSTOMER_CART = "carts/mine/items";
            public static final String EDIT_CUSTOMER_CART_PRODUCT = "carts/mine/items";
            public static final String REMOVE_PRODUCT_FROM_CUSTOMER_CART = "carts/mine/items";
            public static final String GET_CUSTOMER_CART_TOTAL = "carts/mine/totals";
            public static final String CHANGE_PASSWORD = "customers/me/password";
            public static final String GET_MY_ORDERS = "orders";
        }

        public static List<String> TOKEN_SIGN_IN_REQUESTS = Arrays.asList(
                "/" + API_VERSION + REQUESTS.GET_CUSTOMER,
                "/" + API_VERSION + REQUESTS.BILLING_ADDRESS,
                "/" + API_VERSION + REQUESTS.SHIPPING_ADDRESS,
                "/" + API_VERSION + REQUESTS.EDIT_BILLING_ADDRESS,
                "/" + API_VERSION + REQUESTS.SET_SHIPPING_INFO,
                "/" + API_VERSION + REQUESTS.CREATE_CUSTOMER_CART,
                "/" + API_VERSION + REQUESTS.ADD_PRODUCT_TO_CUSTOMER_CART,
                "/" + API_VERSION + REQUESTS.EDIT_CUSTOMER_CART_PRODUCT,
                "/" + API_VERSION + REQUESTS.REMOVE_PRODUCT_FROM_CUSTOMER_CART,
                "/" + API_VERSION + REQUESTS.GET_CUSTOMER_CART_TOTAL,
                "/" + API_VERSION + REQUESTS.GET_SHIPPING_METHODS,
                "/" + API_VERSION + REQUESTS.CHANGE_PASSWORD,
                "/" + API_VERSION + REQUESTS.ESTIMATE_SHIPPING_METHODS,
                "/" + API_VERSION + REQUESTS.SET_PAYMENT_INFO,
                "/" + API_VERSION + REQUESTS.TRANSFER_GUEST_CART
//                "/" + API_VERSION + REQUESTS.GET_MY_ORDERS
        );
    }

    public static final int MODE_LIST = 0;
    public static final int MODE_GRID = 1;


    public static final int FIRST_CLICK_DELAY = 600; // millisecond
    public static final int SPLASH_DELAY = 1; //second

    public static final int MAX_CART_PRODUCT = 99;

    public static final int REQUEST_CODE_GET_CITY = 11;
    public static final int REQUEST_CODE_GET_WAREHOUSE = 12;

    public static final String KEY_CITY = "key_city";
    public static final String KEY_WAREHOUSE = "key_warehouse";

    public static final String[] SEARCH_PRODUCTS_FIELDS = {"name", "sku", "short_description"};

}
