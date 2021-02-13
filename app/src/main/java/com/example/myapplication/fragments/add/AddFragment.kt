package com.example.myapplication.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.models.Priority
import com.example.myapplication.data.models.TodoData
import com.example.myapplication.data.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_add.*


class AddFragment : Fragment() {
    private val mToDoViewModel: ToDoViewModel by viewModels()


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
            inflater.inflate(R.menu.add_menu, menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if(item.itemId == R.id.menu_add){
                insertDataToDb()
            }
            return super.onOptionsItemSelected(item)
        }
        private fun insertDataToDb() {
            val mTitle = title_et.text.toString()
            val mPriority = priorities_spinner.selectedItem.toString()
            val mDescription = description_et.text.toString()

            val validation = verifyDataFromUser(mTitle, mDescription)
            if(validation){
                // Insert Data to Database
                val newData = TodoData(
                    0,
                    mTitle,
                    parsePriority(mPriority),
                    mDescription
                )
                mToDoViewModel.insertData(newData)
                Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
                // Navigate Back
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            }else{
                Toast.makeText(requireContext(), "Please fill ", Toast.LENGTH_SHORT).show()
            }
        }
        fun verifyDataFromUser(title: String, description: String): Boolean {
            return !(title.isEmpty() || description.isEmpty())
        }

        fun parsePriority(priority: String): Priority {
            return when(priority){
                "High Priority" -> { Priority.HIGH }
                "Medium Priority" -> { Priority.MEDIUM }
                "Low Priority" -> { Priority.LOW }
                else -> Priority.LOW
            }
        }


    }