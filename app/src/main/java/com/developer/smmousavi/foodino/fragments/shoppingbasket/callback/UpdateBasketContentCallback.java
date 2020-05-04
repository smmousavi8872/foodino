package com.developer.smmousavi.foodino.fragments.shoppingbasket.callback;

public interface UpdateBasketContentCallback {

    void onProductCountChanged(double priceDifference);

    void onProductRemoved(int position, double priceDifference);
}
