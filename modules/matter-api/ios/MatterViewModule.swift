import ExpoModulesCore

class MatterViewModule : Module {
    public func definition() -> ModuleDefinition {
        Attributes.Name("MatterView")

        View(MatterViewModule.self) {}
    }
}