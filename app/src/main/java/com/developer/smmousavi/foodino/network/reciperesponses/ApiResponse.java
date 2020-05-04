package com.developer.smmousavi.foodino.network.reciperesponses;

import java.io.IOException;

import retrofit2.Response;


public class ApiResponse<T> {

    public ApiResponse<T> create(Throwable error) {
        return new ApiErrorResponse<>(!error.getMessage().equals("") ? error.getMessage() : "Unknown error\n check the network connection.");
    }

    public ApiResponse<T> create(Response<T> response) {
        if (response.isSuccessful()) {
            T body = response.body();

            if (body == null || response.code() == 204) { // 204 is empty response code
                return new ApiEmptyResponse<>();
            } else {
                return new ApiSuccessResponse<>(body);
            }
        } else {
            String errorMsg;
            try {
                errorMsg = response.errorBody().string();

            } catch (IOException e) {
                e.printStackTrace();
                errorMsg = response.message();
            }
            return new ApiErrorResponse<>(errorMsg);
        }

    }


    public class ApiSuccessResponse<T> extends ApiResponse<T> {
        private T mBody;

        ApiSuccessResponse(T body) {
            this.mBody = body;
        }

        public T getBody() {
            return mBody;
        }
    }

    public class ApiErrorResponse<T> extends ApiResponse<T> {

        private String mErrorMsg;

        ApiErrorResponse(String errorMsg) {
            mErrorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return mErrorMsg;
        }
    }

    public class ApiEmptyResponse<T> extends ApiResponse<T> {
    }
}
