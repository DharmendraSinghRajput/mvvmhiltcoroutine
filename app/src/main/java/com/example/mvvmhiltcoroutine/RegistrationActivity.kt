package com.example.mvvmhiltcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/*
class RegistrationActivity : BaseActivity() {

    private val mBinding by lazy { ActivityRegistrationBinding.inflate(layoutInflater) }
    private val registrationViewModel by viewModels<RegistrationViewModel>()
    private var emailPhoneChooserDialog: Dialog? = null
    private var selectedTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.east_bay)
        setContentView(mBinding.root)

        showEmailPhoneChooserDialog()

        registrationViewModel.registrationAPIResponse.observe(this) {
            handleLoader(it) { successResponse ->

                val registrationResponse = successResponse.data as RegistrationResponse
                showToast(registrationResponse.message)

                if (registrationResponse.status.equals(APIConst.success, true)) {
                    startActivity(Intent(this@RegistrationActivity, OtpActivity::class.java).apply {
                        putExtra(APIConst.vEmail, mBinding.etEmail.text.toString())
                        putExtra(APIConst.vCountryCode, mBinding.actvCountryCode.text.toString())
                        putExtra(APIConst.vMobileNumber, mBinding.etPhone.text.toString())
                        putExtra(APIConst.vPassword, mBinding.etPassword.text.toString())
                        finish()
                    })
                }
            }
        }

        setRegisterText()

        mBinding.apply {

            selectedTextView?.let {
                //TODO Noting
            } ?: kotlin.run {
                selectedTextView = tvYes
            }

            actvCountryCode.apply {
                setDropDownBackgroundDrawable(ContextCompat.getDrawable(this@RegistrationActivity, R.drawable.border_athens_gray))
                setOnItemClickListener { _, _, position, _ ->
                    setText(Constants.countryCodeList[position])
                }
                val arrayAdapter = CountryCodeAdapter(this@RegistrationActivity, Constants.countryCodeList)
                setAdapter(arrayAdapter)
            }

            tvLogin.setOnClickListener { onBackPressed() }

            tvRegister.setOnClickListener {

                if (validate()) {
                    val params = hashMapOf<String, Any>()
                    params[APIConst.vFirstName] = etFirstname.text.toString()
                    params[APIConst.vLastName] = etLastname.text.toString()
                    params[APIConst.vPassword] = etPassword.text.toString()
//                    params[APIConst.vValue] = etCode.text.toString()        // 23JZLblK
                    params[APIConst.eDeviceType] = APIConst.eDeviceTypeAndroid
                    params[APIConst.eUnder13] = selectedTextView!!.text.toString()
                    params[APIConst.tandc] = "true"

                    if (tilEmail.visibility == View.VISIBLE)
                        params[APIConst.vEmail] = etEmail.text.toString()

                    if (tilParentEmail.visibility == View.VISIBLE)
                        params[APIConst.vParentEmail] = etParentEmail.text.toString()

                    if (tilCountryCode.visibility == View.VISIBLE) {
                        params[APIConst.vCountryCode] = actvCountryCode.text.toString()
                        params[APIConst.vMobileNumber] = etPhone.text.toString()
                        params[APIConst.channel] = "sms"
                    }

                    printErrorLog(Gson().toJson(params))
                    registrationViewModel.callRegistrationAPI(this@RegistrationActivity, params)
                }
            }

            tvYes.setOnClickListener {
                selectedTextView = tvYes
                manageTint()
            }

            tvNo.setOnClickListener {
                selectedTextView = tvNo
                manageTint()
            }

            manageTint()

            createLink(tvTermsAndCondition, getString(R.string.by_clicking_sign_up_i_accept_the_terms_of_use_and_privacy_policy), listOf(Pair(getString(R.string.terms_of_use), object : ClickableSpan() {
                override fun onClick(p0: View) {
                    openLinkInWeb(Constants.TERMS_OF_USE_URL)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                    ds.color = ContextCompat.getColor(this@RegistrationActivity, R.color.cornflower_blue)
                }
            }), Pair(getString(R.string.privacy_policy), object : ClickableSpan() {
                override fun onClick(p0: View) {
                    openLinkInWeb(Constants.PRIVACY_POLICY_URL)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                    ds.color = ContextCompat.getColor(this@RegistrationActivity, R.color.cornflower_blue)
                }
            })))
        }

    }

    private fun openLinkInWeb(url: String) = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
        startActivity(this)
    }

    private fun validate(): Boolean {
        mBinding.apply {

            if (!GeneralFunctions.validEmptyTextInputLayout(
                    listOf(
                        Triple(tilFirstname, etFirstname, getString(R.string.please_enter_firstname)),
                        Triple(tilLastname, etLastname, getString(R.string.please_enter_lastname))
                    )
                )
            )
                return false

            if (!GeneralFunctions.validatePassword(this@RegistrationActivity, tilPassword, etPassword))
                return false

            if (!GeneralFunctions.validatePassword(this@RegistrationActivity, tilConfirmPassword, etConfirmPassword))
                return false

            if (!etPassword.text.toString().equals(etConfirmPassword.text.toString())) {
                showToast(getString(R.string.password_and_confirm_password_should_be_same))
                return false
            }

            if (tilEmail.visibility == View.VISIBLE) {
                if (!GeneralFunctions.validateEmail(this@RegistrationActivity, tilEmail, etEmail))
                    return false
                else
                    GeneralFunctions.hideTextInputLayoutError(tilEmail)
            }

            if (tilParentEmail.visibility == View.VISIBLE) {
                if (!GeneralFunctions.validateEmail(this@RegistrationActivity, tilParentEmail, etParentEmail))
                    return false
                else
                    GeneralFunctions.hideTextInputLayoutError(tilParentEmail)
            }


            if (tilCountryCode.visibility == View.VISIBLE) {
                if (etPhone.text.toString().isEmpty())
                    return GeneralFunctions.showTextInputLayoutError(tilPhone, getString(R.string.number_required))
                else
                    GeneralFunctions.hideTextInputLayoutError(tilPhone)
            }

            if (!cbxTermsAndCondition.isChecked) {
                showToast(getString(R.string.please_agree_terms_and_privacy_policy))
                return false
            }

            return true
        }
    }

    private fun showEmailPhoneChooserDialog() {
        val dialogEmailPhoneBinding = DialogEmailPhoneChooserBinding.inflate(layoutInflater, mBinding.root, false).apply {

            llSignUpWithEmail.setOnClickListener {
                emailPhoneChooserDialog?.dismiss()
                mBinding.tilEmail.visibility = View.VISIBLE
                mBinding.tilCountryCode.visibility = View.GONE
                mBinding.tilPhone.visibility = View.GONE
            }

            llSignUpWithPhone.setOnClickListener {
                mBinding.tilCountryCode.visibility = View.VISIBLE
                mBinding.tilPhone.visibility = View.VISIBLE
                mBinding.tilEmail.visibility = View.GONE
                emailPhoneChooserDialog?.dismiss()
            }

            ivBack.setOnClickListener { finish() }

        }

        emailPhoneChooserDialog = Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen).apply {
            setContentView(dialogEmailPhoneBinding.root)
            setCancelable(false)
            show()
        }
    }

    private fun setRegisterText() {
        val register = SpannableString(getString(R.string.already_account_log_in))
        register.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.cornflower_blue)), 16, register.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        mBinding.tvLogin.text = register
    }

    private fun createLink(targetTextView: TextView, completeString: String, clickedList: List<Pair<String, ClickableSpan>>): TextView {
        val spannableString = SpannableString(completeString)

        clickedList.forEach {

            val startPosition = completeString.indexOf(it.first)
            val endPosition = completeString.lastIndexOf(it.first) + it.first.length
            spannableString.setSpan(it.second, startPosition, endPosition, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        }
        targetTextView.text = spannableString
        targetTextView.movementMethod = LinkMovementMethod.getInstance()

        return targetTextView
    }

    private fun manageTint() {
        mBinding.apply {

            selectedTextView?.let {

                if (it == tvYes)
                    tilParentEmail.visibility = View.GONE
                else
                    tilParentEmail.visibility = View.VISIBLE

                llYesNo.children.forEach {
                    if (it is TextView) {
                        if (selectedTextView!!.id == it.id) {
                            it.setTextColor(ContextCompat.getColor(this@RegistrationActivity, R.color.white))
                            it.background = ContextCompat.getDrawable(this@RegistrationActivity, R.drawable.round_rect_login_gradient_16sdp)
                        } else {
                            it.setTextColor(ContextCompat.getColor(this@RegistrationActivity, R.color.black))
                            it.background = ContextCompat.getDrawable(this@RegistrationActivity, R.drawable.round_rect_mercury)
                        }
                    }
                }
            }
        }
    }
}*/
