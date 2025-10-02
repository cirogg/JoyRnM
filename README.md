# JoyRnM

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-blue?logo=kotlin)](https://kotlinlang.org/)
[![Compose Multiplatform](https://img.shields.io/badge/Compose-Multiplatform-orange?logo=jetpackcompose)](https://www.jetbrains.com/lp/compose-multiplatform/)
[![Koin](https://img.shields.io/badge/DI-Koin-46a2f1?logo=kotlin)](https://insert-koin.io/)
[![SQLDelight](https://img.shields.io/badge/DB-SQLDelight-ff69b4)](https://cashapp.github.io/sqldelight/)
[![Ktor](https://img.shields.io/badge/Network-Ktor-0095D5?logo=ktor)](https://ktor.io/)

A **Kotlin Multiplatform** app built with **Compose Multiplatform**, **Koin** for DI, and **SQLDelight** for local persistence.  
Runs on **Android** and **iOS**, sharing the same codebase while keeping platform-specific integrations.

---

## 📱 Features
- Shared business logic in **commonMain**.
- **Compose Multiplatform UI** for building declarative UIs.
- **Koin** for dependency injection.
- **SQLDelight** with platform-specific drivers (Android & iOS).
- **Ktor** client for networking.
- Modularized clean architecture.

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|------------|
| **UI** | Jetpack Compose Multiplatform (Material3, Navigation) |
| **DI** | [Koin](https://insert-koin.io/) |
| **Persistence** | [SQLDelight](https://cashapp.github.io/sqldelight/) |
| **Networking** | [Ktor Client](https://ktor.io/) |

---

## ⚙️ Project Structure

```plaintext
composeApp/
 ├── androidMain/       # Android-specific code (Room, AndroidContext, etc.)
 ├── iosMain/           # iOS-specific code (Darwin driver, platform modules)
 ├── commonMain/        # Shared code: business logic, DI, models, repositories
 └── build.gradle.kts   # Multiplatform + dependencies configuration
```

---

## 🚀 Running the project

### Android
```bash
./gradlew :composeApp:installDebug
```

### iOS
Open the project in **Xcode**:
```bash
./gradlew :composeApp:syncFramework
```
Then run the **iosApp** target.

---

## 🧩 Dependency Injection (Koin)

**Common (shared)**
```kotlin
fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(appModules + platformModule)
    }
```

**Android**
```kotlin
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin { androidContext(this@App) }
    }
}
```

**iOS**
```swift
fun MainViewController() =
    ComposeUIViewController(configure = { initKoin() }) {
        App()
    }
```

---

## 📂 Database (SQLDelight)
- Android → `AndroidSqliteDriver`
- iOS → `NativeSqliteDriver`

Configured in platform-specific modules.

---
