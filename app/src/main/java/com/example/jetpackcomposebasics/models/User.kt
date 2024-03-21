package com.example.jetpackcomposebasics.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class User : RealmObject {
    @PrimaryKey
    var _id : ObjectId = ObjectId()
    var name : String = ""
    var password : String = ""
}