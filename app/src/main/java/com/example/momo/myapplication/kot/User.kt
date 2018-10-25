package com.example.momo.myapplication.kot

import java.lang.IllegalArgumentException


fun User.validate() {
    fun validate(name: String) {
        if (name.isEmpty()) {
            throw IllegalArgumentException("sssss")
        }
    }

    validate(this.name)
}

class User(val name: String, val age: Int) {

    fun saveUser(user: User) {
        user.validate()
    }

}