package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    var PLAYER = true
    var TURN_COUNT = 0

    var boardstatus= Array(3){IntArray(3)}

    lateinit var board: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )

        for (i in board){
            for (button1 in i){
                button1.setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        resetbtn.setOnClickListener {
            PLAYER = true
            TURN_COUNT= 0
            initializeBoardStatus()
        }
    }

    private fun initializeBoardStatus() {
        for (i in 0..2){
            for(j in 0..2){
                boardstatus[i][j] = -1
            }
        }

        for(i in board){
            for(button in i){
                button.isEnabled=true
                button.text=""
            }
        }
    }

    override fun onClick(v: View?) {
       when(v?.id){
           R.id.button1 ->{
               updatevalue(row = 0,col = 0,player = PLAYER)
           }
           R.id.button2 ->{
               updatevalue(row = 0,col = 1,player = PLAYER)
           }
           R.id.button3 ->{
               updatevalue(row = 0,col = 2,player = PLAYER)
           }
           R.id.button4 ->{
               updatevalue(row = 1,col = 0,player = PLAYER)
           }
           R.id.button5 ->{
               updatevalue(row = 1,col = 1,player = PLAYER)
           }
           R.id.button6 ->{
               updatevalue(row = 1,col = 2,player = PLAYER)
           }
           R.id.button7 ->{
               updatevalue(row = 2,col = 0,player = PLAYER)
           }
           R.id.button8 ->{
               updatevalue(row = 2,col = 1,player = PLAYER)
           }
           R.id.button9 ->{
               updatevalue(row = 2,col = 2,player = PLAYER)
           }
       }
        TURN_COUNT++
        PLAYER = !PLAYER
        if(PLAYER){
            updateDisplay("Player X Turn")
        }else{
            updateDisplay("Player O Turn")
        }

        if (TURN_COUNT == 9){
            updateDisplay("Game Draw")
        }

        checkWinner()
    }

    private fun checkWinner() {
        for(i in 0..2){
            if(boardstatus[i][0] ==boardstatus[i][1] && boardstatus[i][0] ==boardstatus[i][2]){
                if(boardstatus[i][0] == 1){
                    updateDisplay("Player X Winner")
                    break
                }else if(boardstatus[i][0] == 0){
                    updateDisplay("Player O Winner")
                    break
                }
            }
        }

        for(i in 0..2){
            if(boardstatus[0][i] ==boardstatus[1][i] && boardstatus[0][i] ==boardstatus[2][i]){
                if(boardstatus[0][i] == 1){
                    updateDisplay("Player X Winner")
                    break
                }else if(boardstatus[0][i] == 0){
                    updateDisplay("Player O Winner")
                    break
                }
            }
        }

        if(boardstatus[0][0] ==boardstatus[1][1] && boardstatus[0][0] ==boardstatus[2][2]){
            if(boardstatus[0][0] == 1){
                updateDisplay("Player X Winner")
            }else if(boardstatus[0][0] == 0){
                updateDisplay("Player O Winner")
            }
        }

        if(boardstatus[0][2] ==boardstatus[1][1] && boardstatus[0][2] ==boardstatus[2][0]){
            if(boardstatus[0][2] == 1){
                updateDisplay("Player X Winner")
            }else if(boardstatus[0][2] == 0){
                updateDisplay("Player O Winner")
            }
        }
    }

    private fun updateDisplay(text: String) {
        displytv.text= text
        if (text.contains("Winner")){
            disableButton()
        }

    }

    private fun disableButton(){
        for (i in board){
            for (button1 in i){
                button1.isEnabled = false
            }
        }
    }

    private fun updatevalue(row: Int, col: Int, player: Boolean) {
        val text = if (player) "X" else "O"
        val value = if (player) 1 else 0

        board[row][col].apply {
            isEnabled = false
            setText(text)
        }

        boardstatus[row][col] = value

    }
}