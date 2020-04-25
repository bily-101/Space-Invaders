package myapp

import com.anysolo.toyGraphics.Graphics
import com.anysolo.toyGraphics.Pal16
import com.anysolo.toyGraphics.Window
import com.anysolo.toyGraphics.sleep

fun main () {
    var nok = false

    print("Your username:")
    var username = readLine()


    print("Difficulty(Hard, medium, easy):")
    var difficulty = readLine()
    val wnd = Window(10, 10, Pal16.white, buffered = true)
    while (true) {

        val g = Graphics(wnd)
        g.clear()





        g.close()
        sleep(10)
    }

}