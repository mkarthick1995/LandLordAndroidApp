package com.landlord.app.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class LoginViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun loginWithPhoneAndPassword(phone: String, password: String) {
        if (!validatePhone(phone) || !validatePassword(password)) {
            _loginResult.value = LoginResult.Error("Invalid phone number or password")
            return
        }

        // Add +91 country code for Indian phone numbers
        val phoneWithCode = "+91$phone"

        auth.signInWithEmailAndPassword("$phone@landlord.com", password)
            .addOnSuccessListener {
                _loginResult.value = LoginResult.Success(it.user)
            }
            .addOnFailureListener {
                _loginResult.value = LoginResult.Error(it.message ?: "Login failed")
            }
    }

    private fun validatePhone(phone: String): Boolean {
        return phone.length == 10 && phone.all { it.isDigit() }
    }

    private fun validatePassword(password: String): Boolean {
        return password.length >= 6
    }
}

sealed class LoginResult {
    data class Success(val user: Any?) : LoginResult()
    data class Error(val message: String) : LoginResult()
}
