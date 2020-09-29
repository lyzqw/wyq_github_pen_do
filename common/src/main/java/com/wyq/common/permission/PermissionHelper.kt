package com.wyq.common.permission

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

object PermissionHelper {

    private val pendingRequestManagerFragments = HashMap<FragmentManager, PermissionFragment>()
    private val handler = Handler(Looper.getMainLooper())

    suspend fun requestPermissions(
        activity: AppCompatActivity,
        permissions: List<String>
    ): PermissionResult {
        return suspendCancellableCoroutine { coroutine ->

            val supportFragmentManager = activity.supportFragmentManager
            var fragment =
                supportFragmentManager.findFragmentByTag("PermissionFragmentTag") as? PermissionFragment

            if (fragment == null) {
                fragment = pendingRequestManagerFragments[supportFragmentManager]
                if (fragment == null) {
                    fragment = PermissionFragment.newInstance()
                    fragment.setRequestPermissions(permissions)
                    fragment.requestPermissionCallBack = {
                        if (coroutine.isActive) {
                            coroutine.resume(it)
                        }
                    }
                    pendingRequestManagerFragments[supportFragmentManager] = fragment
                    supportFragmentManager.beginTransaction()
                        .add(fragment, "PermissionFragmentTag")
                        .commitAllowingStateLoss()
                    handler.obtainMessage().sendToTarget()
                }
            }


        }

    }


}


class PermissionResult {

    private val hashMap = HashMap<String?, Boolean>()

    fun isGranted(): Boolean {
        return !hashMap.values.contains(false)
    }

    fun put(key: String?, value: Boolean) {
        hashMap[key] = value
    }

}


class PermissionFragment : Fragment() {

    companion object {
        fun newInstance() = PermissionFragment()
    }

    private val requestPermissionsCode = 12
    var requestPermissionCallBack: ((PermissionResult) -> Unit)? = null
    var permissions: List<String>? = null

    fun setRequestPermissions(permissions: List<String>) {
        this.permissions = permissions
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        permissions?.let { requestPermissions(it.toTypedArray(), requestPermissionsCode) }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode != requestPermissionsCode) return

        val permissionResult = PermissionResult()

        grantResults.forEachIndexed { index, i ->
            permissionResult.put(permissions.getOrNull(index), i == PERMISSION_GRANTED)
        }
        requestPermissionCallBack?.invoke(permissionResult)
    }
}