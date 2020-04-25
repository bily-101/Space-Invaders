package myapp
import com.anysolo.toyGraphics.Window

fun needed1 (wnd: Window) {

    if (tankX >= wnd.width-1)
        tankX = 2

    if (bulletX >= wnd.width-1)
        bulletX = 2

    if (tankX <= 0)
        tankX = wnd.width-1

    if (bulletX <= 1)
        bulletX = wnd.width-1

}