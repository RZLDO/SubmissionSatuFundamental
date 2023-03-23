package com.example.firstsubmission.UI.Activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstsubmission.R
import com.example.firstsubmission.UI.adapter.GithubAdapter
import com.example.firstsubmission.data.Model.ItemsItem
import com.example.firstsubmission.databinding.ActivityMainBinding
import com.example.firstsubmission.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel> ()
    companion object {
        private const val USERNAME = "Rizaldo"
        private const val TAG = "MainActivityTag"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Github Users"
        setDivider()
        getData(USERNAME)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                getData(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }
    private fun setDivider(){
        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvListGithub.layoutManager = linearLayoutManager
        val itemDecoration = DividerItemDecoration(this,linearLayoutManager.orientation)
        binding.rvListGithub.addItemDecoration(itemDecoration)
    }
    private fun getData(username:String){
        mainViewModel.userData.observe(this){
            setAdapter(it)
        }
        mainViewModel.isLoading.observe(this){
            isLoading(it)
        }
        mainViewModel.getUserData(username)
    }
    private fun setAdapter(userData : List<ItemsItem>){
        binding.rvListGithub.setHasFixedSize(true)
        binding.rvListGithub.layoutManager = LinearLayoutManager(this)
        val adapter = GithubAdapter(userData)
        adapter.setOnItemClickListener {item ->
            val intent = Intent(this,DetailUsers::class.java)
            intent.putExtra(DetailUsers.USERNAME, userData[item].login)
            Toast.makeText(this,userData[item].login,Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
        binding.rvListGithub.adapter = adapter

    }

    private fun isLoading(bool : Boolean){
        binding.progressBar.visibility = if (bool) View.VISIBLE else View.GONE
    }
}