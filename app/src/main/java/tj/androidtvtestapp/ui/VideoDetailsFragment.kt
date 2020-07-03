package tj.androidtvtestapp.ui

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.leanback.app.DetailsFragment
import androidx.leanback.app.DetailsFragmentBackgroundController
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ClassPresenterSelector
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.Row
import androidx.leanback.widget.RowPresenter

import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import tj.androidtvtestapp.presenter.CardPresenter
import tj.androidtvtestapp.R
import tj.androidtvtestapp.model.SampleSportList
import tj.androidtvtestapp.model.Sport

/**
 * A wrapper fragment for leanback details screens.
 * It shows a detailed view of video and its metadata plus related videos.
 */
class VideoDetailsFragment : DetailsFragment() {

    private var mSelectedSport: Sport? = null

    private lateinit var mDetailsBackground: DetailsFragmentBackgroundController
    private lateinit var mPresenterSelector: ClassPresenterSelector
    private lateinit var mAdapter: ArrayObjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDetailsBackground = DetailsFragmentBackgroundController(this)
        mPresenterSelector = ClassPresenterSelector()
        mAdapter = ArrayObjectAdapter(mPresenterSelector)
        setupRelatedMovieListRow()
        adapter = mAdapter
        initializeBackground(mSelectedSport)
        onItemViewClickedListener = ItemViewClickedListener()
    }

    private fun initializeBackground(sport: Sport?) {
        mDetailsBackground.enableParallax()
        Glide.with(activity)
            .load(sport?.backgroundImageUrl)
            .asBitmap()
            .centerCrop()
            .error(R.drawable.default_background)
            .into<SimpleTarget<Bitmap>>(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(
                    bitmap: Bitmap,
                    glideAnimation: GlideAnimation<in Bitmap>
                ) {
                    mDetailsBackground.coverBitmap = bitmap
                    mAdapter.notifyArrayItemRangeChanged(0, mAdapter.size())
                }
            })
    }

    private fun setupRelatedMovieListRow() {
        val subcategories = arrayOf(getString(R.string.sport_videos))
        val list = SampleSportList.list
        mSelectedSport = list[1]
        val listRowAdapter = ArrayObjectAdapter(CardPresenter())
        for (j in 0 until NUM_COLS) {
            listRowAdapter.add(list[j % 4])
        }

        val header = HeaderItem(0, subcategories[0])
        mAdapter.add(ListRow(header, listRowAdapter))
        mPresenterSelector.addClassPresenter(ListRow::class.java, ListRowPresenter())

    }

    private inner class ItemViewClickedListener : OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder,
            row: Row
        ) {
            if (item is Sport) {
                if (mSelectedSport == item) {
                    val intent = Intent(activity, PlaybackActivity::class.java)
                    intent.putExtra(DetailsActivity.ARG_SPORT, item)
                    startActivity(intent)
                }
            }
        }
    }

    companion object {
        private const val TAG = "VideoDetailsFragment"
        private const val NUM_COLS = 4
    }
}
