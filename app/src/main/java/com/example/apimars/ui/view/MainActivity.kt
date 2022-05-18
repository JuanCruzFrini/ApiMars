package com.example.apimars.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apimars.databinding.ActivityMainBinding
import com.example.apimars.domain.MarsPhotoAdapter
import com.example.apimars.domain.MarsStateAdapter
import com.example.apimars.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var photosAdapter: MarsPhotoAdapter
    private lateinit var stateAdapter: MarsStateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.btnGetPhotos.setOnClickListener { viewModel.getMarsPhotos() }
        binding.btnGetStates.setOnClickListener { viewModel.getMarsStates() }
    }

    private fun setObservers() {
        viewModel.status.observe(this, Observer {
            binding.txtRandomText.text = it.toString()
        })
        viewModel.listPhotos.observe(this, Observer { list ->
            photosAdapter = MarsPhotoAdapter(list)
            binding.rvPhotos.let { rv ->
                rv.adapter = photosAdapter
                rv.layoutManager = GridLayoutManager(this, 2)
            }
        })
        viewModel.listStates.observe(this, Observer { list ->
            stateAdapter = MarsStateAdapter(list)
            binding.rvPhotos.let { rv ->
                rv.adapter = stateAdapter
                rv.layoutManager = LinearLayoutManager(this)
            }
        })
    }
}