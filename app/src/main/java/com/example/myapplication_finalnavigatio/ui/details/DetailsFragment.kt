package com.example.myapplication_finalnavigatio.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication_finalnavigatio.databinding.FragmentDetailsBinding
import com.example.myapplication_finalnavigatio.ui.base_fragment.BaseFragment


class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
    FragmentDetailsBinding =
        FragmentDetailsBinding::inflate
    private lateinit var vm: DetailsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = DetailsViewModel()
        vm.setImg(binding, arguments)
        vm.addBlur(binding)
        vm.catFact(binding)
    }
}
