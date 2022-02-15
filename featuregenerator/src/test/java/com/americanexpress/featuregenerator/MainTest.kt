package com.americanexpress.featuregenerator

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.text
import com.americanexpress.featuregenerator.featuremodule.FeatureGenerator
import com.americanexpress.featuregenerator.featuremodule.Template
import com.americanexpress.featuregenerator.featuremodule.featureNamePlaceholders
import com.americanexpress.featuregenerator.featuremodule.screenNamePlaceholders
import com.americanexpress.featuregenerator.m1project.M1Project
import org.junit.After
import org.junit.Test
import java.io.File
import java.nio.file.Paths

class MainTest {

    private val rootPath = Paths.get("").toAbsolutePath()

    private val outputDir = "$rootPath/src/test/test-output/"
    private val generatedFeaturePath = "${outputDir}test-generator"
    private val sampleFeaturePath = "$rootPath/src/test/sample-feature/test-generator"

    private val screens = listOf("Home", "Edit Profile")

    private val singleWordFeatureNameInputList = listOf("test", "Test", "TEST", "tEST", "TEst")
    private val multipleWordFeatureNameInputList =
        listOf("Test Generator", "test generator", "TEST GENERATOR", "test-generator", "Test-Generator", "TEST-GENERATOR")

    private val singleWordScreenNameInputList = listOf("first", "First", "FIRst", "fIRsT")
    private val multipleWordScreenNameInputList =
        listOf("Second Input", "second input", "SECOND INPUT", "second-input", "Second-Input", "SECOND-INPUT")

    private val settingsGradle = File("$outputDir$SETTINGS_GRADLE_FILE_NAME")
    private val appGradle = File("$outputDir$APP_GRADLE_FILE_NAME")
    private val initializedAppDaggerBindings = File("$outputDir$DAGGER_BINDINGS_PATH")
    private val initializedComponent = File("$outputDir$INITIALIZED_COMPONENT_PATH")
    private val testInitializedComponent = File("$outputDir$TEST_INITIALIZED_COMPONENT_PATH")

    private val settingsGradleContent = settingsGradle.readText()
    private val appGradleContent = appGradle.readText()
    private val initializedAppDaggerBindingsContent = initializedAppDaggerBindings.readText()
    private val initializedComponentContent = initializedComponent.readText()
    private val testInitializedComponentContent = testInitializedComponent.readText()

    @Test
    fun `verify correct feature file names and content are generated`() {
        val template = Template(featureName = multipleWordFeatureNameInputList.first(), m1ProjectDir = rootPath.parent, m1FeatureDir = outputDir)
        val featureGenerator = FeatureGenerator(template = template, screenList = screens)

        featureGenerator.generate()

        val generatedFeatureFiles = File(generatedFeaturePath).walkTopDown().toMutableList()
            .apply { removeAll { ".DS_Store" in it.name } }
            .toList()

        val sampleFeatureFiles = File(sampleFeaturePath).walkTopDown().toList()
        assertThat(generatedFeatureFiles.map(File::getName).sorted())
            .containsExactly(*sampleFeatureFiles.map(File::getName).sorted().toTypedArray())

        assertThat(generatedFeatureFiles.filter(File::isFile).sorted().map(File::readText))
            .containsExactly(*sampleFeatureFiles.filter(File::isFile).sorted().map(File::readText).toTypedArray())
    }

    @Test
    fun `verify M1 files are updated with new feature`() {
        val template = Template(featureName = multipleWordFeatureNameInputList.first(), m1ProjectDir = Paths.get(outputDir))
        val m1Project = M1Project(template)

        m1Project.update(
            settingsGradlePath = SETTINGS_GRADLE_FILE_NAME,
            appGradlePath = APP_GRADLE_FILE_NAME,
            initializedAppDaggerBindingsPath = DAGGER_BINDINGS_PATH,
            initializedComponentPath = INITIALIZED_COMPONENT_PATH,
            testInitializedComponentPath = TEST_INITIALIZED_COMPONENT_PATH
        )

        // settings.gradle
        assertThat(settingsGradle).text().contains(
            "\":feature:test-generator\"",
            "\":feature:test-generator:test-generator-test-support\""
        )

        // app.gradle
        assertThat(appGradle).text().contains(
            "  implementation project(':feature:test-generator')",
            "  androidTestImplementation project(':feature:test-generator:test-generator-test-support')"
        )

        // InitializedAppDaggerBindings
        assertThat(initializedAppDaggerBindings).text().contains(
            "import com.americanexpress.android.testgenerator.TestGeneratorActivity",
            "    fun inject(activity: TestGeneratorActivity)",
            "    bind(TestGeneratorActivity::class) { bindings.inject(it) }"
        )

        // InitializedComponent
        assertThat(initializedComponent).text().contains(
            "import com.americanexpress.android.testgenerator.TestGeneratorModule",
            "        TestGeneratorModule::class"
        )

        // TestInitializedComponent
        assertThat(testInitializedComponent).text().contains(
            "import com.americanexpress.android.testgenerator.TestGeneratorModule",
            "        TestGeneratorModule::class"
        )
    }

