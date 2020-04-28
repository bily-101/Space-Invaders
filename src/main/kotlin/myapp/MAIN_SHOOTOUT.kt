

import com.anysolo.toyGraphics.*
import kotlin.random.Random


data class Info(var x: Int, var y: Int)


fun main () {


    var score = 0

    var enemyBlockX = Random.nextInt(30, 500)
    var enemyBlockY = 5

    var bomb = Info(enemyBlockX,enemyBlockY)

    val wnd = Window(900, 500, Pal16.white, buffered = true)
    val keyboard = Keyboard(wnd)

    var y = wnd.height - 10
    var x = wnd.width/2



    var bombs = mutableListOf<Info>()

    val bullets = mutableListOf<Info>()
    var start = false


    bombs.add(bomb)
    while (true) {
        // Keyboar
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
            g.drawOval(bullet.x, bullet.y, 10, 20, fill = true)
        }






        //Tank
        g.color = Pal16.brown
        g.drawRect(x,wnd.height -10,30,50)

   for (b in bombs) {
    b.y ++
   }


        //BOMB
        for(b in bombs) {
            g.color = Pal16.red
            g.drawRect(b.x, b.y, 20, 30, fill = true)
        }




        //CONDITION FOR HITTING BULLET l


        if (
                bomb.x - 40 <= x &&
                bomb.x + 40 >= x &&
                bomb.y - 20 <= y &&
                bomb.y + 20 >= y
        ) {
            bomb.y  = 10
            bomb.x= Random.nextInt(30, 500)
            score += 1
        }



        //draw score
        g.drawText(10,wnd.height/2,"Score: $score")



        if (score>=5) {

        }


        //if it goes off screen

        if (x >= wnd.width)
            x = 2

        if (x >= wnd.width)
            x = 2

        if (x <= 0)
            x = wnd.width-1

        if (x<= 1)
            x= wnd.width-1

        //ends game
        if (bomb.y>= wnd.height)
            break

        //FALLING BOMB




        //DRAWING ENDS
        g.close()

        sleep(10)
    }
}