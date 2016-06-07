import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Masatsuna on 2016/05/27.
 */
public class DataBase extends SQLiteOpenHelper {

    static final String CREATE_TABLE = "create table mytable ( _id integer primary key autoincrement, data integer not null );";
    static final String DROP_TABLE = "drop table mytable;";

    /* DataBase名称 */
    private static final String DB = "NIKKIBYBRIGHTSYSTEM_ITEM";

    //コンストラクタ
    public DataBase(Context context) {
        super(context, DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        }
}
