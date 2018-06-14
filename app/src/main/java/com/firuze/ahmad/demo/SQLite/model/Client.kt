package com.firuze.ahmad.demo.SQLite.model

/**
 * Created by antho.firuze@gmail.com on 2018-06-12.
 */

data class Client(val id: Int, val code: String?, val name: String?, val phone: String?,
                 val email: String?, val address: String?, val city: String?,
                 val province: String?, val postal_code: String?){
    companion object {
        val T_NAME = "client"
        val P_ID = "_id"
        var C_ID = "id"
        var C_CODE = "code"
        var C_NAME = "name"
        var C_PHONE = "phone"
        var C_EMAIL = "email"
        var C_ADDRESS = "address"
        var C_CITY = "city"
        var C_PROVINCE = "province"
        var C_POSTAL_CODE = "postal_code"
        val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $T_NAME (" +
                "$P_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$C_ID TEXT, $C_CODE TEXT, $C_NAME TEXT, $C_PHONE TEXT, $C_EMAIL TEXT, " +
                "$C_ADDRESS TEXT, $C_CITY TEXT, $C_PROVINCE TEXT, $C_POSTAL_CODE TEXT" +
                ")"
    }
}
