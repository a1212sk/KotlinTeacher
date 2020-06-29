package alexander.skornyakov.kotlinteacher.ui.main.second

import alexander.skornyakov.kotlinteacher.data.model.StepModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SecondViewModel @Inject constructor() : ViewModel(){

    private val _chapterId = MutableLiveData<String>()
    val chapterId : LiveData<String>
        get() = _chapterId
    fun setChapterId(id: String){
        if(_chapterId.value==null){
            _chapterId.value = id
        }
    }

    val message = "Second fragment"

    val items = MutableLiveData<MutableList<StepModel>>()

    init{
        items.value = mutableListOf()
    }


}