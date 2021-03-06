package io.ashdavies.playground.events

import io.ashdavies.playground.CoroutineTest
import io.ashdavies.playground.database.Event
import io.ashdavies.playground.github.GitHubRepository
import kotlin.test.Test
import kotlin.test.assertEquals

internal class EventsFetcherTest : CoroutineTest() {

    @Test
    fun shouldParseEventName() = runBlockingTest {
        val event: Event = fetch(DroidconBerlinEntry)

        assertEquals(event.name, "Droidcon")
    }

    @Test
    fun shouldParseWithYamlComment() = runBlockingTest {
        val event: Event = fetch(AppDevConEntry)

        assertEquals(event.cfpStart, "2016-11-26")
    }

    @Test
    fun shouldParseOptionalCfpSite() = runBlockingTest {
        val event: Event = fetch(AnDevConEntry)

        assertEquals(event.cfpSite, event.website)
    }

    private suspend fun fetch(entry: GitHubRepository.Entry): Event {
        return EventsFetcher(GitHubService(entry))(Unit)
            .getOrThrow()
            .first()
    }
}

private val DroidconBerlinEntry = EventEntry(
    oid = "9361834b3e310ffd8992c1020eb868ebb56c564a",
    text = """
        ---
        name: "Droidcon"
        website: https://berlin.droidcon.com/
        location: Berlin, Germany
        
        date_start: 2021-10-20
        date_end:   2021-10-22
        
        cfp:
          start:  2020-01-06
          end:    2021-08-31
          site:   https://sessionize.com/droidcon-berlin-2021/
        ---
""".trimIndent()
)

private val AppDevConEntry = EventEntry(
    oid = "f225a030810250f2f6cb8d6f97ade9ae9c9b405f",
    text = """
        ---
        name: "AppDevCon"
        website: http://www.appdevcon.nl/
        location: Amsterdam, NL

        date_start: 2017-03-16
        date_end:   2017-03-17

        cfp:
          start: 2016-11-26  # Optional
          end:   2016-12-23  # Optional
          site: https://docs.google.com/forms/d/e/1FAIpQLSc_Jks4rXUhtY1FZG6dOdMXamVwSosgHgqgMxKVg7l8kIYdNQ/viewform
        ---
    """.trimIndent()
)

private val AnDevConEntry = EventEntry(
    oid = "3d63bc9d12803c859d35deb27083cce7c38dcb58",
    text = """
        ---
        name: "AnDevCon"
        website: http://www.andevcon.com/santaclara
        location: Santa Clara, USA

        date_start: 2015-12-01
        date_end:   2015-12-03

        cfp:
          start:  2015-07-21
          end:    2015-08-10
        ---
    """.trimIndent()
)

private data class EventEntry(
    override val oid: String,
    override val text: String,
) : GitHubRepository.Entry