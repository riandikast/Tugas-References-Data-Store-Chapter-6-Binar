package com.binar.preferencesdatastore

import android.content.SharedPreferences
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
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {
    private lateinit var email : String
    lateinit var get : SharedPreferences
    lateinit var toast: String
    lateinit var  userManager : UserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_register, container, false)
        val btnregis = view.findViewById<Button>(R.id.btnregis)

        userManager = UserManager(requireContext())
        btnregis.setOnClickListener {
            val username = regisusername.text.toString()
            email = regisemail.text.toString()
            val regis_pass = regispassword.text.toString()
            val confirmpass = confirmpassword.text.toString()

            if (regisusername.text.isNotEmpty() && regisemail.text.isNotEmpty()
                && regispassword.text.isNotEmpty()
                && confirmpassword.text.isNotEmpty() ){

                if (regis_pass == confirmpass){

                    GlobalScope.launch {
                        userManager.saveDataRegister(username, email, regis_pass)

                    }
                    toast = "Register Berhasil"
                    customToast2()

                    view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)


                }else{
                    toast = "Konfirmasi password tidak sesuai"
                    customToast()
                }


            }else {
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
        toast.setGravity(Gravity.CENTER or Gravity.TOP, 0, 1080)
    }

    fun customToast2() {
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
        text1.setTextColor(Color.GREEN);
        text1.setTextSize(15F)
        toast.show()
        toast.setGravity(Gravity.CENTER or Gravity.TOP, 0, 740)
    }
}