import SwiftUI

@main
struct iOSApp: App {
    init() {
        DiKt.initKoinIos()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}