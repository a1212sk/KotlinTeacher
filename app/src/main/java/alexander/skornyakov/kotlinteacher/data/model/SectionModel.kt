package alexander.skornyakov.kotlinteacher.data.model

import android.graphics.Bitmap

data class SectionModel(val id: String, val header: String){

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(this.javaClass != other?.javaClass) return false
        other as SectionModel
        if(id==other.id) return true
        return false
    }

}