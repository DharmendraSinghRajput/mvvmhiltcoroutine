package com.example.mvvmhiltcoroutine.basic_info

import android.app.Activity
import android.app.sportapp.remote.APIConst
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmhiltcoroutine.R
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasicInfoActivity : AppCompatActivity() {

 /*   private lateinit var mBinding: FragmentBasicInfoBinding
    private lateinit var secondaryNumberResponse: UserByIdAndAuthcodeResponse
    private lateinit var getAllLinkDataByiUserIdResponse: GetAllLinkDataByiUserIdResponse
    private lateinit var getAllSocialLinkDataByiUserIdResponse: GetAllSocialLinkDataByiUserIdResponse
    private lateinit var allLanguageByUserIdResponse: AllLanguageByUserIdResponse
    private val webSiteAdapter by lazy {
        WebSiteAdapter { position, viewId ->
            if (viewId == R.id.imgEdit) {
                startActivityForResult(Intent(requireContext(), AddWebsiteActivity::class.java).apply {
                    putExtra(Constants.EMPLOYMENT_LIST_RESPONSE, Gson().toJson(getAllLinkDataByiUserIdResponse.data[position]))
                    putExtra(APIConst.updates, "update")
                }, 130)

            } else if (viewId == R.id.imgDelete) {
                context?.showAlertDialog(title = "Alert", message = "Are you sure want to delete that data?", positiveButtonText = "Yes", positiveOnClick = {
                    (activity as AboutActivity).webSitViewModel.callDeleteLinkDataByIdAPI(
                        requireContext(), hashMapOf(Pair(APIConst.iWebsiteid, getAllLinkDataByiUserIdResponse.data[position].iWebsiteid))
                    )
                }, negativeButtonText = "No", negativeOnClick = {}, neutralOnClick = {})
            }
        }
    }

    private val addSocialAdapter by lazy {
        AddSocialAdapter { position, viewId ->
            if (viewId == R.id.imgEdit) {
                startActivityForResult(Intent(requireContext(), AddSocialActivity::class.java).apply {
                    putExtra(Constants.EMPLOYMENT_LIST_RESPONSE, Gson().toJson(getAllSocialLinkDataByiUserIdResponse.data[position]))
                    putExtra(APIConst.updates, "update")
                }, 140)

            } else if (viewId == R.id.imgDelete) {
                context?.showAlertDialog(title = "Alert", message = "Are you sure want to delete that data?", positiveButtonText = "Yes", positiveOnClick = {
                    (activity as AboutActivity).socialMediaViewModel.callDeleteSocialLinksDataByIdAPI(
                        requireContext(), hashMapOf(Pair(APIConst.iSocialLinkId, getAllSocialLinkDataByiUserIdResponse.data[position].iSocialLinkId))
                    )
                }, negativeButtonText = "No", negativeOnClick = {

                }, neutralOnClick = {})
            }
        }
    }

    private val addLanguageAdapter by lazy {
        LanguageAdapter { position, viewId ->
            if (viewId == R.id.imgEdit) {
                startActivityForResult(Intent(requireContext(), AddLanguageActivity::class.java).apply {
                    putExtra(Constants.EMPLOYMENT_LIST_RESPONSE, Gson().toJson(allLanguageByUserIdResponse.data[position]))
                    putExtra(APIConst.updates, "update")
                }, 150)

            } else if (viewId == R.id.imgDelete) {

                context?.showAlertDialog(title = "Alert", message = "Are you sure want to delete that data?", positiveButtonText = "Yes", positiveOnClick = {
                    (activity as AboutActivity).languageViewModel.deleteSocialLinksDataById(
                        requireContext(), hashMapOf(Pair(APIConst.iLanguagetransId, allLanguageByUserIdResponse.data[position].iLanguagetransId)))
                }, negativeButtonText = "No", negativeOnClick = {

                }, neutralOnClick = {})
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = FragmentBasicInfoBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.apply {
            (activity as AboutActivity).secondaryNumberViewModel.getSecondaryNumberResponse.observe(viewLifecycleOwner) {
                handleLoader(it) { successResponse ->
                    secondaryNumberResponse = (successResponse.data as UserByIdAndAuthcodeResponse)
                    if (secondaryNumberResponse.data.vSecondaryNumber.isNullOrEmpty()) {
                        includeAddSecondaryNumber.contactsType.visibility = View.GONE
                        tvAddSecondaryNumber.visibility = View.VISIBLE
                    } else {
                        includeAddSecondaryNumber.contactsType.visibility = View.VISIBLE
                        tvAddSecondaryNumber.visibility = View.GONE
                        includeAddSecondaryNumber.tvCity.visibility = View.GONE
                        includeAddSecondaryNumber.tvCompanyName.visibility = View.GONE
                        includeAddSecondaryNumber.tvUniversity.visibility = View.GONE
                        includeAddSecondaryNumber.tvToDate.visibility = View.GONE
                        includeAddSecondaryNumber.ivEducation.setImageResource(R.drawable.ic_about_phone)
                        includeAddSecondaryNumber.tvEducationTitle.text = secondaryNumberResponse.data.vSecondaryNumber
                        includeAddSecondaryNumber.tvDescription.text = getString(R.string.secondary_number)
                    }
                }
            }

            includeAddSecondaryNumber.imgEdit.setOnClickListener {
                startActivityForResult(Intent(requireContext(), AddSecondaryNumberActivity::class.java).apply {
                    putExtra(Constants.EMPLOYMENT_LIST_RESPONSE, Gson().toJson(secondaryNumberResponse.data))
                    putExtra(APIConst.updates, "update")
                }, 110)
            }

            includeAddSecondaryNumber.imgDelete.setOnClickListener {
                context?.showAlertDialog(title = "Alert", message = "Are you sure want to delete that data?", positiveButtonText = "Yes", positiveOnClick = {
                    (activity as AboutActivity).secondaryNumberViewModel.profileProfileUpdate(
                        requireContext(), hashMapOf(
                            Pair(APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId)), Pair(
                                APIConst.vSecondaryNumber, "")
                        )
                    )
                }, negativeButtonText = "No", negativeOnClick = {

                }, neutralOnClick = {})
            }

            (activity as AboutActivity).secondaryNumberViewModel.profileProfileUpdateResponse.observe(requireActivity()) {
                handleLoader(it) { successResponse ->
                    (successResponse.data as Status).apply {
                        if (status.equals(APIConst.success, true)) {
                            (activity as AboutActivity).secondaryNumberViewModel.getSecondaryNumber(requireContext(), hashMapOf(Pair(
                                APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))
                        }
                    }
                }
            }

            (activity as AboutActivity).secondaryNumberViewModel.getSecondaryNumberResponse.observe(viewLifecycleOwner) {
                handleLoader(it) { successResponse ->
                    secondaryNumberResponse = (successResponse.data as UserByIdAndAuthcodeResponse)
                    if (secondaryNumberResponse.data.vSecondaryEmail.isNullOrEmpty()) {
                        includeAddSecondaryEmail.contactsType.visibility = View.GONE
                        tvAddSecondaryEmail.visibility = View.VISIBLE
                    } else {
                        includeAddSecondaryEmail.contactsType.visibility = View.VISIBLE
                        tvAddSecondaryEmail.visibility = View.GONE
                        includeAddSecondaryEmail.tvCity.visibility = View.GONE
                        includeAddSecondaryEmail.tvCompanyName.visibility = View.GONE
                        includeAddSecondaryEmail.tvToDate.visibility = View.GONE
                        includeAddSecondaryEmail.tvUniversity.visibility = View.GONE
                        includeAddSecondaryEmail.ivEducation.setImageResource(R.drawable.ic_about_envelope)
                        includeAddSecondaryEmail.tvEducationTitle.text = secondaryNumberResponse.data.vSecondaryEmail
                        includeAddSecondaryEmail.tvDescription.text = getString(R.string.add_secondary_email)
                    }
                }
            }

            includeAddSecondaryEmail.imgEdit.setOnClickListener {
                startActivityForResult(Intent(requireContext(), AddSecondaryEmailActivity::class.java).apply {
                    putExtra(Constants.EMPLOYMENT_LIST_RESPONSE, Gson().toJson(secondaryNumberResponse.data))
                }, 120)
            }

            mBinding.includeAddSecondaryEmail.imgDelete.setOnClickListener {
                context?.showAlertDialog(title = "Alert", message = "Are you sure want to delete that data?", positiveButtonText = "Yes", positiveOnClick = {
                    (activity as AboutActivity).secondaryNumberViewModel.profileProfileUpdate(
                        requireContext(), hashMapOf(
                            Pair(APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId)), Pair(
                                APIConst.vSecondaryEmail, "")
                        )
                    )
                }, negativeButtonText = "No", negativeOnClick = {

                }, neutralOnClick = {})
            }

            (activity as AboutActivity).secondaryNumberViewModel.profileProfileUpdateResponse.observe(requireActivity()) {
                handleLoader(it) { successResponse ->
                    (successResponse.data as Status).apply {
                        if (status.equals(APIConst.success, true)) {
                            (activity as AboutActivity).secondaryNumberViewModel.getSecondaryNumber(requireContext(), hashMapOf(Pair(
                                APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))
                        }
                    }
                }
            }

            (activity as AboutActivity).webSitViewModel.deleteLinkDataByIdAPIResponse.observe(viewLifecycleOwner) {
                handleLoader(it) { successResponse ->
                    (successResponse.data as Status).apply {
                        (activity as AboutActivity).webSitViewModel.callGetAllLinkDataByiUserIdAPI(requireContext(), hashMapOf(Pair(
                            APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))
                    }
                }
            }

            (activity as AboutActivity).webSitViewModel.getAllLinkDataByiUserIdAPIResponse.observe(viewLifecycleOwner) {
                handleLoader(it) { successResponse ->
                    getAllLinkDataByiUserIdResponse = (successResponse.data as GetAllLinkDataByiUserIdResponse)
                    webSiteAdapter.setData(getAllLinkDataByiUserIdResponse.data ?: arrayListOf())
                }
            }

            recyclerAddWebsite.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = webSiteAdapter
            }

            (activity as AboutActivity).socialMediaViewModel.deleteSocialLinksDataByIdAPIResponse.observe(viewLifecycleOwner) {
                handleLoader(it) { successResponse ->
                    (successResponse.data as Status).apply {
                        (activity as AboutActivity).socialMediaViewModel.callGetAllSocialLinkDataByiUserIdAPI(requireContext(), hashMapOf(Pair(
                            APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))
                    }
                }
            }

            (activity as AboutActivity).socialMediaViewModel.getAllSocialLinkDataByiUserIdAPIResponse.observe(viewLifecycleOwner) {
                handleLoader(it) { successResponse ->
                    getAllSocialLinkDataByiUserIdResponse = (successResponse.data as GetAllSocialLinkDataByiUserIdResponse)
                    addSocialAdapter.setData(getAllSocialLinkDataByiUserIdResponse.data ?: arrayListOf())
                }
            }

            recyclerAddSocial.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = addSocialAdapter
            }

            (activity as AboutActivity).languageViewModel.deleteLanguageTrans.observe(viewLifecycleOwner) {
                handleLoader(it) { successResponse ->
                    (successResponse.data as Status).apply {
                        (activity as AboutActivity).languageViewModel.getAllLanguageTransiUserId(requireContext(), hashMapOf(Pair(
                            APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))
                    }
                }
            }

            (activity as AboutActivity).languageViewModel.getAllLanguageTranSiResponse.observe(requireActivity()) {
                handleLoader(it) { successResponse ->
                    allLanguageByUserIdResponse = (successResponse.data as AllLanguageByUserIdResponse)
                    addLanguageAdapter.setData(allLanguageByUserIdResponse.data ?: arrayListOf())

                }
            }

            recyclerAddLanguage.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = addLanguageAdapter
            }

            (activity as AboutActivity).secondaryNumberViewModel.getSecondaryNumberResponse.observe(viewLifecycleOwner) {
                handleLoader(it) { successResponse ->
                    secondaryNumberResponse = (successResponse.data as UserByIdAndAuthcodeResponse)
                    if (secondaryNumberResponse.data.eGender == null) {
                        includeAddGender.contactsType.visibility = View.GONE
                        tvAddGender.visibility = View.VISIBLE
                    } else {
                        includeAddGender.contactsType.visibility = View.VISIBLE
                        tvAddGender.visibility = View.GONE
                        includeAddGender.tvCity.visibility = View.GONE
                        includeAddGender.tvCompanyName.visibility = View.GONE
                        includeAddGender.tvUniversity.visibility = View.GONE
                        includeAddGender.tvToDate.visibility = View.GONE
                        includeAddGender.ivEducation.setImageResource(R.drawable.ic_about_gender)
                        includeAddGender.tvEducationTitle.text = secondaryNumberResponse.data.eGender.toString()
                        includeAddGender.tvDescription.text = getString(R.string.gender)
                    }
                }
            }

            includeAddGender.imgEdit.setOnClickListener {
                startActivityForResult(Intent(requireContext(), AddGenderActivity::class.java).apply {
                    putExtra(Constants.EMPLOYMENT_LIST_RESPONSE, Gson().toJson(secondaryNumberResponse.data))
                    putExtra(APIConst.updates, "update")
                }, 160)
            }

            includeAddGender.imgDelete.setOnClickListener {
                context?.showAlertDialog(title = "Alert", message = "Are you sure want to delete that data?", positiveButtonText = "Yes", positiveOnClick = {
                    (activity as AboutActivity).secondaryNumberViewModel.deleteProfileGender(
                        requireContext(), hashMapOf(
                            Pair(APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId)), Pair(
                                APIConst.eGender, "null")
                        )
                    )
                }, negativeButtonText = "No", negativeOnClick = {

                }, neutralOnClick = {})
            }

            (activity as AboutActivity).secondaryNumberViewModel.deleteProfileGenderResponse.observe(requireActivity()) {
                handleLoader(it) { successResponse ->
                    (successResponse.data as Status).apply {
                        (activity as AboutActivity).secondaryNumberViewModel.getSecondaryNumber(requireContext(), hashMapOf(Pair(
                            APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))
                    }
                }
            }

            (activity as AboutActivity).addHomeTownViewModel.userByIdAndAuthCodeResponse.observe(viewLifecycleOwner) {
                handleLoader(it) { successResponse ->
                    secondaryNumberResponse = (successResponse.data as UserByIdAndAuthcodeResponse)
                    if (secondaryNumberResponse.data.dBirthDate.isNullOrEmpty()) {
                        includeAddBirthDate.contactsType.visibility = View.GONE
                        tvAddBirthDate.visibility = View.VISIBLE
                    } else {
                        includeAddBirthDate.contactsType.visibility = View.VISIBLE
                        tvAddBirthDate.visibility = View.GONE
                        includeAddBirthDate.tvCity.visibility = View.GONE
                        includeAddBirthDate.tvCompanyName.visibility = View.GONE
                        includeAddBirthDate.tvUniversity.visibility = View.GONE
                        includeAddBirthDate.tvToDate.visibility = View.GONE
                        includeAddBirthDate.ivEducation.setImageResource(R.drawable.ic_cake_candles)
                        includeAddBirthDate.tvEducationTitle.text =GeneralFunctions.changeDateFormatStr(secondaryNumberResponse.data.dBirthDate, GeneralFunctions.dateFormatDash, GeneralFunctions.dateFormatMMDDYYYYSlash)
                        includeAddBirthDate.tvDescription.text = getString(R.string.added_birth_date)
                    }
                }
            }

            includeAddBirthDate.imgEdit.setOnClickListener {
                startActivityForResult(Intent(requireContext(), AddBirthDateActivity::class.java).apply {
                    putExtra(Constants.EMPLOYMENT_LIST_RESPONSE, Gson().toJson(secondaryNumberResponse.data))
                    putExtra(APIConst.updates, "update")
                }, 170)
            }

            includeAddBirthDate.imgDelete.setOnClickListener {
                context?.showAlertDialog(title = "Alert", message = "Are you sure want to delete that data?", positiveButtonText = "Yes", positiveOnClick = {
                    (activity as AboutActivity).addHomeTownViewModel.updateProfiledBirthDate(
                        requireContext(), hashMapOf(
                            Pair(APIConst.iUserId, secondaryNumberResponse.data.iUserId), Pair(
                                APIConst.dBirthDate, "null")
                        )
                    )
                }, negativeButtonText = "No", negativeOnClick = {}, neutralOnClick = {})
            }

            (activity as AboutActivity).addHomeTownViewModel.updateProfiledBirthDate.observe(viewLifecycleOwner) {
                handleLoader(it) { successResponse ->
                    (activity as AboutActivity).addHomeTownViewModel.getUserByIdAndAuthCodeApi(requireActivity(), hashMapOf(Pair(
                        APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))
                }
            }

            (activity as AboutActivity).addHomeTownViewModel.userByIdAndAuthCodeResponse.observe(viewLifecycleOwner) {
                handleLoader(it) { successResponse ->

                    secondaryNumberResponse = (successResponse.data as UserByIdAndAuthcodeResponse)
                    if (secondaryNumberResponse.data.eRelationship == null) {
                        includeAddRelationship.contactsType.visibility = View.GONE
                        tvAddRelationship.visibility = View.VISIBLE
                        mBinding.includeAddRelationship.contactsType.visibility = View.GONE
                        mBinding.tvAddRelationship.visibility = View.VISIBLE
                    } else {
                        includeAddRelationship.contactsType.visibility = View.VISIBLE
                        tvAddRelationship.visibility = View.GONE
                        includeAddRelationship.tvCity.visibility = View.GONE
                        includeAddRelationship.tvCompanyName.visibility = View.GONE
                        includeAddRelationship.tvUniversity.visibility = View.GONE
                        includeAddRelationship.tvToDate.visibility = View.GONE
                        includeAddRelationship.ivEducation.setImageResource(R.drawable.ic_about_heart)
                        includeAddRelationship.tvEducationTitle.text = secondaryNumberResponse.data.eRelationship.toString()
                        includeAddRelationship.tvDescription.text = getString(R.string.relationship_status)
                    }
                }
            }

            includeAddRelationship.imgEdit.setOnClickListener {
                startActivityForResult(Intent(requireContext(), AddRelationshipActivity::class.java).apply {
                    putExtra(Constants.EMPLOYMENT_LIST_RESPONSE, Gson().toJson(secondaryNumberResponse.data))
                    putExtra(APIConst.updates, "update")
                }, 180)
            }

            includeAddRelationship.imgDelete.setOnClickListener {
                context?.showAlertDialog(title = "Alert", message = "Are you sure want to delete that data?", positiveButtonText = "Yes", positiveOnClick = {
                    (activity as AboutActivity).addHomeTownViewModel.homeTownProfileUpdate(
                        requireContext(), hashMapOf(
                            Pair(APIConst.iUserId, secondaryNumberResponse.data.iUserId), Pair(
                                APIConst.eRelationship, "null")
                        )
                    )
                }, negativeButtonText = "No", negativeOnClick = {}, neutralOnClick = {})
            }

            (activity as AboutActivity).addHomeTownViewModel.updateHomeTownApiResponse.observe(viewLifecycleOwner) {
                handleLoader(it) { successResponse ->
                    (activity as AboutActivity).addHomeTownViewModel.getUserByIdAndAuthCodeApi(requireContext(), hashMapOf(Pair(
                        APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))
                }
            }

            tvAddSecondaryNumber.setOnClickListener {
                startActivityForResult(Intent(requireContext(), AddSecondaryNumberActivity::class.java).apply {}, 110)
            }
            tvAddSecondaryEmail.setOnClickListener {
                startActivityForResult(Intent(requireContext(), AddSecondaryEmailActivity::class.java).apply {}, 120)
            }
            tvAddWebsite.setOnClickListener {
                startActivityForResult(Intent(requireContext(), AddWebsiteActivity::class.java).apply {}, 130)
            }

            tvAddSocial.setOnClickListener {
                startActivityForResult(Intent(requireContext(), AddSocialActivity::class.java).apply {}, 140)
            }

            tvAddLanguage.setOnClickListener {
                startActivityForResult(Intent(requireContext(), AddLanguageActivity::class.java).apply {}, 150)
            }

            tvAddGender.setOnClickListener {
                startActivityForResult(Intent(requireContext(), AddGenderActivity::class.java).apply {}, 160)
            }

            tvAddBirthDate.setOnClickListener {
                startActivityForResult(Intent(requireContext(), AddBirthDateActivity::class.java).apply {}, 170)
            }

            tvAddRelationship.setOnClickListener {
                startActivityForResult(Intent(requireContext(), AddRelationshipActivity::class.java).apply {}, 180)
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 110 && resultCode == Activity.RESULT_OK) {
            (activity as AboutActivity).secondaryNumberViewModel.getSecondaryNumber(requireContext(), hashMapOf(Pair(
                APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))
        } else if (requestCode == 120 && resultCode == Activity.RESULT_OK) {
            (activity as AboutActivity).secondaryNumberViewModel.getSecondaryNumber(requireContext(), hashMapOf(Pair(
                APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))
        } else if (requestCode == 130 && resultCode == Activity.RESULT_OK) {
            (activity as AboutActivity).webSitViewModel.callGetAllLinkDataByiUserIdAPI(requireContext(), hashMapOf(Pair(
                APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))
        } else if (requestCode == 140 && resultCode == Activity.RESULT_OK) {
            (activity as AboutActivity).socialMediaViewModel.callGetAllSocialLinkDataByiUserIdAPI(requireContext(), hashMapOf(Pair(
                APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))
        } else if (requestCode == 150 && resultCode == Activity.RESULT_OK) {
            (activity as AboutActivity).languageViewModel.getAllLanguageTransiUserId(requireContext(), hashMapOf(Pair(
                APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))
        } else if (requestCode == 160 && resultCode == Activity.RESULT_OK) {
            (activity as AboutActivity).secondaryNumberViewModel.getSecondaryNumber(requireContext(), hashMapOf(Pair(
                APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))
        } else if (requestCode == 170 && resultCode == Activity.RESULT_OK) {
            (activity as AboutActivity).addHomeTownViewModel.getUserByIdAndAuthCodeApi(requireContext(), hashMapOf(Pair(
                APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))
        } else if (requestCode == 180 && resultCode == Activity.RESULT_OK) {
            (activity as AboutActivity).addHomeTownViewModel.getUserByIdAndAuthCodeApi(requireContext(), hashMapOf(Pair(
                APIConst.iUserId, getPrefUtil().getInt(PrefUtil.iUserId))))
        }
    }*/
}




