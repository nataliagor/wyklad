package com.nataliagornikowska.wyklad.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.nataliagornikowska.wyklad.R
import com.nataliagornikowska.wyklad.viewModel.AuthViewModel

class LogIn : Fragment() {

    private lateinit var viewModel : AuthViewModel
    private lateinit var navController: NavController

    private lateinit var editTextLogin: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
        .getInstance(activity!!.application))[AuthViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        buttonLogIn = view.findViewById(R.id.buttonLogIn)
        editTextLogin = view.findViewById(R.id.editTextLogin)
        editTextPassword = view.findViewById(R.id.editTextPassword)

        logInActions()
    }

    private fun logInActions(){
        buttonLogIn.setOnClickListener {
            val login = editTextLogin.text.toString()
            val password = editTextPassword.text.toString()

            viewModel.logIn(login, password).observe(viewLifecycleOwner){
                    currentUser ->
                displayToastAndNavigate(currentUser != null)
            }
        }
    }

    private fun displayToastAndNavigate(isSuccessful : Boolean){
        if(isSuccessful){
            Toast.makeText(context, "Zalogowano pomyślnie", Toast.LENGTH_SHORT).show()
            navController.navigate(R.id.action_logIn_to_drugList)
        }else{
            Toast.makeText(context, "Niepoprawne dane", Toast.LENGTH_SHORT).show()
        }
    }



}