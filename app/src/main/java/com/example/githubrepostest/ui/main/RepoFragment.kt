package com.example.githubrepostest.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepostest.R

import com.example.githubrepostest.adapter.RepoRecyclerViewAdapter
import com.example.githubrepostest.api.response.RepoResponse
import com.example.githubrepostest.databinding.RepoFragmentBinding

class RepoFragment : Fragment() {

    companion object {
        fun newInstance() = RepoFragment()
        const val URL_KEY = "url"
    }

    private lateinit var viewModel: RepoViewModel
    private lateinit var binding: RepoFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RepoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RepoFragmentBinding.inflate(layoutInflater)

        viewModel.loadRepoList()

        viewModel.repoLiveData.observe(this, Observer {
            val recyclerViewAdapter = RepoRecyclerViewAdapter(it, requireContext())

            recyclerViewAdapter.onItemClick = {item->
                val bundle = Bundle()
                bundle.putString(URL_KEY, item.html_url)
                findNavController().navigate(R.id.action_repoFragment_to_webviewFragment, bundle)
            }

            binding.repoListRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = recyclerViewAdapter
            }
        })


        return binding.root
    }
}
