package com.binar.preferencesdatastore

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.map


class UserManager (context : Context){

    private val dataStore : DataStore<Preferences> = context.createDataStore(name = "user_prefs")
    private val loginDataStore : DataStore<Preferences> = context.createDataStore(name = "login_prefs")

    companion object{

        val USERNAME = preferencesKey<String>("USER_USERNAME")
        val EMAIL = preferencesKey<String>("USER_EMAIL")
        val PASSWORD = preferencesKey<String>("USER_PASSWORD")
        val LOGIN_STATE = preferencesKey<String>("USER_LOGIN")

    }

    suspend fun saveDataRegister(username : String, email:String, pass: String) {
        dataStore.edit {
            it[USERNAME] = username
            it[EMAIL] = email
            it[PASSWORD] = pass

        }
    }

    suspend fun saveDataLogin(login : String) {
        loginDataStore.edit {
            it[LOGIN_STATE] = login
        }
    }

    suspend fun deleteDataLogin(){
        loginDataStore.edit{
            it.clear()
        }
    }

    val userUsername : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [USERNAME] ?: ""
    }

    val userEmail : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [EMAIL] ?: ""
    }

    val userPassword : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [PASSWORD] ?: ""
    }

    val userLogin : kotlinx.coroutines.flow.Flow<String> = loginDataStore.data.map {
        it [LOGIN_STATE] ?: "false"
    }
}