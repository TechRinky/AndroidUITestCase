import androidx.lifecycle.ViewModel

/**
 * This class is responsible to communicate between the View and the data
 */
class DataViewModel : ViewModel() {
    private var dataRepo = DataRepo()
    var liveData = dataRepo.filteredLiveData
    var loadingState = dataRepo.loadingState

    fun getDataFromAsset() {
        dataRepo.getDataFromAsset()
    }

}
