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
import kotlinx.coroutines.launch

class AddUserInformationFragment : Fragment() {
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
//        val retrofitService = NewsService.getInstance()
//        val myRepository = UserInformationRepository(retrofitService)
//        val shareViewModelRetro = ViewModelProvider(this,MyViewModelFactory(myRepository))[SharedVM::class.java]
        val shareViewModel = ViewModelProvider(requireActivity())[ShareViewModel::class.java]


            shareViewModel.userData.observe(requireActivity()) {
                binding.etUserName.setText(it.userName)
                binding.etUserEmail.setText(it.userEmail)
                binding.etUserAddress.setText(it.userAddress)
                binding.etUserMobileNumber.setText(it.userMobile)
                binding.etUserNewsTopic.setText(it.topic)


            }


binding.btnSaveUserInformation.setOnClickListener {


        shareViewModel.insertUserInformation(
            UserInformation(
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