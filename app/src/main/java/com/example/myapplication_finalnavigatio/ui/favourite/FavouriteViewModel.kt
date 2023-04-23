package com.example.myapplication_finalnavigatio.ui.favourite

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.myapplication_finalnavigatio.R

class FavouriteViewModel : ViewModel() {

    fun navigateToo(root: View) {
        Navigation.findNavController(root)
            .navigate(R.id.action_navigation_favourites_to_navigation_home)
    }
}