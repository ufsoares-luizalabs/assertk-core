package com.memoizr.assertk

import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.AbstractBooleanAssert
import org.assertj.core.api.Assertions
import org.junit.Test

class `BooleanAssert test` {
    lateinit var mockAssertion: AbstractBooleanAssert<*>
    @Suppress("UNCHECKED_CAST")
    val _expect = object : AssertionHook {
        override fun that(subjectUnderTest: Boolean?): BooleanAssert {
            val spy: AbstractBooleanAssert<*> = spy(Assertions.assertThat(subjectUnderTest))
            mockAssertion = spy
            return BooleanAssert(subjectUnderTest, mockAssertion)
        }
    }

    val chained = Any()
    infix fun BooleanAssert.andCanBe(chained: Any) = this

    @Test
    fun isTrue() {
        _expect that true shouldBe true andCanBe chained
        verify(mockAssertion).isTrue()
        verify(mockAssertion, never()).isFalse()
    }

    @Test
    fun isFalse() {
        _expect that false shouldBe false andCanBe chained
        verify(mockAssertion).isFalse()
        verify(mockAssertion, never()).isTrue()
    }
}
