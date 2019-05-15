package sample

import kotlin.test.Test
import kotlin.test.assertEquals

class SampleTestsJvm {
    @Test
    fun testHello() {
        assertEquals("Hello from Android", Proxy().proxyHello())
    }
}
