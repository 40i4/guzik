package com.example.guzik

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.apache.commons.exec.OS
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.openqa.selenium.By
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.net.URL
import java.nio.file.Paths
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@Suppress("UNREACHABLE_CODE")
class ExampleUnitTest {
    //var os: OS = OS.ANDROID
    var driver: AppiumDriver<MobileElement>? = null

    @Before
    fun setup() {
        //os = OS.valueOf(System.getProperty("platform", OS.ANDROID.name))
        val capabilities = DesiredCapabilities()
        val userDir = System.getProperty("user.dir")
        val serverAddress = URL("http://127.0.0.1:4723/wd/hub")

        capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.17.0")
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554")

        val localApp = "/build/outputs/apk/debug/app-debug.apk"
        val appPath = Paths.get(userDir, localApp).toAbsolutePath().toString()
        capabilities.setCapability(MobileCapabilityType.APP, appPath)

        driver = AndroidDriver(serverAddress, capabilities)

        driver?.let {
            it.manage()?.timeouts()?.implicitlyWait(30, TimeUnit.SECONDS)
        }
    }

    @After
    fun tearDown() {
        driver?.quit()
    }


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun clickGuzik() {
        val el1: MobileElement = driver!!.findElementById("com.example.guzik:id/fab")
        val wait = WebDriverWait(driver, 20, 500)
        el1.click()
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.example.guzik:id/snackbar_text"
            )))
        val snackbar1: MobileElement = driver!!.findElementById("com.example.guzik:id/snackbar_text")
        assertEquals(snackbar1.text, "Replace with your own action")
    }
}
