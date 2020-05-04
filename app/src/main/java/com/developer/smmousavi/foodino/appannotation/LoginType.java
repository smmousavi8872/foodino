package com.developer.smmousavi.foodino.appannotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.StringDef;

import static com.developer.smmousavi.foodino.appannotation.LoginType.LoggedIn;
import static com.developer.smmousavi.foodino.appannotation.LoginType.LoggedOut;
import static com.developer.smmousavi.foodino.appannotation.LoginType.NoUserName;


@Retention(RetentionPolicy.SOURCE)
@StringDef({
    LoggedOut,
    LoggedIn,
    NoUserName
})

public @interface LoginType {
    String LoggedOut = "LoggedOut";
    String LoggedIn = "LoggedIn";
    String NoUserName = "NoUserName";
}

