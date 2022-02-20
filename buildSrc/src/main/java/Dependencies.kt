import org.gradle.api.JavaVersion

object Config {
    const val compileSdk = 31
    const val BUILD_TOOLS_VERSION = "30.0.2"
    const val APPLICATION_ID = "com.geekbrains.dictionary"
    const val MIN_SDK = 21
    const val TARGET_SDK = 31
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
    const val ANDROID_JUNIT_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    const val JVM_TARGET = "1.8"
    val JAVA_VERSION = JavaVersion.VERSION_1_8
}

object Modules{
    const val Models = ":models"
}

object Versions {
    const val retrofitVersions = "2.9.0"
    const val okHttpVersion = "4.9.2"
    const val rxJavaAdapterVersion = "1.0.0"
    const val ktx = "1.6.0"
    const val appCompat = "1.3.1"
    const val materialVersions = "1.4.0"
    const val constraintLayout = "2.1.1"
    const val swipeRefreshLayout = "1.1.0"
    const val coroutines = "1.5.1"
    const val koinVersions = "3.1.2"
    const val roomVersions = "2.3.0"
    const val glideVersions= "4.11.0"
    const val viewBindingDelegateVersions = "1.4.7"
    const val retrofitCoroutineVersion = "0.9.2"
}

object Retrofit {
    const val Retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersions}"
    const val ConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersions}"
    const val LoginInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"
    const val RetrofitCoroutine = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutineVersion}"
    const val RxJavaAdapter = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.rxJavaAdapterVersion}"
}

object Android {
    const val CoreKtx = "androidx.core:core-ktx:${Versions.ktx}"
    const val AppCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val Material = "com.google.android.material:material:${Versions.materialVersions}"
    const val ConstrainLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val SwipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
}

object Kotlin {
    const val CoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val CoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object Koin {
    const val Core = "io.insert-koin:koin-core:${Versions.koinVersions}"
    const val Android = "io.insert-koin:koin-android:${Versions.koinVersions}"
    const val AndroidCompat = "io.insert-koin:koin-android-compat:${Versions.koinVersions}"
}

object Room {
    const val Core = "androidx.room:room-runtime:${Versions.roomVersions}"
    const val Compiler = "androidx.room:room-compiler:${Versions.roomVersions}"
    const val Ktx = "androidx.room:room-ktx:${Versions.roomVersions}"
    const val SupportM1 = "org.xerial:sqlite-jdbc:3.34.0"
}

object Tests {
    const val JUnit = "junit:junit:4.13.2"
    const val AndroidJUnit = "androidx.test.ext:junit:1.1.3"
    const val Espresso = "androidx.test.espresso:espresso-core:3.4.0"
}

object Glide{
    const val Glide = "com.github.bumptech.glide:glide:${Versions.glideVersions}"
}

object ViewBindingPropertyDelegate{
    const val ViewBindingProperty=  "com.github.kirich1409:viewbindingpropertydelegate:${Versions.viewBindingDelegateVersions}"
}