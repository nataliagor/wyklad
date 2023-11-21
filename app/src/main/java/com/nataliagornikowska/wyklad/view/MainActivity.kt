package com.nataliagornikowska.wyklad.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.nataliagornikowska.wyklad.R
import com.nataliagornikowska.wyklad.viewModel.AuthViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var buttonLogOut: Button
    private lateinit var navController: NavController
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(application))[AuthViewModel::class.java]

        navigationHandler()
        logOutActions()
    }

    /**
     * initialization of navController
     */
    private fun navigationHandler(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.navigate(R.id.action_global_logIn)
    }

    /**
     * Log out operations
     */
    private fun logOutActions(){
        buttonLogOut = findViewById(R.id.buttonLogOut)
        buttonLogOut.setOnClickListener {
            viewModel.logOut()
            navController.navigate(R.id.action_global_logIn)
        }
    }


}