plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.mohnad.tareqstore"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mohnad.tareqstore"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)

    // ✅ استخدم نسخة activity المتوافقة فقط (اختار واحد)
    implementation(libs.activity.v193)

    // ✅ core-ktx من toml (اللي صار 1.14.0)
    implementation(libs.core.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

