package com.yc.jetpack.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.yc.fragmentmanager.FragmentManagerHelper
import com.yc.jetpack.R


class JetpackActivity : AppCompatActivity(){

    private lateinit var navController: NavController

    companion object{
        fun startActivity(context : Context){
            context.startActivity(Intent(context,JetpackActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack_main)
        initNavController()
        FragmentManagerHelper.getInstance()?.addFragmentLifecycle(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        FragmentManagerHelper.getInstance()?.removeFragmentLifecycle(this)
    }

    private fun initNavController() {
        navController = Navigation.findNavController(this, R.id.fragment_home)
        //通过id跳转
        navController.navigate(R.id.homeFragment)
        //通过action跳转
        //navController.navigate(HomeNavGraphDirections.actionHomeFragment())


        //通过action跳转
        //navigate(R.id.fragment_home, HomeNavGraphDirections.actionHomeFragment())
    }


}