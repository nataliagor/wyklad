package com.nataliagornikowska.wyklad.repository

import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthRepository(private var onUserLogged : IOnUserLogged) {
    private var firebaseAuth: FirebaseAuth = Firebase.auth

    /**
     * checks if login and password are correct, if so logs the user in.
     */
    fun logIn(login: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(login, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onUserLogged.onLogIn(firebaseAuth.currentUser)
                }
                else{
                    onUserLogged.onLogIn(null)
                }
            }
            .addOnFailureListener { e ->
                onUserLogged.onError(e)
            }
    }

    fun logOut(){
        firebaseAuth.signOut()
    }

    interface IOnUserLogged{
        fun onLogIn(firebaseUser: FirebaseUser?)
        fun onError(e : Exception)
    }


}