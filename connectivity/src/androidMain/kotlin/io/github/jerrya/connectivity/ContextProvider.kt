package io.github.jerrya.connectivity

import android.annotation.SuppressLint
import android.content.Context

class ContextProvider(val context: Context) {

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var instance: ContextProvider? = null

        /**
         * Create a new instance of [ContextProvider] with the given [context].
         *
         * @param context The application context.
         * @return The [ContextProvider] instance.
         */
        fun create(context: Context): ContextProvider {
            if (instance == null) {
                instance = ContextProvider(context)
            }

            return instance!!
        }

        /**
         * Get the [ContextProvider] instance.
         *
         * @return The [ContextProvider] instance.
         */
        fun getInstance(): ContextProvider = instance
            ?: throw IllegalStateException("ContextProvider has not been initialized")
    }
}