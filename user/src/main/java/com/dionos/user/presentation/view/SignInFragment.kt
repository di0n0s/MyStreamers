package com.dionos.user.presentation.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dionos.user.BuildConfig
import com.dionos.user.databinding.FragmentSignInBinding
import com.dionos.user.presentation.viewModel.IsTokenSavedState
import com.dionos.user.presentation.viewModel.SignInViewModel
import com.dionos.user.presentation.viewModel.UserIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class SignInFragment : Fragment() {

    //Views
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding

    private var webView: WebView? = null

    //Variables
    private var state: String = UUID.randomUUID().toString()

    //ViewModel
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onInitView(inflater, container)
        setWebView()
        setToolbar()
        setObserver()
        return binding?.root
    }

    private fun onInitView(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        webView = binding?.webView
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        webView?.apply {
            if (BuildConfig.DEBUG) {
                WebView.setWebContentsDebuggingEnabled(true)
            }
            this.settings.apply {
                javaScriptEnabled = true
            }

            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    checkSignIn(request)
                    return super.shouldOverrideUrlLoading(view, request)
                }
            }

            this.loadUrl(
                "https://id.twitch.tv/oauth2/authorize" +
                        "?client_id=f368puphbblwvu2sx5l1ufqfo5izet" +
                        "&redirect_uri=https://localhost" +
                        "&response_type=token" +
                        "&scope=user:read:follows" +
                        "&state=$state"
            )


        }

    }

    private fun setToolbar() {
        (activity as AppCompatActivity?)?.apply {
            setSupportActionBar(binding?.toolbar)
            supportActionBar?.title = "Sign In"
        }
    }

    private fun checkSignIn(request: WebResourceRequest?) {
        val url = request?.url.toString()
        if (url.startsWith("https://localhost/#")) {
            val queryString = url.substringAfter("#")
            val queryList = queryString.split("&")
            val map = mutableMapOf<String, String>()
            queryList.forEach {
                val query = it.split("=")
                map[query[0]] = query[1]
            }

            if (map["state"].equals(state)) {
                val accessToken = map["access_token"]
                if (accessToken?.isNotBlank() == true) {
                    lifecycleScope.launch {
                        viewModel.userIntent.send(UserIntent.SaveToken(accessToken))
                    }
                } else {
                    Log.i("login", "token empty")
                }
            } else {
                Log.i("login", "state wrong")
            }
        } else if (url == "") {

        } else {
            webView?.loadUrl(request?.url.toString())
        }
    }

    private fun setObserver() {
        lifecycleScope.launch {
            viewModel.isTokenSaved.collect {
                if (it is IsTokenSavedState.Success) {
                    //TODO go to next screen
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}