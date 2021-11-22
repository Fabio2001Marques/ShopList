package pt.ipg.shoplist

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TestBaseDados {

    private fun getAppContext() = InstrumentationRegistry.getInstrumentation().targetContext
    private fun getBDShopListOpenHelper() = BDShopListOpenHelper(getAppContext())

    //-----------------------------------------------------------------------------------
    // Tabela ListaProdutos
    //-----------------------------------------------------------------------------------

    private fun getTabelaListaProdutos(db: SQLiteDatabase) = TabelaListaProdutos(db)

    private fun insertListaProdutos(tabelaListaProdutos: TabelaListaProdutos, lista: ListaProdutos): Long {
        val id = tabelaListaProdutos.insert(lista.toContentValues())
        assertNotEquals(-1, id)

        return id
    }

    private fun GetListaProdutosBd(tabelaListaProdutos: TabelaListaProdutos, id: Long): ListaProdutos {
        val cursor = tabelaListaProdutos.query(
            TabelaListaProdutos.TODOS_CAMPOS,
            "${BaseColumns._ID}=?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        assertNotNull(cursor)
        assert(cursor!!.moveToNext())

        return ListaProdutos.fromCursor(cursor)

    }

    private fun criarListaProdutos(nome: String, precoTotal: Float = 0.0f, data: String? = null): ListaProdutos{
        val lista = ListaProdutos(nome = nome,precoTotal = precoTotal,dataCompra = data)

        return lista
    }

    //-----------------------------------------------------------------------------------
    // Tabela Produtos
    //-----------------------------------------------------------------------------------

    private fun getTabelaProduto(db: SQLiteDatabase) = TabelaProduto(db)

    private fun insertProduto(tabelaProdutos: TabelaProduto, produto: Produto): Long {
        val id = tabelaProdutos.insert(produto.toContentValues())
        assertNotEquals(-1, id)

        return id
    }

    private fun GetProdutosBd(tabelaProdutos: TabelaProduto, id: Long): Produto {
        val cursor = tabelaProdutos.query(
            TabelaListaProdutos.TODOS_CAMPOS,
            "${BaseColumns._ID}=?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        assertNotNull(cursor)
        assert(cursor!!.moveToNext())

        return Produto.fromCursor(cursor)

    }


    @Before
    fun apagaBaseDados(){
        getAppContext().deleteDatabase(BDShopListOpenHelper.NOME_BASE_DADOS)
    }

    @Test
    fun consegueAbrirBaseDados(){

        val dbOpenHelper = BDShopListOpenHelper(getAppContext())
        val db = dbOpenHelper.readableDatabase
        assert(db.isOpen)
        db.close()

    }
    @Test

    fun consegueInserirListaProdutos(){

        val db = getBDShopListOpenHelper().writableDatabase

        val lista = ListaProdutos(nome = "Compras para 23/11/2021")
        lista.id = insertListaProdutos(getTabelaListaProdutos(db), lista)

        assertEquals(lista, GetListaProdutosBd(getTabelaListaProdutos(db), lista.id))

        db.close()

    }

    @Test

    fun consegueAlterarListaProdutos(){

        val db = getBDShopListOpenHelper().writableDatabase

        val lista = criarListaProdutos("teste")
        lista.id = insertListaProdutos(getTabelaListaProdutos(db), lista)
        lista.nome = "Compras"

        val registosAlterados = getTabelaListaProdutos(db).update(
            lista.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(lista.id.toString())
        )

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test

    fun consegueApagarListaProdutos() {

        val db = getBDShopListOpenHelper().writableDatabase

        val lista = criarListaProdutos("teste")
        lista.id = insertListaProdutos(getTabelaListaProdutos(db), lista)

        val registosApagados = getTabelaListaProdutos(db).delete("${BaseColumns._ID}=?",arrayOf(lista.id.toString()))
        assertEquals(1, registosApagados)

        db.close()
    }

    @Test

    fun consegueLerListaProdutos() {

        val db = getBDShopListOpenHelper().writableDatabase

        val lista = criarListaProdutos("teste")
        lista.id = insertListaProdutos(getTabelaListaProdutos(db), lista)

        val listaBd = GetListaProdutosBd(getTabelaListaProdutos(db), lista.id)
        assertEquals(lista, listaBd)

        db.close()
    }

}