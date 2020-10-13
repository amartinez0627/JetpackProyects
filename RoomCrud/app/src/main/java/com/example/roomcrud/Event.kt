package com.example.roomcrud

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false

    fun getContentNotHandled(): T?{
        return  if(hasBeenHandled){
            null
        }else{
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content

}