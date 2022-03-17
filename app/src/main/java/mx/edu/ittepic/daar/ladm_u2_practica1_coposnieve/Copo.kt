package mx.edu.ittepic.daar.ladm_u2_practica1_coposnieve

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Copo(l:Lienzo) {
    var x = 0f
    var y = 0f
    var tam = 0f

    init {
        x = (Math.random()*2100).toFloat()
        y = ((Math.random()*2600)*-1).toFloat()
        tam = ((Math.random()*10)+5).toFloat()
    }

    fun moverCopo() {
        y += ((Math.random()*3)+5).toFloat()
        if(y>1000) y= ((Math.random()*2600)*-1).toFloat()
    }

    fun pintarCopo(c: Canvas) {
        val p = Paint()
        p.color = Color.WHITE
        c.drawCircle(x,y,tam,p)
    }
}