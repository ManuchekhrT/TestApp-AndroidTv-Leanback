package tj.androidtvtestapp.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import tj.androidtvtestapp.ui.PlaybackVideoFragment

/** Loads [PlaybackVideoFragment]. */
class PlaybackActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(android.R.id.content,
                        PlaybackVideoFragment()
                    )
                    .commit()
        }
    }
}
