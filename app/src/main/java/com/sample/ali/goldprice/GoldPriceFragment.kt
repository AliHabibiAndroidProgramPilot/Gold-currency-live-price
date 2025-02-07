package com.sample.ali.goldprice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.ali.goldprice.databinding.FragmentCurrencyPriceBinding

class GoldPriceFragment : Fragment() {
    private lateinit var binding: FragmentCurrencyPriceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCurrencyPriceBinding.inflate(inflater)
        return binding.root
    }
}