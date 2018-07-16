package bapspatil.randomite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.longToast
import java.lang.Math.abs
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var random: Random
    private var turn: Int? = PLAYER_ONE_TURN
    private var prevValue: Int? = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)
        random = Random()
        mainToolbar.title = ""

        randomize_iv.setOnClickListener {
            randomize()
        }

        reset_iv.setOnClickListener {
            player1_tv.text = "0"
            player2_tv.text = "0"
            random_tv.text = "0"
            prevValue = -1
            turn = PLAYER_ONE_TURN
            player1_iv.imageResource = R.drawable.player1_outline
            player2_iv.imageResource = R.drawable.player2
            longToast("Game reset! Player 1, it's your turn!")
        }
    }

    private fun randomize() {
        val randomNumber = random.nextInt(50) + 1
        random_tv.text = randomNumber.toString()
        if(abs(prevValue!!.minus(randomNumber)) <= 5) {
            if (turn == PLAYER_ONE_TURN) {
                snackbar(rootLinear, "Player 1 Wins!").show()
                val player1Score = player1_tv.text.toString().toInt()
                player1_tv.text = (player1Score + 1).toString()
            }
            else {
                snackbar(rootLinear, "Player 2 Wins!").show()
                val player2Score = player2_tv.text.toString().toInt()
                player2_tv.text = (player2Score + 1).toString()
            }
        }
        prevValue = randomNumber
        if(turn == PLAYER_ONE_TURN) {
            turn = PLAYER_TWO_TURN
            player1_iv.imageResource = R.drawable.player1
            player2_iv.imageResource = R.drawable.player2_outline
        } else {
            turn = PLAYER_ONE_TURN
            player1_iv.imageResource = R.drawable.player1_outline
            player2_iv.imageResource = R.drawable.player2
        }

    }

    companion object {
        const val PLAYER_ONE_TURN = 0
        const val PLAYER_TWO_TURN = 1
    }
}
