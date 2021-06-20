package io.ashdavies.playground.profile

import io.ashdavies.playground.Graph
import io.ashdavies.playground.network.httpClient

internal val Graph<*>.profileService: ProfileService
    get() = ProfileService(httpClient)