package me.deyinejiddou.skeleton

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import timber.log.Timber
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import com.crashlytics.android.core.CrashlyticsCore
import android.support.multidex.MultiDex
import me.deyinejiddou.skeleton.ui.utils.log.CrashReportingTree
import me.deyinejiddou.skeleton.ui.utils.prefs.Prefs


val prefs: Prefs by lazy {
    SkeletonApplication.prefs!!
}

/**
 * Created by djiddou on 11/23/17.
 */
class SkeletonApplication : MultiDexApplication() {

    companion object {
        var prefs: Prefs? = null
    }

    override fun onCreate() {
        configurePrefs()
        configureLogs()
        configureFont()
        super.onCreate()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun configureFont() {
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Lato-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }

    /**
     * Configure logs
     */
    private fun configureLogs() {

        Timber.plant(
                if (BuildConfig.DEBUG) Timber.DebugTree()
                else CrashReportingTree())

        val core = CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()
        Fabric.with(this, Crashlytics.Builder().core(core).build())
    }


    private fun configurePrefs(){
        prefs = Prefs(applicationContext)
    }
}