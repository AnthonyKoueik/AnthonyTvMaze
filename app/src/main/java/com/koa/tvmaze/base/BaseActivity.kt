package com.koa.tvmaze.base

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by ANTHONY KOUEIK on 7/15/2019.
 * Email: anthony.koueik@gmail.com
 *
 * An abstract class for all my activities where the user needs to set the layout resource and the ViewModel of his
 * activity
 * It also support Fragment Injector and onBackPressed() when home menu item is clicked
 */
abstract class BaseActivity<V : ViewModel> : DaggerAppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @LayoutRes
    abstract fun layoutRes(): Int

    protected lateinit var viewModel: V

    protected abstract fun getViewModel(): Class<V>

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home)
            onBackPressed()

        return super.onOptionsItemSelected(item)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel())
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentAndroidInjector
}