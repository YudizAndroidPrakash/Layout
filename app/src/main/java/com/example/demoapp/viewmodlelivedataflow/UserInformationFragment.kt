package com.example.demoapp.viewmodlelivedataflow

import android.app.Application
import android.os.Bundle
import android.util.Log
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
import com.example.demoapp.viewmodlelivedataflow.api.NewsHelper
import com.example.demoapp.viewmodlelivedataflow.api.NewsServiceProvider
import com.example.demoapp.viewmodlelivedataflow.model.Article
import com.example.demoapp.viewmodlelivedataflow.newviewmodel.MainViewModel
import com.example.demoapp.viewmodlelivedataflow.newviewmodel.MainViewModelFactory
import com.example.demoapp.viewmodlelivedataflow.repository.NewsArticleRepository
import kotlinx.coroutines.launch


class UserInformationFragment : Fragment() {
    private lateinit var binding: FragmentUserInformationBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var articaleDetails: ArrayList<Article>
    private lateinit var adapter: AdapterNewsData

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
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_information, container, false)


        val newsService = NewsHelper.getInstance().create(NewsServiceProvider::class.java)
        val repository = NewsArticleRepository(newsService)
        mainViewModel = ViewModelProvider(
            requireActivity(),
            MainViewModelFactory(repository)
        )[MainViewModel::class.java]




        mainViewModel.userData.observe(requireActivity()) {
            binding.tvUserName.text = it.userName
            binding.tvUserEmail.text = it.userEmail
            binding.tvUserAddress.text = it.userAddress
            binding.tvUserMobileNumber.text = it.userMobile
            binding.tvUserNewsTopic.text = it.topic
            mainViewModel.newsDetails(binding.tvUserNewsTopic.text.toString())
        }
//        mainViewModel.article.observe(requireActivity()) {
//            Log.e("news", it.articles.toString())
//
//            adapter = AdapterNewsData(requireContext(), it.articles)
//            binding.rvNewsInformation.adapter = adapter
//        }
//        lifecycleScope.launch {
//            mainViewModel.newsFlow.collect{
//                it.observe(requireActivity(), Observer {
//                    Log.e("news",it.articles.toString())
//                   adapter = AdapterNewsData(requireContext(),it.articles)
//                    binding.rvNewsInformation.adapter = adapter
//               })
//
//
//            }
//        }

        lifecycleScope.launch {
            mainViewModel.article.collect{
                adapter = AdapterNewsData(requireContext(), it)
                binding.rvNewsInformation.adapter = adapter
            }
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