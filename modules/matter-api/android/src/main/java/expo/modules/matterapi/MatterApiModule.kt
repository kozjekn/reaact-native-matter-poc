package expo.modules.matterapi

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.google.android.gms.home.matter.Matter
import com.google.android.gms.home.matter.commissioning.CommissioningRequest
import expo.modules.kotlin.AppContext
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import expo.modules.kotlin.views.ExpoView
import timber.log.Timber


class MatterApiModule : Module() {
  override fun definition() = ModuleDefinition {
    Name("MatterApi")

    View(MatterView::class) {}
  }
}


class MatterView(context: Context, appContext: AppContext) : ExpoView(context, appContext) {
    @RequiresApi(Build.VERSION_CODES.O_MR1)
    private fun commissionDevice() {
        val request: CommissioningRequest = CommissioningRequest.builder().build()
        Matter.getCommissioningClient(appContext.currentActivity!!)
            .commissionDevice(request)
            .addOnSuccessListener { result ->
                val mToast = Toast.makeText(context,"success", Toast.LENGTH_SHORT)
                mToast.show()
                appContext.currentActivity!!.startIntentSenderForResult(result, 1000, null, 0,0,0)
            }
            .addOnFailureListener { error ->
                val mToast = Toast.makeText(context,"fail", Toast.LENGTH_SHORT)
                mToast.show()
            }
    }

    @RequiresApi(Build.VERSION_CODES.O_MR1)
    internal val webView = LinearLayout(context).also { it ->
        it.addView(Button(context).also { but ->
            but.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            but.text = "launch commissioning"
            but.setOnClickListener() {
                commissionDevice()
             }
        })
        addView(it)
    }
}
