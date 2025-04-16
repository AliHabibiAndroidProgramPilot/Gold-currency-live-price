package com.sample.ali.goldprice

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
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

    override fun btnContinueToAppClick() {
        binding.btnTryAgain.setOnClickListener {
            if (presenter.connectivityChecker(requireContext())) {
                parentFragmentManager.beginTransaction()
                    .remove(this)
                    .commit()
            }
        }
    }

    override fun txtInternetSettingClick() {
        binding.txtInternetSetting.setOnClickListener {
            val intent: Intent =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                    Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
                else
                    Intent(Settings.ACTION_WIRELESS_SETTINGS)
            val packageManager = requireContext().packageManager
            if (activity?.intent?.resolveActivity(packageManager) != null)
                startActivity(intent)
            else
                return@setOnClickListener
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        presenter.detachView()
    }

}