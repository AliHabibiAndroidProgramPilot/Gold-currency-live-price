package info.alihabibi.goldcurrencyprice.mvp.ext

interface ActivityLifecycle {
    fun presenterOnCreate()
    fun presenterOnStart() {}
    fun presenterOnResume() {}
    fun presenterOnPause() {}
    fun presenterOnStop() {}
    fun presenterOnRestart() {}
    fun presenterOnDestroy()
}