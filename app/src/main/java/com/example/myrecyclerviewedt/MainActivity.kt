package com.example.myrecyclerviewedt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.myrecyclerviewedt.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var userList: ArrayList<UserData>
    private lateinit var myAdapter: UserAdapter

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        userList = ArrayList()
        val myLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        myAdapter = UserAdapter(this, userList)
        binding.mRecycler.apply {
            adapter = myAdapter
            layoutManager = myLayoutManager
        }
        binding.addingBtn.setOnClickListener {

            addInfo()

        }

    }

    private fun addInfo() {
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.add_item, null)
        val nameEdt = view.findViewById<EditText>(R.id.userName)
        val numberEdt = view.findViewById<EditText>(R.id.userNo)


        AlertDialog.Builder(this)
            .setView(view)
            .setPositiveButton("Yes") { dialog, _ ->
                val names = nameEdt.text.toString()
                val number = numberEdt.text.toString()
                userList.add(UserData("Name: $names", "Mobile No. : $number"))
                myAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Adding User Information Success", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()

            }
            .create()
            .show()


    }
}