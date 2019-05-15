package sample

import kotlin.test.Test
import kotlin.test.assertEquals

class SampleTestsIOS {
    @Test
    fun testHello() {
        assertEquals("Hello from iOS", Proxy().proxyHello())
    }
}
