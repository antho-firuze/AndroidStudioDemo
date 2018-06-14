package com.firuze.ahmad.demo.SQLite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.firuze.ahmad.demo.MainApplication
import com.firuze.ahmad.demo.SQLite.model.Client
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

/**
 * Created by antho.firuze@gmail.com on 2018-06-12.
 */

class DBHelper_SQLite(var ctx: Context = MainApplication.instance) : ManagedSQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {
    companion object {
        @JvmField
        val DB_NAME = "mydb"
        @JvmField
        val DB_VERSION = 1

        val instance by lazy { DBHelper_SQLite() }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(Client.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }
}

val Context.database: DBHelper_SQLite
    get() = DBHelper_SQLite.instance