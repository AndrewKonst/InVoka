package andrewkonst.invoka.ui.test

import andrewkonst.invoka.core.api.CoreDependencies
import com.arkivanov.decompose.ComponentContext
import dagger.Component
import dagger.assisted.AssistedFactory


@Component(dependencies = [CoreDependencies::class])
internal interface TestScreenBuilder : CoreDependencies {

    fun factory(): Factory

    @AssistedFactory
    interface Factory {
        fun create(componentContext: ComponentContext): TestScreenComponent
    }

    companion object {

        fun build(coreDependencies: CoreDependencies): TestScreenBuilder {
            return DaggerTestScreenBuilder.builder()
                .coreDependencies(coreDependencies)
                .build()
        }

    }

}

