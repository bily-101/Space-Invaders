package myapp

import com.anysolo.toyGraphics.Graphics
import com.anysolo.toyGraphics.Pal16
import com.anysolo.toyGraphics.Window
import com.anysolo.toyGraphics.sleep

fun main () {
    var nok = false


    val wnd = Window(10, 10, Pal16.white, buffered = true)
    while (true) {

        val g = Graphics(wnd)
        g.clear()





        g.close()
        sleep(10)
    }

}