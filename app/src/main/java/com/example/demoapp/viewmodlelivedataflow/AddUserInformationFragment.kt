package com.example.demoapp.viewmodlelivedataflow

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.demoapp.R
import com.example.demoapp.databinding.FragmentAddUserInformationBinding
import com.example.demoapp.databinding.FragmentUserInformationBinding
import com.example.demoapp.viewmodlelivedataflow.api.NewsHelper
import com.example.demoapp.viewmodlelivedataflow.api.NewsServiceProvider
import com.example.demoapp.viewmodlelivedataflow.model.UserInformationModel
import com.example.demoapp.viewmodlelivedataflow.newviewmodel.MainViewModel
import com.example.demoapp.viewmodlelivedataflow.newviewmodel.MainViewModelFactory
import com.example.demoapp.viewmodlelivedataflow.repository.NewsArticleRepository
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class AddUserInformationFragment : Fragment() {
//    private lateinit var    mainViewModel: MainViewModel
    private  val mainViewModel: MainViewModel by inject()

//    private val shareViewModel by lazy {
//        ViewModelProvider(
//            requireActivity(),
//            ViewModelProvider.AndroidViewModelFactory.getInstance(application = Application())
//        )[SharedVM::class.java]

//        ViewModelProvider(requireActivity())[SharedVM::class.java]
//
//    }

    private lateinit var binding: FragmentAddUserInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_user_information, container, false)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_user_information,
            container,
            false
        )
//        val newsService = NewsHelper.getInstance().create(NewsServiceProvider::class.java)
//        val repository = NewsArticleRepository(newsService)
//        mainViewModel = ViewModelProvider(requireActivity(),
//            MainViewModelFactory(repository)
//        )[MainViewModel::class.java]


            mainViewModel.userData.observe(requireActivity()) {
                binding.etUserName.setText(it.userName)
                binding.etUserEmail.setText(it.userEmail)
                binding.etUserAddress.setText(it.userAddress)
                binding.etUserMobileNumber.setText(it.userMobile)
                binding.etUserNewsTopic.setText(it.topic)
            }


binding.btnSaveUserInformation.setOnClickListener {


        mainViewModel.insertUserInformation(
            UserInformationModel(
                binding.etUserName.text.toString(),
                binding.etUserEmail.text.toString(),
                binding.etUserAddress.text.toString(),
                binding.etUserMobileNumber.text.toString(),
                binding.etUserNewsTopic.text.toString()
            )
        )

    activity?.supportFragmentManager!!.beginTransaction().replace(R.id.fragment_container_view,UserInformationFragment()).addToBackStack(null).commit()
}

        return binding.root

    }


}