    @Test
    fun `verify feature name input map for various multiple word inputs`() {
        multipleWordFeatureNameInputList.forEach { assertFeatureNameMapv1(Template(featureName = it)) }
    }

    @Test
    fun `verify feature name input map for various single word inputs`() {
        singleWordFeatureNameInputList.forEach { assertFeatureNameMapv2(Template(featureName = it)) }
    }

    @Test
    fun `verify screen name input map for multiple word inputs`() {
        multipleWordScreenNameInputList.forEach { assertScreenNameMapv1(Template(featureName = "Feature Name"), it) }
    }

    @Test
    fun `verify screen name input map for single word inputs`() {
        singleWordScreenNameInputList.forEach { assertScreenNameMapv2(Template(featureName = "Feature Name"), it) }
    }

    private fun assertFeatureNameMapv1(template: Template) {
        val featureNameMap = template.getFeatureNameMap()
        featureNamePlaceholders.forEach {
            val featureNameValue = featureNameMap[it]

            when (it) {
                "\${FeatureName}" -> assertThat(featureNameValue).isEqualTo("TestGenerator")
                "\${featureName}" -> assertThat(featureNameValue).isEqualTo("testGenerator")
                "\${featurename}" -> assertThat(featureNameValue).isEqualTo("testgenerator")
                "\${feature-name}" -> assertThat(featureNameValue).isEqualTo("test-generator")
            }
        }
    }

    private fun assertFeatureNameMapv2(template: Template) {
        val featureNameMap = template.getFeatureNameMap()
        featureNamePlaceholders.forEach {
            val featureNameValue = featureNameMap[it]

            when (it) {
                "\${FeatureName}" -> assertThat(featureNameValue).isEqualTo("Test")
                "\${featureName}" -> assertThat(featureNameValue).isEqualTo("test")
                "\${featurename}" -> assertThat(featureNameValue).isEqualTo("test")
                "\${feature-name}" -> assertThat(featureNameValue).isEqualTo("test")
            }
        }
    }

    private fun assertScreenNameMapv1(template: Template, screenNameInput: String) {
        val screenNameMap = template.getScreenNameMap(screenNameInput)
        screenNamePlaceholders.forEach {
            val screenNameValue = screenNameMap[it]

            when (it) {
                "\${ScreenName}" -> assertThat(screenNameValue).isEqualTo("SecondInput")
                "\${screen_name}" -> assertThat(screenNameValue).isEqualTo("second_input")
            }
        }
    }

    private fun assertScreenNameMapv2(template: Template, screenNameInput: String) {
        val screenNameMap = template.getScreenNameMap(screenNameInput)
        screenNamePlaceholders.forEach {
            val screenNameValue = screenNameMap[it]

            when (it) {
                "\${ScreenName}" -> assertThat(screenNameValue).isEqualTo("First")
                "\${screen_name}" -> assertThat(screenNameValue).isEqualTo("first")
            }
        }
    }

    @After
    fun tearDown() {
        File(generatedFeaturePath).deleteRecursively()

        settingsGradle.writeText(settingsGradleContent)
        appGradle.writeText(appGradleContent)
        initializedAppDaggerBindings.writeText(initializedAppDaggerBindingsContent)
        initializedComponent.writeText(initializedComponentContent)
        testInitializedComponent.writeText(testInitializedComponentContent)
    }

    companion object {
        const val SETTINGS_GRADLE_FILE_NAME = "dummy-settings.gradle.kts"
        const val APP_GRADLE_FILE_NAME = "dummy-app.gradle"
        const val DAGGER_BINDINGS_PATH = "DummyInitializedAppDaggerBindings.kt"
        const val INITIALIZED_COMPONENT_PATH = "DummyInitializedComponent.kt"
        const val TEST_INITIALIZED_COMPONENT_PATH = "DummyTestInitializedComponent.kt"
    }
}
