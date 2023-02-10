package andrewkonst.invoka.core.api.json

import kotlinx.serialization.json.Json


interface JsonProvider {

    val json: Json

}