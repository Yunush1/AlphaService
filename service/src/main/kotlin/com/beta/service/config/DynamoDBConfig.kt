package com.beta.service.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput
import com.beta.service.data.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class DynamoDBConfig {

    @Value("\${aws.endpoint}")
    lateinit var endpoint:String
    @Value("\${aws.accessKey}")
    lateinit var accessKey:String
    @Value("\${aws.secretKey}")
    lateinit var secretKey:String
    @Value("\${aws.region}")
    lateinit var region:String

    @Bean
    fun dynamoDBMapper():DynamoDBMapper{
        val amazonDynamoDB = amazonDynamoDB()
        val mapper = DynamoDBMapper(amazonDynamoDB)


        val createTableRequest = mapper.generateCreateTableRequest(User::class.java)
        createTableRequest.provisionedThroughput = ProvisionedThroughput(25L, 25L)
        try {

//            amazonDynamoDB.createTable(createTableRequest)
        }catch (e:Exception){
            println(e.message)
        }
        return mapper
    }


    fun amazonDynamoDB(): AmazonDynamoDB {

        return AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(endpoint, region))
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .build()
    }
}