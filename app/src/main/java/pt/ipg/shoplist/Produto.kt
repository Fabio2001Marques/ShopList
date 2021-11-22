package pt.ipg.shoplist

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns


data class Produto (var id: Long = -1, var nome: String, var quantidade: Float, var precoUni: Float, var id_lista: Long, var nomeLista: String? = null){

    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaProduto.NOME, nome)
        valores.put(TabelaProduto.QUANTIDADE, quantidade)
        valores.put(TabelaProduto.PRECO_UNI, precoUni)
        valores.put(TabelaProduto.ID_LISTA, id_lista)
        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor) : Produto{

            val posCampoId = cursor.getColumnIndex(BaseColumns._ID)
            val posCampoNome = cursor.getColumnIndex(TabelaProduto.NOME)
            val posCampoQuantidade = cursor.getColumnIndex(TabelaProduto.QUANTIDADE)
            val posCampoPrecoUNI = cursor.getColumnIndex(TabelaProduto.PRECO_UNI)
            val posCampoIdLista = cursor.getColumnIndex(TabelaProduto.ID_LISTA)
            val colNomeLista = cursor.getColumnIndex(TabelaProduto.CAMPO_EXTERNO_NOME_LISTA)

            val id =cursor.getLong(posCampoId)
            val nome = cursor.getString(posCampoNome)
            val quantidade = cursor.getFloat(posCampoQuantidade)
            val precoUni = cursor.getFloat(posCampoPrecoUNI)
            val id_lista = cursor.getLong(posCampoIdLista)
            val nomeLista = if (colNomeLista != -1) cursor.getString(colNomeLista) else null

            return Produto(id,nome,quantidade, precoUni, id_lista, nomeLista)
        }
    }
}