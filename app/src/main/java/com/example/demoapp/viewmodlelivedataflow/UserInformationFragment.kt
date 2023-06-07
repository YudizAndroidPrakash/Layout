package com.example.demoapp.viewmodlelivedataflow

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.demoapp.R
import com.example.demoapp.databinding.FragmentUserInformationBinding
import kotlinx.coroutines.launch


class UserInformationFragment : Fragment() {
    private lateinit var binding: FragmentUserInformationBinding

//    private val svm by lazy {
//        ViewModelProvider(requireActivity(),ViewModelProvider.AndroidViewModelFactory.getInstance(application = Application()))[SharedVM::class.java]

//        ViewModelProvider(requireActivity())[SharedVM::class.java]
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_user_information, container, false)
        binding =DataBindingUtil.inflate(inflater, R.layout.fragment_user_information, container, false)
        val svm = ViewModelProvider(requireActivity())[ShareViewModel::class.java]
        val retrofitService = NewsService.getInstance()
        val myRepository = UserInformationRepository(retrofitService)
        val adapter = AdapterNewsData(requireContext())
        binding.rvNewsInformation.adapter = adapter

        val svmRetrofit = ViewModelProvider(this,MyViewModelFactory(myRepository))[SharedVM::class.java]


//        svm.newsList.observe(requireActivity(), Observer {
//            adapter.newsList = it
//
//        })

            svm.userData.observe(requireActivity()) {
                binding.tvUserName.text = it.userName
                binding.tvUserEmail.text = it.userEmail
                binding.tvUserAddress.text = it.userAddress
                binding.tvUserMobileNumber.text = it.userMobile
                binding.tvUserNewsTopic.text = it.topic
                svmRetrofit.getAllNews(binding.tvUserNewsTopic.text.toString())
            }

        binding.btnEditUserInformation.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, AddUserInformationFragment())
                .addToBackStack(null)
                .commit()
        }

        return binding.root

    }


}