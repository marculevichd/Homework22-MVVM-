package com.example.homework21_tms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework21_tms.adapter.ItemsAdapter
import com.example.homework21_tms.listener.ItemListener


class ItemsFragment : Fragment(), ItemListener {

    private lateinit var itemsAdapter: ItemsAdapter

    private val viewModel: ItemsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsAdapter = ItemsAdapter(this)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = itemsAdapter


        viewModel.getData()
        viewModel.items.observe(viewLifecycleOwner){ listItems ->
            itemsAdapter.submitList(listItems)
        }

        viewModel.msg.observe(viewLifecycleOwner){ msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        viewModel.bundle.observe(viewLifecycleOwner){navBundle->
            val detailsFragment = DetailsFragment()
            val bundle = Bundle()

            bundle.putString("title_listener", navBundle.title)
            bundle.putInt("description_listener", navBundle.description)
            bundle.putString("time_listener", navBundle.time)
            bundle.putInt("image_listener", navBundle.image)

            detailsFragment.arguments = bundle

            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activity_container, detailsFragment)
                .addToBackStack("Details")
                .commit()


        }




        }



    override fun onClick() {
        viewModel.imageViewClicked()
    }

    override fun onElementSelected(
        title_listener: String,
        description_listener: Int,
        time_listener: String,
        image_listener: Int
    ) {
        viewModel.elementClicked(title_listener, description_listener, time_listener, image_listener)
    }
}