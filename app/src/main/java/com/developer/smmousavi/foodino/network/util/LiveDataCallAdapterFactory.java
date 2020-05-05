package com.developer.smmousavi.foodino.network.util;

import com.developer.smmousavi.foodino.network.reciperesponses.ApiResponse;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.lifecycle.LiveData;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class LiveDataCallAdapterFactory extends CallAdapter.Factory {

    public static final String IllegalRawObservableTypeException = "Type must be a defined resource";

    /**
     * This method performs a number of checks and returns the response type for the Retrofit requests.
     * (@bodyType is the Response type it can be recipe response or the recipeSearchResponse
     * <p>
     * Check #1) returnType returns LiveData
     * Check #2) Type LiveData<T> is of ApiResponse.Class
     * Check #3) Make sure  the apiResponse is parameterized. AKA: ApiResponse<T> exists.
     */

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        // Check #1
        // Make sure the CallAdapter is returning a type of LiveData
        if (CallAdapter.Factory.getRawType(returnType) != LiveData.class) {
            return null;
        }

        // Check #2
        // Type that Live is wrapping
        Type observableType = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) returnType);
        // Check if it's of Type ApiResponse
        Type rawObservableType = CallAdapter.Factory.getRawType(observableType);
        if (rawObservableType != ApiResponse.class) {
            throw new IllegalArgumentException(IllegalRawObservableTypeException);
        }
        // Check #3
        // Check if ApiResponse is parameterized. AKA: Does ApiResponse<T> exists? (must wrap around T)
        // FYI: T is either RecipeResponse or T will be a RecipeSearchResponse
        if (!(observableType instanceof ParameterizedType)) {
            throw new IllegalArgumentException("");
        }

        Type bodyType = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) observableType);
        return new LiveDataCallAdapter<Type>(bodyType);
    }

}
