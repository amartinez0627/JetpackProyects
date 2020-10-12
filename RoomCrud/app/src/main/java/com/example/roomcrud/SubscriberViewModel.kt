package com.example.roomcrud

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomcrud.db.Subscriber
import com.example.roomcrud.db.SubscriberRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SubscriberViewModel(private  val repository: SubscriberRepository): ViewModel(),Observable {

    val subscribers = repository.subscribers

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateBtnText = MutableLiveData<String>()

    @Bindable
    val clearOrDeleteBtnText = MutableLiveData<String>()

    init {
        saveOrUpdateBtnText.value = "Save"
        clearOrDeleteBtnText.value = "Clear All"
    }

    fun saveOrUpdate(){
        val name:String = inputName.value!!
        val email:String = inputEmail.value!!

        insert(Subscriber(0,name,email))

        inputName.value = null
        inputEmail.value = null
    }

    fun clearOrDelete(){
        clearAll()
    }

    fun insert(subscriber: Subscriber): Job = viewModelScope.launch { repository.insert(subscriber) }

    fun update(subscriber: Subscriber): Job = viewModelScope.launch { repository.update(subscriber) }

    fun delete(subscriber: Subscriber): Job = viewModelScope.launch { repository.delete(subscriber) }

    fun clearAll(): Job = viewModelScope.launch { repository.deleteAll() }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}