package myapp

import com.anysolo.toyGraphics.*
import kotlin.random.Random


fun main () {
    val wnd = Window(800, 700, buffered = true)
    var color = 0
    val keyboard = Keyboard(wnd)
    var boxX = wnd.width / 2
    var boxY = 600
    var boxW = 40
    var boxH = 10

    var Ex = 400
    var Ey = 1
    var Espeed = 0


    while (true) {
        val gc = Graphics(wnd)
        gc.clear()
        gc.color = Pal16[color]

        gc.color = Pal16.blue
        gc.drawRect(boxX, boxY, boxW, boxH, fill = true)

        gc.color = Pal16.black

        //Key binds

        gc.drawRect(Ex,Ey,15,15)
        Ey+=Espeed+5

        while (true) {
            val key = keyboard.getPressedKey() ?: break
            when (key.code) {
                KeyCodes.LEFT -> {
                    boxX-=10
                }
                KeyCodes.RIGHT -> {
                    boxX+=10
                }

            }

        }
        if (boxX-35<=Ex && boxX+35>=Ex && boxY-boxH<=Ey && boxY+boxH>=Ey) {
            Espeed = -8

        }
        if (Ey<=1) {
            Ex = Random.nextInt(1, 799)
            Espeed = 1
        }

        if (Ey >= 700)
            break
        //end
        gc.close()
        sleep(10)
        //map
        }

    }