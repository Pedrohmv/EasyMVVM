# EasyMVVM

```gradle
    allprojects {
        repositories {
	    ...
	    maven { url 'https://jitpack.io' }
	}
    }
```

```gradle
    dependencies {
        implementation 'com.github.Pedrohmv:EasyMVVM:0.0.3-alpha'
    }
```

```kotlin
class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel //inject or create instance
	
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		
        easyObserve(viewModel.userInfoEvent ) { event ->
	    when (event) {
                LoadingEvent -> showLoadingUI()
		is ErrorEvent -> showError(event.error)
		is SuccessEvent -> showUserProfileData(event.value)
	    }
	}
        
        viewModel.loadUserInfo(currentUserId)
    }
}
```

```kotlin
class ProfileViewModel(
	private val userRepository: UserRepository,
	appCoroutineScope: AppCoroutineScope
) : EasyViewModel(appCoroutineScope) {

    private val userInfoEvent = MutableLiveData<Event>()
	
    fun loadUserInfo(userId: Int){
	launchOnIO({
	    userInfoEvent.updateValue(LoadingEvent)
	    val userInfo = userRepository.getUserInfo(userId)
	    userInfoEvent.updateValue(SuccesEvent(userInfo))
	},{ error ->
	    userInfoEvent.updateValue(ErrorEvent(error))
	})
    }
}
```
