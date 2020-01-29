package com.example.githubrepostest.ui.webview

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import com.example.githubrepostest.R
import com.example.githubrepostest.databinding.WebviewFragmentBinding
import com.example.githubrepostest.ui.main.RepoFragment
import retrofit2.http.HTTP

class WebviewFragment : Fragment() {

    companion object {
        fun newInstance() = WebviewFragment()
    }

    private lateinit var viewModel: WebviewViewModel
    private lateinit var binding: WebviewFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = WebviewFragmentBinding.inflate(layoutInflater)
        binding.repositoryDetailWebView.loadUrl(arguments?.getString(RepoFragment.URL_KEY))
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WebviewViewModel::class.java)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val shareMenuItem = menu.findItem(R.id.share_menu_item)
        shareMenuItem.isVisible = true
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, arguments?.getString(RepoFragment.URL_KEY))
        }
        val chooser = Intent.createChooser(intent, "Send")
        startActivity(chooser)
        return true
    }
}
