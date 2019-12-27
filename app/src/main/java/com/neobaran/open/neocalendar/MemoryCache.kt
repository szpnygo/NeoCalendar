package com.neobaran.open.neocalendar

class MemoryCache {

    companion object {
        private var instanceCache: MemoryCache? = null
            get() {
                if (field == null) {
                    field = MemoryCache()
                }
                return field
            }

        @Synchronized
        fun getInstance(): MemoryCache {
            return instanceCache!!
        }
    }

    private val cache = object : LinkedHashMap<Any, Any>(12, .75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Any, Any>?): Boolean {
            return size > 12
        }
    }

    val size: Int
        get() = cache.size

    fun set(key: Any, value: Any) {
        this.cache[key] = value
    }

    fun remove(key: Any) = this.cache.remove(key)

    fun get(key: Any) = this.cache[key]

    fun clear() = this.cache.clear()

}