package com.example.mvvmhiltcoroutine.basic_info

import android.app.sportapp.remote.APIConst
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmhiltcoroutine.R
import com.example.mvvmhiltcoroutine.util.BaseActivity
import com.google.gson.Gson

class AddWebsiteActivity : BaseActivity() {

  /*  private val mBinding by lazy { ActivityAddWebsiteBinding.inflate(layoutInflater) }
    private var getAllLinkDataByiUserIdResponse: GetAllLinkDataByiUserIdResponse.Data? = null
    private val webSitViewModel by viewModels<WebSiteViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {
            toolbar.apply {
                toolbar.tvTitle.text = getString(R.string.add_website)
                ivBack.setOnClickListener { finish() }
                tvCancel.setOnClickListener { finish() } }
            intent.extras?.let {
                getAllLinkDataByiUserIdResponse = Gson().fromJson(it.getString(Constants.EMPLOYMENT_LIST_RESPONSE), GetAllLinkDataByiUserIdResponse.Data::class.java)
                etAddWebsite.setText(getAllLinkDataByiUserIdResponse?.vUrl)
                tvSave.text=getText(R.string.update)
            }

            webSitViewModel.createWebSiteLinksAPIResponse.observe(this@AddWebsiteActivity) {
                handleLoader(it) { successResponse ->
                    (successResponse.data as CreateWebSiteLinksResponse).apply {
                        if (status.equals(APIConst.success, true)) {
                            setResult(RESULT_OK)
                            finish()
                        }
                    }
                }
            }
            webSitViewModel.updateWebSiteLinksAPIResponse.observe(this@AddWebsiteActivity) {
                handleLoader(it) { successResponse ->
                    (successResponse.data as Status).apply {
                        if (status.equals(APIConst.success, true)) {
                            setResult(RESULT_OK)
                            finish()
                        }
                    }
                }
            }


            tvSave.setOnClickListener {
                if (GeneralFunctions.validEmptyTextInputLayout(
                        listOf(Triple(tilAddWebsite, etAddWebsite, getString(R.string.website_is_required))))) {
                    if (getAllLinkDataByiUserIdResponse!=null) {
                        webSitViewModel.callUpdateWebSiteLinksAPI(
                            this@AddWebsiteActivity, hashMapOf(
                                Pair(APIConst.vUrl, etAddWebsite.text.toString()),
                                Pair(APIConst.iWebsiteid, getAllLinkDataByiUserIdResponse!!.iWebsiteid),
                                Pair(APIConst.iUserId, prefUtil.getInt(PrefUtil.iUserId)),
                            )
                        )

                    } else {
                        webSitViewModel.callCreateWebSiteLinksAPI(
                            this@AddWebsiteActivity, hashMapOf(
                                Pair(APIConst.iUserId, prefUtil.getInt(PrefUtil.iUserId)),
                                Pair(APIConst.vUrl, etAddWebsite.text.toString())
                            )
                        )
                    }
                }
            }
        }
    }*/
}