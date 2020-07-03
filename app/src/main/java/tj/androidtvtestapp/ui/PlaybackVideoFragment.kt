package tj.androidtvtestapp.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import androidx.leanback.media.MediaPlayerAdapter
import androidx.leanback.media.PlaybackTransportControlGlue
import androidx.leanback.widget.PlaybackControlsRow
import tj.androidtvtestapp.R
import tj.androidtvtestapp.model.Sport


/** Handles video playback with media controls. */
class PlaybackVideoFragment : VideoSupportFragment() {

    private lateinit var mTransportControlGlue: PlaybackTransportControlGlue<MediaPlayerAdapter>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val (_, title, description, _, _, videoUrl) =
            requireActivity().intent?.getSerializableExtra(DetailsActivity.ARG_SPORT) as Sport

        val glueHost = VideoSupportFragmentGlueHost(this@PlaybackVideoFragment)
        val playerAdapter = MediaPlayerAdapter(requireContext())
        playerAdapter.setRepeatAction(PlaybackControlsRow.RepeatAction.INDEX_ONE)

        mTransportControlGlue = PlaybackTransportControlGlue(requireContext(), playerAdapter)
        mTransportControlGlue.host = glueHost
        playerAdapter.setDataSource(Uri.parse(videoUrl))
        mTransportControlGlue.playWhenPrepared()

    }

    override fun onPause() {
        super.onPause()
        mTransportControlGlue.pause()
    }

}
