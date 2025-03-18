1. Architecture: MVVM pattern is used for this implementation
2. UX view: Jetpack Compose(LazyColumn) is used 
3. Network: Retrofit is used for the network connection. (In order that the network task status is noticed to users, a stateflow, an enum class, a LaunchedEffect and Toasts are used.)
4. Thread: Coroutine is used for the network Asynchronus task.
5. Reactive Databinding: StateFlow and Flow are used.
6. Dependency Injection: Koin is used.
7. Unit test: Mockk is used.
