package com.workshop.aroundme.app.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.workshop.aroundme.R
import com.workshop.aroundme.app.di.Injector
import com.workshop.aroundme.app.home.view.HomeFragment
import com.workshop.aroundme.app.login.contract.LoginContract
import com.workshop.aroundme.app.login.presenter.LoginPresenter

class LoginFragment : Fragment(), LoginContract.View {

    private val presenter: LoginContract.Presenter by lazy(LazyThreadSafetyMode.NONE) {
        LoginPresenter(Injector.provideUserRepository(requireContext()))
    }

    private var messageDialog: AlertDialog? = null

    private lateinit var usernameEditText: EditText

    private lateinit var passwordEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewReady(this)

        usernameEditText = view.findViewById(R.id.username)
        passwordEditText = view.findViewById(R.id.password)
        view.findViewById<View>(R.id.login).setOnClickListener {
            presenter.onLoginButtonClicked()
        }
    }

    override fun getUsernameValue(): String {
        return usernameEditText.text.toString()
    }

    override fun getPasswordValue(): String {
        return passwordEditText.text.toString()
    }

    override fun goToHomeFragment() {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.content_frame, HomeFragment())
            ?.commit()
    }

    override fun showDialogMessage(title: Int, message: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(title))
            .setMessage(getString(message))
            .setPositiveButton(getString(R.string.ok)) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .create()
            .apply { messageDialog = this }
            .show()
    }

    override fun onDestroyView() {
        if (messageDialog?.isShowing == true) {
            messageDialog?.dismiss()
        }
        super.onDestroyView()
    }

}
