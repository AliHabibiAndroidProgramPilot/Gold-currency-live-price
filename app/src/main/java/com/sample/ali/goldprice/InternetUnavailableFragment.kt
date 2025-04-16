package com.sample.ali.goldprice

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
        val model = ModelInternetUnavailableFragment()
        presenter = PresenterInternetUnavailableFragment(model)
        presenter.attachView(this)
        presenter.viewCaller(requireContext())
    }

    override fun btnContinueToAppClick(isConnectivityAvailable: Boolean) {
        binding.btnTryAgain.setOnClickListener {
            if (isConnectivityAvailable) {
                // Check if this Fragment is showing and added to fragment container
                if (isAdded) {
                    parentFragmentManager.beginTransaction()
                        .remove(this)
                        .commit()
                }
            } else
                return@setOnClickListener
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        presenter.detachView()
    }

}