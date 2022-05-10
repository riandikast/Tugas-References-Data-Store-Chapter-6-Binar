package com.binar.preferencesdatastore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.asLiveData
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.logout_dialog.view.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class HomeFragment : Fragment() {
    lateinit var userManager: UserManager

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        userManager = UserManager(requireContext())
        userManager.userUsername.asLiveData().observe(requireActivity()){
            welcome.text = "welcome : "+it.toString()
        }

        view.logout.setOnClickListener {
            val custom = LayoutInflater.from(requireContext()).inflate(R.layout.logout_dialog, null)
            val a = AlertDialog.Builder(requireContext())
                .setView(custom)
                .create()
            custom.btnlogouttidak.setOnClickListener {

                a.dismiss()
            }

            custom.btnlogoutya.setOnClickListener {
                GlobalScope.launch {
                    userManager.deleteDataLogin()
                }

                a.dismiss()
                view.findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            }

            a.show()
        }


        return view
    }

}
