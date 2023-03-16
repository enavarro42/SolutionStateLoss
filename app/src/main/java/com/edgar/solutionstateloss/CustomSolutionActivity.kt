package com.edgar.solutionstateloss

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class CustomSolutionActivity : AppCompatActivity() {

    //Variable booleana para marcar si la transacción es segura
    private var isTransactionSafe = false

    //Variable booleana para marcar si hay alguna transacción pendiente
    private var isTransactionPending = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_solution)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        commitFragment()
    }

    /*
    onPostResume es llamado solo cuando el estado de la actividad es completamente restaurado. En este caso
    ponemos nuestra variable booleana a true. Indicando que la transacción es segura ahora
    */
    override fun onPostResume() {
        super.onPostResume()
        isTransactionSafe = true
        // Aquí, una vez restaurada la actividad, comprobamos si hay alguna transacción pendiente desde la última restauración
        if (isTransactionPending) {
            commitFragment()
        }
    }


    /*
    onPause es llamado justo antes de que la actividad pase a segundo plano y también antes de onSaveInstanceState. En este
    punto marcaremos la transacción como insegura
    */
    override fun onPause() {
        super.onPause()
        isTransactionSafe = false
    }

    private fun commitFragment() {
        if (isTransactionSafe) {
            val helloFragment = HelloFragment()
            val fragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.frameLayout, helloFragment)
            fragmentTransaction.commit()
            isTransactionPending = false
        } else {
            /*
            Si no se realiza ninguna transacción porque la actividad está en segundo plano.
            Establecemos la variable isTransactionPending a true para poder retomarla cuando volvamos al primer plano
            */
            isTransactionPending = true
        }
    }
}