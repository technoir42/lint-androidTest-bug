package com.sample

import com.lib.LibAnnotation
import com.testlib.TestAnnotation

class SampleTest {
    @LibAnnotation
    @TestAnnotation
    fun test() {
    }
}
