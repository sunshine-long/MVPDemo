package com.marlon.mvpdaggerretrofit.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * @desc ActivityScope
 * @author Marlon
 * @date 2018/12/18
 */
@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
