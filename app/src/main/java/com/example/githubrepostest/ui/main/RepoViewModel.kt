package com.example.githubrepostest.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubrepostest.api.ApiService
import com.example.githubrepostest.api.response.RepoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoViewModel : ViewModel() {

    val repoLiveData:MutableLiveData<List<RepoResponse>> = MutableLiveData()


    fun loadRepoList(){
        ApiService.api.getRepositoriesList().enqueue(object: Callback<List<RepoResponse>>{
            override fun onFailure(call: Call<List<RepoResponse>>, t: Throwable) {
                Log.d("RepoLoading", "Error message: ${t.message}")
            }

            override fun onResponse(
                call: Call<List<RepoResponse>>,
                response: Response<List<RepoResponse>>
            ) {
                if(response.isSuccessful){
                    repoLiveData.value = response.body()
                }else{
                    Log.d("RepoLoading", "Error message: ${response.errorBody()?.string()}")
                }
            }
        })
    }

}
