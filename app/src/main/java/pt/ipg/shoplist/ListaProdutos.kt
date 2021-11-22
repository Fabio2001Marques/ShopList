package pt.ipg.shoplist

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns


data class ListaProdutos (var id: Long = -1, var nome: String, var precoTotal: Float? = null, var dataCompra: String? = null){

    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaListaProdutos.CAMPO_NOME, nome)
        valores.put(TabelaListaProdutos.PRECO_TOTAL, precoTotal)
        valores.put(TabelaListaProdutos.DATA_COMPRA, dataCompra)
        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor) : ListaProdutos{

            val posCampoId = cursor.getColumnIndex(BaseColumns._ID)
            val posCampoNome = cursor.getColumnIndex(TabelaListaProdutos.CAMPO_NOME)
            val posCampoPrecoTotal = cursor.getColumnIndex(TabelaListaProdutos.PRECO_TOTAL)
            val posCampoDataCompra = cursor.getColumnIndex(TabelaListaProdutos.DATA_COMPRA)

            val id =cursor.getLong(posCampoId)
            val nome = cursor.getString(posCampoNome)
            val precoTotal = cursor.getFloat(posCampoPrecoTotal)
            val dataCompra = cursor.getString(posCampoDataCompra)

            return ListaProdutos(id,nome,precoTotal,dataCompra)
        }
    }
}