// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.version.catalog.update)
    alias(libs.plugins.versions.update)
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.dagger.hilt) apply false
}
true // Needed to make the Suppress annotation work for the plugins block