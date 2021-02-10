package com.example.roomusingrxjava.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.roomusingrxjava.R
import com.example.roomusingrxjava.databinding.ActivityMainBinding
import com.example.roomusingrxjava.model.dataClass.User
import com.example.roomusingrxjava.viewmodel.UserViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var disposable: CompositeDisposable
    private lateinit var list: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = ArrayList()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        disposable = CompositeDisposable()
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        viewModel.init(this)

        readAllUser()
        binding.addUser.setOnClickListener(this)
        binding.updateUser.setOnClickListener(this)
        binding.deleteUser.setOnClickListener(this)
        binding.deleteAllUser.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.addUser.id ->{
                insertUser()
            }
            binding.updateUser.id ->{
                updateUser()
            }
            binding.deleteUser.id ->{
                deleteUser()
            }
            binding.deleteAllUser.id ->{
                deleteAllUser()
            }
        }
    }

    private fun insertUser() {
        val user = User(
            name = binding.userName.text.toString(),
            age = binding.userAge.text.toString()
        )

        viewModel.insertUser(user)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                Toast.makeText(this, "Successfully Inserted", Toast.LENGTH_LONG).show()
            }) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            }?.let { disposable.add(it) }
    }

    private fun readAllUser() {
        viewModel.readAllUser()
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                list.clear()
                list.addAll(it)
                Log.d("UserList", it.toString())
            }) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            }?.let { disposable.add(it) }
    }

    private fun updateUser() {
        val user = list[binding.userId.text.toString().toInt()]
        user.name = binding.userName.text.toString()
        user.age = binding.userAge.text.toString()

        viewModel.updateUser(user)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                Toast.makeText(this, "Successfully Updated", Toast.LENGTH_LONG).show()
            }) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            }?.let { disposable.add(it) }
    }

    private fun deleteUser() {
        val user = list[binding.userId.text.toString().toInt()]

        viewModel.deleteUser(user)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                Toast.makeText(this, "Successfully Deleted", Toast.LENGTH_LONG).show()
            }) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            }?.let { disposable.add(it) }
    }

    private fun deleteAllUser() {
        viewModel.deleteAllUser()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                Toast.makeText(this, "Successfully Deleted All User", Toast.LENGTH_LONG).show()
            }) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            }?.let { disposable.add(it) }
    }
}