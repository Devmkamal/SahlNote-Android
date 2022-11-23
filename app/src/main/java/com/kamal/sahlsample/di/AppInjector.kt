package com.kamal.sahlsample.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.kamal.sahlsample.App
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection


object AppInjector {
    fun init(application: App) {
        DaggerAppComponent.builder().application(application).build().inject(application)
        application
            .registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    handleActivity(activity)
                    //App.currentActivity = activity
                }

                override fun onActivityStarted(activity: Activity) {
                    //App.currentActivity = activity
                }

                override fun onActivityResumed(activity: Activity) {
                    //App.currentActivity = activity
                }

                override fun onActivityPaused(activity: Activity) {
                    // onActivityPaused

                }

                override fun onActivityStopped(activity: Activity) {
                    // onActivityStopped

                }


                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                    // onActivitySaveInstanceState

                }

                override fun onActivityDestroyed(activity: Activity) {
                    // onActivityDestroyed
                }
            })
    }

    private fun handleActivity(activity: Activity) {
        if (activity is Injectable) {
            AndroidInjection.inject(activity)
            if (activity is FragmentActivity) {
                activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(
                        object : FragmentManager.FragmentLifecycleCallbacks() {
                            override fun onFragmentCreated(
                                fm: FragmentManager,
                                f: Fragment,
                                savedInstanceState: Bundle?
                            ) {
                                if (f is Injectable) {
                                    AndroidSupportInjection.inject(f)
                                }
                            }
                        }, true
                    )
            }
        }
    }
}