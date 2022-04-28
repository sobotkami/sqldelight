package app.cash.sqldelight.tests

import app.cash.sqldelight.withCommonConfiguration
import com.google.common.truth.Truth
import org.gradle.testkit.runner.GradleRunner
import org.junit.Test
import java.io.File

class MigrationSquashTest {
  @Test fun `verification enabled fails when database file is missing`() {
    val output = GradleRunner.create()
      .withCommonConfiguration(File("src/test/missing-database-file-verification-enabled"))
      .withArguments("clean", "verifyMainDatabaseMigration", "--stacktrace")
      .buildAndFail()

    Truth.assertThat(output.output).contains(
      """
      |Verifying a migration requires a database file to be present. To generate one, use the generate Gradle task.
      |""".trimMargin()
    )
  }
}