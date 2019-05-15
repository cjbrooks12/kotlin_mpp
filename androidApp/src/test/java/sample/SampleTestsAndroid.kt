package sample

import kotlin.test.Test
import kotlin.test.assertEquals

class SampleTestsAndroid {
    @Test
    fun testHello() {
        assertEquals("Hello from Android", Proxy().proxyHello())
    }
}
