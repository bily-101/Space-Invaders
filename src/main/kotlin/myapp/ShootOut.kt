package myapp
import com.anysolo.toyGraphics.*
import kotlin.random.Random

var tankX = 365
val tankY = 665
var bulletX = tankX
var bulletY = tankY

/*
* Author: bily 2019
* Left key to go left
* right key to go right
* space to shoot
* Have fun
*/

data class Bloxs(var x: Int,var y: Int)



var blocks = mutableListOf<Bloxs>()


var bullets = mutableListOf<Bloxs>()

var blockSpeed = 0
fun level (score:Int, gc : Graphics, wnd : Window) {
    gc.color = Pal16.black

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

}

fun main () {
    val wnd = Window(1000, 700, buffered = true, background = Pal16.blue)

    val keyboard = Keyboard(wnd)
    var bulletSpeed = 0
    var stop = false
    var startBullet = Bloxs(tankX,tankY)
    var bullet = Bloxs(bulletX,bulletY)

    var score = 0


    var enemyBlock = Bloxs(Random.nextInt(30, 500),40)

    while (true) {
        val gc = Graphics(wnd)
        gc.clear()

        for (b in bullets) {
            gc.color = Pal16.green
            gc.drawRect( bullet.x,bullet.y, 14, 24, fill = true)
            if (startBullet.y == bullet.y) {
                startBullet.y = tankY
            }

        }

        level(score,gc,wnd)
        // Key codes

        while(true) {
            val key = keyboard.getPressedKey() ?: break

            when (key.code) {
                KeyCodes.LEFT -> {

                    tankX -= 10
                    if(!stop) {
                        bullet.x -=10
                    }else{
                        bullet.x += 0
                    }
                    }

                KeyCodes.RIGHT -> {
                    tankX+=10

                    if(!stop) {
                        bullet.x += 10
                    }else{
                        bullet.x+=0
                    }
                }

                KeyCodes.SPACE -> {
                    bulletSpeed = 15
                    stop = true

                    bullets.add(bullet)

                }

                'R'.toInt() -> {


                }
            }
        }


        for (b in bullets) {
            b.y -= 1
        }


        for (b in bullets) {
            gc.drawRect(b.x, b.y, 40, 20, fill = true)

        }


        if(enemyBlock.y <= 5) {
            enemyBlock.y = 20
            enemyBlock.x = Random.nextInt(8, 500)
            blockSpeed += 1
        }

        //Tank 1
        if (enemyBlock.y >= wnd.height) {
            startBullet.y -= bulletSpeed
            break
        }

        bullet.y -= bulletSpeed

        //TANK
        gc.color = Pal16.brightRed
        gc.drawRect(tankX,tankY,34,34,fill = true)

        //bullet



        //bullet stop when reaches the top
        if (bullet.y<=20) {
            bullet.y= tankY
            stop = false
            bullet.x = tankX
            bulletSpeed = 0
        }

        // Score board
        gc.color = Pal16.white
        gc.drawText(20,300,"Score: $score")





        //IMPORTANT



        //END OF IMPORTANT






        if (score>=5 && enemyBlock.y<=5)
            enemyBlock.x = Random.nextInt(8, 500)

        // End of the frame
        gc.close()
        sleep (ms = 80)
    }
}