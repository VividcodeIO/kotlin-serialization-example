package io.vividcode.kotlinserializationexample

import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class TestDataController {
    @GetMapping
    fun get(): TestData {
        return TestData("hello", 100, false)
    }

    @PostMapping
    fun update(@RequestBody data: TestData): TestData {
        return data.copy(
            v1 = data.v1.toUpperCase(),
            v2 = data.v2 + 100,
        )
    }

    @PostMapping("/callMyself")
    fun callMyself(): TestData? {
        val restTemplate = RestTemplate(listOf(KotlinSerializationJsonHttpMessageConverter()))
        return restTemplate.postForObject(
            "http://localhost:8080",
            TestData("world", 1, true),
            TestData::class.java
        )
    }

    @PostMapping("/polymorphic")
    fun polymorphic(@RequestBody data: InheritanceData): InheritanceData {
        return data.copy(
            extra = data.extra + 100
        )
    }
}