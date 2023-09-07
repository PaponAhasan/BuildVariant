plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.buildvariants"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.buildvariants"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
//            applicationIdSuffix = ".debug"
//            versionNameSuffix = ".debug"
            isDebuggable = true
            isMinifyEnabled = false
//            isRenderscriptDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            android.buildFeatures.buildConfig = true
            buildConfigField("String", "BASE_URL", "\"https://www.example_debug.com/\"")
        }

        getByName("release") {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://www.example_prod.com/\"")
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    flavorDimensions += listOf("version", "api")
    //flavorDimensions += listOf("api", "mode")
    productFlavors {
        create("free") {
            dimension = "version"
            applicationIdSuffix = ".free"
            versionNameSuffix = "-free"
//            buildConfigField("String", "BASE_URL", "\"https://www.example_free.com/\"")
        }
        create("pro") {
            dimension = "version"
            applicationIdSuffix = ".paid"
            versionNameSuffix = "-paid"
        }
        create("api31"){
            minSdk = 31
            dimension = "api"
            applicationIdSuffix = ".api31"
            versionNameSuffix = "-api31"
        }
    }

    sourceSets.getByName("free"){
        java.srcDirs(listOf("src/free/java"))
        res.srcDirs(listOf("src/free/res"))
//        manifest.srcFile()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}