package alexander.skornyakov.kotlinteacher.ui.main.first

import alexander.skornyakov.kotlinteacher.data.model.MainFirstModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class FirstViewModel @Inject constructor() : ViewModel(){

    val message = "First fragment"

    val items = MutableLiveData<MutableList<MainFirstModel>>()

    init{
        items.value = mutableListOf()
    }

}