package com.workshop.aroundme.app.login.presenter

import com.workshop.aroundme.R
import com.workshop.aroundme.app.login.contract.LoginContract
import com.workshop.aroundme.data.model.UserEntity
import com.workshop.aroundme.data.repository.UserRepository
import java.lang.ref.WeakReference

class LoginPresenter(private val userRepository: UserRepository) : LoginContract.Presenter {

    private lateinit var viewContract: WeakReference<LoginContract.View>

    override fun onViewReady(viewContract: LoginContract.View) {
        this.viewContract = WeakReference(viewContract)
    }

    override fun onLoginButtonClicked() {
        val username = viewContract.get()?.getUsernameValue() ?: ""
        val password = viewContract.get()?.getPasswordValue() ?: ""
        if (isUsernameValid(username) && isPasswordValid(password)) {
            val user = UserEntity(username)
            userRepository.login(user)
            viewContract.get()?.goToHomeFragment()
        } else {
            viewContract.get()?.showDialogMessage(R.string.error, R.string.invalid_user_or_pass)
        }
    }

    private fun isUsernameValid(username: String): Boolean {
        return username.isNotEmpty() && username == "reza"
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty() && password == "1234"
    }

}
