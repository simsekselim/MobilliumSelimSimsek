package com.mobillium.zonezero.domain.usecase.base

import com.mobillium.zonezero.common.utils.AppSchedulerProvider
import com.mobillium.zonezero.domain.model.base.DataHolder
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

interface BaseUseCase {

    abstract class RequestInteractor<params : Any, T : Any> : BaseUseCase {

        fun fetch(compositeDisposable: CompositeDisposable,
                  postParams: params,
                  onResponse : (DataHolder<T>) -> Unit) : Disposable {

            return this.executeAsync(postParams)
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(AppSchedulerProvider.io())
                .observeOn(AppSchedulerProvider.ui())
                .subscribe(
                    {
                        onResponse(it)
                    },
                    {
                        onResponse(DataHolder.Fail(Error(it.message)))
                    }
                ).also { compositeDisposable.add(it) }
        }

        abstract fun executeAsync(params: Any): Single<DataHolder<T>>
    }

}