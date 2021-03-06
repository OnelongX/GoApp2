package com.ways2u.android.goapp.dagger.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by huanglong on 2016/12/10.
 */
//自定义@Qualifier，类似@Named("name")
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultQualifier {
}
