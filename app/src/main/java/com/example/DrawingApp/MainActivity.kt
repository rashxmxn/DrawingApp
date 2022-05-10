package com.example.DrawingApp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.example.DrawingApp.R
import com.example.drawingapp.DrawingView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var mImageButtonCurrentPaint: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawing_view = findViewById(R.id.drawing_view) as DrawingView
        drawing_view.setSizeForBrush(20.toFloat())
        val ib_brush = findViewById(R.id.ib_brush) as ImageButton
        ib_brush.setOnClickListener {
            showBrushSizeChooseDialog()
        }
        mImageButtonCurrentPaint = ll_paint_colors[1] as ImageButton
        mImageButtonCurrentPaint!!.setImageDrawable(
            ContextCompat.getDrawable(
                this, R.drawable.pallet_pressed
            )
        )

        ib_undo.setOnClickListener {
            drawing_view.onClickUndo()
        }

        ib_eraser.setOnClickListener {
            val colorTag = ib_eraser.tag.toString()
            drawing_view.setColor(colorTag)
        }

    }

    private fun showBrushSizeChooseDialog() {
        val brushDialog = Dialog(this)
        val drawing_view = findViewById(R.id.drawing_view) as DrawingView
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Brush size: ")
        val smallBtn = brushDialog.findViewById<AppCompatImageButton>(R.id.ib_small_brush)
        val mediumBtn = brushDialog.findViewById<AppCompatImageButton>(R.id.ib_medium_brush)
        val largeBtn = brushDialog.findViewById<AppCompatImageButton>(R.id.ib_large_brush)
        smallBtn.setOnClickListener {
            drawing_view.setSizeForBrush(10.toFloat())
            brushDialog.dismiss()
        }
        mediumBtn.setOnClickListener {
            drawing_view.setSizeForBrush(20.toFloat())
            brushDialog.dismiss()
        }
        largeBtn.setOnClickListener {
            drawing_view.setSizeForBrush(30.toFloat())
            brushDialog.dismiss()
        }
        brushDialog.show()
    }

    fun paintClicked(view: View) {
        if (view !== mImageButtonCurrentPaint) {
            val imageButton = view as ImageButton
            val colorTag = imageButton.tag.toString()
            drawing_view.setColor(colorTag)
            imageButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.pallet_pressed
                )
            )
            mImageButtonCurrentPaint!!.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.pallet_normal
                )
            )
            mImageButtonCurrentPaint = view
        }
    }
}