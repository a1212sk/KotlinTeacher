package alexander.skornyakov.kotlinteacher.data

interface Storage {
    fun getString(key: String):String
    fun putString(key: String, value: String)
}