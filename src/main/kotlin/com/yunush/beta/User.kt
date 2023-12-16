package com.yunush.beta

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

@DynamoDBTable(tableName = "User")
class User {
    @DynamoDBHashKey(attributeName = "id")
    var id: Long = 0

    @DynamoDBAttribute(attributeName = "name")
    var name: String? = null

    @DynamoDBIndexHashKey(attributeName = "email", globalSecondaryIndexName = "email-index")
    var email: String? = null

    @DynamoDBAttribute(attributeName = "password")
    var password: String? = null

    @DynamoDBAttribute(attributeName = "DOB")
    var dob: String? = null
}
