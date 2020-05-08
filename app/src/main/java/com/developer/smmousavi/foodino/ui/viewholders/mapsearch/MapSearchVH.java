package com.developer.smmousavi.foodino.ui.viewholders.mapsearch;

import android.view.View;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.ui.fragments.mapsearch.AddressSelectCallback;
import com.developer.smmousavi.foodino.network.mapresponse.Value;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MapSearchVH extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.txtAddressTitle)
    AppCompatTextView mTxtAddressTitle;
    @BindView(R.id.txtAddress)
    AppCompatTextView mTxtAddress;

    private Value mItem;
    private AddressSelectCallback mAddressSelectCallback;


    public MapSearchVH(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void onBind(Value item, AddressSelectCallback addressSelectCallback) {
        mItem = item;
        mAddressSelectCallback = addressSelectCallback;
        mTxtAddressTitle.setText(item.getTitle());
        mTxtAddress.setText(item.getAddress());
    }

    @Override
    public void onClick(View v) {
        double[] latLng = {mItem.getGeom().getCoordinates().get(0),
            mItem.getGeom().getCoordinates().get(1)
        };
        mAddressSelectCallback.onAddressSelected(latLng);
    }

}
