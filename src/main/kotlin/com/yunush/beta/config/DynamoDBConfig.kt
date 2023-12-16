package com.yunush.beta.config

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput
import com.yunush.beta.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DynamoDBConfig {

    @Value("\${aws.endpoint}")
    lateinit var awsEndPoint: String

    @Value("\${aws.accessKey}")
    lateinit var awsAccessKey: String

    @Value("\${aws.secretKey}")
    lateinit var awsSecretKey: String

    @Value("\${aws.region}")
    lateinit var awsRegion: String

    @Bean
    fun dynamoDb(): DynamoDBMapper {
        val mapper = DynamoDBMapper(amazonDynamoDB())
        val dynamoDB = amazonDynamoDB()
        val createTableAmazon: CreateTableRequest = mapper.generateCreateTableRequest(User::class.java)
        createTableAmazon.provisionedThroughput = ProvisionedThroughput(25L, 25L)
//        dynamoDB.createTable(createTableAmazon)
        return mapper
    }

    fun amazonDynamoDB(): AmazonDynamoDB {
        return AmazonDynamoDBAsyncClientBuilder.standard()
                .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(awsEndPoint, awsRegion))
                .withCredentials(
                        AWSStaticCredentialsProvider(
                                BasicAWSCredentials(
                                        awsAccessKey, awsSecretKey
                                )
                        )
                ).build()
    }

}