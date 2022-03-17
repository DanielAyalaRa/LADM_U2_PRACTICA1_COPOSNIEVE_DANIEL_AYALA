package mx.edu.ittepic.daar.ladm_u2_practica1_coposnieve

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.View
import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext

class Lienzo(p:MainActivity) : View(p) {
    var nieves = Array<Copo>(800, {Copo(this) }) //invocación del constructor
    var leve = 30
    var intermedio = 200
    var contador = 0

    val scope = CoroutineScope(Job() + Dispatchers.Main)
    val nevada = scope.launch(EmptyCoroutineContext, CoroutineStart.LAZY) {
        while (true) {
            (0..nieves.size-1).forEach {
                nieves[it].moverCopo()
            }
            contador++
            p.runOnUiThread {
                invalidate()
            }
            delay(30L)
        }
    }

    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        var p = Paint()

        // Color Fondo
        c.drawColor(Color.rgb(197, 219, 232))

        // Montañas
        p.color = Color.WHITE
        c.drawOval(-200f, 550f, 1000f, 1300f, p)
        c.drawOval(500f, 450f, 1750f, 1250f, p)
        c.drawOval(1400f, 475f, 2400f, 900f, p)

        //Arboles
        p.color = Color.rgb(180, 114, 20)
        c.drawRect(100f, 500f, 180f, 660f, p)
        p.color = Color.rgb(1, 134, 103) // color verde oscuro
        c.drawOval(30f, 350f, 240f, 450f, p)
        c.drawOval(30f, 410f, 240f, 520f, p)
        c.drawOval(30f, 480f, 240f, 580f, p)

        p.color = Color.rgb(180, 114, 20)
        c.drawRect(825f, 400f, 885f, 560f, p)
        p.color = Color.rgb(1, 134, 103) // color verde oscuro
        c.drawOval(775f, 250f, 935f, 350f, p)
        c.drawOval(775f, 310f, 935f, 420f, p)
        c.drawOval(775f, 380f, 935f, 480f, p)

        p.color = Color.rgb(180, 114, 20)
        c.drawRect(2000f, 620f, 2400f, 960f, p)
        p.color = Color.rgb(1, 134, 103) // color verde oscuro
        c.drawOval(1900f, 220f, 2500f, 500f, p)
        c.drawOval(1900f, 320f, 2500f, 650f, p)
        c.drawOval(1900f, 420f, 2500f, 750f, p)

        // Casa
        p.color = Color.rgb(180, 124, 75) // color madera
        c.drawRect(1100f, 400f, 1500f, 550f, p)
        p.color = Color.YELLOW
        c.drawRect(1140f, 470f, 1200f, 550f, p)
        p.color = Color.rgb(248, 225, 159) // color calido
        c.drawRect(1240f, 445f, 1450f, 510f, p)
        p.color = Color.rgb(86, 29, 24) // Color rojillo
        var path = Path()
        path.moveTo(1065f, 410f)
        path.lineTo(1535f, 410f)
        path.lineTo(1500f, 310f)
        path.lineTo(1100f, 310f)
        path.lineTo(1065f, 410f)
        c.drawPath(path, p)
        p.color = Color.rgb(162, 61, 1) // color chimenea
        c.drawRect(1150f, 275f, 1220f, 360f, p)
        p.color = Color.LTGRAY
        c.drawOval(1160f, 235f, 1210f, 265f, p)
        c.drawOval(1190f, 170f, 1270f, 230f, p)
        c.drawOval(1285f, 150f, 1400f, 220f, p)

        //Copos de nieve
        nevada.start()

        ejecutar(c)
    }

    fun ejecutar(c:Canvas) {
        if (contador >= 0 && contador < 300) {
            (0..leve).forEach {
                nieves[it].moverCopo()
                nieves[it].pintarCopo(c)
            }
        }
        if (contador > 300 && contador < 600) {
            (0..intermedio).forEach {
                nieves[it].moverCopo()
                nieves[it].pintarCopo(c)
            }
        }
        if (contador > 600 && contador < 1100) {
            (0..nieves.size-1).forEach {
                nieves[it].moverCopo()
                nieves[it].pintarCopo(c)
            }
        }
        if (contador == 1100){
            contador = 1
        }
    }
}