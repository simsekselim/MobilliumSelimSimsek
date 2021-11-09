/**
 * Core Libraries
 */
object CoreLibraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
}

/**
 * Support Libraries
 */
object ArcComponentsLibs {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.xVersion}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.xVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentVersion}"
    const val viewModelExtensions = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelExtension}"
    const val lifecycleExtension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
    const val daggerHilViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.daggerHilViewModelVersion}"
}

/**
 * Test Libraries
 */
object TestLibraries {
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val runner = "androidx.test:runner:${Versions.testRunnerVersion}"
    const val androidTestImplementation = "androidx.test.ext:junit:${Versions.testImplementationVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
}

/**
 * Common Libraries
 */
object Libraries {
    const val javaxAnnotation = "org.glassfish:javax.annotation:${Versions.javaxAnnotationVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val logInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLoggingInterceptorVersion}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.gsonVersion}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    const val multiDex = "androidx.multidex:multidex:${Versions.multidexVersion}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideKapt = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    const val circularImageView = "com.mikhaellopez:circularimageview:${Versions.circularImageViewVersion}"
    const val rxAndroid =  "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
    const val rxJava2 =  "io.reactivex.rxjava2:rxjava:${Versions.rxJava2Version}"
    const val retrofitRxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitRxJavaAdapterVersion}"
    const val materialDesign = "com.google.android.material:material:${Versions.supportDesignVersion}"
    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHiltVersion}"
    const val daggerAnnotations = "com.google.dagger:hilt-android-compiler:${Versions.daggerHiltVersion}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"
}