package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)

        val responseLiveData:LiveData<Response<Albums>> = liveData {
            val response = retService.getAlbums()
            emit(response)
        }

        responseLiveData.observe(this, Observer {
            val albumsList = it.body()?.listIterator()
            if (albumsList != null){
                while (albumsList.hasNext()){
                    val albumsItem = albumsList.next()
                    Log.i("MYTAG",albumsItem.title)
                    val result = " "+"Album Title : ${albumsItem.title}"+"\n"+
                                 " "+"Album Id : ${albumsItem.id}"+"\n"+
                                 " "+"User Id : ${albumsItem.userId}"+"\n\n\n"

                    text_view.append(result)

                }
            }
        })
    }
}