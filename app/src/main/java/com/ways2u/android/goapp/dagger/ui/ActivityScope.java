package com.ways2u.android.goapp.dagger.ui;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

//作用域
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {}