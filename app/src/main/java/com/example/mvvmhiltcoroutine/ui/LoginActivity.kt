package com.example.mvvmhiltcoroutine.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmhiltcoroutine.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    val mBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    //private val loginViewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {

            /*           PermissionUtil.verifyPermissions(this@LoginActivity)
            loginViewModel.loginAPIResponse.observe(this@LoginActivity) { it ->
                handleLoader(it) { successResponse ->
                    (successResponse.data as LoginResponse).apply {
                        showToast(message)
                        if (status.equals(APIConst.success, true)) {
                            prefUtil.putString(PrefUtil.LOGIN_DATA, Gson().toJson(this))
                            prefUtil.putInt(PrefUtil.iUserId, data.iUserId)
                            if (data.eTwoStepVerification == "Yes") {
                                startActivity(Intent(this@LoginActivity, TwoStepOtpVerificationActivity::class.java).let {
                                    it.putExtra(APIConst.vEmail, data.vEmail)
                                    it.putExtra(APIConst.vCountryCode, data.vCountryCode)
                                    it.putExtra(APIConst.vMobileNumber, data.vMobileNumber)
                                    it.putExtra(APIConst.vPassword, etPassword.text.toString())
                                    it.putExtra(APIConst.eTwoStepVerificationType, data.eTwoStepVerificationType)
                                }.apply {
                                    finish()
                                })

                            } else if (data.eInterest.equals("No", true)) {

                                startActivity(Intent(this@LoginActivity, WelcomeActivity::class.java).apply {
                                    finish()
                                })

                            } else if (data.eInterest.equals("Yes", true)) {
                                startActivity(Intent(this@LoginActivity, DashboardActivity::class.java).apply {
                                    finish()
                                })
                            }

                        } else {
                            if (code == "400" && status.equals(APIConst.error, true)) {
                                startActivity(Intent(this@LoginActivity, VerifyAccountActivity::class.java).apply {
                                    if (tilEmail.visibility == View.VISIBLE) {
                                        putExtra(APIConst.vEmail, etEmail.text.toString())
                                        putExtra(APIConst.vPassword, etPassword.text.toString())
                                    } else {
                                        putExtra(APIConst.vCountryCode, actvCountryCode.text.toString())
                                        putExtra(APIConst.vMobileNumber, etPhone.text.toString())
                                        putExtra(APIConst.vPassword, etPassword.text.toString())
                                    }
                                })
                            }
                        }
                    }
                }
            }

            tvRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
            }

            tvForgotPassword.setOnClickListener { startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java)) }

            tvLogin.setOnClickListener {
                if (validate()) {
                    val params = hashMapOf<String, Any>()
                    params[APIConst.vPassword] = etPassword.text.toString()

                    if (tilEmail.visibility == View.VISIBLE)
                        params[APIConst.vEmail] = etEmail.text.toString()

                    if (tilCountryCode.visibility == View.VISIBLE) {
                        params[APIConst.vCountryCode] = actvCountryCode.text.toString()
                        params[APIConst.vMobileNumber] = etPhone.text.toString()
                    }

                    loginViewModel.callLoginAPI(this@LoginActivity, params)
                }
            }

            actvCountryCode.apply {
                setDropDownBackgroundDrawable(ContextCompat.getDrawable(this@LoginActivity, R.drawable.border_athens_gray))
                setOnItemClickListener { adapterView, view, position, l ->
                    actvCountryCode.setText(Constants.countryCodeList[position])
                }
                val arrayAdapter = GenderAdapter(this@LoginActivity, Constants.countryCodeList)
                setAdapter(arrayAdapter)
            }

            rgEmailPhone.setOnCheckedChangeListener { radioGroup, i ->
                if (rbEmail.isChecked) {
                    tilEmail.visibility = View.VISIBLE
                    tilCountryCode.visibility = View.GONE
                    tilPhone.visibility = View.GONE
                } else {
                    tilCountryCode.visibility = View.VISIBLE
                    tilPhone.visibility = View.VISIBLE
                    tilEmail.visibility = View.GONE
                }
            }

            setRegisterText()
        }
    }

    private fun validate(): Boolean {
        mBinding.apply {

            if (tilEmail.visibility == View.VISIBLE) {
                if (!GeneralFunctions.validateEmail(this@LoginActivity, tilEmail, etEmail))
                    return false
                else
                    GeneralFunctions.hideTextInputLayoutError(tilEmail)
            }

            if (tilCountryCode.visibility == View.VISIBLE) {
                if (etPhone.text.toString().isEmpty())
                    return GeneralFunctions.showTextInputLayoutError(tilPhone, getString(R.string.number_required))
                else
                    GeneralFunctions.hideTextInputLayoutError(tilPhone)
            }

            if (!GeneralFunctions.validatePassword(this@LoginActivity, tilPassword, etPassword))
                return false

        }
        return true
    }

    private fun setRegisterText() {
        val register = SpannableString(getString(R.string.new_account_create))
        register.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.cornflower_blue)), 13, register.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        mBinding.tvRegister.text = register
    }*/
        }
    }
}
