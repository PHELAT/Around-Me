package com.workshop.aroundme.app.login.contract

import androidx.annotation.StringRes

interface LoginContract {

    interface View : LoginContract {

        fun getUsernameValue(): String

        fun getPasswordValue(): String

        fun goToHomeFragment()

        fun showDialogMessage(@StringRes title: Int, @StringRes message: Int)

    }

    interface Presenter : LoginContract {

        fun onViewReady(viewContract: LoginContract.View)

        fun onLoginButtonClicked()

    }

}
