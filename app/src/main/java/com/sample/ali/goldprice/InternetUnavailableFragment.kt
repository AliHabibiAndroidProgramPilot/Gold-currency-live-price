package com.sample.ali.goldprice

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class InternetUnavailableFragment : Fragment(R.layout.fragment_internet_unavailable) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_try_again)
            .setOnClickListener {
                //TODO()
            }
    }

}