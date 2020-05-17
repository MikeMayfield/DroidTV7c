package com.nds.droidtv.common.views

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.nds.droidtv2.R


class SeriesPosterCustomView @JvmOverloads constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int = 0) :
    androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {

    var placeholderUrl: String
    var posterUrl: String
    var isFavorite: Boolean
    var recordingStatus: Int
    var isPlayable: Boolean
    var nextPlayableEpisodeId: Int
    var castingIsEnabled: Boolean
    var numberOfRecordings: Int
    var numberOfPlayableRecordings: Int

    init {
        val typArray = context.obtainStyledAttributes(attrs, R.styleable.SeriesPosterCustomView)
        placeholderUrl = typArray.getString(R.styleable.SeriesPosterCustomView_placeholderUrl).toString()
        posterUrl = typArray.getString(R.styleable.SeriesPosterCustomView_posterUrl).toString()
        isFavorite = typArray.getBoolean(R.styleable.SeriesPosterCustomView_isFavorite, false)
        recordingStatus = typArray.getInt(R.styleable.SeriesPosterCustomView_recordingStatus, 0)
        isPlayable = typArray.getBoolean(R.styleable.SeriesPosterCustomView_isPlayable, false)
        nextPlayableEpisodeId = typArray.getInt(R.styleable.SeriesPosterCustomView_nextPlayableEpisodeId, 0)
        castingIsEnabled = typArray.getBoolean(R.styleable.SeriesPosterCustomView_castingIsEnabled, false)
        numberOfRecordings = typArray.getInt(R.styleable.SeriesPosterCustomView_numberOfRecordings, 0)
        numberOfPlayableRecordings = typArray.getInt(R.styleable.SeriesPosterCustomView_numberOfPlayableRecordings, 0)
        typArray.recycle()
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        Glide.with(context)
            .load(posterUrl)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(R.drawable.episode_thumbnail_placeholder)
            .into(this)

        val w = width
        val h = height
        val paint = Paint()
        val res = resources

        drawFavoriteIcon(res, canvas, paint, dpToPix(4), dpToPix(4))
        drawNumberOfRecordingsIcon(res, canvas, paint, dpToPix(4), h - dpToPix(28))
        drawNumberOfPlayableRecordingsIcon(res, canvas, paint, w - dpToPix(28), h - dpToPix(28))
        drawRecordingStatusIcon(res, canvas, paint, w - dpToPix(28), dpToPix(4))

    }

    private fun drawRecordingStatusIcon(res: Resources, canvas: Canvas?, paint: Paint, left: Int, top: Int) {
        if (recordingStatus == 1) drawWithCanvas(res, R.drawable.poster_status_overlay_1, canvas, paint, left.toFloat(), top.toFloat())
    }

    private fun drawFavoriteIcon(res: Resources, canvas: Canvas?, paint: Paint, left: Int, top: Int) {
        if (isFavorite) {
            drawWithCanvas(res, R.drawable.poster_favorite_overlay, canvas, paint, left.toFloat(), top.toFloat())
        }
    }

    private fun drawNumberOfRecordingsIcon(res: Resources, canvas: Canvas?, paint: Paint, left: Int, top: Int) {
        when (numberOfRecordings) {
            0 -> drawWithCanvas(res, R.drawable.poster_recording_count_0, canvas, paint, left.toFloat(), top.toFloat())
            1 -> drawWithCanvas(res, R.drawable.poster_recording_count_1, canvas, paint, left.toFloat(), top.toFloat())
            2 -> drawWithCanvas(res, R.drawable.poster_recording_count_2, canvas, paint, left.toFloat(), top.toFloat())
            3 -> drawWithCanvas(res, R.drawable.poster_recording_count_3, canvas, paint, left.toFloat(), top.toFloat())
            4 -> drawWithCanvas(res, R.drawable.poster_recording_count_4, canvas, paint, left.toFloat(), top.toFloat())
            5 -> drawWithCanvas(res, R.drawable.poster_recording_count_5, canvas, paint, left.toFloat(), top.toFloat())
            6 -> drawWithCanvas(res, R.drawable.poster_recording_count_6, canvas, paint, left.toFloat(), top.toFloat())
            7 -> drawWithCanvas(res, R.drawable.poster_recording_count_7, canvas, paint, left.toFloat(), top.toFloat())
            8 -> drawWithCanvas(res, R.drawable.poster_recording_count_8, canvas, paint, left.toFloat(), top.toFloat())
            9 -> drawWithCanvas(res, R.drawable.poster_recording_count_9, canvas, paint, left.toFloat(), top.toFloat())
            else -> drawWithCanvas(res, R.drawable.poster_recording_count_10, canvas, paint, left.toFloat(), top.toFloat())
        }
    }

    private fun drawNumberOfPlayableRecordingsIcon(res: Resources, canvas: Canvas?, paint: Paint, left: Int, top: Int) {
        when (numberOfPlayableRecordings) {
            0 -> drawWithCanvas(res, R.drawable.poster_playable_count_0, canvas, paint, left.toFloat(), top.toFloat())
            1 -> drawWithCanvas(res, R.drawable.poster_playable_count_1, canvas, paint, left.toFloat(), top.toFloat())
            2 -> drawWithCanvas(res, R.drawable.poster_playable_count_2, canvas, paint, left.toFloat(), top.toFloat())
            3 -> drawWithCanvas(res, R.drawable.poster_playable_count_3, canvas, paint, left.toFloat(), top.toFloat())
            4 -> drawWithCanvas(res, R.drawable.poster_playable_count_4, canvas, paint, left.toFloat(), top.toFloat())
            5 -> drawWithCanvas(res, R.drawable.poster_playable_count_5, canvas, paint, left.toFloat(), top.toFloat())
            6 -> drawWithCanvas(res, R.drawable.poster_playable_count_6, canvas, paint, left.toFloat(), top.toFloat())
            7 -> drawWithCanvas(res, R.drawable.poster_playable_count_7, canvas, paint, left.toFloat(), top.toFloat())
            8 -> drawWithCanvas(res, R.drawable.poster_playable_count_8, canvas, paint, left.toFloat(), top.toFloat())
            9 -> drawWithCanvas(res, R.drawable.poster_playable_count_9, canvas, paint, left.toFloat(), top.toFloat())
            else -> drawWithCanvas(res, R.drawable.poster_playable_count_10, canvas, paint, left.toFloat(), top.toFloat())
        }
    }

    private fun drawWithCanvas(res: Resources, drawable: Int, canvas: Canvas?, paint: Paint, left: Float, top: Float) {
        val posterNumberOfRecordings =
            BitmapFactory.decodeResource(res, drawable)
        canvas?.drawBitmap(Bitmap.createScaledBitmap(posterNumberOfRecordings, dpToPix(24), dpToPix(24), false), left, top, paint)
    }

    private fun dpToPix(dp: Int): Int {
        val scale = resources.displayMetrics.density
        return (scale * dp + 0.5f).toInt()
    }

}