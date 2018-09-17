package br.ufrn.eaj.tads.exemploaulaadapter.banco;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Fruta.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FrutaDao frutaDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "fruta_database.sqlite")
                            .allowMainThreadQueries()
                            .build();

                }
            }
        }

        //deleta todas
        INSTANCE.frutaDao().deleteAll();

        Fruta f1 = new Fruta("Maça", "Vermelha");
        Fruta f2 = new Fruta("Uva", "Verde");
        Fruta f3 = new Fruta("Goiaba", "Verde");
        Fruta f4 = new Fruta("Graviola", "Verde");
        Fruta f5 = new Fruta("Jambo", "Roxa");
        Fruta f6 = new Fruta("Melão", "Laranja");

        //cadastra novamente
        INSTANCE.frutaDao().inserirAll(new Fruta[]{f1, f2, f3, f4, f5, f6});

        return INSTANCE;
    }
}
