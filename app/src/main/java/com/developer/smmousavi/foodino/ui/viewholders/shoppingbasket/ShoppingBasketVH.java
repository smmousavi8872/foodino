package com.developer.smmousavi.foodino.ui.viewholders.shoppingbasket;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.helper.GlideHelper;
import com.developer.smmousavi.foodino.models.Product;
import com.developer.smmousavi.foodino.ui.fragments.alertdialog.AlertDialogFragment;
import com.developer.smmousavi.foodino.ui.fragments.alertdialog.OnDialogButtonClickListener;
import com.developer.smmousavi.foodino.ui.fragments.shoppingbasket.callback.UpdateBasketContentCallback;
import com.developer.smmousavi.foodino.util.Calculator;
import com.developer.smmousavi.foodino.util.PriceUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ShoppingBasketVH extends RecyclerView.ViewHolder {

    private static final String ALERT_DIALOG_FM_TAG = "alertDialogFmTag";
    public static final String TAG = "shoppingBasketVHTag";

    @BindView(R.id.imgShoppingBasketRowProdcutAvatar)
    AppCompatImageView mImgProductAvatar;
    @BindView(R.id.txtShoppingBasketRowProdcutTitle)
    AppCompatTextView mTxtProductTitle;
    @BindView(R.id.txtShoppingBasketRowTitleDescription)
    AppCompatTextView mTxtTitleDescription;
    @BindView(R.id.txtShoppingBasketRowGuaranteeName)
    AppCompatTextView mTxtProductGuaranteeName;
    @BindView(R.id.txtShoppingBasketProductSeller)
    AppCompatTextView mTxtProductSeller;
    @BindView(R.id.spnShoppingBasketRowProductCount)
    AppCompatSpinner mSpnProductCount;
    @BindView(R.id.txtShopingBasketRowProductMainPrice)
    AppCompatTextView mTxtProductMainPrice;
    @BindView(R.id.txtShopingBasketRowProductDiscountPrice)
    AppCompatTextView mTxtProductDiscountPrice;
    @BindView(R.id.txtShopingBasketRowProductFinalPrice)
    AppCompatTextView mTxtProductFinalPrice;
    @BindView(R.id.txtShoppingBasketRowDeleteProduct)
    AppCompatTextView mTxtDeleteProduct;
    @BindView(R.id.rlShoppingBasketDiscountContainer)
    RelativeLayout mRlShoppingBasketDiscountContainer;

    private View mItemView;
    private List<Integer> mSpinnerList;
    private UpdateBasketContentCallback mUpdateBasketContentCallback;
    private Product mProduct;
    private int mProductCount = 1;
    private int mCountDifference = 1;
    private FragmentManager mFm;

    public void setUpdateBasketContentCallback(UpdateBasketContentCallback callback) {
        mUpdateBasketContentCallback = callback;
    }

    public ShoppingBasketVH(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mItemView = itemView;
    }

    public void bind(Product product, FragmentManager fm) {
        mProduct = product;
        mFm = fm;
        bindProductSpecification();
        bindProductPrices();
        initSpinnerList();
        setSpinnerAdapter();
    }

    private void initSpinnerList() {
        mSpinnerList = new ArrayList<>();
        for (int i = 1; i <= mProduct.maxBuyCount(); i++) {
            mSpinnerList.add(i);
        }
    }

    public void setSpinnerAdapter() {
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(mItemView.getContext(), R.layout.item_spinner_text, mSpinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnProductCount.setAdapter(adapter);
        mProductCount = (int) mSpnProductCount.getSelectedItem();
        mSpnProductCount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mCountDifference = mSpinnerList.get(i) - mProductCount;
                mProductCount = mSpinnerList.get(i);
                if (mCountDifference != 0) {
                    double priceDifference = getPriceDifference(mCountDifference);
                    mUpdateBasketContentCallback.onProductCountChanged(priceDifference);
                    bindProductPrices();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void bindProductSpecification() {
        if (mProduct.avatarUrl() != null)
            GlideHelper.build(R.drawable.foodino_place_holder,
                mItemView.getContext(),
                mProduct.avatarUrl(),
                mImgProductAvatar);

        if (mProduct.title() != null)
            mTxtProductTitle.setText(mProduct.title());
        else
            mTxtProductTitle.setText("");

        if (mProduct.titleDescription() != null)
            mTxtTitleDescription.setText(mProduct.titleDescription());
        else
            mTxtTitleDescription.setText("");


        if (mProduct.guaranteeName() != null)
            mTxtProductGuaranteeName.setText(mProduct.guaranteeName());
        else
            mTxtProductGuaranteeName.setText("");

        if (mProduct.sellerName() != null)
            mTxtProductSeller.setText(mProduct.sellerName());
        else
            mTxtProductSeller.setText("");
    }

    private void bindProductPrices() {
        double totalPrice = mProduct.price() * mProductCount;
        String productDiscount;
        String productFinalPrice;
        mTxtProductMainPrice.setText(PriceUtils.getFormattedPrice(totalPrice));
        if (mProduct.isSpecialOffer() && mProduct.discountPercent() != null) {
            productDiscount = PriceUtils.getFormattedPrice(Calculator.getDiscountAmount(totalPrice, mProduct.discountPercent()));
            productFinalPrice = PriceUtils.getFormattedPrice(Calculator.getFinalPrice(totalPrice, mProduct.discountPercent()));
            mTxtProductDiscountPrice.setText(productDiscount);
            mTxtProductFinalPrice.setText(productFinalPrice);
        } else {
            productFinalPrice = PriceUtils.getFormattedPrice(mProduct.price());
            mRlShoppingBasketDiscountContainer.setVisibility(View.GONE);
            mTxtProductFinalPrice.setText(productFinalPrice);
        }
    }

    private double getPriceDifference(int count) {
        return Calculator.getFinalPrice(mProduct.price(), mProduct.discountPercent()) * count;
    }

    @OnClick(R.id.txtShoppingBasketRowDeleteProduct)
    public void setProductRemoveListener() {
        Context context = mItemView.getContext();
        String title = context.getString(R.string.removePorductAlertTitle);
        String message = context.getString(R.string.removeProductAlertMessage, mProduct.title());
        String positiveButtonText = context.getString(R.string.alertDialogPositiveButton);
        String negetiveButtonText = context.getString(R.string.alertDialogNegetiveButtonText);

        AlertDialogFragment dialog = AlertDialogFragment.newInstance(title, message, positiveButtonText, negetiveButtonText);
        dialog.setCancelable(false);
        dialog.setButtonClickListener(new OnDialogButtonClickListener() {
            @Override
            public void onPositiveButtonClick(View v) {
                double priceDifference = getPriceDifference(mProductCount) * -1;
                mUpdateBasketContentCallback.onProductRemoved(getAdapterPosition(), priceDifference);
                dialog.dismiss();
            }

            @Override
            public void onNegativeButtonClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show(mFm, ALERT_DIALOG_FM_TAG);
    }

    @OnClick(R.id.imgShoppingBasketRowProdcutAvatar)
    public void setAvatarClickListener(View v) {

    }
}
