package com.example.roomcrud

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
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

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage

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

    fun insert(subscriber: Subscriber): Job = viewModelScope.launch {
        val newRowId =  repository.insert(subscriber)
        if(newRowId > -1 ){
            statusMessage.value = Event("Subscriber Inserted Successfully $newRowId")
        }else{
            statusMessage.value = Event("Error Occurred")
        }

    }

    fun update(subscriber: Subscriber): Job = viewModelScope.launch {
        val noOfRows = repository.update(subscriber)
        if(noOfRows  > 0){
            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            saveOrUpdateBtnText.value = "Save"
            clearOrDeleteBtnText.value = "Clear All"
            statusMessage.value = Event("$noOfRows Row Updated Successfully")
        }else{
            statusMessage.value = Event("Error Occurred")
        }

    }

    fun delete(subscriber: Subscriber): Job = viewModelScope.launch {
        val noOfRowsDeleted = repository.delete(subscriber)
        if (noOfRowsDeleted > 0){
            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            saveOrUpdateBtnText.value = "Save"
            clearOrDeleteBtnText.value = "Clear All"
            statusMessage.value = Event("$noOfRowsDeleted Row Deleted Successfully")
        }else{
            statusMessage.value = Event("Error Occurred")
        }

    }

    fun clearAll(): Job = viewModelScope.launch {
        val noOfRowsDeleted =  repository.deleteAll()
        if (noOfRowsDeleted > 0){
            statusMessage.value = Event("$noOfRowsDeleted Subscribers Deleted Successfully")
        }else{
            statusMessage.value = Event("Error Occurred")
        }
    }

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