package com.example.todo.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.data.model.ToDoData
import com.example.todo.data.viewmodel.ToDoViewModel
import com.example.todo.data.viewmodel.SharedVM
import kotlinx.android.synthetic.main.fragment_add.*

class addFragment : Fragment() {

    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val sharedViewModel: SharedVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_add){
            insertDatainDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDatainDb(){
        val mTitle = etTitle.text.toString()
        val mPriority = spinnerPriority.selectedItem.toString()
        val mDescription = etDescription.text.toString()

        val validation = sharedViewModel.verifyDataFromUser(mTitle,mDescription)

        if(validation){
            val newData = ToDoData(0,mTitle,sharedViewModel.parsePriority(mPriority),mDescription)
            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(),"Successfully Added", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill all fields.", Toast.LENGTH_SHORT).show()
        }

    }

}