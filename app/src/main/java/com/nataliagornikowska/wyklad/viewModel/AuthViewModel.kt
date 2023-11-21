package com.nataliagornikowska.wyklad.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.nataliagornikowska.wyklad.repository.AuthRepository

class AuthViewModel : ViewModel(), AuthRepository.IOnUserLogged{

    private var repository: AuthRepository = AuthRepository(this)
    private var currentUserLiveData : MutableLiveData<FirebaseUser?> = MutableLiveData<FirebaseUser?>()

    override fun onLogIn(firebaseUser: FirebaseUser?) {
        currentUserLiveData.postValue(firebaseUser)
    }

    override fun onError(e: Exception) {
        Log.d("ERROR", "onError" + e.message)
    }

    fun logIn(login : String, password : String) : MutableLiveData<FirebaseUser?>{
        repository.logIn(login, password)
        return currentUserLiveData
    }

    fun logOut(){
        repository.logOut()
    }

}