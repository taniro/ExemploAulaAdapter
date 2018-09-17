package br.ufrn.eaj.tads.exemploaulaadapter.banco;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface FrutaDao {
    @Insert
    long inserir(Fruta fruta);

    @Insert
    long[] inserirAll(Fruta... fruta);

    @Delete
    int deletar(Fruta fruta);

    @Query("DELETE FROM Fruta")
    int deleteAll();

    @Update
    int atualizar(Fruta fruta);

    @Query("SELECT * FROM Fruta")
    Fruta[] listAll();

    @Query("SELECT * FROM Fruta WHERE id = :id")
    Fruta[] listById(long id);

    @Query("SELECT COUNT(id) FROM fruta")
    int getNumberOfRows();

}
