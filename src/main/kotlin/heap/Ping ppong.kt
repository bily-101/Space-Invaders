package myapp

import com.anysolo.toyGraphics.*
import kotlin.random.Random


private fun drawEnemy(gc: Graphics, x: Int, y: Int) {
    gc.drawRect(x, y,15,15)
}

fun main () {
    val wnd = Window(800, 700, buffered = true)
    var color = 0
    val keyboard = Keyboard(wnd)
    var boxX = wnd.width / 2
    var boxY = 600
    var boxW = 40
    var boxH = 10

    var Ex = Random.nextInt(200,600)
    var Ey = Random.nextInt(0,10)
    var Espeed = 0

    var score = 0

    var Rx = Random.nextInt(200,550)
    var Ry = 1
    var Rspeed = 0

    while (true) {
        val gc = Graphics(wnd)
        gc.clear()
        gc.color = Pal16[color]

        gc.color = Pal16.blue
        gc.drawRect(boxX, boxY, boxW, boxH, fill = true)

        gc.color = Pal16.black


        if (boxX<=0)
            boxX = wnd.width-1

        if (boxX>=wnd.width)
            boxX = 1

        //Key binds

        gc.drawRect(Ex,Ey,15,15)
        Ey+=Espeed

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
        if (boxX-35<=Ex && boxX+35>=Ex && boxY-boxH<=Ey && boxY+boxH>=Ey ) {
            Espeed = -8
            score +=1

        }

        if (boxX-35<=Rx && boxX+35>=Rx && boxY-boxH<=Ry && boxY+boxH>=Ry) {
            Ry = boxY-12
            Rspeed = -8
            score +=1
        }

        if (Ey<=1) {
            Ex = Random.nextInt(200, 600)
            Espeed = 0
        }

        if (Ry<=1) {
            Rx = Random.nextInt(290,550)
            Rspeed = 0
        }


        if (Ey >= 700)
            break

        gc.drawText(20,200,"Score:$score")

        drawEnemy(gc, Ex, Ey)

        if (score >= 5) {

        }
        //end
        gc.close()
        sleep(10)
        }
    }