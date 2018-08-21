package com.foxkiev.app.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.foxkiev.app.Constants;
import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseListFragment;
import com.foxkiev.app.base.list.BaseListPresenter;
import com.foxkiev.app.base.list.DataProvider;
import com.foxkiev.app.base.list.ListConfiguration;
import com.foxkiev.app.model.models.CartItem;
import com.foxkiev.app.model.models.CartTotal;
import com.foxkiev.app.presentation.presenter.CartItemsPresenter;
import com.foxkiev.app.presentation.view.CartItemsView;
import com.foxkiev.app.ui.activity.MainActivity;
import com.foxkiev.app.ui.adapters.cart.CartItemViewCallback;
import com.foxkiev.app.ui.adapters.cart.CartListAdapter;
import com.foxkiev.app.ui.adapters.cart.CartListConfiguration;
import com.foxkiev.app.ui.custom_views.CartTotalView;
import com.foxkiev.app.ui.custom_views.TopBanner;
import com.foxkiev.app.ui.custom_views.dialogs.ConfirmDialog;
import com.foxkiev.app.ui.custom_views.dialogs.EditCountDialog;
import com.foxkiev.app.utils.ErrorHandler;

import butterknife.BindView;

/**
 * Created by lipcha on 06.04.18.
 */

public class CartFragment extends BaseListFragment<CartItem, CartListAdapter> implements CartItemsView, CartItemViewCallback {

    public static CartFragment newInstance(){
        return new CartFragment();
    }

    @InjectPresenter(type = PresenterType.GLOBAL)
    CartItemsPresenter mCartItemsPresenter;

    @BindView(R.id.btnCheckout)
    Button btnCheckout;

    @BindView(R.id.cartTotalView)
    CartTotalView cartTotalView;

    @BindView(R.id.cartInfoContainer)
    View cartInfoContainer;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_cart;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        setupClick(btnCheckout, o -> mCartItemsPresenter.checkout());
    }

    @Override
    protected String getTitle() {
        return getString(R.string.menu_item_shopping_cart);
    }

    @Override
    protected BaseListPresenter<CartItem, ?> getListPresenter() {
        return mCartItemsPresenter;
    }

    @Override
    protected CartListAdapter createListAdapter(DataProvider<CartItem> dataProvider) {
        return new CartListAdapter(dataProvider, this, getMvpDelegate());
    }

    @Override
    public void onResume() {
        super.onResume();
        mCartItemsPresenter.updateCart(true);
    }

    @Override
    public void onItemClick(CartItem cartItem, int position) {
        if (!TextUtils.isEmpty(cartItem.getSku()))
            getRouter().addFragmentToBackStack(ProductDetailFragment.newInstance(cartItem.getSku()));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void renderCartTotal(CartTotal cart) {
        if (getBaseActivity() instanceof MainActivity){
            ((MainActivity)getBaseActivity()).setCartItemsCount(cart.getItemsQty());
        }
        if (cart.getItems() == null || cart.getItems().isEmpty()){
            cartInfoContainer.setVisibility(View.GONE);
            return;
        }
        cartInfoContainer.setVisibility(View.VISIBLE);
        cartTotalView.renderCartTotal(cart);
    }

    @Override
    public void onClickRemoveCartItem(CartItem cartItem) {
        ConfirmDialog
                .newBuilder(getBaseActivity())
                .setTitle(R.string.title_remove)
                .setMessage(R.string.message_confirm_remove_cart_item)
                .setIcon(R.drawable.ic_remove)
                .setConfirmCallback(() -> mCartItemsPresenter.removeCartItem(cartItem))
                .show();
    }

    @Override
    public void onClickPlusItemCount(CartItem cartItem) {
        mCartItemsPresenter.plusItemCount(cartItem.getItemId(), cartItem.getQty());
    }

    @Override
    public void onClickMinusItemCount(CartItem cartItem) {
        mCartItemsPresenter.minusItemCount(cartItem.getItemId(), cartItem.getQty());
    }

    @Override
    public void onClickEditItemCount(CartItem cartItem) {
        EditCountDialog.show(getBaseActivity(), cartItem.getQty(),1, Constants.MAX_CART_PRODUCT, count -> mCartItemsPresenter.editCartItem(cartItem, count));
    }

    @Override
    public void onEditCartItemError(Throwable throwable) {
        ErrorHandler.onError(getContext(), throwable);
    }

    @Override
    public void navigateToCheckout() {
        getRouter().addFragmentToBackStack(ShippingAddressFragment.newInstance());
    }

    @Override
    public void navigateToAuthenticate() {
        ConfirmDialog
                .newBuilder(getBaseActivity())
                .setMessage(R.string.message_only_registered_users_can_bye)
                .setVisibleCancelButton(true)
                .setHeaderBackgroundColor(Color.GREEN)
                .setIcon(R.drawable.ic_warning)
                .setTextPositiveButton(R.string.btn_text_sign_in)
                .setConfirmCallback(() -> {
                    final Activity activity = getActivity();
                    if (activity != null && activity instanceof MainActivity){
                        ((MainActivity) activity).changeTab(3);
                        ((MainActivity) activity).selectTabItem(3);
                        getRouter().showRoot();
                    }
                }).show();
    }

    @Override
    protected ListConfiguration createListConfiguration() {
        return new CartListConfiguration(getContext());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.cart_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.clearCart:
                mCartItemsPresenter.clearCart();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
