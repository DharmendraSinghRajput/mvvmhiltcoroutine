package com.example.mvvmhiltcoroutine.ui

import android.app.sportapp.remote.APIConst
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmhiltcoroutine.databinding.ActivityMainBinding
import com.example.mvvmhiltcoroutine.util.BaseActivity
import com.example.mvvmhiltcoroutine.util.ConstantsVal
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val addPostViewModel by viewModels<PostViewModel>()
    private var getAllPostResponse:GetAllPostResponse?=null

    private val addUniversityAdapter by lazy {
        TeamAboutCoachAdapter { position, viewId ->
            startActivity(Intent(this, UpdateActivity::class.java).apply {
                putExtra(
                    ConstantsVal.TEAM_HEAD_COACH_DETAILS,
                    Gson().toJson(getAllPostResponse!!.data[position])
                )

            })
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {
            addPostViewModel.callGetAllEducationDataByiUserAPI(this@MainActivity, hashMapOf(Pair(APIConst.iUserId,158 )))
            addPostViewModel.getAllEducationDataByiUserIdAPIResponse.observe(this@MainActivity) {
                handleLoader(it) { successResponse ->
                    getAllPostResponse = (successResponse.data as GetAllPostResponse)
                    addUniversityAdapter.setData(getAllPostResponse?.data ?: arrayListOf())
                }
            }
            recyclerAddItems.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = addUniversityAdapter
            }
        }
    }
}