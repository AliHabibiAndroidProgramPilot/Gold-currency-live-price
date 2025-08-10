package info.alihabibi.goldcurrencyprice

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.alihabibi.goldcurrencyprice.adapters.CurrencyRecyclerViewAdapter
import info.alihabibi.goldcurrencyprice.databinding.FragmentCurrencyPriceBinding
import info.alihabibi.goldcurrencyprice.mvp.ext.CurrencyPriceFragmentContract
import info.alihabibi.goldcurrencyprice.mvp.presenter.PresenterCurrencyPriceFragment
import info.alihabibi.goldcurrencyprice.remote.model.PriceApiModel

class CurrencyPriceFragment : Fragment(), CurrencyPriceFragmentContract.View {

    private var _binding: FragmentCurrencyPriceBinding? = null

    private val binding get() = _binding!!

    private lateinit var presenter: PresenterCurrencyPriceFragment

    private lateinit var adapter: CurrencyRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyPriceBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = PresenterCurrencyPriceFragment()
        presenter.attachView(this)
        presenter.viewCaller()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setupRecyclerView() {
        @Suppress("DEPRECATION") val data =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("DATA", PriceApiModel::class.java)
            } else {
                arguments?.getParcelable("DATA")
            }
        adapter = CurrencyRecyclerViewAdapter(data?.currency ?: arrayListOf())
        binding.recyclerCurrencyPrice.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerCurrencyPrice.adapter = adapter
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
