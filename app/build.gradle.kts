plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.watersystem"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.watersystem"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Navigation依赖项
    // Java基础依赖
    implementation("androidx.navigation:navigation-fragment:2.5.3")
    implementation("androidx.navigation:navigation-ui:2.5.3")
    // Kotlin基础依赖
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    // 功能模块支持
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.5.3")
    // 测试功能支持
    androidTestImplementation("androidx.navigation:navigation-testing:2.5.3")
    // Jetpack Compose 集成
    implementation("androidx.navigation:navigation-compose:2.5.3")

    // 高德地图依赖项
    // 搜索功能
    implementation("com.amap.api:search:latest.integration")
    // 导航+地图+定位功能
    implementation("com.amap.api:navi-3dmap:latest.integration")

    // Google推荐的EasyPermission库
    implementation("pub.devrel:easypermissions:3.0.0")
    // Material库
    implementation("com.google.android.material:material:1.6.1")
    // RecyclerView库
    implementation("io.github.cymchad:BaseRecyclerViewAdapterHelper:3.0.14")

    // 视频播放器
    implementation("com.github.NodeMedia:NodeMediaClient-Android:2.9.7")
    // 数据流拉取
    implementation("com.github.pedroSG94.rtmp-rtsp-stream-client-java:rtplibrary:2.1.9")
    // 自定义摇杆
    implementation("com.github.kongqw:AndroidRocker:1.0.1")




}


