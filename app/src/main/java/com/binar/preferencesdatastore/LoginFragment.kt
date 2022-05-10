package com.binar.preferencesdatastore

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.asLiveData
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    lateinit var  userManager : UserManager

    lateinit var getEmail : String
    lateinit var getPassword : String
    lateinit var loginstate : String
    lateinit var email: String
    lateinit var password: String
    lateinit var toast: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val daftar = view.findViewById<TextView>(R.id.daftar2)
        val login = view.findViewById<Button>(R.id.btnlogin)
        userManager = UserManager(requireContext())
        getEmail = ""
        getPassword = ""
        userManager.userEmail.asLiveData().observe(requireActivity()){
            getEmail = it
        }

        userManager.userPassword.asLiveData().observe(requireActivity()){
            getPassword = it
        }

        daftar.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        login.setOnClickListener {


            if (loginemail.text.isNotEmpty() && loginpassword.text.isNotEmpty()){

                email = loginemail.text.toString()
                password = loginpassword.text.toString()

                loginstate = ""


                if (email == getEmail && password == getPassword){

                    GlobalScope.launch {
                        userManager.saveDataLogin("true")

                    }
                    view.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

                }else{
                    toast = "Email Atau Password Salah"
                    customToast()
                }

            }
            else{
                toast = "Harap Isi Semua Data"

                customToast()


            }

        }
        return view
    }

    fun customToast() {
        val text = toast
        val toast = Toast.makeText(
            requireActivity()?.getApplicationContext(),
            text,
            Toast.LENGTH_SHORT
        )
        val text1 =
            toast.getView()?.findViewById(android.R.id.message) as TextView
        val toastView: View? = toast.getView()
        toastView?.setBackgroundColor(Color.TRANSPARENT)
        text1.setTextColor(Color.RED);
        text1.setTextSize(15F)
        toast.show()
        toast.setGravity(Gravity.CENTER or Gravity.TOP, 0, 740)
    }



}