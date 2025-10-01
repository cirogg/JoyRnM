import SwiftUI

@main
struct iOSApp: App {
    init() {
        // Inicializar Koin
        DiKt.initKoinIos()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}