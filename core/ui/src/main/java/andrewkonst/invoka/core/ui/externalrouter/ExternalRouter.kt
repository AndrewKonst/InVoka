package andrewkonst.invoka.core.ui.externalrouter

import java.io.File


interface ExternalRouter {

    fun openFile(file: File)

    fun openLink(link: String)

}