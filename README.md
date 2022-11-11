# Tuned Global - Code Exam

## Summary
* Used clean-code architecture pattern following modern MVVM(Model-View-ViewModel)
* Heavily inspired by ![Now-in-android](https://github.com/android/nowinandroid), Google's official android app sample featuring modern architecture and best practices. 
* Setup with multiple modules
    * `:app`
      - The main module where Android App screens, application, fragments, resources, and all other modules were integrated.
      - Ideally, screens could be segregated into multiple sub-modules(i.e. feature modules `:feature:trending`, `:feature:album-details`, etc.)
    * `:core:model`
      - Consists of datamodels used across multiple modules in the project.
    * `:core:data`
      - Contains business logic codes that bridges multiple datasources(i,e. local and remote) and acts as the mediator of each source.
    * `:core:network`
      - Consist of codes dealing with remote datasources such as REST API(ex. using Retrofit)
    * `:core:domain`
      - Contains UseCase implementations and may contain additional domain models(i.e. Result classes, etc.) which may be used to address more-specific business use-cases.
    * `:core:common`
      - Contains utility classes and/or functions that can be used in any modules.
      
* Dependency Injection using Koin 2.0 (https://insert-koin.io/)
* App Project was constructed following Single Activity App pattern.
    * Using 1 Activity to host NavigationView which manages the entire Navigation Graph.
* Unit Test implementation targeting each *ViewModel classes using ![cashapp/Turbine](https://github.com/cashapp/turbine) test framework.
   - Please see `TrendingAlbumListViewModelTest.kt` and `AlbumDetailsViewModelTest.kt` for unit test implementation.
   - (TODO: Provide unit test implementation on data layer and network layer for full coverage)

## Navigation Graph
![Navigation Graph](https://user-images.githubusercontent.com/6966201/201339649-a8bbf92e-31b0-440b-b557-0e7e903b24c7.png)

### Trending Albums
#### Success
![Trending Albums - Success](https://user-images.githubusercontent.com/6966201/201339759-985db9c1-440a-42a8-9fc8-f1963080da67.png)

#### Empty
![Trending Albums - Empty](https://user-images.githubusercontent.com/6966201/201340086-715578a9-4ce2-4d01-869a-9c842b89aad3.png)

### Album Details
#### Success
![Album Details - Success - Expanded](https://user-images.githubusercontent.com/6966201/201340235-7fa4c3ef-d7b0-4a02-a5e5-e0a5c115b533.png)
![Album Details - Success - Collapsed](https://user-images.githubusercontent.com/6966201/201340300-78193405-8b3f-470a-8079-9204d19585e3.png)

#### Error
![Album Details - Error](https://user-images.githubusercontent.com/6966201/201340375-146d3979-5344-46d7-acaf-424abd81cc68.png)


