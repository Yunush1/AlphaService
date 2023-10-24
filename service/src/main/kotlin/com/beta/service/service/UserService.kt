package com.beta.service.service

import com.beta.service.data.User
import com.beta.service.data.db.DbUser
import com.beta.service.data.message.SuccessMessage
import com.beta.service.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

/**
 *  Created by Yunush on 23/10/2023.
 */

@Service
class UserService {

    @Autowired
    val userRepository : UserRepository?=null


    fun putUser(dbUser: DbUser):SuccessMessage{
        val passwordString = dbUser.password.encodeToByteArray()
        val user = User()
        user.userId = UUID.randomUUID().toString()
        user.name = dbUser.name
        user.email = dbUser.email
        user.password = passwordString.toString()
        user.phone = dbUser.phone

        return userRepository!!.putUser(user)
    }
    fun getUser(userId:String): User?{
        return userRepository?.getUserById(userId)
    }

}