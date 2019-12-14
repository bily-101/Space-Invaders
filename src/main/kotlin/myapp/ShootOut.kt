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
    var wnd = Window(1000, 700, buffered = true, background = Pal16.blue)
    val keyboard = Keyboard(wnd)

    var enemyBlockX =  Random.nextInt(30, 500)
    var enemyBlockY = 40

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
            gc.drawRect(wnd.height, wnd.width, wnd.height, wnd.width, fill = true)
        }

        if (score >= 10) {
            blockSpeed = 15
            gc.color = Pal16.darkGray
            gc.drawRect(wnd.height, wnd.width, wnd.height, wnd.width)
        }

        // Key codes
        while (true) {
            val key = keyboard.getPressedKey() ?: break

            when (key.code) {
                KeyCodes.LEFT -> {
                    tankX -= 10

                    if(!stop)
                        bulletX -= 10
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

        gc.drawRect(enemyBlockX, enemyBlockY,40,20, fill = true)
        enemyBlockY += blockSpeed + 10

        if(enemyBlockY <= 5) {
            enemyBlockY = 20
            enemyBlockX = Random.nextInt(8, 500)
            blockSpeed += 1
        }

        //Tank 1
        if (enemyBlockY >= wnd.height)
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

        // Score board
        gc.drawText(20,300,"Score: $score")

        if (
            enemyBlockX - 40 <= bulletX &&
            enemyBlockY + 40 >= bulletX &&
            enemyBlockY - 20 <= bulletY &&
            enemyBlockY + 20 >= bulletY
        ) {
            score += 1
            enemyBlockY = -10
        }

        if (score>=5 && enemyBlockY<=5)
            enemyBlockX = Random.nextInt(8, 500)

        // End of the frame
        gc.close()
        sleep (ms = 80)
    }
}
