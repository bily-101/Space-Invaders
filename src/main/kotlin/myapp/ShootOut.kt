package myapp
import com.anysolo.toyGraphics.*
import kotlin.random.Random

/*
* Author: bily 2019
* Left key to go left
* right key to go right
* space to shoot
* Have fun
*/

fun main () {
    // The Window
    var wnd = Window(1000, 700, buffered = true, background = Pal16.blue)
    // The Keyboard Variable
    val keyboard = Keyboard(wnd)
    //enemy block X and Y
    var blockX =  Random.nextInt(30, 500)
    var blockY = 40

    var tankX = 365
    var tankY = 665
    var color = 0
    var bulletX = tankX
    var bulletY = tankY
    var bulletSpeed = 0
    var stop = false
    var blockSpeed = 0
    var score = 0

    while (true) {
        val gc = Graphics(wnd)
        gc.clear()
        gc.color = Pal16[color]

        if (score in 5..10) {
            blockSpeed = 12
            gc.color = Pal16.brightYellow
            gc.drawRect(wnd.height,wnd.width,wnd.height,wnd.width, fill = true)
        }

        if (score >= 10) {
            blockSpeed = 15
            gc.color = Pal16.darkGray
            gc.drawRect(wnd.height,wnd.width,wnd.height,wnd.width)
        }

        // Key codes
        while (true) {
            val key = keyboard.getPressedKey() ?: break

            when (key.code) {
                KeyCodes.LEFT -> {
                    tankX -= 10
                    bulletX -= if(stop) 0 else 10
                }

                KeyCodes.RIGHT -> {
                    tankX+=10

                    if(!stop)
                        bulletX += 10
                }

                KeyCodes.SPACE -> {
                    bulletSpeed = 15
                    stop = true
                }

                'R'.toInt() -> {
                    bulletX = tankX
                    bulletY = tankY
                    bulletSpeed = 0
                    stop = false
                }
            }
        }

        if (tankX >= wnd.width-1)
            tankX = 2

        if (bulletX >= wnd.width-1)
            bulletX = 2

        if (tankX <= 0)
            tankX = wnd.width-1

        if (bulletX <= 1)
            bulletX = wnd.width-1

        bulletY -= bulletSpeed

        gc.drawRect(blockX, blockY,40,20, fill = true)
        blockY += blockSpeed + 10

        if(blockY <= 5) {
            blockY = 20
            blockX = Random.nextInt(8, 500)
            blockSpeed += 1
        }

        //Tank 1
        if (blockY >= wnd.height)
            break

        //TANK
        gc.color = Pal16.brightRed
        gc.drawRect(tankX,tankY,34,34,fill = true)
        //bullet
        gc.color = Pal16.green
        gc.drawRect(bulletX,bulletY,14,24,fill = true)
        //bullet stop when reaches the top
        if (bulletY<=20) {
            bulletY= tankY
            stop = false
            bulletX = tankX
            bulletSpeed = 0
        }
        //SCORE BOARD
        gc.drawText(20,300,"Score: $score")

        if (
            blockX + 40 <= bulletX &&
            blockY - 40 >= bulletX &&
            blockY - 20 <= bulletY &&
            blockY + 20 >= bulletY
        ) {
            score += 1
            blockY = -10
        }

        if (score>=5 && blockY<=5)
            blockX = Random.nextInt(8, 500)

        //END
        gc.close()
        sleep (ms = 80)
    }
}