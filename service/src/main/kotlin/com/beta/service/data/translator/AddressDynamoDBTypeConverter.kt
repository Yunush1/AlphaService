package com.beta.service.data.translator

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter
import com.beta.service.data.Address
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.IOException

class AddressDynamoDBTypeConverter : DynamoDBTypeConverter<String, Address> {
    private val objectMapper = ObjectMapper().registerKotlinModule()
    override fun convert(address: Address?): String {
        return try {
            objectMapper.writeValueAsString(address)
        } catch (e: Exception) {
            throw RuntimeException(e.message)
        }
    }

    override fun unconvert(address: String?): Address {
        return try {
            objectMapper.readValue(address, object : TypeReference<Address>() {})
        } catch (jp: JsonMappingException) {
            throw RuntimeException(jp.message)
        } catch (jp: JsonParseException) {
            throw RuntimeException(jp.message)
        } catch (io: IOException) {
            throw RuntimeException(io.message)
        }
    }
}