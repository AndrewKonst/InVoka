package andrewkonst.invoka.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


interface Screen {

    @Composable
    fun Content(modifier: Modifier)

}
