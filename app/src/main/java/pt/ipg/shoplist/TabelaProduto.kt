package pt.ipg.shoplist

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaProduto(db: SQLiteDatabase) {
    private val db: SQLiteDatabase = db

    fun cria() {
        db.execSQL("CREATE TABLE $NOME_Tabela (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $NOME TEXT NOT NULL, $QUANTIDADE INTEGER NOT NULL, $PRECO_UNI REAL NOT NULL, $ID_LISTA INTEGER NOT NULL, Foreign KEY ($ID_LISTA) REFERENCES ${TabelaListaProdutos.NOME_Tabela})")

    }


    // CRUD

    fun insert(values: ContentValues): Long {
        return db.insert(NOME_Tabela, null, values)
    }

    fun update(values: ContentValues, whereClause: String, whereArgs: Array<String>): Int {
        return db.update(NOME_Tabela, values, whereClause, whereArgs)
    }

    fun delete(whereClause: String, whereArgs: Array<String>): Int {
        return db.delete(NOME_Tabela, whereClause, whereArgs)
    }

    fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor? {
        return db.query(TabelaListaProdutos.NOME_Tabela, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    companion object{
        const val NOME_Tabela = "Produto"
        const val NOME = "nome"
        const val QUANTIDADE = "quantidade"
        const val PRECO_UNI = "preco_uni"
        const val ID_LISTA = "ID_lista"
        const val CAMPO_EXTERNO_NOME_LISTA = "nome_lista"
        val TODOS_CAMPOS = arrayOf(BaseColumns._ID, NOME, QUANTIDADE, PRECO_UNI, ID_LISTA, CAMPO_EXTERNO_NOME_LISTA)
    }
}
