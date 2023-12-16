package com.yunush.beta

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository

@Repository
class UserRepository {
    @Autowired
    val dynamoDBMapper: DynamoDBMapper? = null

    fun putUser(user: User): ResponseEntity<Any> {
        return try {
            dynamoDBMapper!!.save(user)
            ResponseEntity.status(HttpStatus.OK).body("Registration success")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error ${e.message}")
        }
    }

    fun getAll(): List<User> {
        val scanExpression = DynamoDBScanExpression()
        return dynamoDBMapper!!.scan(User::class.java, scanExpression)
    }

    fun getUser(email: String): ResponseEntity<Any> {
        val user = User()
        user.email = email
        return try {
            ResponseEntity.status(HttpStatus.OK).body(dynamoDBMapper!!.load(User::class.java, email))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid email id")
        }
    }

}