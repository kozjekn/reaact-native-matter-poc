package expo.modules.matterapi

import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.app.Service
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.common.moduleinstall.ModuleInstall
import com.google.android.gms.home.matter.commissioning.CommissioningCompleteMetadata
import com.google.android.gms.home.matter.commissioning.CommissioningRequestMetadata
import com.google.android.gms.home.matter.commissioning.CommissioningService
import com.google.android.gms.tflite.java.TfLite
import expo.modules.kotlin.exception.Exceptions
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import timber.log.Timber

class MatterApiModule : Module() {
  // Each module class must implement the definition function. The definition consists of components
  // that describes the module's functionality and behavior.
  // See https://docs.expo.dev/modules/module-api for more details about available components.
  override fun definition() = ModuleDefinition {
    // Sets the name of the module that JavaScript code will use to refer to the module. Takes a string as an argument.
    // Can be inferred from module's class name, but it's recommended to set it explicitly for clarity.
    // The module will be accessible from `requireNativeModule('MatterApi')` in JavaScript.
    Name("MatterApi")

   Function("getAvailable") {
       val moduleInstallClient = ModuleInstall.getClient(appContext.reactContext!!)
       val optionalModuleApi = TfLite.getClient(appContext.reactContext!!)
       val resultCall = moduleInstallClient
           .areModulesAvailable(optionalModuleApi)

       while(!resultCall.isComplete){
            Thread.sleep(100)
       }

      return@Function "system-android Avl: " + resultCall.result.areModulesAvailable()
    }
  }
}

