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
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete: Subscriber

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
        if (isUpdateOrDelete){
            subscriberToUpdateOrDelete.name = inputEmail.value!!
            subscriberToUpdateOrDelete.email = inputEmail.value!!
            update(subscriberToUpdateOrDelete)
        }else{
            val name:String = inputName.value!!
            val email:String = inputEmail.value!!

            insert(Subscriber(0,name,email))

            inputName.value = null
            inputEmail.value = null
        }

    }

    fun clearOrDelete(){
        if(isUpdateOrDelete){
            delete(subscriberToUpdateOrDelete)
        }else{
            clearAll()
        }

    }

    fun insert(subscriber: Subscriber): Job = viewModelScope.launch { repository.insert(subscriber) }

    fun update(subscriber: Subscriber): Job = viewModelScope.launch {
        repository.update(subscriber)
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateBtnText.value = "Save"
        clearOrDeleteBtnText.value = "Clear All"
    }

    fun delete(subscriber: Subscriber): Job = viewModelScope.launch {
        repository.delete(subscriber)
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateBtnText.value = "Save"
        clearOrDeleteBtnText.value = "Clear All"
    }

    fun clearAll(): Job = viewModelScope.launch { repository.deleteAll() }

    fun initUpdateAndDelete(subscriber: Subscriber){
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber
        saveOrUpdateBtnText.value = "Update"
        clearOrDeleteBtnText.value = "Delete"
    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}