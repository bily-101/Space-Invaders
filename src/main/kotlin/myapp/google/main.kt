package myapp.google

import com.anysolo.toyGraphics.*

class Item (var x: Int,var y : Int)

fun main () {
    val wnd = Window(1000, 300, Pal16.white, buffered = true)
    val keyboard = Keyboard(wnd)

    var dragon = Item(40, 280)
    var bird = Item(wnd.width - 10, 230)
    var cactus = Item(wnd.width-20,wnd.height-30)

    while (true) {
        val g = Graphics(wnd)
        g.clear()
        drawDragon(g, dragon.x, dragon.y)
        drawBird(g, bird.x, bird.y)
        drawCactus(cactus.x,cactus.y,g)


        while (true) {
            // Keyboard
            val key = keyboard.getPressedKey()
            if (key != null) {
                when (key.code) {
                    KeyCodes.SPACE -> {
                    dragon.y += 20

                    }
                }
            }


            if (dragon.x != 40)
                dragon.x--

            g.close()
            sleep(10)
        }
    }
}