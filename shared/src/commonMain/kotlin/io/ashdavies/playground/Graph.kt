package io.ashdavies.playground

val <T> T.graph: Graph<T>
    get() = InstanceGraph(this)

interface Graph<T> {
    val seed: T
}

inline fun <T : Any, R> graph(seed: T, block: Graph<T>.() -> R): R {
    return seed.graph(block)
}

private data class InstanceGraph<T>(
    override val seed: T,
) : Graph<T>

inline operator fun <T, R> Graph<T>.invoke(
    block: Graph<T>.() -> R,
): R = run(block)
