import ExpoModulesCore
import _Concurrency
import MatterSupport

public class MatterApiModule: Module {
  // Each module class must implement the definition function. The definition consists of components
  // that describes the module's functionality and behavior.
    // See https://docs.expo.dev/modules/module-api for more details about @available(iOS 16.1, *)
  public func definition() -> ModuleDefinition {
    // Sets the name of the module that JavaScript code will use to refer to the module. Takes a string as an argument.
    // Can be inferred from module's class name, but it's recommended to set it explicitly for clarity.
    // The module will be accessible from `requireNativeModule('MatterApi')` in JavaScript.
    Name("MatterApi")

    View(MatterView.self) {}
  }
}


class MatterView: ExpoView {


  required init(appContext: AppContext? = nil) {
    super.init(appContext: appContext)
    clipsToBounds = true
    let button = UIButton(frame: CGRect(x:100, y:100, width:100, height: 50))
    button.backgroundColor = .green
    button.setTitle("Launch comissioning", for: .normal)
    button.addTarget(self, action: #selector(buttonAction), for: .touchUpInside)
    addSubview(button)

  }
  @objc func buttonAction(sender: UIButton!) {
      if #available(iOS 16.1, *) {
          let homes = [MatterAddDeviceRequest.Home(displayName: "My Home")]
          let topology = MatterAddDeviceRequest.Topology(ecosystemName: "MyEcosystemName", homes: homes)
          let request = MatterAddDeviceRequest(topology: topology)
          
          do {
              let sem = DispatchSemaphore(value: 0)
              let op = Task {
                  try await request.perform()
                  sem.signal()
              }
              sem.wait()
              print("Successfully set up device!")
         } catch {
             print("Failed to set up device with error: \(error)")
         }      } else {
          // Fallback on earlier versions
      }
    
    }
  // override func layoutSubviews() {
  //  webView.frame = bounds
  // }
}
