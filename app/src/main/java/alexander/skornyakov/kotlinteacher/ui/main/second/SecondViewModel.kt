package alexander.skornyakov.kotlinteacher.ui.main.second

import alexander.skornyakov.kotlinteacher.data.model.StepModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SecondViewModel @Inject constructor() : ViewModel(){

    private val _chapter = MutableLiveData<Int>()
    val chapter : LiveData<Int>
        get() = _chapter

    fun setChapter(num: Int){
        if(_chapter.value==null){
            _chapter.value = num
        }
    }

    val message = "Second fragment"

    val items = MutableLiveData<MutableList<StepModel>>()

    init{
        items.value = mutableListOf()
    }


}