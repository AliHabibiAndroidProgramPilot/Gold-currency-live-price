package com.sample.ali.goldprice

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.ali.goldprice.databinding.FragmentInternetUnavailableBinding
import com.sample.ali.goldprice.mvp.ext.InternetUnavailableFragmentContract
import com.sample.ali.goldprice.mvp.model.ModelInternetUnavailableFragment
import com.sample.ali.goldprice.mvp.presenter.PresenterInternetUnavailableFragment

class InternetUnavailableFragment : Fragment(), InternetUnavailableFragmentContract.View {

    private var _binding: FragmentInternetUnavailableBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: PresenterInternetUnavailableFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInternetUnavailableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /* view.findViewById<Button>(R.id.btn_try_again)
            .setOnClickListener {
                val networkCapabilities: Boolean? = checkConnectivityManager()
                if (networkCapabilities == true) {
                    if (isAdded) {
                        parentFragmentManager.beginTransaction()
                            .remove(this)
                            .commit()
                    }
                }
            }*/
        val model = ModelInternetUnavailableFragment()
        presenter = PresenterInternetUnavailableFragment(model)
        presenter.attachView(this)
        presenter.viewCaller()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        presenter.detachView()
    }

    private fun checkConnectivityManager(): Boolean? {
        val connectivityManager =
            context?.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}