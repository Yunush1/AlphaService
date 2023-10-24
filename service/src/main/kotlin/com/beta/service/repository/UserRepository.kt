package com.beta.service.repository

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression
import com.beta.service.data.User
import com.beta.service.data.message.SuccessMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

/**
 * Created by Yunush on 24/10/2023.
 */

@Repository
class UserRepository {

    @Autowired
    val dynamoDBMapper: DynamoDBMapper? = null

    fun putUser(user: User): SuccessMessage {
        return try {
            dynamoDBMapper!!.save(user)
            SuccessMessage(true, "Success")
        } catch (e: Exception) {

            SuccessMessage(false, "${e.message}")
        }
    }

    fun getUserById(id: String): User {
        return try {
            dynamoDBMapper!!.load(User::class.java, id)
        } catch (e: Exception) {
            throw RuntimeException(e.message)
        }
    }
    fun deleteUser(id: String): SuccessMessage {
        return try {
            dynamoDBMapper!!.delete(getUserById(id))
            SuccessMessage(true, "Success")
        } catch (e: Exception) {
            SuccessMessage(false, "${e.message}")
        }
    }
    fun updateUser(user: User): SuccessMessage {
        return try {
            dynamoDBMapper!!.save(user)
            SuccessMessage(true, "Success")
        } catch (e: Exception) {
            SuccessMessage(false, "${e.message}")
        }
    }
    fun getAllUsers(): List<User> {
        val scanExpression = DynamoDBScanExpression()
        return try {
            dynamoDBMapper!!.scan(User::class.java, scanExpression)
        } catch (e: Exception) {
            throw RuntimeException(e.message)
        }
    }



}