package andrewkonst.invoka.impl

import andrewkonst.invoka.core.api.scope.AppScope
import andrewkonst.invoka.core.ui.externalrouter.ExternalRouter
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import java.io.File
import javax.inject.Inject


@AppScope
internal class ExternalRouterImpl @Inject constructor() : ExternalRouter {

    private val commandsBuffer = Channel<Command>(Channel.BUFFERED)

    val commands get() = commandsBuffer.receiveAsFlow()

    override fun openFile(file: File) {
        commandsBuffer.trySend(Command.OpenFile(file))
    }

    override fun openLink(link: String) {
        commandsBuffer.trySend(Command.OpenLink(link))
    }

    sealed interface Command {
        data class OpenFile(val file: File) : Command
        data class OpenLink(val link: String) : Command
    }

}
