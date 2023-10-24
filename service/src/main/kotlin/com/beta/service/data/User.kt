package com.beta.service.data

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted
import com.beta.service.data.translator.AddressDynamoDBTypeConverter
import jakarta.annotation.Nullable

@DynamoDBTable(tableName = "User")
class User {
    @DynamoDBHashKey(attributeName = "userId")
    var userId:String?=null
    @DynamoDBAttribute(attributeName = "name")
    var name:String?=null
    @DynamoDBAttribute(attributeName = "email")
    var email:String?=null
    @DynamoDBAttribute(attributeName = "password")
    var password:String?=null
    @DynamoDBAttribute(attributeName = "phone")
    var phone:Long=0
    @Nullable
    @DynamoDBAttribute(attributeName = "address")
    @DynamoDBTypeConverted(converter = AddressDynamoDBTypeConverter::class)
    val address:Address?=null

}
@DynamoDBDocument
class Address
{
    @DynamoDBAttribute(attributeName = "city")
    val city:String?=null
    @DynamoDBAttribute(attributeName = "state")
    val state:String?=null
    @DynamoDBAttribute(attributeName = "pin")
    val pin:Long = 0
    @DynamoDBAttribute(attributeName = "country")
    val country:String?=null
}
