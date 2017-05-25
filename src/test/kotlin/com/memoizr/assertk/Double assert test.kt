package com.memoizr.assertk

import com.nhaarman.mockito_kotlin.spy
import org.assertj.core.api.AbstractDoubleAssert
import org.assertj.core.api.Assertions
import org.junit.Test
import org.mockito.Mockito

class `Double assert test` {
    lateinit var mockAssertion: AbstractDoubleAssert<*>
    @Suppress("UNCHECKED_CAST")
    val _expect = object : AssertionHook {
            override fun that(subjectUnderTest: Double?): DoubleAssert {
            val spy: AbstractDoubleAssert<*> = spy(Assertions.assertThat(subjectUnderTest))
            mockAssertion = spy
            return DoubleAssert(subjectUnderTest, mockAssertion)
        }
    }

    val chained = Any()
    infix fun DoubleAssert.andCanBe(chained: Any) = this

    private val verify by lazy { Mockito.verify(mockAssertion) }

    private val four = 4.0
    private val three = 3.0
    private val five = 5.0
    private val one = 1.0
    private val negativeOne = -one

    @Test
    fun isEqualTo() {
        three isEqualTo three andCanBe chained
    }

    @Test
    fun isNotEqualTo() {
        three isNotEqualTo four andCanBe chained
    }

    @Test
    fun isInstance() {
        three isInstance of<Double>() andCanBe chained
    }

    @Test
    fun is_() {
        three toBe notNull andCanBe chained
        three toBe notNegative andCanBe chained
    }

    @Test
    fun describedAs() {
        three describedAs "foo" andCanBe chained
    }

    @Test
    fun isLessThan() {
        _expect that three isLessThan four andCanBe chained
        three isLessThan four andCanBe chained
        verify.isLessThan(four)
    }

    @Test
    fun isLessThanOrEqualTo() {
        _expect that four isLessThanOrEqualTo four andCanBe chained
        four isLessThanOrEqualTo four andCanBe chained
        verify.isLessThanOrEqualTo(four)
    }

    @Test
    fun isGreaterThan() {
        _expect that five isGreaterThan four andCanBe chained
        five isGreaterThan four andCanBe chained
        verify.isGreaterThan(four)
    }

    @Test
    fun isGreaterThanOrEqualTo() {
        _expect that four isGreaterThanOrEqualTo four andCanBe chained
        four isGreaterThanOrEqualTo four andCanBe chained
        verify.isGreaterThanOrEqualTo(four)
    }

    @Test
    fun isBetween() {
        _expect that four isBetween (three..five) andCanBe chained
        four isBetween (three..five) andCanBe chained
        verify.isBetween(three, five)
    }

    @Test
    fun isStrictlyBetween() {
        _expect that four isStrictlyBetween (three..five) andCanBe chained
        four isStrictlyBetween (three..five) andCanBe chained
        verify.isStrictlyBetween(three, five)
    }

    @Test
    fun `isCloseTo within`() {
        _expect that four isCloseTo three withinOffset one andCanBe chained
        four isCloseTo three withinOffset one andCanBe chained
        verify.isCloseTo(three, Assertions.within(one))
    }

    @Test
    fun `isCloseTo percentage int`() {
        _expect that three isCloseTo four withinPercentage 25 andCanBe chained
        three isCloseTo four withinPercentage 25 andCanBe chained
        verify.isCloseTo(four, Assertions.withinPercentage(25))
    }

    @Test
    fun `isCloseTo percentage double`() {
        _expect that three isCloseTo four withinPercentage 25.3 andCanBe chained
        three isCloseTo four withinPercentage 25.3 andCanBe chained
        verify.isCloseTo(four, Assertions.withinPercentage(25.3))
    }

    @Test
    fun `isCloseTo percentage float`() {
        _expect that three isCloseTo four withinPercentage 25f andCanBe chained
        three isCloseTo four withinPercentage 25f andCanBe chained
        verify.isCloseTo(four, Assertions.withinPercentage(25))
    }

    @Test
    fun isZero() {
        _expect that 0.0 toBe zero andCanBe chained
        0.0 shouldBe zero andCanBe chained
        verify.isZero()
    }

    @Test
    fun isNotZero() {
        _expect that one toBe notZero andCanBe chained
        one shouldBe notZero andCanBe chained
        verify.isNotZero()
    }

    @Test
    fun isPositive() {
        _expect that one toBe positive andCanBe chained
        one shouldBe positive andCanBe chained
        verify.isPositive()
    }


    @Test
    fun isNotPositive() {
        _expect that negativeOne toBe notPositive andCanBe chained
        negativeOne shouldBe notPositive andCanBe chained
        verify.isNotPositive()
    }

    @Test
    fun isNegative() {
        _expect that negativeOne toBe negative andCanBe chained
        negativeOne shouldBe negative andCanBe chained
        verify.isNegative()
    }

    @Test
    fun isNotNegative() {
        _expect that one toBe notNegative andCanBe chained
        one shouldBe notNegative andCanBe chained
        verify.isNotNegative()
    }

    @Test
    fun `supports block syntax`() {
        _expect that one isSuchThat {
            it toBe positive
            it toBe notNegative
            it isBetween (negativeOne..three)
        }
        assert that one isSuchThat {
            it toBe positive
            it toBe notNegative
            it isBetween (negativeOne..three)
        }
    }
}

