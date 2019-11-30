package myapp
import com.anysolo.toyGraphics.*
import kotlin.random.Random
/*
*Made by bily
* Left key to go left
* right key to go right
* space to shoot
* Have fun
*  */
fun main () {
    var wnd = Window(1000, 1000, buffered = true, background = Pal16.blue)
    val keyboard = Keyboard(wnd)

    var ax =  Random.nextInt(30, 500)
    var ay = 40

    var x = 365
    var y = 665
    var color = 0
    var rx = x
    var ry = y
    var bulletSpeed = 0
    var stop = false
    var blockSpeed = 0
    var score = 0

    while (true) {
        val gc = Graphics(wnd)
        gc.clear()
        gc.color = Pal16[color]

        if (score in 5..10) {
            blockSpeed = 15
            gc.color = Pal16.brightYellow
            gc.drawRect(wnd.height,wnd.width,wnd.height,wnd.width)
        }
        if (score>=10) {
            blockSpeed = 18
            gc.color = Pal16.darkGray
            gc.drawRect(wnd.height,wnd.width,wnd.height,wnd.width)
        }
        // Key codes
        while (true) {
            val key = keyboard.getPressedKey() ?: break
            when (key.code) {
                KeyCodes.LEFT -> {
                    x-=10
                    if (stop) {
                        rx += 0
                    }else{
                        rx-=10
                    }
                }

                KeyCodes.RIGHT -> {

                    x+=10

                    rx += if (stop) {
                        0
                    }else{
                        10
                    }

                }

                KeyCodes.SPACE -> {
                    bulletSpeed = 15
                    stop = true
                }

                'R'.toInt() -> {
                    rx = x
                    ry = y
                    bulletSpeed = 0
                    stop = false
                }
            }
        }
        if (x>=699)
            x = 2

        if (rx>=699)
            rx=2

        if (x<=1)
            x = 688

        if (rx<=1)
            rx = 688

        ry -= bulletSpeed

        gc.drawRect(ax,ay,40,20,fill = true)
        ay+=blockSpeed+10

        if(ay<=5) {
            ay = 20
            ax = Random.nextInt(8, 500)
            blockSpeed =  + 1
        }

        //Tank 1
        if (ay>=700) {
            break
        }
        //TANK
        gc.color = Pal16.brightRed
        gc.drawRect(x,y,34,34,fill = true)
        //bullet
        gc.color = Pal16.green
        gc.drawRect(rx,ry,14,24,fill = true)
        //bullet stop when reaches the top
        if (ry<=20) {
            ry = y
            stop=false
            rx = x
            bulletSpeed = 0
        }
        //SCORE BOARD
        gc.drawText(20,300,"Score: $score")

        if (ax-35<=rx && ax+35>=rx && ay-20<=ry && ay+20>=ry) {
            score+=1
            ay = -10
        }


        if (score>=5 && ay<=5)
            ax = Random.nextInt(8, 500)

        if (blockSpeed==-25 && ay<=0) {
            blockSpeed = 0
            blockSpeed += 8
        }
        //END
        gc.close()
        sleep (ms = 80)
    }
}