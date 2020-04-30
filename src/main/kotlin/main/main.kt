package myapp.space_Invaders


import com.anysolo.toyGraphics.*

data class Object(var x : Int, var y: Int, var Image : Image)

data class Oo(var x: Int, var y : Int)


var gameTime = 0
var bbt = 0

fun processKeyBoard (keyboard:Keyboard, bullet: Oo, tank: Oo, bullets: MutableList<Oo>, wnd:Window) {
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
}

fun objectCalc (invaders: MutableList<Object>, invaderBullets: MutableList<Oo>, spaceInvader: Object, bullets: MutableList<Oo>, bbt: Int,wnd:Window) {
    for (b in invaders)
        b.x += .1.toInt()

    for (b in invaderBullets) {
        if (bbt<20)
            b.x = spaceInvader.x
        b.y++
    }

    for (b in bullets)
        b.y -= 5

    if (spaceInvader.x > wnd.width) {
        spaceInvader.x = 0
        spaceInvader.x -= 0
        spaceInvader.y += 10
    }
    if (spaceInvader.x >= 0)
        spaceInvader.x += 3
    else
        spaceInvader.x = 4

}

fun drawObjects (invaders: MutableList<Object>, invaderBullets: MutableList<Oo>, g: Graphics, bullets: MutableList<Oo>, tank: Oo) {
    for (b in invaders)
        g.drawImage(b.x, b.y, b.Image)

    for (b in invaderBullets)
        g.drawRect(b.x, b.y, 20, 30, fill = true)


    for (b in bullets) {
        g.color = Pal16.white
        g.drawRect(b.x, b.y, 10, 20, fill = true)
    }
    g.color = Pal16.green
    g.drawRect(x = tank.x, y = tank.y, width = 70, height = 30, fill = true)
    g.drawRect(x = tank.x + 29, y = tank.y - 40, width = 10, height = 40)


}

fun preGame (g: Graphics,wnd: Window) {
    val background = Image("graphics/images.jpg")
    g.drawImage(wnd.width - 730, wnd.height - 700, background)
    g.color = Pal16.white
    g.drawText(wnd.width / 2 - 40, wnd.height / 2 - 49, "Starting in 5 seconds")
}

fun lateGame (
        invaders: MutableList<Object>
        , spaceInvader: Object, invaderBullets: MutableList<Oo>
        , bullets: MutableList<Oo>, g: Graphics, invaderBullet: Oo, tank: Oo
)
{
    invaders.add(Object(spaceInvader.x, spaceInvader.y, Image("graphics/lol.jpg")))
    invaderBullets.add(Oo(invaderBullet.x, invaderBullet.y))

    drawObjects(invaders,invaderBullets,g,bullets,tank)

    if (spaceInvader.x >= 0)
        spaceInvader.x += 3
    else
        spaceInvader.x = 4

}


fun run (
        wnd: Window, g: Graphics, invaderBullets: MutableList<Oo>
        ,invaderBullet: Oo,keyboard: Keyboard,spaceInvader: Object
        ,invaders: MutableList<Object>
)

{
    val bullets = mutableListOf<Oo>()
    val tank = Oo(wnd.width / 2, wnd.height / 2 + 30)
    val bullet = Oo(wnd.width / 2 + 29, wnd.height / 2 - 40)

    processKeyBoard(keyboard,bullet,tank,bullets,wnd)
    objectCalc(invaders,invaderBullets,spaceInvader,bullets,bbt,wnd)
    bbt+=1

    if (gameTime <= 500) {
        preGame(g,wnd)
    } else {
        invaders.add(Object(spaceInvader.x, spaceInvader.y, Image("graphics/lol.jpg")))
        invaderBullets.add(Oo(invaderBullet.x, invaderBullet.y))
        drawObjects(invaders,invaderBullets,g,bullets,tank)
        lateGame(invaders,spaceInvader,invaderBullets,bullets,g,invaderBullet,tank)
    }
    gameTime++
}


fun main () {
    val wnd = Window(1000, 1000, Pal16.black, buffered = true)
    val keyboard = Keyboard(wnd)

    val spaceInvader = Object(20, 50, Image("graphics/lol.jpg"))
    val invaders = mutableListOf<Object>()

    val invaderBullet = Oo(spaceInvader.x, spaceInvader.y)
    val invaderBullets = mutableListOf<Oo>()

    while (true) {
        val g = Graphics(wnd)
        g.clear()

        run(wnd, g, invaderBullets, invaderBullet, keyboard, spaceInvader, invaders)

        g.close()
        sleep(10)
    }
}