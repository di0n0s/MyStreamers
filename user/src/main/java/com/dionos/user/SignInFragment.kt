package com.dionos.user

import android.annotation.SuppressLint
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
import com.dionos.user.databinding.FragmentSignInBinding
import java.util.*

class SignInFragment : Fragment() {

    //Views
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding

    //Variables
    private var state: String = UUID.randomUUID().toString()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onInitView(inflater, container)
        setWebView()
        setToolbar()
        return binding?.root
    }

    private fun onInitView(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        binding?.webView?.apply {
            this.settings.javaScriptEnabled = true

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
                Log.i("login", "state OK")
                if (map["access_token"]?.isNotBlank() == true) {
                    Log.i("login", "save token")
                } else {
                    Log.i("login", "token empty")
                }
            } else {
                Log.i("login", "state wrong")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}