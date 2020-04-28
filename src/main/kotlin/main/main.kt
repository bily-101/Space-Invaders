package main


import com.anysolo.toyGraphics.*

data class Object(var x : Int, var y: Int, var Image : Image)

data class Oo(var x: Int, var y : Int)

fun main () {
    val wnd = Window(1000, 1000, Pal16.black, buffered = true)

    val background = Image("graphics/images.jpg")

    val spaceInvader = Object(20, 50, Image("graphics/lol.jpg"))


    val invaders = mutableListOf<Object>()

    val keyboard = Keyboard(wnd)

    var t = 0

    var bt = 0

    var bbt = 0

    val invaderBullet = Oo(spaceInvader.x, spaceInvader.y)
    val invaderBullets = mutableListOf<Oo>()

    val tank = Oo(wnd.width / 2, wnd.height / 2 + 30)


    val bullet = Oo(wnd.width / 2 + 29, wnd.height / 2 - 40)

    val bullets = mutableListOf<Oo>()

    while (true) {

        invaderBullet.x = spaceInvader.x
        val key = keyboard.getPressedKey()
        if (key != null) {
            when (key.code) {
                KeyCodes.LEFT -> {
                    bullet.x -= 10
                    tank.x -= 10
                }
                KeyCodes.RIGHT -> {
                    bullet.x += 10
                    tank.x += 10
                }


                KeyCodes.SPACE -> {
                    bullets.add(Oo(bullet.x, wnd.height / 2))
                }
            }
        }
        for (b in invaders) {
            b.x += .1.toInt()
        }

        for (b in invaderBullets) {
            if (bbt<20)
                b.x = spaceInvader.x
            b.y++
        }

        bbt++

        for (b in bullets) {
            b.y -= 5
        }

        val g = Graphics(wnd)





        g.clear()

        if (t <= 500) {
            g.drawImage(wnd.width - 730, wnd.height - 700, background)
            g.color = Pal16.white
            g.drawText(wnd.width / 2 - 40, wnd.height / 2 - 49, "Starting in 5 seconds")

        } else {
            invaders.add(Object(spaceInvader.x, spaceInvader.y, Image("graphics/lol.jpg")))
            if (spaceInvader.x > wnd.width) {
                spaceInvader.x = 0
                spaceInvader.x -= 0
                spaceInvader.y += 10
            }

            for (b in bullets) {
                g.color = Pal16.white
                g.drawRect(b.x, b.y, 10, 20, fill = true)
            }

            if (bt > 500)
                bt = 0
            if (bt == 199)
                invaderBullets.add(Oo(invaderBullet.x, invaderBullet.y))

            bt++



            for (b in invaders) {
                g.drawImage(b.x, b.y, b.Image)
            }

            for (b in invaderBullets)
                g.drawRect(b.x, b.y, 20, 30, fill = true)

            if (spaceInvader.x >= 0)
                spaceInvader.x += 3
            else
                spaceInvader.x = 4






            g.color = Pal16.green
            g.run {
                this.drawRect(
                        x = tank.x,
                        y = tank.y,
                        width = 70,
                        height = 30,
                        fill = true
                )
                drawRect(x = tank.x + 29, y = tank.y - 40, width = 10, height = 40)
            }

        }
        t++

        g.close()
        sleep(10)
    }

}