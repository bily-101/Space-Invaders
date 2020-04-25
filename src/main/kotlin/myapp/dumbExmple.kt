package myapp

import com.anysolo.toyGraphics.*
import kotlin.random.Random


data class Info(var x: Int, var y: Int)


fun main () {


    var enemyBlockX = Random.nextInt(30, 500)
    var enemyBlockY = 5

    val wnd = Window(900, 500, Pal16.white, buffered = true)
    val keyboard = Keyboard(wnd)

    var y = wnd.height - 10
    var x = wnd.width/2


    val bullets = mutableListOf<Info>()

    while (true) {
        // Keyboard
        val key = keyboard.getPressedKey()
        if (key != null) {
            when (key.code) {
                KeyCodes.LEFT -> {
                    x-=10
                }
                KeyCodes.RIGHT -> {
                x+=10

                }

                KeyCodes.SPACE -> {
                    y = wnd.height -10
                    bullets.add(Info(x, y))
                }
            }
        }

        // Calculations of bullet moving
        for (bullet in bullets) {
            y = bullet.y
            bullet.y -= 1
        }

        // Drawing STARTS

        val g = Graphics(wnd)

        g.clear()
        for (bullet in bullets) {
            g.color = Pal16.blue
            g.drawRect(bullet.x, bullet.y, 10, 40, fill = true)
        }


        //Tank
        g.color = Pal16.brown
        g.drawRect(x,wnd.height -10,30,50)


        //BOMB
        g.color = Pal16.red
        g.drawRect(enemyBlockX,enemyBlockY,20,30,fill = true)



        var score = 0

        //CONDITION FOR HITTING BULLET


        if (
                enemyBlockX - 40 <= x &&
                enemyBlockX + 40 >= x &&
                enemyBlockY - 20 <= y &&
                enemyBlockY + 20 >= y
        ) {
            enemyBlockY= -10
            enemyBlockX= Random.nextInt(30, 500)
            score=1
        }

        //draw score
        g.drawText(10,wnd.height/2,"Score: $score")
        //ends game
        if (enemyBlockY>= wnd.height)
            break

        //FALLING BOMB
        enemyBlockY+=2



        //DRAWING ENDS
        g.close()

        sleep(10)
    }
}
