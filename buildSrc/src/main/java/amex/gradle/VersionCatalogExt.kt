/**
 * Source: https://github-dev.aexp.com/amex-eng/jvm-paved-road-template/blob/main/buildSrc/src/main/kotlin/aexp/VersionCatalogExt.kt
 */
package amex.gradle

import org.gradle.api.InvalidUserDataException
import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionConstraint
import org.gradle.api.provider.Provider
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import java.util.Optional


private fun exceptionTemplate(type: String, name: String) =
    "Couldn't resolve $type $name is it defined in the version catalog? (toml file)"

/**
 * Wraps VersionCatalog#findBundle to give a clean error message in the event of a failure
 */
fun VersionCatalog.resolveBundle(bundle: String): Provider<ExternalModuleDependencyBundle> =
    resolveCatalogItem(VersionCatalog::findBundle, "bundle", bundle)

/**
 * Wraps VersionCatalog#findDependency to give a clean error message in the event of a failure
 */
fun VersionCatalog.resolveDependency(dependency: String): Provider<MinimalExternalModuleDependency> =
    resolveCatalogItem(VersionCatalog::findDependency, "dependency", dependency)

/**
 * Wraps VersionCatalog#findVersion to give a clean error message in the event of a failure
 */
fun VersionCatalog.resolveVersion(version: String): VersionConstraint =
    resolveCatalogItem(VersionCatalog::findVersion, "version", version)

private fun <T> VersionCatalog.resolveCatalogItem(
    finder: VersionCatalog.(String) -> Optional<T>,
    type: String,
    identifier: String
): T {
    return finder(identifier)
        .orElseThrow { InvalidUserDataException(exceptionTemplate(type, identifier)) }
}

val Project.libs: VersionCatalog
    get() = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")
