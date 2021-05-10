import ProjectDependencies.AndroidX
import ProjectDependencies.Dropbox
import ProjectDependencies.Google
import ProjectDependencies.JetBrains
import ProjectDependencies.Ktor
import ProjectDependencies.Square

plugins {
    `android-application`
    `kotlin-android`
    sqldelight
}

android {
    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    composeOptions {
        kotlinCompilerExtensionVersion = AndroidX.Compose.version
        kotlinCompilerVersion = JetBrains.Kotlin.version
    }

    defaultConfig {
        applicationId = "io.ashdavies.playground"
        compileSdk = 30
        minSdk = 23

        versionCode = 1
        versionName = "1.0"
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    sourceSets.configureEach {
        java.srcDirs("src/$name/kotlin")
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(AndroidX.activityKtx)
    implementation(AndroidX.annotation)
    implementation(AndroidX.Compose.foundation)
    implementation(AndroidX.Compose.material)
    implementation(AndroidX.Compose.runtime)
    implementation(AndroidX.Compose.ui)
    implementation(AndroidX.Compose.uiTooling)
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.fragmentKtx)
    implementation(AndroidX.Lifecycle.lifecycleLivedataKtx)
    implementation(AndroidX.Lifecycle.lifecycleViewmodelKtx)
    implementation(AndroidX.pagingRuntime)
    implementation(AndroidX.Navigation.navigationCompose)
    implementation(AndroidX.Navigation.navigationRuntimeKtx)
    implementation(AndroidX.Navigation.navigationUiKtx)
    implementation(Dropbox.store4)
    implementation(Google.Firebase.firebaseCommonKtx)
    implementation(Google.Firebase.firebaseAnalytics)
    implementation(Google.Android.material)
    implementation(JetBrains.KotlinX.kotlinxCoroutinesAndroid)
    implementation(JetBrains.KotlinX.kotlinxCoroutinesCore)
    implementation(JetBrains.KotlinX.kotlinxDatetime)
    implementation(Ktor.ktorClientCore)
    implementation(Square.SqlDelight.androidDriver)
    implementation(Square.SqlDelight.coroutinesExtensions)
    implementation(Square.SqlDelight.runtime)

    testImplementation(JetBrains.Kotlin.kotlinTest)
    testImplementation(JetBrains.Kotlin.kotlinTestJunit)
    testImplementation(JetBrains.KotlinX.kotlinxCoroutinesTest)
    testImplementation(Square.SqlDelight.sqliteDriver)
}
