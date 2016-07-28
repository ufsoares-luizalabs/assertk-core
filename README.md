# AssertK - Fluent assertions for Kotlin
AssertK provides a Kotlin-friendly syntax for using the amazing AssertJ assertion framework.

###Simple assertions
```kotlin
assert that Unit isEqualTo Unit
assert that Any() isNotEqualTo Any()
assert that Any() _is notNull
assert that nullObject _is null
assert that anObject isInstance of<Any>()
assert that Any() describedAs "A labeled object" isInstance of<Unit>()
```

###Chained syntax
```kotlin
assert that Unit isNotEqualTo Any() isEqualTo Unit _is notNull isInstance of<Any>()
```

###Block syntax
```kotlin
assert that Any() isSuchThat {
    it _is notNull
    it isInstance of<Any>()
    it isNotEqualTo Unit
    it isNotEqualTo Any()
}
```

##Assertions on exceptions
###Chained syntax
```kotlin
assert thatExceptionIsThrownBy { failFunction() } hasMessageContaining "foo" hasCause Throwable()
```

###Block syntax
```kotlin
assert thatExceptionIsThrownBy {
    throw Throwable("exception foo", Throwable())
} and {
    it hasMessage "exception foo"
    it hasCause Throwable()
    it hasCauseExactlyInstanceOf Throwable::class.java
    it hasMessageContaining "foo"
    it hasMessageStartingWith "ex"
    it hasMessageEndingWith "foo"
}
```

##Get it
```groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
dependencies {
    ...
    testCompile 'com.github.memoizr:assertk-core:0.0.2-Alpha'
    ...
}
```